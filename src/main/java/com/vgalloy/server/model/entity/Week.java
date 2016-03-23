package com.vgalloy.server.model.entity;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 25/01/16.
 */
public class Week {

    private final List<Day> dayList;

    /**
     * Default constructor.
     */
    public Week() {
        dayList = new ArrayList<>();
        for (int i = 0; i < DayOfWeek.values().length; i++) {
            dayList.add(new Day());
        }
    }

    public List<Day> getDayList() {
        return dayList;
    }

    /**
     * Get one specify day.
     *
     * @param day The day of the week
     * @return The day.
     */
    public Day getDay(DayOfWeek day) {
        return getDay(day.getValue());
    }

    /**
     * Get one specify day with its position in the week.
     *
     * @param dayOfWeek The position of the day in the week [1, 7]
     * @return The day
     */
    public Day getDay(int dayOfWeek) {
        return dayList.get(dayOfWeek - 1);
    }

    @Override
    public String toString() {
        return "Week{" +
                "dayList=" + dayList +
                '}';
    }
}
