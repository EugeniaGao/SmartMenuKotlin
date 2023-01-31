package com.jing.www.smartbj


import android.app.Application
import android.content.Context
import com.jing.www.smartbj.bean.MenuManager
import org.litepal.LitePal


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
        LitePal.initialize(this)
        MenuManager.initializeDefaultMenus()
    }

    companion object {
        var instance: MyApplication? = null
            private set
        var context: Context? = null
            private set
    }
}