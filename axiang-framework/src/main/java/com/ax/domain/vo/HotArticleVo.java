package com.ax.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className: HotArticleVo
 * description:
 *
 * @author: axiang
 * date: 2023/4/9 11:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {

    private Long id;
    //标题
    private String title;
    //访问量
    private Long viewCount;
}
