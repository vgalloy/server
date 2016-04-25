package com.vgalloy.server.service.manager.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.api.client.auth.oauth2.Credential;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.IFeed;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.ServiceException;

import com.vgalloy.server.model.entity.Calendar;
import com.vgalloy.server.model.entity.Month;
import com.vgalloy.server.service.exception.GoogleServiceException;
import com.vgalloy.server.service.exception.NoCredentialException;
import com.vgalloy.server.service.manager.CredentialManager;
import com.vgalloy.server.service.manager.GoogleManager;
import com.vgalloy.server.service.manager.mapper.MonthMapper;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 25/01/16.
 */
@Service
public class GoogleManagerImpl implements GoogleManager {

    @Value("${google.sheetKey}")
    private String sheetKey;
    @Autowired
    private CredentialManager credentialManager;

    @Override
    public Calendar getCalendar() throws NoCredentialException {
        Credential credential = credentialManager.getCredential();
        if (credential == null) {
            throw new NoCredentialException();
        }
        List<Month> months = getMonth(credential);
        return new Calendar(months);
    }

    /**
     * Extract the month list.
     *
     * @param credential The google credential
     * @return The month list
     */
    private List<Month> getMonth(Credential credential) {
        SpreadsheetService service = new SpreadsheetService("Application-name");
        service.setOAuth2Credentials(credential);

        SpreadsheetEntry spreadsheet = getSpreadsheetEntry(service);
        List<CellFeed> cellFeedsList = getCellFeeds(spreadsheet, service);

        return cellFeedsList.stream()
                .map(MonthMapper::mapRow)
                .collect(Collectors.toList());
    }

    /**
     * Get the List of Cell Feeds (ie a list of table).
     *
     * @param spreadsheet The spreadSheet
     * @param service     The google service
     * @return The list of cell feed
     */
    private List<CellFeed> getCellFeeds(SpreadsheetEntry spreadsheet, SpreadsheetService service) {
        WorksheetFeed worksheetFeed = getGoogleFeed(service, spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
        return worksheetFeed.getEntries()
                .stream()
                .map(e -> getGoogleFeed(service, e.getCellFeedUrl(), CellFeed.class))
                .collect(Collectors.toList());
    }

    /**
     * Get the Google Feed and wrap Exceptions.
     *
     * @param service The Google Service
     * @param url     The url to access the feed
     * @param clazz   The class of the feed
     * @param <T>     The abstract parameter
     * @return The feed
     */
    private <T extends IFeed> T getGoogleFeed(SpreadsheetService service, URL url, Class<T> clazz) {
        try {
            return service.getFeed(url, clazz);
        } catch (ServiceException | IOException e) {
            throw new GoogleServiceException(e);
        }
    }

    /**
     * Get the correct SpreadsheetFeed.
     *
     * @param service The google service
     * @return The spreadSheet
     */
    private SpreadsheetEntry getSpreadsheetEntry(SpreadsheetService service) {
        // Url Creation
        URL spreadsheetFeedUrl;
        try {
            spreadsheetFeedUrl = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
        } catch (MalformedURLException e) {
            throw new GoogleServiceException(e);
        }

        // Obtain Spreadsheet
        SpreadsheetFeed spreadsheetFeed = getGoogleFeed(service, spreadsheetFeedUrl, SpreadsheetFeed.class);

        // Get the correct spreadSheet
        List<SpreadsheetEntry> spreadsheets = spreadsheetFeed.getEntries();
        return spreadsheets.stream()
                .filter(e -> sheetKey.equals(e.getKey()))
                .findFirst()
                .get();
    }
}
