package com.ax.service;

import com.ax.domain.entity.Menu;
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
}
