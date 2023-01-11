package com.jing.www.smartbj.fragment


import android.view.LayoutInflater
import com.jing.www.smartbj.base.BaseFragment
import android.view.View
import android.view.ViewGroup
import com.jing.www.smartbj.R


/**
 * Created by Administrator on 2017/2/5.
 * 第一个底部tab
 */
class HomeTabFragment : BaseFragment() {
    override fun initTitle() {
        setTitle("首页")


    }

    override fun createContent(): View? {
        //todo 写recucylerView 实现图文
        val view = LayoutInflater.from(getContext()).inflate(R.layout.newscenter_content, view as ViewGroup?, false)
        return view;
    }
}