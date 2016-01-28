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
     * Contructeur.
     */
    public Week() {
        this.dayList = new ArrayList<>();
        for (int i = 0; i < DayOfWeek.values().length; i++) {
            dayList.add(new Day());
        }
    }

    public List<Day> getDayList() {
        return dayList;
    }

    /**
     * Permet l'otention d'un jour.
     *
     * @param day Le jour sous forme de date (API time)
     * @return Le jour demander
     */
    public Day getDay(DayOfWeek day) {
        return getDay(day.getValue());
    }

    /**
     * Permet l'otention d'un jour.
     *
     * @param dayOfWeek Le numero du jour dans la semaine. Lundi = 1
     * @return Le jour demander
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
