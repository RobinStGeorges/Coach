package com.example.kireta.coach.outils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kIRETA on 30/12/2016.
 */

public abstract class MesOutils {
    public static Date convertStringToDate(String unedate) {
        Log.d("avant string2date","******************"+unedate);
        //String expectedPattern = "EEE MMM dd hh:mm:ss 'UTC' yyyy";
        String expectedPattern = "hh:mm:ss";
        //String expectedPattern = "dd/mm/yy hh:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try {
            Date date = formatter.parse(unedate);
            Log.d("apres string2date","*****************"+date);
            return date;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return date.format(uneDate);
    }
}
