package com.vgalloy.server.model;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 25/01/16.
 */
public enum MonthsOfTheYear {
    GOLD("Or"),
    RED("Rouge"),
    COPPER("Cuivre"),
    BRONZE("Bronze"),
    BRAZEN("Airain"),
    WHITE("Blanc"),
    SILVER("Argent"),
    BLUE("Blue"),
    BLACK("Noir"),
    GREEN("Vert");

    private String name;
    private int value;

    /**
     * Le constructeur du mois.
     *
     * @param name Le nom en Français du mois
     */
    MonthsOfTheYear(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return ordinal() + 1;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
