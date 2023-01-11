package com.jing.www.smartbj.adapter


import android.view.ViewGroup

import com.jing.www.smartbj.pager.NewsCenterContentTabPager

import androidx.viewpager.widget.PagerAdapter
import android.content.ContentValues
import android.util.Log
import android.view.View

import com.jing.www.smartbj.bean.NewsCenterBean

import com.jing.www.smartbj.utils.*

/**
 * Created by Administrator on 2017/2/10.
 */
class NewsCenterTabVPAdapter(//通过构造传入数据 //设置适配器,该ViewPAger要存储Views,beanList数据
        var views: List<NewsCenterContentTabPager?>?,

        private val tabBeenList: List<String>?) : PagerAdapter() {
    override fun getCount(): Int {
        return tabBeenList!!.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    //将只指定条目的view放入container中
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = views!![position]!!.view
        container.addView(view)

        val tabPager = views!![position]

        return view!!
    }

    //删除条目
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    //设置indcater的头
    override fun getPageTitle(position: Int): CharSequence? {
        return tabBeenList!![position]
    }
}