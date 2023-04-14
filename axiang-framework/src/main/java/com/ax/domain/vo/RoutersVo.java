package com.ax.domain.vo;

import com.ax.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * className: RoutersVo
 * description:
 *
 * @author: axiang
 * date: 2023/4/11 0011 22:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutersVo {

    private List<Menu> menus;
}
