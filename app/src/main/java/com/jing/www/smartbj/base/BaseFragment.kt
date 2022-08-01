package com.jing.www.smartbj.base

import android.widget.TextView
import android.widget.ImageButton
import android.widget.FrameLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.jing.www.smartbj.R
import com.jing.www.smartbj.activity.MainActivity

import android.view.View
import androidx.fragment.app.Fragment


/**
 * Created by Administrator on 2017/2/6.
 */
abstract class BaseFragment : Fragment(), View.OnClickListener {
    private var title: TextView? = null
    private var menu: ImageButton? = null
    private var type: ImageButton? = null
    private var frameContaner: FrameLayout? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.base_fragment_activity, container, false)
        title = view.findViewById<View>(R.id.tv_title) as TextView
        menu = view.findViewById<View>(R.id.ib_menu) as ImageButton
        type = view.findViewById<View>(R.id.ib_type) as ImageButton
        frameContaner = view.findViewById<View>(R.id.base_fl_container) as FrameLayout
        initEvent()
        return view
    }

    private fun initEvent() {
        menu!!.setOnClickListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //初始化标题
        initTitle()
    }

    //我只是一个基类,标题的内容,图标的显示,我不能决定,需要子类来决定,好吧,那就强制告诉子类去提供吧
    abstract fun initTitle()

    //设置标题
    fun setTitle(text: String?) {
        title!!.text = text
    }

    //设置menu可见性
    fun setMenu(boo: Boolean) {
        menu!!.visibility = if (boo) View.VISIBLE else View.GONE
    }

    //设置type可见性
    fun setType(boo: Boolean) {
        type!!.visibility = if (boo) View.VISIBLE else View.GONE
    }

    //创建内容
    abstract fun createContent(): View?

    //向容器中添加内容
    fun addView(view: View?) {
        frameContaner!!.removeAllViews()
        frameContaner!!.addView(view)
    }

    //点击侧滑菜单
    override fun onClick(v: View) {
        //侧滑菜单在activity中
        (activity as MainActivity?)!!.slidingMenu!!.toggle()
    }
}