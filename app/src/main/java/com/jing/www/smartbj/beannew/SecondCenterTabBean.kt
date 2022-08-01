package com.jing.www.smartbj.beannew

/**
 * 聚合数据API的新闻
 */
class SecondCenterTabBean {
    var data: NewsCenterDataBean? = null
    var retcode = 0

    inner class NewsCenterDataBean {
        var countcommenturl: String? = null
        var more: String? = null
        var news: List<NewsBean>? = null
        var title: String? = null
        var topic: List<TopicBean>? = null
        var topnews: List<TopNewsBean>? = null
    }

    //轮播图
    inner class TopNewsBean {
        var comment = false
        var commentlist: String? = null
        var commenturl: String? = null
        var id: String? = null
        var pubdate: String? = null
        var title: String? = null
        var topimage: String? = null
        var type: String? = null
        var url: String? = null
    }

    inner class TopicBean {
        var description: String? = null
        var id: String? = null
        var listimage: String? = null
        var sort: String? = null
        var title: String? = null
        var url: String? = null
    }

    //新闻列表数据模型
    inner class NewsBean {
        var comment: String? = null
        var commentlist: String? = null
        var commenturl: String? = null
        var id: String? = null
        var listimage: String? = null
        var pubdate: String? = null
        var title: String? = null
        var type: String? = null
        var url: String? = null
    }
}