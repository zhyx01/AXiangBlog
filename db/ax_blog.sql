create table ax_article
(

    id            bigint(200) not null auto_increment,
    title         varchar(256)  default null comment '标题',
    content       longtext comment '文章内容',
    type          char(1)       default '2' comment '文章类型:1 文章 2 草稿',
    summary       varchar(1024) default null comment '文章摘要',
    category_id   bigint(20)    default null comment '所属分类id',
    thumbnail     varchar(256)  default null comment '缩略图',
    is_top        char(1)       default '0' comment '是否置顶(0否 1是)',
    status        char(1)       DEFAULT '1' COMMENT '状态(0已发布 1草稿)',
    comment_count int(11)       default '0' comment '评论数',
    view_count    bigint(200)   default '0' comment '访问量',
    is_comment    char(1)       default '1' comment '是否允许评论 1是 0否',
    create_by     bigint(20)    default null,
    create_time   datetime      default null,
    update_by     bigint(20)    default null,
    update_time   datetime      default null,
    del_flag      int(1)        default '0' comment '删除标志(0代表未删除, 1代表已删除)',
    primary key (id)
) engine = InnoDB
  auto_increment = 8
  default charset = utf8mb4 comment = '文章表';