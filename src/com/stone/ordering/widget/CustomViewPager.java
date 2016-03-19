package com.stone.ordering.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {
	private boolean canScroll = true;
	public CustomViewPager(Context context) {
		super(context);
	}
	
	public CustomViewPager(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }
	/**
	 * ���������Ƿ�ɷ���
	 * @param canScroll
	 */
	public void setCanScroll(boolean canScroll) {
		this.canScroll = canScroll;
	}
	
	@Override
	public void scrollTo(int x, int y) {
		if (canScroll) {
			super.scrollTo(x, y);
		}
	}
	
	@Override
	public void scrollBy(int x, int y) {
		if (canScroll) {
			super.scrollBy(x, y);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (canScroll) {
			return super.onTouchEvent(arg0);
		}else {
			return false;
		}
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (canScroll) {
			return super.onInterceptTouchEvent(arg0);
		}else {
			return false;
		}
	}
}
