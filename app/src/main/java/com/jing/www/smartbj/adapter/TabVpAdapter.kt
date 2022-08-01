package com.jing.www.smartbj.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


/**
 * Created by Administrator on 2017/2/6.
 */
class TabVpAdapter(fm: FragmentManager?, //我要绑定多个fragment,就要传入fragment的集合
                   private val fragments: List<Fragment>) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}