package com.ax.controller;

import com.ax.annotation.SystemLog;
import com.ax.domain.ResponseResult;
import com.ax.domain.dto.MenuListDto;
import com.ax.domain.entity.Menu;
import com.ax.domain.vo.MenuTreeVo;
import com.ax.domain.vo.MenuVo;
import com.ax.domain.vo.RoleMenuTreeSelectVo;
import com.ax.service.MenuService;
import com.ax.utils.SystemConverter;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @SystemLog(businessName = "新增菜单")
    public ResponseResult add(@RequestBody Menu menu) {
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    @GetMapping(value = "/{menuId}")
    @SystemLog(businessName = "修改菜单查询回显")
    public ResponseResult getInfo(@PathVariable Long menuId) {
        return ResponseResult.okResult(menuService.getById(menuId));
    }

    @PutMapping
    @SystemLog(businessName = "修改菜单")
    public ResponseResult edit(@RequestBody Menu menu) {
        if (menu.getId().equals(menu.getParentId())) {
            return ResponseResult.errorResult(500, "修改菜单'" + menu.getMenuName()
                    + "'失败，上级菜单不能选择自己");
        }
        menuService.updateById(menu);
        return ResponseResult.okResult();
    }

    /**
     * description: 能够删除菜单，但是如果要删除的菜单有子菜单则提示：存在子菜单不允许删除 并且删除失败
     * @param menuId: 菜单id
     * @return: ResponseResult <br>
     * date: 2023/4/15 0015 <br>
     */
    @DeleteMapping("/{menuId}")
    @SystemLog(businessName = "删除菜单")
    public ResponseResult remove(@PathVariable("menuId") Long menuId) {
        if (menuService.hasChild(menuId)) {
            return ResponseResult.errorResult(500,"存在子菜单不允许删除");
        }
        menuService.removeById(menuId);
        return ResponseResult.okResult();
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    @SystemLog(businessName = "添加角色 => 获取菜单下拉树列表")
    public ResponseResult treeselect() {
        //复用之前的selectMenuList方法。方法需要参数，参数可以用来进行条件查询，而这个方法不需要条件，所以直接new Menu()传入
        List<Menu> menus = menuService.selectMenuList(new Menu());
        List<MenuTreeVo> options =  SystemConverter.buildMenuSelectTree(menus);
        return ResponseResult.okResult(options);
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    @SystemLog(businessName = "加载对应角色菜单列表树")
    public ResponseResult roleMenuTreeSelect(@PathVariable("roleId") Long roleId) {
        List<Menu> menus = menuService.selectMenuList(new Menu());
        List<Long> checkedKeys = menuService.selectMenuListByRoleId(roleId);
        List<MenuTreeVo> menuTreeVos = SystemConverter.buildMenuSelectTree(menus);
        RoleMenuTreeSelectVo vo = new RoleMenuTreeSelectVo(checkedKeys,menuTreeVos);
        return ResponseResult.okResult(vo);
    }

}
