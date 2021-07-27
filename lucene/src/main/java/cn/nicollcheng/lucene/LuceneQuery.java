package cn.nicollcheng.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * 注意：
 *     若 lucene 为子模块时，配置启动工作空间（Working directory）为 $ModuleFileDir$
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/27 11:00.
 */
public class LuceneQuery {
    private static final String INDEX_PATH = System.getProperty("user.dir") + File.separator +"index";

    public static void main(String[] args) throws Exception {
        IndexSearcher indexSearcher = getIndexSearcher();
        // 查询所有
//        searchAll(indexSearcher);
        // 根据文件大小范围查询
//        searchByFileSize(indexSearcher);
        // 组合查询
        combinationQuery(indexSearcher);
    }

    /**
     * 组合查询
     *      1．MUST和MUST：取得连个查询子句的交集
     *      2．MUST和MUST_NOT：表示查询结果中不能包含MUST_NOT所对应得查询子句的检索结果
     *      3．SHOULD与MUST_NOT：连用时，功能同MUST和MUST_NOT
     *      4．SHOULD与MUST连用时，结果为MUST子句的检索结果,但是SHOULD可影响排序
     *      5．SHOULD与SHOULD：表示“或”关系，最终检索结果为所有检索子句的并集
     *      6．MUST_NOT和MUST_NOT：无意义，检索无结果
     */
    private static void combinationQuery(IndexSearcher indexSearcher) throws Exception {
        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();

        Query query1 = new TermQuery(new Term("fileName","what"));
        Query query2 = new TermQuery(new Term("fileName","the"));

        booleanQuery.add(query1, BooleanClause.Occur.MUST);
        booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
        System.out.println(booleanQuery);
        printResult(indexSearcher, booleanQuery.build());
        //关闭资源
        indexSearcher.getIndexReader().close();
    }

    private static void searchByFileSize(IndexSearcher indexSearcher) throws Exception {
        Query query = LongPoint.newRangeQuery("fileSize", 0L, 300000L);
        System.out.println(query);
        printResult(indexSearcher, query);
        //关闭资源
        indexSearcher.getIndexReader().close();
    }

    private static void searchAll(IndexSearcher indexSearcher) throws Exception {
        Query query = new MatchAllDocsQuery();
        System.out.println(query);
        printResult(indexSearcher, query);
        //关闭资源
        indexSearcher.getIndexReader().close();
    }

    /**
     * 创建IndexReader和IndexSearcher
     */
    public static IndexSearcher getIndexSearcher() throws Exception{
        // 第一步：创建一个Directory对象，也就是索引库存放的位置。
        Directory directory = FSDirectory.open(Paths.get(INDEX_PATH));
        // 第二步：创建一个indexReader对象，需要指定Directory对象。
        IndexReader indexReader;
        try {
            indexReader = DirectoryReader.open(directory);
        } catch (IOException e) {
            LuceneSimple.buildIndex();
            indexReader = DirectoryReader.open(directory);
        }
        // 第三步：创建一个indexsearcher对象，需要指定IndexReader对象
        return new IndexSearcher(indexReader);
    }
    /**
     * 执行查询的结果
     */
    public static void printResult(IndexSearcher indexSearcher, Query query)throws Exception{
        // 第五步：执行查询。
        TopDocs topDocs = indexSearcher.search(query, 10);
        // 第六步：返回查询结果。遍历查询结果并输出。
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            // 文件名称
            String fileName = document.get("fileName");
            System.out.println(fileName);
            // 文件内容
            String fileContent = document.get("fileContent");
            System.out.println(fileContent);
            // 文件路径
            String filePath = document.get("filePath");
            System.out.println(filePath);
            // 文件路径
            String fileSize = document.get("fileSize");
            System.out.println(fileSize);
            System.out.println("------------");
        }
    }
}
