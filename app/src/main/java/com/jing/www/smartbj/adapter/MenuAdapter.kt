package com.jing.www.smartbj.adapter


import android.view.LayoutInflater
import android.view.ViewGroup

import com.jing.www.smartbj.R
import com.jing.www.smartbj.activity.MainActivity

import android.annotation.SuppressLint

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.jing.www.smartbj.bean.NewsCenterBean


/**
 * Created by Administrator on 2017/2/7.
 */
class MenuAdapter
//private NewsCenterBean.NewsCenterMenuBean newsCenterMenuBean;
(var context: Context,var NewsCenterMenuBeanList: List<NewsCenterBean.NewsCenterMenuBean>?) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {
    private var selectedPosition = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //添加子条目布局,上下文的传入和数据的传入都需要调用者传入构造中
        val view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
        //将布局传给ViewHolder
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val viewHolder = holder as MyViewHolder?
      /*  val newsCenterMenuBean = NewsCenterMenuBeanList!![position]
        viewHolder!!.tvMenuTitle.text = newsCenterMenuBean.title
        if (selectedPosition == position) {
            viewHolder.ivArrow.setImageResource(R.drawable.menu_arr_select)
            viewHolder.tvMenuTitle.setTextColor(Color.RED)
        } else {
            viewHolder.ivArrow.setImageResource(R.drawable.menu_arr_normal)
            viewHolder.tvMenuTitle.setTextColor(Color.WHITE)
        }*/
     /*   viewHolder.itemView.setOnClickListener { item点击事件
            if (selectedPosition != position) {
                selectedPosition = position
                notifyDataSetChanged()
                //修改对应的标题
                val baseFragment = (context as MainActivity).currentTabFragment
                baseFragment.setTitle(newsCenterMenuBean.title) //侧边栏的点击事件
            }
            (context as MainActivity).slidingMenu!!.toggle()
        }*/
    }

    override fun getItemCount(): Int {
//        return if (NewsCenterMenuBeanList != null) NewsCenterMenuBeanList!!.size else 0
        return 5;
    }

 class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var photo: ImageView
        var tvMenuName: TextView
        var tvMenuTime: TextView
        init {
            photo = view.findViewById<View>(R.id.iv_menu) as ImageView
            tvMenuName = view.findViewById<View>(R.id.menuName) as TextView
            tvMenuTime = view.findViewById<View>(R.id.createTime) as TextView
        }
    }


}