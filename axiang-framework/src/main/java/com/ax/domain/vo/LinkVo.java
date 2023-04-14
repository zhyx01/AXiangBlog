package com.ax.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className: LinkVo
 * description:
 *
 * @author: axiang
 * date: 2023/4/9 15:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {

    private Long id;


    private String name;

    private String logo;

    private String description;
    //网站地址
    private String address;
}
