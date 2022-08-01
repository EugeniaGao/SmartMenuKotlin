package com.jing.www.smartbj


import android.app.Application

import android.content.Context


class MyApplication : Application() { //
    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
    }

    companion object {
        var instance: MyApplication? = null
            private set
        var context: Context? = null
            private set
    }
}