package com.projects.modular.attendance.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 新闻
 * </p>
 *
 * @author demo
 * @since 2022-04-22
 */
@Data
public class NewsParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long newsId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 作者
     */
    private String author;

    private String pic;

    @Override
    public String checkParam() {
        return null;
    }

}
