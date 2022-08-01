package com.jing.www.smartbj.fragment

import com.jing.www.smartbj.base.BaseFragment

import android.view.View


/**
 * Created by Administrator on 2017/2/5.
 * 第五个底部tab
 */
class SettingTabFragment : BaseFragment() {
    override fun initTitle() {
        setMenu(false)
        setTitle("设置")
        setType(false)
    }

    override fun createContent(): View? {
        return null
    }
}