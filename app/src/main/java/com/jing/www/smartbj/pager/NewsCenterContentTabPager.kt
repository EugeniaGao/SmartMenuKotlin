package com.jing.www.smartbj.pager

import android.view.LayoutInflater

import com.jing.www.smartbj.R
import androidx.viewpager.widget.ViewPager

import com.jing.www.smartbj.bean.NewsCenterTabBean
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.jing.www.smartbj.adapter.SwitchImgVPAdapter

import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.*

import java.util.ArrayList

/**
 * Created by Administrator on 2017/2/12.
 * 加载新闻中心新闻主体内容的碎片
 */
class NewsCenterContentTabPager(  //添加布局,在构造时候就初始化
        var context: Context?) : ViewPager.OnPageChangeListener {
    //1.创建handler
    private val mHandler = Handler()
    private var hasSwitch = false
    var view: View
    var vpSwitchImage: SwitchImageViewViewPager? = null
    var tvTitle: TextView? = null
    var llPointContainer: LinearLayout? = null
    private var newsCenterTabBean: NewsCenterTabBean? = null
    private var imgViews: MutableList<ImageView>? = null
    private fun initView(): View {
        val view = LayoutInflater.from(context).inflate(R.layout.newscenter_content_tab, null)
        vpSwitchImage = view.findViewById<View>(R.id.vp_switch_image) as SwitchImageViewViewPager
        tvTitle = view.findViewById<View>(R.id.vp_switch_image) as TextView
        llPointContainer = view.findViewById<View>(R.id.vp_switch_image) as LinearLayout
        return view
    }

    // 加载第二个tab主页面的url
    fun loadNetData(url: String?) {
//        OkHttpUtils
//                .get()
//                .url(url)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        MyToast.show(context, "tab_新闻主体数据加载失败");
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        MyLogger.i(TAG,response);
//                        processData(response);
//                    }
//
//
//                });
    }

    fun processData(json: String?) {
        val gson = Gson()
        newsCenterTabBean = gson.fromJson(json, NewsCenterTabBean::class.java)
        //加载数据要将数据绑定到对应的控件上
        bindDataToView()
        //把当前的NewsCenterContentTabPager对象传递给SwitchImageViewViewPager
        vpSwitchImage!!.setTabPager(this)
    }

    private fun bindDataToView() {
        initSwitchImgView()
        initPoint()
    }

    /**
     * 创建小圆点
     */
    private fun initPoint() {
        llPointContainer!!.removeAllViews()
        for (i in newsCenterTabBean!!.data!!.topnews!!.indices) {
            val view = View(context)
            val params = LinearLayout.LayoutParams(5, 5)
            params.rightMargin = 10
            view.setBackgroundResource(R.drawable.point_gray_bg)
            llPointContainer!!.addView(view, params)
        }
        //设置小红点
        llPointContainer!!.getChildAt(0).setBackgroundResource(R.drawable.point_red_bg)
    }

    private fun initSwitchImgView() {
        //根据网络数据中的图片个数来进行添加
        imgViews = ArrayList()
        val size = newsCenterTabBean!!.data!!.topnews!!.size
        for (i in -1 until size + 1) {
            var topNewsBean: NewsCenterTabBean.TopNewsBean? = null
            topNewsBean = if (i == -1) {
                newsCenterTabBean!!.data!!.topnews!![size - 1]
            } else if (i == size) {
                newsCenterTabBean!!.data!!.topnews!![0]
            } else {
                newsCenterTabBean!!.data!!.topnews!![i]
            }
            val iv = ImageView(context)
            Picasso.get()
                    .load(topNewsBean!!.topimage)
                    .into(iv)
            (imgViews as ArrayList<ImageView>).add(iv)
        }
        //创建轮播图适配器
        val adapter = SwitchImgVPAdapter(imgViews as ArrayList<ImageView>, newsCenterTabBean!!.data!!.topnews)
        vpSwitchImage!!.adapter = adapter
        //添加轮播图上文字的显示
        tvTitle!!.text = newsCenterTabBean!!.data!!.topnews!![0]!!.title
        //设置文字的改变监听
        vpSwitchImage!!.addOnPageChangeListener(this)
        vpSwitchImage!!.setCurrentItem(1, false)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
    override fun onPageSelected(position: Int) {
        //动态改变轮播文字
        //修正文字下表
        val size = newsCenterTabBean!!.data!!.topnews!!.size
        var textIndex = 0
        if (position == 0) {
            textIndex = size - 1
            vpSwitchImage!!.setCurrentItem(size, false)
        } else if (position == size + 1) {
            textIndex = 0
            vpSwitchImage!!.setCurrentItem(1, false)
        } else {
            textIndex = position - 1
        }
        tvTitle!!.text = newsCenterTabBean!!.data!!.topnews!![textIndex]!!.title
        //动态改变原点
        val pointCount = llPointContainer!!.childCount
        for (i in 0 until pointCount) {
            val child = llPointContainer!!.getChildAt(i)
            if (textIndex == i) {
                child.setBackgroundResource(R.drawable.point_red_bg)
            } else {
                child.setBackgroundResource(R.drawable.point_gray_bg)
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {}

    //2.自定义开始切换方法开始轮播,未指定嗲用
    //被调用时机思考:tab被选中或是切换时,这个类是放在views中的,切换时是tab点击
    fun startSwitch() {
        if (!hasSwitch) {
            //发送handler切换
            mHandler.postDelayed(SwitchTask(), 3000)
            hasSwitch = true
        }
    }

    //3.自定义开始切换方法停止轮播,未指定嗲用
    fun stopSwitch() {
        if (hasSwitch) {
            //发送handler切换,参数为取而代之
            mHandler.removeCallbacksAndMessages(null)
            hasSwitch = false
        }
    }

    //4.在子线程处理切换的逻辑
    private inner class SwitchTask : Runnable {
        override fun run() {
            //指定循环的出口
            if (vpSwitchImage != null) {
                //1.获取当前的item
                println(newsCenterTabBean!!.data!!.topnews!!.size.toString() + "COUNT")
                var currentItem = vpSwitchImage!!.currentItem
                //2.判断是否是最后条目
                //                  newsCenterTabBean.data.topnews.size()
                if (currentItem == vpSwitchImage!!.childCount) {
                    // if (currentItem == newsCenterTabBean.data.topnews.size()) {
                    currentItem = 0
                } else {
                    // currentItem = currentItem + 1;
                    currentItem++
                }

                //3.重置当前的条目
                vpSwitchImage!!.currentItem = currentItem
            }
            //4.返回主线程或循环
            mHandler.postDelayed(this, 3000)
        }
    }

    companion object {
        private const val TAG = "tag"
    }

    init {
        view = initView()
    }
}