package com.jmrp.calendar;

/**
 * The interface Calendar interface.
 */
public interface CalendarInterface extends CalendarBaseInterface {
    /**
     * Gets colours.
     *
     * @return the colour resources object
     */
    CalendarColourResources getColours();

    /**
     * Gets drawables.
     *
     * @return the drawables resources object
     */
    CalendarDrawResources getDrawables();

    /**
     * Gets strings.
     *
     * @return the strings resources object
     */
    CalendarStringResources getStrings();
}
