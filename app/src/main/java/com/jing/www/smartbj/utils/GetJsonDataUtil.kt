package com.jing.www.smartbj.utils


import android.content.Context

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * 获取本地json数据
 */
class GetJsonDataUtil {
    fun getJson(context: Context?, fileName: String?): String {
        val stringBuffer = StringBuffer()
        try {
            val assetsManager = context!!.assets //使用这个，创建的file需要在assets文件夹下，不然会找不到,!!表示context为空直接抛出异常，？是如果是空的就不执行
            val bufferedReader = BufferedReader(InputStreamReader(assetsManager.open(fileName!!)))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuffer.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuffer.toString()
    }
}