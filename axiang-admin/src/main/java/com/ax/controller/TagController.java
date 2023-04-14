package com.ax.controller;

import com.ax.annotation.SystemLog;
import com.ax.constans.SystemConstants;
import com.ax.domain.ResponseResult;
import com.ax.domain.dto.AddTagDto;
import com.ax.domain.dto.TagListDto;
import com.ax.domain.dto.UpdateTagDto;
import com.ax.domain.entity.Tag;
import com.ax.domain.vo.PageVo;
import com.ax.domain.vo.TagVo;
import com.ax.enums.AppHttpCodeEnum;
import com.ax.exception.SystemException;
import com.ax.service.TagService;
import com.ax.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * className: TagController
 * description:
 *
 * @author: axiang
 * date: 2023/4/11 0011 14:02
 */
@RestController
@RequestMapping("content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * description: 接口测试
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/12 0012 <br>
     */
    @RequestMapping("/test")
    public ResponseResult test() {
        return ResponseResult.okResult(tagService.list(null));
    }

    /**
     * description: 分页查询标签列表
     * @param pageNum: 页码
     * @param pageSize: 每页显示大小
     * @param tagListDto: 封装查询条件, name, remark
     * @return: ResponseResult<PageVo> <br>
     * date: 2023/4/12 0012 <br>
     */
    @GetMapping("/list")
    @SystemLog(businessName = "查询标签列表")
    public ResponseResult<PageVo> getTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {

        return tagService.getTagList(pageNum, pageSize, tagListDto);

    }

    /**
     * description: 添加一个标签
     * @param addTagDto: name, remark
     * @return: ResponseResult <br>
     * date: 2023/4/12 0012 <br>
     */
    @PostMapping
    @SystemLog(businessName = "添加同一个标签列表")
    public ResponseResult addTag(@RequestBody AddTagDto addTagDto) {
        // 判空
        if (!StringUtils.hasText(addTagDto.getName())) {
            throw new SystemException(AppHttpCodeEnum.TAG_NAME_NOT_NULL);
        }
        if (!StringUtils.hasText(addTagDto.getRemark())) {
            throw new SystemException(AppHttpCodeEnum.TAG_REMARK_NOT_NULL);
        }

        Tag tag = BeanCopyUtil.copyBean(addTagDto, Tag.class);
        tagService.save(tag);

        return ResponseResult.okResult();
    }

    /**
     * description: 删除标签
     * @param id: 标签id
     * @return: ResponseResult <br>
     * date: 2023/4/12 0012 <br>
     */
    @DeleteMapping("/{id}")
    @SystemLog(businessName = "删除标签")
    public ResponseResult deleteTag(@PathVariable Long id) {
        tagService.removeById(id);
        return ResponseResult.okResult();
    }

    /**
     * description: 修改之前查询信息回显
     * @param id: 标签id
     * @return: ResponseResult <br>
     * date: 2023/4/12 0012 <br>
     */
    @GetMapping(value = "/{id}")
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        Tag tag = tagService.getById(id);
        return ResponseResult.okResult(tag);
    }

    /**
     * description: 修改操作
     * @param updateTagDto: 标签信息
     * @return: ResponseResult <br>
     * date: 2023/4/12 0012 <br>
     */
    @PutMapping
    @SystemLog(businessName = "修改标签")
    public ResponseResult updateTag(@RequestBody UpdateTagDto updateTagDto) {
        Tag tag = BeanCopyUtil.copyBean(updateTagDto, Tag.class);
        tagService.updateById(tag);
        return ResponseResult.okResult();
    }

    /**
     * description: 写博文, 获取下拉框中的所有标签列表
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/12 0012 <br>
     */
    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}
