package com.alexandrepiveteau.library.tutorial.utils;

import android.content.Context;

/**
 * Created by Alexandre on 15.07.2015.
 */
public class ConversionUtils {

    private float density;

    public ConversionUtils(Context context) {
        density = context.getResources().getDisplayMetrics().density;
    }

    public int getPixelsFromDp(float dp) {
        return (int) (density * dp);
    }
}
