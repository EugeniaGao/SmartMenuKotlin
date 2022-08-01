package com.jing.www.smartbj.utils


import android.widget.Toast

import android.content.Context


/**
 * Created by Apple on 2016/9/26.
 * 吐司的工具类
 */
object MyToast {
    fun show(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}