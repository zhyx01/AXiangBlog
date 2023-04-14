package com.ax.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className: TagListVo
 * description:
 *
 * @author: axiang
 * date: 2023/4/12 0012 13:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagListVo {

    private Long id;
    //标签名
    private String name;
    //备注
    private String remark;
}
