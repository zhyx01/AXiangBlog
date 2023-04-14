package com.ax.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className: MenuListDto
 * description:
 *
 * @author: axiang
 * date: 2023/4/14 0014 16:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuListDto {

    // 状态
    private String status;
    //菜单名称
    private String menuName;
}
