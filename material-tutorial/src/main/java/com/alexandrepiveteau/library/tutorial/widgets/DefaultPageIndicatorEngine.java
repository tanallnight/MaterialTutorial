package com.alexandrepiveteau.library.tutorial.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.alexandrepiveteau.library.tutorial.R;
import com.alexandrepiveteau.library.tutorial.utils.ConversionUtils;

/**
 * Created by Alexandre on 19.07.2015.
 */
public class DefaultPageIndicatorEngine extends PageIndicator.Engine {

    private ConversionUtils mConversionUtils;

    private PageIndicator mPageIndicator;

    private Paint mSelectedPaint;
    private Paint mUnselectedPaint;

    @Override
    public int getMeasuredHeight(int widthMeasuredSpec, int heightMeasuredSpec) {
        return mConversionUtils.getPixelsFromDp(8);
    }

    @Override
    public int getMeasuredWidth(int widthMeasuredSpec, int heightMeasuredSpec) {
        return mConversionUtils.getPixelsFromDp(8 * (mPageIndicator.getTotalPages() * 2 - 1));
    }

    @Override
    public void onInitEngine(PageIndicator indicator, Context context) {

        mPageIndicator = indicator;

        mSelectedPaint = new Paint();
        mUnselectedPaint = new Paint();

        int selectedColor = context.getResources().getColor(R.color.dots_default_selected);
        int unselectedColor = context.getResources().getColor(R.color.dots_default_unselected);

        mSelectedPaint.setColor(selectedColor);
        mUnselectedPaint.setColor(unselectedColor);
        mSelectedPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mUnselectedPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        mConversionUtils = new ConversionUtils(context);
    }

    @Override
    public void onDrawIndicator(Canvas canvas) {
        int height = mPageIndicator.getHeight();

        //We draw the unselected page indicators
        for (int i = 0; i < mPageIndicator.getTotalPages(); i++) {
            int radius;
            if (i == mPageIndicator.getActualPosition() + 1) {
                radius = mConversionUtils.getPixelsFromDp(4 * (1 - mPageIndicator.getPositionOffset()));
            } else if (i == mPageIndicator.getActualPosition()) {
                radius = mConversionUtils.getPixelsFromDp(4 * (mPageIndicator.getPositionOffset()));
            } else {
                radius = mConversionUtils.getPixelsFromDp(4);
            }
            int x = mConversionUtils.getPixelsFromDp(4) + mConversionUtils.getPixelsFromDp(16 * i);
            canvas.drawCircle(x, height / 2, radius, mUnselectedPaint);
        }

        int firstX;
        int secondX;

        firstX = mConversionUtils.getPixelsFromDp(4 + mPageIndicator.getActualPosition() * 16);

        if (mPageIndicator.getPositionOffset() > .5f) {
            firstX += mConversionUtils.getPixelsFromDp(16 * (mPageIndicator.getPositionOffset() - .5f) * 2);
        }

        secondX = mConversionUtils.getPixelsFromDp(4 + mPageIndicator.getActualPosition() * 16);

        if (mPageIndicator.getPositionOffset() < .5f) {
            secondX += mConversionUtils.getPixelsFromDp(16 * mPageIndicator.getPositionOffset() * 2);
        } else {
            secondX += mConversionUtils.getPixelsFromDp(16);
        }

        canvas.drawCircle(firstX, mConversionUtils.getPixelsFromDp(4), mConversionUtils.getPixelsFromDp(4), mSelectedPaint);
        canvas.drawCircle(secondX, mConversionUtils.getPixelsFromDp(4), mConversionUtils.getPixelsFromDp(4), mSelectedPaint);
        canvas.drawRect(firstX, 0, secondX, mConversionUtils.getPixelsFromDp(8), mSelectedPaint);
    }
}
