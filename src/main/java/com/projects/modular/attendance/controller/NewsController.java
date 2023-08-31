package com.projects.modular.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.News;
import com.projects.modular.attendance.model.params.NewsParam;
import com.projects.modular.attendance.service.NewsService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;


/**
 * 新闻控制器
 *
 * @author demo
 * @Date 2022-04-22 10:30:44
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

    private String PREFIX = "/modular/news";

    @Autowired
    private NewsService newsService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/news.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/news_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/news_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(NewsParam newsParam) {
        this.newsService.add(newsParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(NewsParam newsParam) {
        this.newsService.update(newsParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(NewsParam newsParam) {
        this.newsService.delete(newsParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author demo
     * @Date 2022-04-22
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(NewsParam newsParam) {
        News detail = this.newsService.getById(newsParam.getNewsId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author demo
     * @Date 2022-04-22
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(NewsParam newsParam) {
        return this.newsService.findPageBySpec(newsParam);
    }

}


