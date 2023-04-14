package com.ax.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className: TagListDto
 * description:
 *
 * @author: axiang
 * date: 2023/4/12 0012 13:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagListDto {

    // 标签名
    private String name;
    // 备注
    private String remark;
}
