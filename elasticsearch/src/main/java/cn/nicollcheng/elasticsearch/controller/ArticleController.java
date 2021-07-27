package cn.nicollcheng.elasticsearch.controller;

import cn.nicollcheng.elasticsearch.common.Result;
import cn.nicollcheng.elasticsearch.entity.Article;
import cn.nicollcheng.elasticsearch.repository.ArticleRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ScrolledPage;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/27 15:21.
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final ElasticsearchOperations elasticsearchOperations;


    public ArticleController(ArticleRepository articleRepository, ElasticsearchRestTemplate elasticsearchRestTemplate, ElasticsearchOperations elasticsearchOperations) {
        this.articleRepository = articleRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    /**
     * 根据 id 获取
     * @param id Article id
     */
    @GetMapping("{id}")
    public Result findById(@PathVariable String id) {
        Optional<Article> article = articleRepository.findById(id);
        Result Result = new Result(true);
        Result.put("article", article.orElse(null));
        return Result;
    }

    /**
     * 根据 id 删除
     * @param id Article id
     */
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        // 根据 id 删除
        articleRepository.deleteById(id);
        return new Result(true, "删除成功");
    }

    /**
     * 新增 Article
     * @param article {@link Article} obj
     */
    @PostMapping
    public Result save(Article article) {
        // 新增或更新
        String verifyRes = verifySaveForm(article);
        article.setCreateTime(new Date());
        if (!StringUtils.isEmpty(verifyRes)) {
            return new Result(false, verifyRes);
        }

        if (StringUtils.isEmpty(article.getId())) {
            article.setCreateTime(new Date());
        }

        Article a = articleRepository.save(article);
        boolean res = a.getId() != null;
        return new Result(res, res ? "保存成功" : "");
    }

    /**
     * {@link Article} data verify
     * @param article {@link Article} data
     */
    private String verifySaveForm(Article article) {
        if (article == null || StringUtils.isEmpty(article.getTitle())) {
            return "标题不能为空";
        } else if (StringUtils.isEmpty(article.getContent())) {
            return "内容不能为空";
        }
        return null;
    }

    /**
     * 分页查询数据
     * @param currentPage 当前页码
     * @param limit 每页数据量
     */
    @GetMapping("list")
    public Result list(Integer currentPage, Integer limit) {
        if (currentPage == null || currentPage < 0 || limit == null || limit <= 0) {
            return new Result(false, "请输入合法的分页参数");
        }
        Result result = new Result(true);
        NativeSearchQuery query = new NativeSearchQuery(new BoolQueryBuilder());
        query.setPageable(PageRequest.of(currentPage, limit));

        // 方法1：使用 elasticsearchRestTemplate
        AggregatedPage<Article> articlePage = elasticsearchRestTemplate.queryForPage(query, Article.class);

        // 方法2：使用 elasticsearchOperations
//        Page<Article> articlePage = elasticsearchOperations.queryForPage(query, Article.class);

        result.put("count", articlePage.getTotalElements());
        result.put("articles", articlePage.getContent());
        return result;
    }

    /**
     * 滚动查询
     * @param scrollId 滚动id
     * @param size 查询数量
     */
    @GetMapping("scroll")
    public Result scroll(String scrollId, Integer size) {
        // 滚动查询 scroll api
        if (size == null || size <= 0) {
            return new Result(false, "请输入每页查询数");
        }
        NativeSearchQuery query = new NativeSearchQuery(new BoolQueryBuilder());
        query.setPageable(PageRequest.of(0, size));
        ScrolledPage<Article> searchHits = null;
        if (StringUtils.isEmpty(scrollId)) {
            // 开启一个滚动查询，设置该 scroll 上下文存在 60s
            // 同一个 scroll 上下文，只需要设置一次 query（查询条件）
            searchHits = elasticsearchRestTemplate.startScroll(60000, query, Article.class);
            if (searchHits instanceof AggregatedPageImpl) {
                scrollId = searchHits.getScrollId();
            }
        } else {
            // 继续滚动
            searchHits = elasticsearchRestTemplate.continueScroll(scrollId, 60000, Article.class);
        }

        List<Article> articles = searchHits.getContent();
        if (articles.size() == 0) {
            // 结束滚动
            elasticsearchRestTemplate.clearScroll(scrollId);
            scrollId = null;
        }

        if (scrollId == null) {
            return new Result(false, "已到末尾");
        } else {
            Result result = new Result(true);
            result.put("count", searchHits.getTotalElements());
            result.put("size", articles.size());
            result.put("articles", articles);
            result.put("scrollId", scrollId);
            return result;
        }

    }

}
