package com.ax.mapper;

import com.ax.domain.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-11 19:27:04
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * description: 根据用户id查询用户权限信息
     * @param id:
     * @return: List<String> <br>
     * date: 2023/4/11 0011 <br>
     */
    List<String> selectPermsByUserId(Long id);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<Long> selectMenuListByRoleId(Long roleId);
}
