package com.vgalloy.server.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.vgalloy.server.model.MonthsOfTheYear;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 25/01/16.
 */
public class Calendar {

    private final List<Month> months;

    /**
     * Constructor with the list of month.
     *
     * @param months The month list
     */
    public Calendar(List<Month> months) {
        this.months = months;
    }

    /**
     * Constructor.
     */
    public Calendar() {
        months = new ArrayList<>();
        for (int i = 0; i < MonthsOfTheYear.values().length; i++) {
            months.add(new Month());
        }
    }

    public List<Month> getMonths() {
        return months;
    }

    /**
     * Get a specific month of the year.
     *
     * @param monthsOfTheYear The month of the year
     * @return The month of the year
     */
    public Month getMonth(MonthsOfTheYear monthsOfTheYear) {
        return months.get(monthsOfTheYear.getValue());
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "months=" + months +
                '}';
    }
}
