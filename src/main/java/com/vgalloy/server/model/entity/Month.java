package com.vgalloy.server.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 25/01/16.
 */
public class Month {

    private final List<Week> weekList;

    /**
     * Constructor.
     */
    public Month() {
        weekList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            weekList.add(new Week());
        }
    }

    public List<Week> getWeekList() {
        return weekList;
    }

    /**
     * Get the week of the month.
     *
     * @param weekNumber The number of the week
     * @return The week with the given number
     */
    public Week getWeek(int weekNumber) {
        return weekList.get(weekNumber);
    }

    @Override
    public String toString() {
        return "Month{" +
                "weekList=" + weekList +
                '}';
    }
}
