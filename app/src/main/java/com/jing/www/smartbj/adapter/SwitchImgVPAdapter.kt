package com.jing.www.smartbj.adapter


import android.view.ViewGroup

import com.jing.www.smartbj.bean.NewsCenterTabBean

import androidx.viewpager.widget.PagerAdapter
import android.view.View
import android.widget.*


/**
 * Created by Administrator on 2017/2/12.
 * 轮播图图片适配器
 */
class SwitchImgVPAdapter(private val imageViews: List<ImageView>, private val topNewsBeanList: List<NewsCenterTabBean.TopNewsBean?>?) : PagerAdapter() {
    override fun getCount(): Int {
        return imageViews.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = imageViews[position]
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}