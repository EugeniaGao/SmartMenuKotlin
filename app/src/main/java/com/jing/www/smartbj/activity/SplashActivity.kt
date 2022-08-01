package com.jing.www.smartbj.activity

import android.app.Activity
import android.view.animation.Animation.AnimationListener
import android.widget.RelativeLayout
import android.os.Bundle
import com.jing.www.smartbj.R
import com.jing.www.smartbj.utils.SPUtils
import android.content.Intent
import android.os.Handler
import android.view.View
import android.view.animation.*

import com.jing.www.smartbj.utils.Constant

class SplashActivity : Activity(), AnimationListener {
    private val mHandler = Handler()
    private var rl: RelativeLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rl = findViewById<View>(R.id.activity_main) as RelativeLayout
        // Intent intent = new Intent(this,MainActivity.class);
        // startActivity(intent);
        //創建閃屏界面的動畫
        val animation = createAnimation()
        rl!!.startAnimation(animation)
        animation.setAnimationListener(this)
    }

    private fun createAnimation(): Animation {
        val animationSet = AnimationSet(false)
        //旋转
        val rotate = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 2000
        val alpha = AlphaAnimation(0f, 1f)
        alpha.duration = 2000
        val scale = ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scale.duration = 2000
        animationSet.addAnimation(rotate)
        animationSet.addAnimation(alpha)
        animationSet.addAnimation(scale)
        return animationSet
    }

    override fun onAnimationStart(animation: Animation) {}
    override fun onAnimationEnd(animation: Animation) {
        //要进行延迟跳转
        mHandler.postDelayed(MyTask(), 500)
    }

    override fun onAnimationRepeat(animation: Animation) {}
    inner class MyTask : Runnable {
        override fun run() {
            val hasGuide = SPUtils.getBoolean(this@SplashActivity, Constant.KEY_HAS_GUIDE, false)
            if (hasGuide) {
                //sp中进行过向导的演示,就直接进入home界面中
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(applicationContext, GuideActivity::class.java)
                startActivity(intent)
            }
            finish()
        }
    }
}