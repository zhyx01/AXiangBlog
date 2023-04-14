package com.ax.service.impl;

import com.ax.domain.ResponseResult;
import com.ax.domain.dto.TagListDto;
import com.ax.domain.entity.Tag;
import com.ax.domain.vo.PageVo;
import com.ax.domain.vo.TagListVo;
import com.ax.domain.vo.TagVo;
import com.ax.mapper.TagMapper;
import com.ax.service.TagService;
import com.ax.utils.BeanCopyUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-04-11 13:56:31
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult<PageVo> getTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {

        // 条件
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()), Tag::getName, tagListDto.getRemark());
        // 分页查询
        Page<Tag> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        // 封装数据
        List<TagListVo> tagListVos = BeanCopyUtil.copyBeanList(page.getRecords(), TagListVo.class);
        PageVo pageVo = new PageVo(tagListVos, page.getTotal());
        // 返回
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public List<TagVo> listAllTag() {

        // 获取标签的id, name
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        // 只查询id和name属性
        queryWrapper.select(Tag::getId, Tag::getName);
        List<Tag> tags = list(queryWrapper);
        // 封装成vo
        List<TagVo> tagVos = BeanCopyUtil.copyBeanList(tags, TagVo.class);
        // 返回
        return tagVos;
    }

}
