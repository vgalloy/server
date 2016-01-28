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
     * Constructeur.
     */
    public Month() {
        this.weekList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.weekList.add(new Week());
        }
    }

    public List<Week> getWeekList() {
        return weekList;
    }

    /**
     * Permet d'obtenir une semaine du mois.
     *
     * @param weekNumber Le numero de la semaine
     * @return La semaine
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
