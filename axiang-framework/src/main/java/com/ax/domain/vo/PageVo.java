package com.ax.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * className: PageVo
 * description: 分页
 *
 * @author: axiang
 * date: 2023/4/9 14:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {

    private List rows;
    private Long total;
}
