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
        ///**
    //         * id : 10007
    //         * title : 北京
    //         * type : 1
    //         * url : /10007/list_1.json
    //         */
        private val tabBeenList: List<String>?) : PagerAdapter() {
    override fun getCount(): Int {
//        return if (views != null) views!!.size else 0
        return tabBeenList!!.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    //将只指定条目的view放入container中
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = views!![position]!!.view
        container.addView(view)

        //之前 是将头放进去,现在要加载网络的主体内容:调用fragment中的方法
        //通过position得到对应的view,转换成对应的Basepager,即新闻主体的碎片
        val tabPager = views!![position]
//        val url = Constant.HOST + tabBeenList!![position]?.url//todo 替换成
//        Log.d(ContentValues.TAG, "instantiateItem: url$url")
//        tabPager!!.loadNetData(url)
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