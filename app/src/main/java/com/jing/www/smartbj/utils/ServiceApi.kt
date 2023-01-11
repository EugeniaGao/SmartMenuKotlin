package com.jing.www.smartbj.utils

import com.jing.www.smartbj.bean.NewsCenterTabBean
import retrofit2.http.POST
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers

/**
 * Created by Eugenia Gao on 2020/6/3.
 * Describe:使用Retrofit联网框架,网络接口
 */
interface ServiceApi {
    //登录接口
    @Headers("Content-Type: application/json", "Accept: application/json") //需要添加头
    @POST("appWxMini/login")
    fun getTopNews(@Body body: RequestBody?): Call<NewsCenterTabBean.TopNewsBean?>?

    companion object {
        const val base_url = "http://v.juhe.cn/toutiao/index"
    }
}