package com.jing.www.smartbj.fragment



import com.jing.www.smartbj.base.BaseFragment
import android.view.View


/**
 * Created by Administrator on 2017/2/5.
 * 第三个底部tab
 */
class SmartServiceTabFragment : BaseFragment() {
    override fun initTitle() {
        setMenu(true)
        setTitle("广场")
        setType(false)
    }

    override fun createContent(): View? {
        return null
    }
}