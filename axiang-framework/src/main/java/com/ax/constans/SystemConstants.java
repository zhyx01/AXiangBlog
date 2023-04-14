package com.ax.constans;

/**
 * className: SystemContants
 * description:
 *
 * @author: axiang
 * date: 2023/4/9 11:55
 */
public class SystemConstants {
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常发布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    /**
     * 分类状态是正常的
     */
    public static final String CATEGORIES_STATUS_NORMAL = "0";

    /**
     * description: 友链状态为审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";

    /**
     * description: 根评论
     */
    public static final Long IS_ROOT_COMMENT = -1L;

    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为：友链评论
     */
    public static final String LINK_COMMENT = "1";

    /** 存入redis中的key */
    public static final String ARTICLE_VIEWCOUNT = "article:viewCount";
    /** 菜单 */
    public static final String MENU = "C";
    /** 按钮 */
    public static final String BUTTON = "F";

    /** 正常状态 */
    public static final String NORMAL = "0";

    /** 超级管理员 */
    public static final String ADMIN = "1";

}
