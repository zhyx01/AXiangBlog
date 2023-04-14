package com.ax.service.impl;

import com.ax.constans.SystemConstants;
import com.ax.domain.ResponseResult;
import com.ax.domain.entity.Comment;
import com.ax.domain.vo.CommentVo;
import com.ax.domain.vo.PageVo;
import com.ax.enums.AppHttpCodeEnum;
import com.ax.exception.SystemException;
import com.ax.mapper.CommentMapper;
import com.ax.service.CommentService;
import com.ax.service.UserService;
import com.ax.utils.BeanCopyUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-04-10 09:28:06
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {

        // 查询对应的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper();
        // 根据articleId查询对应的文章
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType), Comment::getArticleId, articleId);

        // 判断rootId是否为-1(该评论是否为根评论)
        queryWrapper.eq(Comment::getRootId, SystemConstants.IS_ROOT_COMMENT);

        // 评论类型
        queryWrapper.eq(Comment::getType, commentType);

        // 分页查询
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        // 封装vo
        List<CommentVo> commentVos = toCommentVoList(page.getRecords());

        // 根据根评论的id查询对应的子评论, 并赋值给对应的属性
        for (CommentVo commentVo : commentVos) {
            // 查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            // 赋值
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVos, page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        // 对内容进行处理 ==> 敏感词, 是否为空
        // 判断是否为空
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    /**
     * description: 根据根评论id获取子评论,
     * 并对username和toCommentUserName赋值
     * @param id: 根评论id
     * @return: List<CommentVo> <br>
     * date: 2023/4/10 0010 <br>
     */
    private List<CommentVo> getChildren(Long id) {

        // 查询
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Comment::getRootId, id);

        queryWrapper.orderByAsc(Comment::getCreateTime);

        List<Comment> comments = list(queryWrapper);

        List<CommentVo> commentVos = toCommentVoList(comments);

        return commentVos;
    }

    /**
     * description: 将comment转换成commentVo,
     * 对username和toCommentUserName进行赋值
     * @param list: comment集合
     * @return: List<CommentVo> <br>
     * date: 2023/4/10 0010 <br>
     */
    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtil.copyBeanList(list, CommentVo.class);

        for (CommentVo commentVo : commentVos) {

            // 根据createBy查询评论发表用户的昵称
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);

            // 根据toCommentUserId查询评论的 是谁的评论的昵称
            // 首先判断是否为-1, 为-1是根评论, 则不需要赋值
            if (commentVo.getToCommentUserId() != -1) {
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }
}
