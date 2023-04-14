package com.ax.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * className: ArticleVo
 * description:
 *
 * @author: axiang
 * date: 2023/4/9 13:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListVo {

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

}
