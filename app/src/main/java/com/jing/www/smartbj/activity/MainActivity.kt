package com.jing.www.smartbj.activity


import android.os.Bundle
import com.jing.www.smartbj.R

import android.view.View
import android.widget.RadioButton

import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu
import com.jing.www.smartbj.adapter.MenuAdapter
import com.jing.www.smartbj.bean.NewsCenterBean
import com.jing.www.smartbj.fragment.HomeTabFragment
import com.jing.www.smartbj.fragment.NewsCenterFragment
import com.jing.www.smartbj.fragment.SmartServiceTabFragment
import com.jing.www.smartbj.fragment.GovaffairsFragment
import com.jing.www.smartbj.fragment.SettingTabFragment
import com.jing.www.smartbj.adapter.TabVpAdapter
import com.jing.www.smartbj.base.BaseFragment
import com.jing.www.smartbj.base.BaseLoadNetDataOperator
import com.jing.www.smartbj.beannew.SecondTabBean
import java.util.ArrayList

/**
 * Created by Administrator on 2017/2/5.
 */
class MainActivity : FragmentActivity(), RadioGroup.OnCheckedChangeListener {
    private var vp: ViewPager? = null
    private var rb_home: RadioButton? = null
    private var rb_newscenter: RadioButton? = null
    private var rb_smartservice: RadioButton? = null
    private var rb_govaffairs: RadioButton? = null
    private var rb_setting: RadioButton? = null
    private var rg_tab: RadioGroup? = null
    private var fragments: MutableList<Fragment>? = null
    @JvmField
    var slidingMenu: SlidingMenu? = null
    var newsCenterMenuBeanList: List<NewsCenterBean.NewsCenterMenuBean>? = null

    private var adapter: MenuAdapter? = null
    @JvmName("setNewsCenterMenuBeanList1")
    fun setNewsCenterMenuBeanList(newsCenterMenuBeanList: List<NewsCenterBean.NewsCenterMenuBean>?) {
        this.newsCenterMenuBeanList = newsCenterMenuBeanList
        initRecycleView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
        initEvent()
        initViewPager()
        initSlidingMenu()
    }

    private fun initRecycleView() {
        //1.初始化..侧滑菜单
        val rv_menu = slidingMenu!!.findViewById<View>(R.id.rv_menu) as RecyclerView
        //2.找到布局管理器
        rv_menu.layoutManager = LinearLayoutManager(this)
        //3、边栏适配器添加数据
        adapter = MenuAdapter(this, newsCenterMenuBeanList)
        rv_menu.adapter = adapter
    }

    private fun initView() {
        vp = findViewById<View>(R.id.vp) as ViewPager
        rg_tab = findViewById<View>(R.id.rg) as RadioGroup
        rb_home = findViewById<View>(R.id.rb_tab_home) as RadioButton
        rb_newscenter = findViewById<View>(R.id.rb_tab_newscenter) as RadioButton
        rb_smartservice = findViewById<View>(R.id.rb_tab_smartservice) as RadioButton
        rb_govaffairs = findViewById<View>(R.id.rb_tab_govaffairs) as RadioButton
        rb_setting = findViewById<View>(R.id.rb_tab_setting) as RadioButton
    }

    private fun initEvent() {
        rg_tab!!.setOnCheckedChangeListener(this)
    }

    private fun initViewPager() {
        fragments = ArrayList()
        (fragments as ArrayList<Fragment>).add(HomeTabFragment())
        (fragments as ArrayList<Fragment>).add(NewsCenterFragment())
        (fragments as ArrayList<Fragment>).add(SmartServiceTabFragment())
        (fragments as ArrayList<Fragment>).add(GovaffairsFragment())
        (fragments as ArrayList<Fragment>).add(SettingTabFragment())
        vp!!.adapter = TabVpAdapter(supportFragmentManager, fragments as ArrayList<Fragment>)
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        var item = 0
        when (checkedId) {
            R.id.rb_tab_home -> {
                slidingMenu!!.touchModeAbove = SlidingMenu.TOUCHMODE_NONE
                item = 0
            }
            R.id.rb_tab_newscenter -> {
                item = 1
                slidingMenu!!.touchModeAbove = SlidingMenu.TOUCHMODE_FULLSCREEN
            }
            R.id.rb_tab_smartservice -> {
                slidingMenu!!.touchModeAbove = SlidingMenu.TOUCHMODE_FULLSCREEN
                item = 2
            }
            R.id.rb_tab_govaffairs -> {
                item = 3
                slidingMenu!!.touchModeAbove = SlidingMenu.TOUCHMODE_FULLSCREEN
            }
            R.id.rb_tab_setting -> {
                slidingMenu!!.touchModeAbove = SlidingMenu.TOUCHMODE_NONE
                item = 4
            }
        }
        vp!!.setCurrentItem(item, false)

        //获取子fragment
        val itemFragment = fragments!![item] as BaseFragment
        if (itemFragment is BaseLoadNetDataOperator) {
            //调用子fragment的下载数据，这里进行网络接口调用，如果实现了接口就调用
            (itemFragment as BaseLoadNetDataOperator).loadNetData()
        }
    }

    private fun initSlidingMenu() {
        slidingMenu = SlidingMenu(this)
        slidingMenu!!.mode = SlidingMenu.LEFT
        slidingMenu!!.touchModeAbove = SlidingMenu.TOUCHMODE_FULLSCREEN
        slidingMenu!!.setBehindWidth(150)
        slidingMenu!!.attachToActivity(this, SlidingMenu.SLIDING_CONTENT)
        slidingMenu!!.setMenu(R.layout.sliding_menu_activity)
    }//2.利用集合获取当前fragment//1.利用容器获取当前fragment的id

    //接口回调独立方法
    val currentTabFragment: BaseFragment
        get() {
            val currentItem = vp!!.currentItem //1.利用容器获取当前fragment的id
            return fragments!![currentItem] as BaseFragment //2.利用集合获取当前fragment
        }
}