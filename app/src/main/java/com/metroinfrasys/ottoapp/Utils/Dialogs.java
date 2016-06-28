package com.metroinfrasys.ottoapp.Utils;

import android.app.DatePickerDialog;
import android.content.Context;

/**
 * Created by Skbhati on 27-06-2016.
 */
public class Dialogs extends DatePickerDialog {


    private final Context context;
    private final int theme;
    private final OnDateSetListener listener;
    private final OnDateSetListener callBack;
    private final int year;
    private final int monthOfYear;
    private final int dayOfMonth;

    public Dialogs(Context context, int theme, OnDateSetListener listener, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
        this.context = context;
        this.theme = theme;
        this.listener = listener;
        this.callBack = callBack;
        this.year = year;
        this.monthOfYear = monthOfYear;
        this.dayOfMonth = dayOfMonth;
    }

}
