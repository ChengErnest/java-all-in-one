package cn.nicollcheng.elasticsearch.repository;

import cn.nicollcheng.elasticsearch.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

}