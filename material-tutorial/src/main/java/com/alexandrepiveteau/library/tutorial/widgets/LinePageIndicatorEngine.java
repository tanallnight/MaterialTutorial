package com.alexandrepiveteau.library.tutorial.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.IntDef;

import com.alexandrepiveteau.library.tutorial.utils.ConversionUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Tanmay Parikh on 7/19/2015.
 */
public class LinePageIndicatorEngine extends PageIndicator.Engine {

    public static final int ANIMATION_PROGRESS = 0;
    public static final int ANIMATION_BAR = 1;
    private static final String HALF_WHITE = "#88FFFFFF";
    private int mLineColor;
    private int mBackgroundColor;
    private int mAnimMode;
    private ConversionUtils mConversionUtils;
    private PageIndicator mPageIndicator;
    private Paint mLinePaint;
    private Paint mBackgroundPaint;

    public LinePageIndicatorEngine() {
        this(Color.WHITE, Color.parseColor(HALF_WHITE), ANIMATION_PROGRESS);
    }

    public LinePageIndicatorEngine(@AnimationMode int animMode) {
        this(Color.WHITE, Color.parseColor(HALF_WHITE), animMode);
    }

    public LinePageIndicatorEngine(int lineColor, int backgroundColor, @AnimationMode int animMode) {
        mLineColor = lineColor;
        mBackgroundColor = backgroundColor;
        mAnimMode = animMode;
    }

    @Override
    public int getMeasuredHeight(int widthMeasuredSpec, int heightMeasuredSpec) {
        return mConversionUtils.getPixelsFromDp(6);
    }

    @Override
    public int getMeasuredWidth(int widthMeasuredSpec, int heightMeasuredSpec) {
        return mConversionUtils.getPixelsFromDp(20 * mPageIndicator.getTotalPages());
    }

    @Override
    public void onInitEngine(PageIndicator indicator, Context context) {
        mConversionUtils = new ConversionUtils(context);
        mPageIndicator = indicator;

        mLinePaint = new Paint();
        mLinePaint.setColor(mLineColor);
        mLinePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(mBackgroundColor);
        mBackgroundPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onDrawIndicator(Canvas canvas) {

        //Background Rect
        RectF backgroundRect = new RectF(0, 0,
                mPageIndicator.getWidth(),
                mConversionUtils.getPixelsFromDp(3));
        canvas.drawRoundRect(backgroundRect, mConversionUtils.getPixelsFromDp(1.5f), mConversionUtils.getPixelsFromDp(1.5f), mBackgroundPaint);

        //Draw progress
        int left;
        int right;
        RectF indicatorRect;
        if (mAnimMode == ANIMATION_PROGRESS) {
            left = 0;
            right = mConversionUtils.getPixelsFromDp(20) +
                    mConversionUtils.getPixelsFromDp(20 * mPageIndicator.getActualPosition()) +
                    mConversionUtils.getPixelsFromDp(20 * mPageIndicator.getPositionOffset());

        } else {
            left = mConversionUtils.getPixelsFromDp(20 * mPageIndicator.getActualPosition()) +
                    mConversionUtils.getPixelsFromDp(20 * mPageIndicator.getPositionOffset());
            right = mConversionUtils.getPixelsFromDp(20) +
                    mConversionUtils.getPixelsFromDp(20 * mPageIndicator.getActualPosition()) +
                    mConversionUtils.getPixelsFromDp(20 * mPageIndicator.getPositionOffset());
        }
        indicatorRect = new RectF(left, 0, right, mConversionUtils.getPixelsFromDp(3));
        canvas.drawRoundRect(indicatorRect, mConversionUtils.getPixelsFromDp(1.5f), mConversionUtils.getPixelsFromDp(1.5f), mLinePaint);

    }

    @IntDef({ANIMATION_PROGRESS, ANIMATION_BAR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnimationMode {}
}
