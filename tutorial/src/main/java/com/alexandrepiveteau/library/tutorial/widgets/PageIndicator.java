package com.alexandrepiveteau.library.tutorial.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.alexandrepiveteau.library.tutorial.R;
import com.alexandrepiveteau.library.tutorial.utils.ConversionUtils;

/**
 * Created by Alexandre on 15.07.2015.
 */
public class PageIndicator extends View implements ViewPager.OnPageChangeListener{

    private int mActualPosition;
    private ConversionUtils mConversionUtils;
    private float mPositionOffset;
    private int mTotalPages;
    private ViewPager mViewPager;

    private Paint mSelectedPaint;
    private Paint mUnselectedPaint;

    public PageIndicator(Context context) {
        this(context, null);
    }

    public PageIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PageIndicator, 0, 0);

        int selectedColor = typedArray.getColor(R.styleable.PageIndicator_dotsSelectedColor, context.getResources().getColor(R.color.dots_default_selected));
        int unselectedColor = typedArray.getColor(R.styleable.PageIndicator_dotsUnselectedColor, context.getResources().getColor(R.color.dots_default_unselected));

        typedArray.recycle();

        mSelectedPaint = new Paint();
        mUnselectedPaint = new Paint();

        mConversionUtils = new ConversionUtils(getContext());

        mSelectedPaint.setColor(selectedColor);
        mUnselectedPaint.setColor(unselectedColor);

        mTotalPages = 2;
    }

    public void notifyNumberPagesChanged() {
        mTotalPages = mViewPager.getAdapter().getCount();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        //We draw the unselected page indicators
        for(int i = 0; i < mTotalPages; i++) {
            int radius;
            if(i == mActualPosition + 1) {
                radius = mConversionUtils.getPixelsFromDp(4*(1-mPositionOffset));
            } else if (i == mActualPosition) {
                radius = mConversionUtils.getPixelsFromDp(4*(mPositionOffset));
            } else {
                radius = mConversionUtils.getPixelsFromDp(4);
            }
            int x = mConversionUtils.getPixelsFromDp(4) + mConversionUtils.getPixelsFromDp(16*i);
            canvas.drawCircle(x, height/2, radius, mUnselectedPaint);
        }

        int firstX;
        int secondX;

        firstX = mConversionUtils.getPixelsFromDp(4 + mActualPosition*16);

        if(mPositionOffset > .5f) {
            firstX += mConversionUtils.getPixelsFromDp(16*(mPositionOffset - .5f)*2);
        }

        secondX = mConversionUtils.getPixelsFromDp(4 + mActualPosition*16);

        if(mPositionOffset < .5f) {
            secondX += mConversionUtils.getPixelsFromDp(16*mPositionOffset*2);
        } else {
            secondX += mConversionUtils.getPixelsFromDp(16);
        }

        canvas.drawCircle(firstX, mConversionUtils.getPixelsFromDp(4), mConversionUtils.getPixelsFromDp(4), mSelectedPaint);
        canvas.drawCircle(secondX, mConversionUtils.getPixelsFromDp(4), mConversionUtils.getPixelsFromDp(4), mSelectedPaint);
        canvas.drawRect(firstX, 0, secondX, mConversionUtils.getPixelsFromDp(8), mSelectedPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = mConversionUtils.getPixelsFromDp(8);
        int width = mConversionUtils.getPixelsFromDp(8 * (mTotalPages * 2 - 1));

        setMeasuredDimension(width, height);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mActualPosition = position;
        mPositionOffset = positionOffset;
        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
        //Ignore
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //Ignore
    }

    public void setSelectedColor(int color) {
        mSelectedPaint.setColor(color);
        invalidate();
    }

    public void setUnselectedColor(int color) {
        mUnselectedPaint.setColor(color);
        invalidate();
    }

    /**
     * You must call this AFTER setting the Adapter for the ViewPager, or it won't display the right amount of points.
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(this);
        mTotalPages = viewPager.getAdapter().getCount();
        invalidate();
    }
}
