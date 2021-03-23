package com.jmrp.calendar;

/**
 * The type Calendar init exception.
 */
public class CalendarInitException extends Exception {

    /**
     * Instantiates a new Calendar init exception.
     *
     * @param message the message of the exception
     */
    public CalendarInitException(String message) {
        super("Calendar component inicialization exception: " + message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
