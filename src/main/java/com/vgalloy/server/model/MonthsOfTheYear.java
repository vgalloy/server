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

    private final String name;

    /**
     * Constructor.
     *
     * @param name The French name of the month.
     */
    MonthsOfTheYear(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return ordinal() + 1;
    }
}
