package com.jmrp.calendar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.ref.WeakReference;


/**
 * The type Directory.
 */
final public class CustomCalendar {
    private static CustomCalendar mInstance;

    private final String TAG = getClass().getSimpleName();
    private CalendarColourResources mColourRes;
    private CalendarDrawResources mDrawableRes;
    private CalendarStringResources mStringRes;

    private WeakReference<Context> mContext;
    private CalendarBaseInterface mBaseInterface;
    private CalendarInterface mCalendarInterface;
    private boolean mConfigured = false;


    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public static CustomCalendar getInstance(Context context) {
        if (mInstance == null)
            mInstance = new CustomCalendar();

        mInstance.mContext = new WeakReference<>(context);

        return mInstance;
    }

    /**
     * Directory Constructor
     */
    public CustomCalendar() {
        mBaseInterface = new CalendarBaseInterface() {
            @Override
            public CalendarBaseInterface configureCalendar(CalendarBaseInterface calendarBaseInterface) {
                if (calendarBaseInterface instanceof CalendarInterface) {
                    mCalendarInterface = (CalendarInterface) calendarBaseInterface;
                    mColourRes = mCalendarInterface.getColours();
                    mDrawableRes = mCalendarInterface.getDrawables();
                    mStringRes = mCalendarInterface.getStrings();

                    mConfigured = true;
                }

                return this;
            }

            @Override
            public void launchCalendar() {
                if (mConfigured) {
                    try {
                        init();
                    } catch (CalendarInitException e) {
                        Log.e(TAG, "launchCalendar: Bad configured,cannot init");
                        e.printStackTrace();
                    }
                }
            }
        };
    }


    /**
     * Gets base ifz.
     *
     * @return the base ifz
     */
    public CalendarBaseInterface getBaseIfz() {
        return mInstance.mBaseInterface;
    }


    /**
     * Init Calendar. If params are not correct it throws an exception
     *
     * @throws CalendarInitException Exception thrown if there is a failure in initialization
     */
    private void init() throws CalendarInitException {

        boolean failed = false;
        String errorMsg = "";

        //Checking drawables
        if (mInstance.mDrawableRes.getShowMoreArrow() == null) {
            failed = true;
            errorMsg = errorMsg.concat("ShowMoreArrow, ");
        }

        //Checking Colours
        if (mInstance.mColourRes.getColorPrimary() == null) {
            failed = true;
            errorMsg = errorMsg.concat("ColorPrimary, ");
        }

        if (mInstance.mColourRes.getColorPrimaryDark() == null) {
            failed = true;
            errorMsg = errorMsg.concat("ColorPrimaryDark, ");
        }

        //Checking Strings
        if (mInstance.mStringRes.getKeyame() == null) {
            failed = true;
            errorMsg = errorMsg.concat("AppIdentifier, ");
        }

        if (!errorMsg.isEmpty() && " ".equals(String.valueOf(errorMsg.charAt(errorMsg.length() - 2)))) {
            errorMsg = errorMsg.substring(0, errorMsg.length() - 2);
            Log.e(TAG, "Initialization Error: Message: " + errorMsg);
        }

        //Arrancamos Directorio Activity
        Intent intent = new Intent(mInstance.mContext.get(), CalendarActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mInstance.mContext.get().startActivity(intent);
    }

    /**
     * Gets colour resources.
     *
     * @return the colour resources
     */
    public CalendarColourResources getColourRes() {
        return mColourRes;
    }

    /**
     * Gets drawable res.
     *
     * @return the drawable resource
     */
    public CalendarDrawResources getDrawableRes() {
        return mDrawableRes;
    }

    /**
     * Gets string resource.
     *
     * @return the string resources
     */
    public CalendarStringResources getStringRes() {
        return mStringRes;
    }
}

