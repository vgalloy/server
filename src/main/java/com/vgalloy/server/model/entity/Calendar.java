package com.vgalloy.server.model.entity;

import com.vgalloy.server.model.MonthsOfTheYear;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 25/01/16.
 */
public class Calendar {
    private List<Month> months;

    /**
     * Constructeur avec la liste des mois.
     *
     * @param months la liste de mois
     */
    public Calendar(List<Month> months) {
        this.months = months;
    }

    /**
     * Constructeur.
     */
    public Calendar() {
        this.months = new ArrayList<>();
        for (int i = 0; i < MonthsOfTheYear.values().length; i++) {
            this.months.add(new Month());
        }
    }

    public List<Month> getMonths() {
        return months;
    }

    /**
     * Obtention d'un mois de l'anné.
     *
     * @param monthsOfTheYear Le mois de l'année
     * @return Le mois demandé
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
