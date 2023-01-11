package com.jing.www.smartbj.pager


import androidx.viewpager.widget.ViewPager
import android.view.MotionEvent

import com.jing.www.smartbj.utils.MyToast

import android.content.Context
import android.util.AttributeSet


/**
 * Created by Administrator on 2017/2/12.
 * 自定义轮播图的切换自定控件
 * 控制轮播图的开始和结束
 * 处理轮播图的点击事件
 */
class SwitchImageViewViewPager : ViewPager {
    //控制轮播图开始和停止的对象
    private var tabPager: NewsCenterContentTabPager? = null
    private var startX = 0f
    private var startY = 0f

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}

    fun setTabPager(tabPager: NewsCenterContentTabPager?) {
        this.tabPager = tabPager
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
       /* when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                tabPager!!.stopSwitch()
                startX = ev.x
                startY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                tabPager!!.stopSwitch()
                val moveX = ev.x
                val moveY = ev.y
                val disX = (moveX - startX).toInt()
                val disY = (moveY - startY).toInt()
                if (Math.abs(disX) > Math.abs(disY) && moveX > startX) {
                    requestDisallowInterceptTouchEvent(true)
                }
            }
            MotionEvent.ACTION_UP -> {
                val upX = ev.x
                val upY = ev.y
                if (startX == upX && startY == upY) {
                    MyToast.show(context, "点击事件")
                }
                tabPager!!.startSwitch()
            }
        }*/
        return super.dispatchTouchEvent(ev)
    }
}