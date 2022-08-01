package com.jing.www.smartbj.fragment


import com.jing.www.smartbj.base.BaseFragment
import android.view.View


/**
 * Created by Administrator on 2017/2/5.
 * 第一个底部tab
 */
class HomeTabFragment : BaseFragment() {
    override fun initTitle() {
        setMenu(false)
        setTitle("首页")
        setType(true)
    }

    override fun createContent(): View? {
        return null
    }
}