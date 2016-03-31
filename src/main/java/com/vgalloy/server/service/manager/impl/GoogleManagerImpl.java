package com.vgalloy.server.service.manager.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.google.gdata.util.ServiceException;
import com.vgalloy.server.service.manager.mapper.MonthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.api.client.auth.oauth2.Credential;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;

import com.vgalloy.server.model.entity.Calendar;
import com.vgalloy.server.model.entity.Month;
import com.vgalloy.server.service.exception.GoogleServiceException;
import com.vgalloy.server.service.exception.NoCredentialException;
import com.vgalloy.server.service.manager.CredentialManager;
import com.vgalloy.server.service.manager.GoogleManager;

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
        List<Month> months;
        months = getMonth(credential);
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

        WorksheetFeed worksheetFeed = service.getFeed(spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
        List<WorksheetEntry> worksheets = worksheetFeed.getEntries();

        List<Month> months = new ArrayList<>();
        for (WorksheetEntry worksheet : worksheets) {
            // Fetch the cell feed of the worksheet.
            URL cellFeedUrl = worksheet.getCellFeedUrl();
            CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
            Month month = MonthMapper.mapRow(cellFeed);
            months.add(month);
        }

        return months;
    }

    /**
     * Get SpreadsheetFeed.
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
        SpreadsheetFeed spreadsheetFeed;
        try {
            spreadsheetFeed = service.getFeed(spreadsheetFeedUrl, SpreadsheetFeed.class);
        } catch (ServiceException | IOException e) {
            throw new GoogleServiceException(e);
        }

        // Get the correct spreadSheet
        List<SpreadsheetEntry> spreadsheets = spreadsheetFeed.getEntries();
        SpreadsheetEntry spreadsheet = spreadsheets.get(0);
        for (SpreadsheetEntry e : spreadsheets) {
            if (sheetKey.equals(e.getKey())) {
                spreadsheet = e;
                break;
            }
        }

        return spreadsheet;
    }
}
