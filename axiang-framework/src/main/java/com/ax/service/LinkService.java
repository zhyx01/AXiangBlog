package com.ax.service;

import com.ax.domain.ResponseResult;
import com.ax.domain.entity.Link;
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
}
