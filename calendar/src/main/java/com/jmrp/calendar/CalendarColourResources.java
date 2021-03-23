package com.jmrp.calendar;

/**
 * Colour resources required for Calendar instance
 */
public class CalendarColourResources {

    private final Integer mColorPrimary, mColorPrimaryDark;

    /**
     * Instantiates a new Colour resources.
     *
     * @param colorPrimary       the color primary
     * @param colorPrimaryDark   the color primary dark
     */
    public CalendarColourResources(Integer colorPrimary, Integer colorPrimaryDark) {
        this.mColorPrimary = colorPrimary;
        this.mColorPrimaryDark = colorPrimaryDark;
    }

    /**
     * Gets color primary.
     *
     * @return the color primary
     */
    public Integer getColorPrimary() {
        return this.mColorPrimary;
    }

    /**
     * Gets color primary dark.
     *
     * @return the color primary dark
     */
    public Integer getColorPrimaryDark() {
        return mColorPrimaryDark;
    }
}
