package com.ax.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * className: AdminArticleListVo
 * description:
 *
 * @author: axiang
 * date: 2023/4/14 0014 14:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminArticleListVo {

    private Long id;
    //标题
    private String title;
    //文章内容
    private String content;
    //文章摘要
    private String summary;
    //所属分类id
    private Long categoryId;

    //缩略图
    private String thumbnail;
    //是否置顶（0否，1是）
    private String isTop;
    //状态（0已发布，1草稿）
    private String status;
    //访问量
    private Long viewCount;
    //是否允许评论 1是，0否
    private String isComment;
    // 标签(可能是多个)
    private Date createTime;
}
