package com.jing.www.smartbj.bean

import org.litepal.crud.DataSupport

object MenuManager {


    fun getMenu(menuId: Int?):  MenuBean? {
        val userList: List<MenuBean> =
            DataSupport.where("id = \"" +  menuId+ "\"")
                .find(
                    MenuBean::class.java
                )
        return if (userList != null && userList.size > 0) {
            userList[0]
        } else {
            null
        }
    }
    fun getMenuByType(menutype: String?):  List<MenuBean?>? {
        val userList: List<MenuBean> =
            DataSupport.where("menutype = \"" +  menutype+ "\"")
                .find(
                    MenuBean::class.java
                )
        return if (userList != null && userList.size > 0) {
            userList
        } else {
            null
        }
    }

    fun getAllMenus(): List<MenuBean?>? {
        val userList: List<MenuBean?> = DataSupport.findAll(
            MenuBean::class.java
        )
        return if (userList != null && userList.size > 0) {
            userList
        } else {
            null
        }
    }

    fun addMenu(id: Int, menuName: String, menuDesc: String, menuImg: String, createDate: String, menuType: String): Boolean {
        val menu = MenuBean(id, menuName, menuDesc, menuImg, createDate,menuType)
        return menu.save()
    }


    fun deleteMenu(menuId: Int) {
        DataSupport.delete(MenuBean::class.java, (menuId + 1).toLong())
    }


    fun updateMenu(menuId: Int, menuName: String, menuDesc: String, menuImg: String, createDate: String): Boolean {
        var menu: MenuBean? = getMenu(menuId)
        return if (menu != null) {
           menu.menuName= menuName
           menu.menuDesc= menuDesc
           menu.menuImg= menuImg
           menu.createDate= createDate

            menu.saveOrUpdate("id = \"" +menuId + "\"")
        } else {
            false
        }
    }
    fun getMenuCount(): Int {
        return DataSupport.count(MenuBean::class.java)
    }
    fun initializeDefaultMenus(): Boolean { //没用到
        var menu: MenuBean

           for (i in  0..5) {
            menu = MenuBean(i , "宫保鸡丁", "美味佳肴", "1111","20230506","炒菜")
               menu.save()
           }
        return true
    }
}