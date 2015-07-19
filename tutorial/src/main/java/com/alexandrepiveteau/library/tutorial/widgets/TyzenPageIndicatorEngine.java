package com.alexandrepiveteau.library.tutorial.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.alexandrepiveteau.library.tutorial.R;
import com.alexandrepiveteau.library.tutorial.utils.ConversionUtils;

/**
 * Created by Alexandre on 19.07.2015.
 */
public class TyzenPageIndicatorEngine extends PageIndicator.Engine {

    private ConversionUtils mConversionUtils;
    private PageIndicator mPageIndicator;
    private Paint mPaint;

    public TyzenPageIndicatorEngine() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    public TyzenPageIndicatorEngine(int color) {
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public int getMeasuredHeight(int widthMeasuredSpec, int heightMeasuredSpec) {
        return mConversionUtils.getPixelsFromDp(8f);
    }

    @Override
    public int getMeasuredWidth(int widthMeasuredSpec, int heightMeasuredSpec) {
        return mConversionUtils.getPixelsFromDp(8 * (mPageIndicator.getTotalPages() * 2 - 1));
    }

    @Override
    public void onInitEngine(PageIndicator indicator, Context context) {
        mPageIndicator = indicator;

        mConversionUtils = new ConversionUtils(context);
    }

    @Override
    public void onDrawIndicator(Canvas canvas) {
        int height = mPageIndicator.getHeight();

        for(int i = 0; i < mPageIndicator.getTotalPages(); i++) {
            if(i == mPageIndicator.getActualPosition() + 1) {
                int x = mConversionUtils.getPixelsFromDp(4) + mConversionUtils.getPixelsFromDp(16*i);

                canvas.save();
                canvas.rotate(90*mPageIndicator.getPositionOffset(), x, height/2);
            } else if (i == mPageIndicator.getActualPosition()) {
                int x = mConversionUtils.getPixelsFromDp(4) + mConversionUtils.getPixelsFromDp(16*i);

                canvas.save();
                canvas.rotate(90+(90*mPageIndicator.getPositionOffset()), x, height/2);
            } else {
                canvas.save();
            }
            int x = mConversionUtils.getPixelsFromDp(0) + mConversionUtils.getPixelsFromDp(16*i);
            int twoDp = mConversionUtils.getPixelsFromDp(3);
            int sixDp = mConversionUtils.getPixelsFromDp(5);
            int heightDp = mConversionUtils.getPixelsFromDp(8);
            canvas.drawRect(x, twoDp, x+heightDp, sixDp, mPaint);
            canvas.restore();
        }
    }
}
