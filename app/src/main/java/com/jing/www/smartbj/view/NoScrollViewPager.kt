package com.jing.www.smartbj.view


import androidx.viewpager.widget.ViewPager
import android.view.MotionEvent
import android.content.Context
import android.util.AttributeSet


/**
 * Created by Administrator on 2017/2/6.
 */
class NoScrollViewPager : ViewPager {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return false
    }
}