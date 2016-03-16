package com.vgalloy.server.service.manager.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential.Builder;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.vgalloy.server.service.exception.CredentialException;
import com.vgalloy.server.service.manager.CredentialManager;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 26/01/16.
 */
@Service
public class CredentialManagerImpl implements CredentialManager {

    private static final String REDIRECT_URI = "https://mon-url-impossible.com/";
    private static final List<String> SCOPES = Arrays.asList("https://spreadsheets.google.com/feeds");

    private Credential credential;
    private GoogleClientSecrets clientSecrets;
    private JacksonFactory jsonFactory;

    /**
     * Constructor.
     *
     * @throws IOException IOException
     */
    public CredentialManagerImpl() throws IOException {
        jsonFactory = new JacksonFactory();
        File f = new File("client_secrets.json");
        if (f.exists()) {
            clientSecrets = GoogleClientSecrets.load(jsonFactory, new FileReader(f));
        } else {
            clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(Drive.class.getResourceAsStream("/client_secrets.json")));
        }
    }

    @Override
    public Credential getCredential() {
        return credential;
    }

    @Override
    public synchronized void setToken(String token) {
        HttpTransport transport = new NetHttpTransport();
        GoogleTokenResponse response;
        try {
            response = new GoogleAuthorizationCodeTokenRequest(transport, jsonFactory, clientSecrets.getDetails().getClientId(), clientSecrets.getDetails().getClientSecret(), token, REDIRECT_URI).execute();
        } catch (IOException e) {
            throw new CredentialException("Impossible de recevoir le token de Google ou le code est erroné", e);
        }
        // Build a new GoogleCredential instance and return it.
        credential = new Builder()
                .setClientSecrets(clientSecrets.getDetails().getClientId(), clientSecrets.getDetails().getClientSecret())
                .setJsonFactory(jsonFactory)
                .setTransport(transport)
                .build()
                .setAccessToken(response.getAccessToken())
                .setRefreshToken(response.getRefreshToken());
    }

    @Override
    public String generateGoogleTokenUrl() {
        return new GoogleAuthorizationCodeRequestUrl(clientSecrets.getDetails().getClientId(), REDIRECT_URI, SCOPES).build();
    }
}
