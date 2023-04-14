package com.ax.runner;

import com.ax.constans.SystemConstants;
import com.ax.domain.entity.Article;
import com.ax.mapper.ArticleMapper;
import com.ax.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * className: ViewCountRunner
 * description: 增加浏览量定时任务
 *
 * @author: axiang
 * date: 2023/4/11 0011 9:58
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {

        // 从数据库获取数据存入redis id viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(),
                        article -> {
                    return article.getViewCount().intValue();
                        }));

        // 存入redis
        redisCache.setCacheMap(SystemConstants.ARTICLE_VIEWCOUNT, viewCountMap);

    }
}
