package com.example.utils;

import android.content.Context;
import android.util.AttributeSet;

import androidx.viewpager.widget.ViewPager;

/**
 * 作者：hzh on 2021/12/10 10:22
 * 邮箱：565150953qq.com
 * 备注：去除viewpager动画或者禁止滑动
 */
public class CustomViewPager extends ViewPager {

    private static final String TAG = "MyViewPager";
    private boolean result = false;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewPager(Context context) {
        super(context);
    }

    //去除页面切换时的滑动翻页效果
//    @Override
//    public void setCurrentItem(int item, boolean smoothScroll) {
//        // TODO Auto-generated method stub
//        super.setCurrentItem(item, smoothScroll);
//    }
//
//    @Override
//    public void setCurrentItem(int item) {
//        // TODO Auto-generated method stub
//        super.setCurrentItem(item, false);
//    }


    //可以禁止滑动
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent arg0) {
//        if (result)
//            return super.onInterceptTouchEvent(arg0);
//        else
//            return false;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent arg0) {
//        if (result)
//            return super.onTouchEvent(arg0);
//        else
//            return false;
//    }

}
