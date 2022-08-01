package com.jing.www.smartbj.fragment


import android.widget.ImageButton

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.jing.www.smartbj.R
import com.jing.www.smartbj.activity.MainActivity
import androidx.viewpager.widget.ViewPager

import com.jing.www.smartbj.pager.NewsCenterContentTabPager

import com.jing.www.smartbj.bean.NewsCenterTabBean
import com.google.gson.Gson

import com.jing.www.smartbj.base.BaseFragment

import android.content.Context
import android.util.Log
import android.view.View
import com.jing.www.smartbj.base.BaseLoadNetDataOperator
import com.jing.www.smartbj.bean.NewsCenterBean
import com.viewpagerindicator.TabPageIndicator
import com.jing.www.smartbj.adapter.NewsCenterTabVPAdapter
import com.jing.www.smartbj.utils.GetJsonDataUtil
import java.util.ArrayList

//迁移后包改变
/**
 * Created by Administrator on 2017/2/5.
 * 第二个底部tab
 * 实现网络请求接口
 */
class NewsCenterFragment : BaseFragment(), BaseLoadNetDataOperator {
    private var newsCenterBean: NewsCenterBean? = null
//    var context: Context?= null
    private val newsCenterTabBean: NewsCenterTabBean? = null
    private var ibNext: ImageButton? = null
    private var tabPagerIndicator: TabPageIndicator? = null
    private var vpNewscenterContent: ViewPager? = null
    private var views: MutableList<NewsCenterContentTabPager?>? = null
    override fun initTitle() {
        setMenu(true)
        setTitle("视频新闻")
        setType(false)
    }

    /**
     * getContext()获取上下文 使用this替换
     * getActivity()
     * getView()
     * @return
     */
    override fun createContent(): View? {
        val view = LayoutInflater.from(getContext()).inflate(R.layout.newscenter_content, view as ViewGroup?, false)
        //       this.context=getContext();
        tabPagerIndicator = view.findViewById<View>(R.id.tabPagerIndicator) as TabPageIndicator
        ibNext = view.findViewById<View>(R.id.ib_next) as ImageButton
        vpNewscenterContent = view.findViewById<View>(R.id.vp_newscenter_content) as ViewPager

        //初始化viewPager
        initViewPager()
        ibNext!!.setOnClickListener {
            val currentItem = vpNewscenterContent!!.currentItem
            if (currentItem != newsCenterBean!!.data!![0]!!.children!!.size - 1) {
                vpNewscenterContent!!.currentItem = currentItem + 1
            }
        }
        return view
    }

    private fun initViewPager() {
        views = ArrayList<NewsCenterContentTabPager?>()
        for (tabBean in newsCenterBean!!.data!![0]!!.children!!) {
            val tabPager = NewsCenterContentTabPager(getContext())
            (views as ArrayList<NewsCenterContentTabPager?>).add(tabPager)
        }
        val newsCenterTabVPAdapter = NewsCenterTabVPAdapter(views, newsCenterBean!!.data!![0]!!.children)
        vpNewscenterContent!!.adapter = newsCenterTabVPAdapter
        //indicater与viewPager联合
        tabPagerIndicator!!.setViewPager(vpNewscenterContent)
        //第一个调用时机
        (views as ArrayList<NewsCenterContentTabPager?>).get(0)!!.startSwitch()
        tabPagerIndicator!!.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                for (i in (views as ArrayList<NewsCenterContentTabPager?>).indices) {
                    val newsCenterContentTabPager = (views as ArrayList<NewsCenterContentTabPager?>).get(i)
                    if (position == i) {
                        newsCenterContentTabPager!!.startSwitch()
                    } else {
                        newsCenterContentTabPager!!.stopSwitch()
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun loadNetData() {
//        final String url = Constant.NEWSCENTER_URL;

        /*  OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        MyToast.show(getActivity(), "网络连接失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyToast.show(getActivity(), "网络连接成功");
                        ProcessData(response);
                        //渲染的是本fragment的布局
                        View view = createContent();
                        addView(view);
                    }
                });*/
        val getJsonDataUtil = GetJsonDataUtil()
//        context = requireActivity()
        val jsonData = getJsonDataUtil.getJson(context, "NewsCenterBean.json")
        Log.d("jing", "loadNetData:边栏数据请求 $jsonData")
        ProcessData(jsonData)
    }

    var newsCenterMenuBeenList: List<NewsCenterBean.NewsCenterMenuBean>? = null
    private fun ProcessData(json: String?) {
        val gson = Gson()
        //转换成模型对象用bean接受
        newsCenterBean = gson.fromJson(json, NewsCenterBean::class.java)
        //因为是要给slidingmenu添加数据,所以需要让MainAcyicvity来操作,获取碎片所在的activity
        //传递数据给Acyivity
        (activity as MainActivity?)!!.setNewsCenterMenuBeanList(newsCenterBean?.data as List<NewsCenterBean.NewsCenterMenuBean>?)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}