package com.jing.www.smartbj.activity

import android.app.Activity
import androidx.viewpager.widget.ViewPager
import com.jing.www.smartbj.R
import android.widget.LinearLayout
import android.os.Bundle
import android.view.WindowManager
import com.jing.www.smartbj.adapter.GuideVpAdapter
import android.widget.RelativeLayout
import com.jing.www.smartbj.utils.SPUtils
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.jing.www.smartbj.utils.Constant
import java.util.ArrayList

/**
 * Created by Administrator on 2017/2/5.
 * 存放fragment的容器,使用ViewPager来完成
 */
class GuideActivity : Activity(), ViewPager.OnPageChangeListener, View.OnClickListener {
    private var vp: ViewPager? = null
    private var views: MutableList<ImageView>? = null
    private val imgs = intArrayOf(R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3)
    private var btStart: Button? = null
    private var containerGrayPoint: LinearLayout? = null
    private var redPoint: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
        initView()

        //去除状态栏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        initVp()
        vp!!.addOnPageChangeListener(this)
        initGrayPoint()
    }

    private fun initView() {
        vp = findViewById<View>(R.id.vp) as ViewPager
        btStart = findViewById<View>(R.id.btn_guide) as Button
        containerGrayPoint = findViewById<View>(R.id.container_gray_point) as LinearLayout
        redPoint = findViewById(R.id.red_point)
        btStart!!.setOnClickListener(this)
    }

    //动态创建灰色的点
    private fun initGrayPoint() {
        for (resId in imgs) {
            val view = View(this)
            view.setBackgroundResource(R.drawable.point_gray_bg)
            val params = LinearLayout.LayoutParams(10, 10)
            params.rightMargin = 20
            containerGrayPoint!!.addView(view, params)
        }
    }

    //初始化ViewPager的数据,即初始化fragment图片
    private fun initVp() {
        views = ArrayList()
        for (resId in imgs) {
            //因为imgs中存放的是数字,需要转换成对象
            val iv = ImageView(this)
            iv.setBackgroundResource(resId)
            (views as ArrayList<ImageView>).add(iv)
        }
        //给ViewPager绑定适配器
        vp!!.adapter = GuideVpAdapter(views as ArrayList<ImageView>)
    }

    //1.滑动时调用
    //position:当前滑动页面的下标
    // positionOffset：页面的滑动比率
    // positionOffsetPixels：页面滑动的实际像素
    private var width = 0
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        //计算两个灰色点的距离,计算红点移动的距离
        if (width == 0) {
            width = containerGrayPoint!!.getChildAt(1).left - containerGrayPoint!!.getChildAt(0).left
        }
        //width*positionOffset+width*position;
        //重新设置小红点的位置与相对布局的位置
        val params = redPoint!!.layoutParams as RelativeLayout.LayoutParams
        params.leftMargin = (width * positionOffset + width * position).toInt()
        redPoint!!.layoutParams = params
    }

    //2.页面被选中的时候调用
    override fun onPageSelected(position: Int) {
        if (position == imgs.size - 1) {
            btStart!!.visibility = View.VISIBLE
        } else {
            btStart!!.visibility = View.GONE
        }
    }

    //3.滑动状态改变时调用
    override fun onPageScrollStateChanged(state: Int) {}

    //引导按钮的点击事件
    override fun onClick(v: View) {
        SPUtils.saveBoolean(this, Constant.KEY_HAS_GUIDE, true)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}