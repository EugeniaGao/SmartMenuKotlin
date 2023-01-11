package com.jing.www.smartbj.fragment


import android.view.LayoutInflater
import android.view.ViewGroup
import com.jing.www.smartbj.R
import androidx.viewpager.widget.ViewPager
import com.jing.www.smartbj.pager.NewsCenterContentTabPager
import com.jing.www.smartbj.bean.NewsCenterTabBean
import com.jing.www.smartbj.base.BaseFragment
import android.view.View
import com.jing.www.smartbj.bean.NewsCenterBean
import com.viewpagerindicator.TabPageIndicator
import com.jing.www.smartbj.adapter.NewsCenterTabVPAdapter
import java.util.ArrayList

//迁移后包改变
/**
 * Created by Administrator on 2017/2/5.
 * 第二个底部tab
 * 实现网络请求接口
 * 第一层嵌套：app标题基础框架
 * 第二层嵌套，tab嵌套
 * 第三层嵌套,tab下的pager嵌套
 * 可类比小红书首页UI
 *
 */
class NewsCenterFragment : BaseFragment() {
    private var newsCenterBean: NewsCenterBean? = null
    private val newsCenterTabBean: NewsCenterTabBean? = null
    private var tabPagerIndicator: TabPageIndicator? = null
    private var vpNewscenterContent: ViewPager? = null
    private var views: MutableList<NewsCenterContentTabPager?>? = null


override fun initTitle() {
        setTitle("首页")

    var view = createContent()
    addView(view)//需要向base容器中添加本view
}


    /**
     * getContext()获取上下文 使用this替换
     * getActivity()
     * getView()
     * @return
     */
    override fun createContent(): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.newscenter_content, view as ViewGroup?, false)
        tabPagerIndicator = view.findViewById<View>(R.id.tabPagerIndicator) as TabPageIndicator
        vpNewscenterContent = view.findViewById<View>(R.id.vp_newscenter_content) as ViewPager

        //初始化viewPager
        initViewPager()

        return view
    }

    private fun initViewPager() {
        views = ArrayList<NewsCenterContentTabPager?>()
        val list= listOf("粥类","盖饭","面食","炒菜","炖菜","靓汤","轻食")//初始化水果集合
        for (item in list){
            val tabPager = NewsCenterContentTabPager(getContext())
            (views as ArrayList<NewsCenterContentTabPager?>).add(tabPager)
        }
        val newsCenterTabVPAdapter = NewsCenterTabVPAdapter(views, list)
        vpNewscenterContent!!.adapter = newsCenterTabVPAdapter //1.设置adapter

        //indicater与viewPager联合
        tabPagerIndicator!!.setViewPager(vpNewscenterContent) //2.设置viewPager

    }


}