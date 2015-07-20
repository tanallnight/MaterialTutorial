package com.alexandrepiveteau.library.tutorial.utils;

import java.math.BigDecimal;

/**
 * Created by Tanmay Parikh on 7/19/2015.
 */
public class MathUtils {

    public static float round(float d, int decimalPlace) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(d));
        bigDecimal = bigDecimal.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.floatValue();
    }

}
