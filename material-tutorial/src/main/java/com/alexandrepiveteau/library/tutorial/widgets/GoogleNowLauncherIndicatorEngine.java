package com.alexandrepiveteau.library.tutorial.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.alexandrepiveteau.library.tutorial.utils.ConversionUtils;
import com.alexandrepiveteau.library.tutorial.utils.MathUtils;

/**
 * Created by Tanmay Parikh on 7/19/2015.
 */
public class GoogleNowLauncherIndicatorEngine extends PageIndicator.Engine {

    private static final int SELECTED_RADIUS = 5;
    private static final int UNSELECTED_RADIUS = 3;
    private static final int DIFFERENCE = SELECTED_RADIUS - UNSELECTED_RADIUS;

    private ConversionUtils mConversionUtils;
    private PageIndicator mPageIndicator;

    private Paint mDotPaint;

    private int mDotColor;

    public GoogleNowLauncherIndicatorEngine() {
        this(Color.WHITE);
    }

    public GoogleNowLauncherIndicatorEngine(int dotColor) {
        mDotColor = dotColor;
    }

    @Override
    public void onInitEngine(PageIndicator indicator, Context context) {
        mConversionUtils = new ConversionUtils(context);
        mPageIndicator = indicator;

        mDotPaint = new Paint();
        mDotPaint.setColor(mDotColor);
        mDotPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public int getMeasuredHeight(int widthMeasuredSpec, int heightMeasuredSpec) {
        return mConversionUtils.getPixelsFromDp(SELECTED_RADIUS * 2);
    }

    @Override
    public int getMeasuredWidth(int widthMeasuredSpec, int heightMeasuredSpec) {
        return mConversionUtils.getPixelsFromDp(SELECTED_RADIUS * 2 * (mPageIndicator.getTotalPages() * 2 - 1));
    }

    @Override
    public void onDrawIndicator(Canvas canvas) {
        float offset = MathUtils.round(mPageIndicator.getPositionOffset() * DIFFERENCE, 1);

        //Draw unselected page indicators
        for (int i = 0; i < mPageIndicator.getTotalPages(); i++) {
            int radius;
            if (i == mPageIndicator.getActualPosition()) {
                radius = mConversionUtils.getPixelsFromDp(SELECTED_RADIUS - offset);
            } else if (i == mPageIndicator.getActualPosition() + 1) {
                radius = mConversionUtils.getPixelsFromDp(UNSELECTED_RADIUS + offset);
            } else {
                radius = mConversionUtils.getPixelsFromDp(UNSELECTED_RADIUS);
            }

            int cx = mConversionUtils.getPixelsFromDp(SELECTED_RADIUS) + mConversionUtils.getPixelsFromDp(SELECTED_RADIUS * 4 * i);
            int cy = mPageIndicator.getHeight() / 2;

            canvas.drawCircle(cx, cy, radius, mDotPaint);
        }
    }
}
