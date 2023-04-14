package com.ax.service.impl;

import com.ax.domain.entity.ArticleTag;
import com.ax.mapper.ArticleTagMapper;
import com.ax.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2023-04-12 18:48:44
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
