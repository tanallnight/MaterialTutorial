package com.alexandrepiveteau.library.tutorial.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IntDef;

import com.alexandrepiveteau.library.tutorial.utils.ConversionUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Tanmay Parikh on 7/19/2015.
 */
public class RingPageIndicatorEngine extends PageIndicator.Engine {

    public static final int ANIMATION_SCALE = 0;
    public static final int ANIMATION_FADE = 1;
    private int mRingColor;
    private int mDotColor;
    private int mAnimMode;
    private ConversionUtils mConversionUtils;
    private PageIndicator mPageIndicator;
    private Paint mRingPaint;
    private Paint mDotPaint;

    public RingPageIndicatorEngine() {
        this(Color.WHITE, Color.WHITE, ANIMATION_FADE);
    }

    public RingPageIndicatorEngine(@AnimationMode int animMode) {
        this(Color.WHITE, Color.WHITE, animMode);
    }

    public RingPageIndicatorEngine(int ringColor, int dotColor, @AnimationMode int animMode) {
        mRingColor = ringColor;
        mDotColor = dotColor;
        mAnimMode = animMode;
    }

    @Override
    public int getMeasuredHeight(int widthMeasuredSpec, int heightMeasuredSpec) {
        return mConversionUtils.getPixelsFromDp(10);
    }

    @Override
    public int getMeasuredWidth(int widthMeasuredSpec, int heightMeasuredSpec) {
        return mConversionUtils.getPixelsFromDp(10 * (mPageIndicator.getTotalPages() * 2 - 1));
    }

    @Override
    public void onInitEngine(PageIndicator indicator, Context context) {
        mPageIndicator = indicator;
        mConversionUtils = new ConversionUtils(context);

        mRingPaint = new Paint();
        mRingPaint.setColor(mRingColor);
        mRingPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(4);

        mDotPaint = new Paint();
        mDotPaint.setColor(mDotColor);
        mDotPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onDrawIndicator(Canvas canvas) {
        float offset = mPageIndicator.getPositionOffset();

        //Draw rings
        for (int i = 0; i < mPageIndicator.getTotalPages(); i++) {
            int radius = mConversionUtils.getPixelsFromDp(4);
            int cx = mConversionUtils.getPixelsFromDp(5) + mConversionUtils.getPixelsFromDp(20 * i);
            int cy = mPageIndicator.getHeight() / 2;
            canvas.drawCircle(cx, cy, radius, mRingPaint);
        }

        //Draw indicators
        int firstX = mConversionUtils.getPixelsFromDp(5) + mConversionUtils.getPixelsFromDp(20 * mPageIndicator.getActualPosition());
        int secondX = mConversionUtils.getPixelsFromDp(5) + mConversionUtils.getPixelsFromDp(20 * (mPageIndicator.getActualPosition() + 1));

        if (mAnimMode == ANIMATION_SCALE) {
            int radiusFirst = mConversionUtils.getPixelsFromDp(4 * (1 - offset));
            int radiusSecond = mConversionUtils.getPixelsFromDp(4 * offset);
            canvas.drawCircle(firstX, mConversionUtils.getPixelsFromDp(5), radiusFirst, mDotPaint);
            canvas.drawCircle(secondX, mConversionUtils.getPixelsFromDp(5), radiusSecond, mDotPaint);
        } else {
            int radius = mConversionUtils.getPixelsFromDp(4);
            mDotPaint.setAlpha((int) ((1 - offset) * 255));
            canvas.drawCircle(firstX, mConversionUtils.getPixelsFromDp(5), radius, mDotPaint);
            mDotPaint.setAlpha((int) (offset * 255));
            canvas.drawCircle(secondX, mConversionUtils.getPixelsFromDp(5), radius, mDotPaint);
        }
    }

    @IntDef({ANIMATION_SCALE, ANIMATION_FADE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnimationMode {}
}
