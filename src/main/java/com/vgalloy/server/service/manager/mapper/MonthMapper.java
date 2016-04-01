package com.vgalloy.server.service.manager.mapper;

import com.google.gdata.data.spreadsheet.CellFeed;

import com.vgalloy.server.model.entity.Month;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 31/03/16.
 */
public final class MonthMapper {

    /**
     * Private Constructor.
     */
    private MonthMapper() {

    }

    /**
     * Convert cellFeed into Month.
     *
     * @param cellFeed THe cell
     * @return The month
     */
    public static Month mapRow(CellFeed cellFeed) {
        Month month = new Month();
        cellFeed.getEntries().stream()
                .filter(cell -> cell.getCell().getRow() > 1)
                .forEach(cell -> month.getWeek(cell.getCell().getRow() - 2)
                        .getDay(cell.getCell().getCol())
                        .getEvent()
                        .setAction(cell.getCell().getValue()));
        return month;
    }
}
