package com.jing.www.smartbj.utils


import android.util.Log


/**
 * 日志工具类
 * @author apple
 */
object MyLogger {
    //开关
    private const val flag = true //true 测试                 false  上线
    fun v(tag: String?, msg: String?) {
        if (flag) {
            Log.v(tag, msg)
        }
    }

    fun d(tag: String?, msg: String?) {
        if (flag) {
            Log.d(tag, msg)
        }
    }

    fun i(tag: String?, msg: String?) {
        if (flag) {
            Log.i(tag, msg)
        }
    }

    fun w(tag: String?, msg: String?) {
        if (flag) {
            Log.w(tag, msg)
        }
    }

    fun e(tag: String?, msg: String?) {
        if (flag) {
            Log.e(tag, msg)
        }
    }
}