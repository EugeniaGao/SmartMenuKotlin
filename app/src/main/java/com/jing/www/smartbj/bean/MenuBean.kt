package com.jing.www.smartbj.bean

import org.litepal.crud.DataSupport

class MenuBean : DataSupport {
var  id=0
   var menuName: String=""
   var menuType: String=""
   var menuDesc: String=""
   var menuImg: String=""
   var createDate: String=""
    constructor(
        id: Int,
        menuName: String,
        menuDesc: String,
        menuImg: String,
        createDate: String,
         menuType: String
    ) {
        this.id=id;
       this.menuName= menuName
       this.menuDesc = menuDesc
       this.menuImg = menuImg
       this.createDate= createDate
        this. menuType=menuType
    }
}