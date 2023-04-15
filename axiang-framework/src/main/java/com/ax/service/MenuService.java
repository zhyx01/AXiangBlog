package com.ax.service;

import com.ax.domain.dto.MenuListDto;
import com.ax.domain.entity.Menu;
import com.ax.domain.vo.MenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-04-11 19:27:07
 */
public interface MenuService extends IService<Menu> {

    /**
     * description: 根据用户id查询权限信息
     * @param id: 用户id
     * @return: List<String> <br>
     * date: 2023/4/11 0011 <br>
     */
    List<String> selectPermsByUserId(Long id);

    /**
     * description: 根据用户id查询路由
     * @param userId: 用户id
     * @return: List<Menu> <br>
     * date: 2023/4/11 0011 <br>
     */
    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    /**
     * description: 查询菜单列表
     * @param menuListDto: 封装条件
     * @return: List<Menu> <br>
     * date: 2023/4/14 0014 <br>
     */
    List<MenuVo> getAllList(MenuListDto menuListDto);

    /**
     * description: 删除菜单
     * @param menuId:
     * @return: boolean <br>
     * date: 2023/4/15 0015 <br>
     */
    boolean hasChild(Long menuId);

    /**
     * description: 添加角色, 查询菜单列表选项
     * @param menu:
     * @return: List<Menu> <br>
     * date: 2023/4/16 0016 <br>
     */
    List<Menu> selectMenuList(Menu menu);
}
