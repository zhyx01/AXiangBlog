package com.ax.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className: AddTagDto
 * description:
 *
 * @author: axiang
 * date: 2023/4/12 0012 14:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTagDto {

    private String name;
    private String remark;
}
