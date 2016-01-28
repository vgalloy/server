package com.vgalloy.server.service.manager.impl;

import com.google.api.client.auth.oauth2.Credential;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.ServiceException;
import com.vgalloy.server.model.entity.Calendar;
import com.vgalloy.server.model.entity.Month;
import com.vgalloy.server.service.manager.CredentialManager;
import com.vgalloy.server.service.manager.GoogleManager;
import com.vgalloy.server.service.exception.GoogleServiceException;
import com.vgalloy.server.service.exception.NoCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
            throw new NoCredentialException("Aucun credential n'est defini");
        }
        List<Month> months;
        try {
            months = getMonth(credential);
        } catch (Exception e) {
            throw new GoogleServiceException("Impossible d'obtenir la liste de mois", e);
        }
        return new Calendar(months);
    }

    /**
     * Permet de lire la google sheet et d'en extraire une liste de mois.
     *
     * @param credential Les credentials permettant d'acceder à l'api google
     * @return La liste de moi
     * @throws IOException      IOException
     * @throws ServiceException ServiceException de Google
     */
    private List<Month> getMonth(Credential credential) throws IOException, ServiceException {
        SpreadsheetService service = new SpreadsheetService("Application-name");
        service.setOAuth2Credentials(credential);
        URL spreadsheetFeedUrl = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
        SpreadsheetFeed feed = service.getFeed(spreadsheetFeedUrl, SpreadsheetFeed.class);
        List<SpreadsheetEntry> spreadsheets = feed.getEntries();
        SpreadsheetEntry spreadsheet = spreadsheets.get(0);
        for (SpreadsheetEntry e : spreadsheets) {
            if (sheetKey.equals(e.getKey())) {
                spreadsheet = e;
            }
        }

        WorksheetFeed worksheetFeed = service.getFeed(spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
        List<WorksheetEntry> worksheets = worksheetFeed.getEntries();

        List<Month> months = new ArrayList<>();

        for (WorksheetEntry worksheet : worksheets) {
            Month month = new Month();


            // Fetch the cell feed of the worksheet.
            URL cellFeedUrl = worksheet.getCellFeedUrl();
            CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);

            // Iterate through each cell, printing its value.
            cellFeed.getEntries().stream()
                    .filter(cell -> cell.getCell().getRow() > 1)
                    .forEach(cell -> month.getWeek(cell.getCell().getRow() - 2)
                            .getDay(cell.getCell().getCol())
                            .getEvent()
                            .setAction(cell.getCell().getValue()));
            months.add(month);
        }
        return months;
    }
}
