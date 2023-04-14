package com.ax.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className: ArticleListDto
 * description:
 *
 * @author: axiang
 * date: 2023/4/14 0014 14:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListDto {

    /** 文章标题 */
    private String title;
    /** 文章摘要 */
    private String summary;
}
