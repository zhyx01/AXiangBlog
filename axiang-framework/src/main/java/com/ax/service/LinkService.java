package com.ax.service;

import com.ax.domain.ResponseResult;
import com.ax.domain.entity.Link;
import com.ax.domain.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-04-09 15:42:32
 */
public interface LinkService extends IService<Link> {

    /**
     * description: 获取所有的友链
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/9 <br>
     */
    ResponseResult getAllLink();

    /**
     * description: 分页查询友链列表
     * @param link: 封装条件
     * @param pageNum:
     * @param pageSize:
     * @return: PageVo <br>
     * date: 2023/4/16 0016 <br>
     */
    PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize);
}
