package com.ax.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className: CategoryVo
 * description:
 *
 * @author: axiang
 * date: 2023/4/9 13:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {

    private long id;
    private String name;

    //描述
    private String description;
}
