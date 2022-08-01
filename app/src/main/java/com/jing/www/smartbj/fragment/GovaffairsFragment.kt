package com.jing.www.smartbj.fragment

import com.jing.www.smartbj.base.BaseFragment
import android.view.View


/**
 * Created by Administrator on 2017/2/5.
 * 第四个底部tab
 */
class GovaffairsFragment : BaseFragment() {
    override fun initTitle() {
        setMenu(true)
        setTitle("热搜")
        setType(false)
    }

    override fun createContent(): View? {
        return null
    }
}