package com.vgalloy.server.service.manager.impl;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.vgalloy.server.service.manager.CredentialManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 26/01/16.
 */
@Service
public class CredentialManagerImpl implements CredentialManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialManagerImpl.class);
    private static final String REDIRECT_URI = "https://mon-url-impossible.com/";
    private static final List<String> SCOPES = Arrays.asList("https://spreadsheets.google.com/feeds");

    private Credential credential;
    private GoogleClientSecrets clientSecrets;
    private JacksonFactory jsonFactory;

    /**
     * Contructeur.
     *
     * @throws IOException IOException
     */
    public CredentialManagerImpl() throws IOException {
        jsonFactory = new JacksonFactory();
        clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(Drive.class.getResourceAsStream("/client_secrets.json")));
    }

    @Override
    public Credential getCredential() {
        return credential;
    }

    @Override
    public synchronized boolean setToken(String token) {
        HttpTransport transport = new NetHttpTransport();
        GoogleTokenResponse response;
        try {
            response = new GoogleAuthorizationCodeTokenRequest(transport, jsonFactory, clientSecrets.getDetails().getClientId(), clientSecrets.getDetails().getClientSecret(), token, REDIRECT_URI).execute();
        } catch (IOException e) {
            LOGGER.warn("Impossible de recevoir le token de Google ou le code est erroné", e);
            return false;
        }
        // Build a new GoogleCredential instance and return it.
        credential = new GoogleCredential.Builder()
                .setClientSecrets(clientSecrets.getDetails().getClientId(), clientSecrets.getDetails().getClientSecret())
                .setJsonFactory(jsonFactory)
                .setTransport(transport)
                .build()
                .setAccessToken(response.getAccessToken())
                .setRefreshToken(response.getRefreshToken());
        return true;

    }

    /**
     * Genère l'url qui va permettre le "user consent".
     */
    @Override
    public String generateUrl() {
        return new GoogleAuthorizationCodeRequestUrl(clientSecrets.getDetails().getClientId(), REDIRECT_URI, SCOPES).build();
    }
}
