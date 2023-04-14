package com.ax.controller;

import com.ax.constans.SystemConstants;
import com.ax.domain.ResponseResult;
import com.ax.domain.dto.AddCommentDto;
import com.ax.domain.entity.Comment;
import com.ax.service.CommentService;
import com.ax.utils.BeanCopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * className: CommentController
 * description: 评论
 *
 * @author: axiang
 * date: 2023/4/10 0010 9:55
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论", description = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * description: 查询根评论
     * @param articleId: 文章id
     * @param pageNum: 页码
     * @param pageSize: 大小
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    @GetMapping("/commentList")
    public ResponseResult getCommentList(Long articleId, Integer pageNum, Integer pageSize) {

        return commentService.getCommentList(SystemConstants.ARTICLE_COMMENT, articleId, pageNum, pageSize);
    }

    /**
     * description: 添加一条评论
     * @param addCommentDto: 评论dto对象
     * @return: ResponseResult <br>
     * date: 2023/4/11 0011 <br>
     */
    @PostMapping
    @ApiOperation(value = "添加评论", notes = "添加一条评论")
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto) {
        Comment comment = BeanCopyUtil.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表", notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示大小")
    })
    public ResponseResult getLinkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.getCommentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }
}
