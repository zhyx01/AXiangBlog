package com.ax.controller;

import com.ax.annotation.SystemLog;
import com.ax.domain.ResponseResult;
import com.ax.domain.dto.MenuListDto;
import com.ax.domain.entity.Menu;
import com.ax.domain.vo.MenuVo;
import com.ax.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * className: MenuController
 * description: 菜单管理
 *
 * @author: axiang
 * date: 2023/4/14 0014 16:02
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    @SystemLog(businessName = "菜单列表")
    public ResponseResult getSystemMenuList(MenuListDto menuListDto) {
        List<MenuVo> menuVos = menuService.getAllList(menuListDto);
        return ResponseResult.okResult(menuVos);
    }
}
