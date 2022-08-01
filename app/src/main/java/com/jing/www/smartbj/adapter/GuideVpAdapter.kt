package com.jing.www.smartbj.adapter


import android.view.ViewGroup

import androidx.viewpager.widget.PagerAdapter

import android.view.View
import android.widget.*


/**
 * Created by Administrator on 2017/2/5.
 */
class GuideVpAdapter(private val views: List<ImageView>) : PagerAdapter() {
    override fun getCount(): Int {
        return views.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        //不明所以的方法
        return view === `object`
    }

    //实例化条目,也就是填充数据
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = views[position]
        container.addView(view)
        return view
    }

    //销毁条目
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}