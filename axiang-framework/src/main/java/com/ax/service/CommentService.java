package com.ax.service;

import com.ax.domain.ResponseResult;
import com.ax.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-04-10 09:28:06
 */
public interface CommentService extends IService<Comment> {

    /**
     * description: 分页查根评论
     * @param articleId:
     * @param pageNum:
     * @param pageSize:
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    ResponseResult getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    /**
     * description: 添加评论
     * @param comment: 内容
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    ResponseResult addComment(Comment comment);
}
