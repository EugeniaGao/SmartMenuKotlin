package com.jing.www.smartbj.adapter


import android.view.LayoutInflater
import android.view.ViewGroup

import com.jing.www.smartbj.R

import android.annotation.SuppressLint

import android.content.Context
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.jing.www.smartbj.bean.MenuBean


/**
 * Created by Administrator on 2017/2/7.
 */
class MenuAdapter
(var context: Context,var NewsCenterMenuBeanList: MutableList<MenuBean>?) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {
    private var selectedPosition = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //添加子条目布局,上下文的传入和数据的传入都需要调用者传入构造中
        val view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
        //将布局传给ViewHolder
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val viewHolder = holder as MyViewHolder?
        var menuBean = NewsCenterMenuBeanList?.get(position)
        viewHolder?.tvMenuName?.setText(menuBean?.menuName)
        viewHolder?.tvMenuTime?.setText(menuBean?.createDate)
        viewHolder?.descipt?.setText(menuBean?.menuDesc)
    }

    override fun getItemCount(): Int {
        return if (NewsCenterMenuBeanList != null) NewsCenterMenuBeanList!!.size else 0
    }

 class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var photo: ImageView
        var tvMenuName: TextView
        var tvMenuTime: TextView
        var descipt: TextView
        init {
            photo = view.findViewById<View>(R.id.iv_menu) as ImageView
            tvMenuName = view.findViewById<View>(R.id.menuName) as TextView
            descipt = view.findViewById<View>(R.id.descipt) as TextView
            tvMenuTime = view.findViewById<View>(R.id.createTime) as TextView
        }
    }


}