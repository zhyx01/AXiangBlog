package com.ax.service.impl;

import com.ax.constans.SystemConstants;
import com.ax.domain.ResponseResult;
import com.ax.domain.entity.Link;
import com.ax.domain.vo.LinkVo;
import com.ax.mapper.LinkMapper;
import com.ax.service.LinkService;
import com.ax.utils.BeanCopyUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-04-09 15:42:32
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {

        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();

        // 状态为已经审核通过的
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);

        List<Link> links = list(queryWrapper);

        // 封装
        List<LinkVo> linkVos = BeanCopyUtil.copyBeanList(links, LinkVo.class);

        // 返回
        return ResponseResult.okResult(linkVos);
    }
}
