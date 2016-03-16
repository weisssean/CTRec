package com.rec.ct.rec_txt.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by MonkeyFish on 3/25/14.
 */
public class NoSwipeViewPager extends ViewPager {

    private static boolean mAllowed=false;

    public NoSwipeViewPager(Context context) {
        super(context);
    }
    public NoSwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (mAllowed){
            return super.onInterceptTouchEvent(arg0);
        }
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mAllowed){
            return super.onTouchEvent(event);
        }
        // Never allow swiping to switch between pages
        return false;
    }

    public static void allowSwipe(boolean allow){
        mAllowed =allow;
    }
}
