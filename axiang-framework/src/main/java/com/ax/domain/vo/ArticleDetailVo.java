package com.ax.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * className: ArticleDetailVo
 * description:
 *
 * @author: axiang
 * date: 2023/4/9 15:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {

    //所属分类名称
    private String categoryName;

    private Date createTime;

    private Long id;
    //标题
    private String title;
    //缩略图
    private String thumbnail;
    //评论数
    private Integer commentCount;
    //访问量
    private Long viewCount;

    // 分类id
    private Long categoryId;

    //文章内容
    private String content;

}
