package com.jing.www.smartbj.pager

import android.view.LayoutInflater
import com.jing.www.smartbj.R
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jing.www.smartbj.adapter.MenuAdapter
import com.jing.www.smartbj.bean.MenuBean

/**
 * Created by Administrator on 2017/2/12.
 * 每一种的菜品展示 recycler 流布局
 */
class NewsCenterContentTabPager(  //添加布局,在构造时候就初始化
    var context: Context?,
    var newsCenterMenuBeanList: MutableList<MenuBean>?
) {
    var view: View
    private var rv_menu: RecyclerView? = null
    private var adapter: MenuAdapter? = null
//    var newsCenterMenuBeanList: MutableList<MenuBean>? = mutableListOf();

    private fun initView(): View {

        val view = LayoutInflater.from(context).inflate(R.layout.newscenter_content_tab, null)
        rv_menu = view.findViewById<View>(R.id.rv_memu_item) as RecyclerView
        initRecycleView(view);
        return view
    }

    private fun initRecycleView(view:View) {
        //2.找到布局管理器
        rv_menu?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//         newsCenterMenuBeanList = MenuManager.getAllMenus() as MutableList<MenuBean>?

        adapter = MenuAdapter(view.context, newsCenterMenuBeanList)
         rv_menu!!.adapter = adapter
    }

    companion object {
        private const val TAG = "tag"
    }

    init {
        view = initView()
    }
}