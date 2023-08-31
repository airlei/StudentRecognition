package com.projects.modular.attendance.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.News;
import com.projects.modular.attendance.model.params.NewsParam;
import com.projects.modular.attendance.model.result.NewsResult;

/**
 * <p>
 * 新闻 服务类
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
public interface NewsService extends IService<News> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2022-04-22
     */
    void add(NewsParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2022-04-22
     */
    void delete(NewsParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2022-04-22
     */
    void update(NewsParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
    NewsResult findBySpec(NewsParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
    List<NewsResult> findListBySpec(NewsParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2022-04-22
     */
     LayuiPageInfo findPageBySpec(NewsParam param);

}
