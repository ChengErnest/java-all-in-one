package cn.nicollcheng.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

import java.io.File;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/27 11:16.
 */
public class LuceneQueryParser {
    private static final String INDEX_PATH = System.getProperty("user.dir") + File.separator +"index";

    public static void main(String[] args) throws Exception {
        IndexSearcher indexSearcher = LuceneQuery.getIndexSearcher();
        // 条件解释的对象查询
//        queryParser(indexSearcher);
        // 多域查询
        multiFieldQueryParser(indexSearcher);
    }

    private static void multiFieldQueryParser(IndexSearcher indexSearcher) throws Exception {
        String[] fields = {"fileName","fileContent"};
        //参数1： 默认查询的域
        //参数2：采用的分析器
        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(fields,new StandardAnalyzer());
        // *:*   域：值
        Query query = queryParser.parse("lucene is apache");
        System.out.println(query);

        LuceneQuery.printResult(indexSearcher, query);
        //关闭资源
        indexSearcher.getIndexReader().close();
    }

    private static void queryParser(IndexSearcher indexSearcher) throws Exception {
        //参数1： 默认查询的域
        //参数2：采用的分析器
        QueryParser queryParser = new QueryParser("fileName",new StandardAnalyzer());
        // *:*   域：值
        Query query = queryParser.parse("fileName:What is the Lucene.txt OR fileContent:lucene is apache");
        LuceneQuery.printResult(indexSearcher, query);
        //关闭资源
        indexSearcher.getIndexReader().close();
    }
}
