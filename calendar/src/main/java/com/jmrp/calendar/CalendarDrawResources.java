package com.jmrp.calendar;

/**
 * Drawable resources required for Calendar instance
 */
final public class CalendarDrawResources {

    private final Integer mShowMoreArrow;

    /**
     * Instantiates a new Drawable resources.
     *
     * @param showMoreArrow  the show More drawable resource
     */
    public CalendarDrawResources(Integer showMoreArrow) {
        this.mShowMoreArrow = showMoreArrow;
    }

    /**
     * Gets show more arrow drawable.
     *
     * @return the show more drawable
     */
    public Integer getShowMoreArrow() {
        return mShowMoreArrow;
    }
}
