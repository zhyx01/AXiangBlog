package com.ax.mapper;

import com.ax.domain.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 评论表(Comment)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-10 09:28:04
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
