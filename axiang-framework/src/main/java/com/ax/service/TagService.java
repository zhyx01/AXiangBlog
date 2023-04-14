package com.ax.service;

import com.ax.domain.ResponseResult;
import com.ax.domain.dto.TagListDto;
import com.ax.domain.entity.Tag;
import com.ax.domain.vo.PageVo;
import com.ax.domain.vo.TagVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-04-11 13:56:31
 */
public interface TagService extends IService<Tag> {

    /**
     * description: 分页查询标签列表
     * @param pageNum: 页码
     * @param pageSize: 每页显示大小
     * @param tagListDto: 封装查询条件, name, remark
     * @return: ResponseResult<PageVo> <br>
     * date: 2023/4/12 0012 <br>
     */
    ResponseResult<PageVo> getTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    /**
     * description: 写博文, 获取所有的标签列表, 显示在下拉框中
     * @param :
     * @return: List<TagVo> <br>
     * date: 2023/4/12 0012 <br>
     */
    List<TagVo> listAllTag();
}
