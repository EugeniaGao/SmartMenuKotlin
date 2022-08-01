package com.jing.www.smartbj.utils


import android.content.Context


object SPUtils {
    private const val name = "config"
    private const val mode = Context.MODE_PRIVATE

    /**
     * 保存首选项
     * @param context
     * @param key
     * @param value
     */
    fun saveBoolean(context: Context, key: String?, value: Boolean) {
        val sp = context.getSharedPreferences(name, mode)
        val edit = sp.edit()
        edit.putBoolean(key, value)
        edit.commit()
    }

    fun saveInt(context: Context, key: String?, value: Int) {
        val sp = context.getSharedPreferences(name, mode)
        val edit = sp.edit()
        edit.putInt(key, value)
        edit.commit()
    }

    fun saveString(context: Context, key: String?, value: String?) {
        val sp = context.getSharedPreferences(name, mode)
        val edit = sp.edit()
        edit.putString(key, value)
        edit.commit()
    }

    /**
     * 获取首选项
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    fun getBoolean(context: Context, key: String?, defValue: Boolean): Boolean {
        val sp = context.getSharedPreferences(name, mode)
        return sp.getBoolean(key, defValue)
    }

    fun getInt(context: Context, key: String?, defValue: Int): Int {
        val sp = context.getSharedPreferences(name, mode)
        return sp.getInt(key, defValue)
    }

    fun getString(context: Context, key: String?, defValue: String?): String? {
        val sp = context.getSharedPreferences(name, mode)
        return sp.getString(key, defValue)
    }
}