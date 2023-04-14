package com.ax.job;

import com.ax.constans.SystemConstants;
import com.ax.domain.entity.Article;
import com.ax.service.ArticleService;
import com.ax.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * className: UpdateViewCountJob
 * description: 定时更新mysql数据库里的浏览量
 *
 * @author: axiang
 * date: 2023/4/11 0011 10:40
 */
@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    /** cron表达式:
     *  秒（0~59），分钟（0~59），小时（0~23），日期（1-月最后一天），月份（1-12），星期几（1-7,1表示星期日），年份
     *  0/5 * * * * ? 每五秒更新一次
     */
    @Scheduled(cron = "* 0/10 * * * ?") // 每十分钟定时更新一次
    public void updateViewCountJob() {

        // 获取redis中存储的文章浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEWCOUNT);

        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry->new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        // 更新到数据库中 通过MP批量操作进行
        articleService.updateBatchById(articles);

    }
}
