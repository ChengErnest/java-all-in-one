package cn.nicollcheng.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
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
 *
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/26 17:42.
 * <p>
 *     为 txt 文件夹中的文本文件创建索引，然后进行查找，
 *     凡是文件名或文件内容包括关键字“ 索引  ”的文件都要找出来
 * </p>
 */
public class LuceneSimple {

    private static final String TXT_PATH = System.getProperty("user.dir") + File.separator +"txt";
    private static final String INDEX_PATH = System.getProperty("user.dir") + File.separator +"index";


    public static void main(String[] args) throws IOException {
        buildIndex();
        search("fileName", "how");
        // 删除 fileContent 为 lucene 的索引
        deleteIndex("fileName", "how");
        System.out.println("指定索引已删除");
        search("fileName", "how");

        deleteAllIndex();
    }


    /**
     * 搜索
     */
    private static void search(String field, String text) throws IOException {
        // 第一步：创建一个Directory对象，也就是索引库存放的位置。
        Directory directory = FSDirectory.open(Paths.get(INDEX_PATH));
        // 第二步：创建一个indexReader对象，需要指定Directory对象。
        IndexReader indexReader = DirectoryReader.open(directory);
        // 第三步：创建一个indexSearcher对象，需要指定IndexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // 第四步：创建一个TermQuery对象，指定查询的域和查询的关键词。
        Query query = new TermQuery(new Term(field, text));
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
            // 文件大小
            String fileSize = document.get("fileSize");
            System.out.println(fileSize);
            // 文件路径
            String filePath = document.get("filePath");
            System.out.println(filePath);
            System.out.println("------------");
        }
        // 第七步：关闭IndexReader对象
        indexReader.close();
    }

    /**
     * 删除指定的索引
     * @param field 索引域
     * @param text 索引值
     * @throws IOException
     */
    private static void deleteIndex(String field, String text) throws IOException {
        /*
         * 第一步：创建一个indexWriter对象
         * 1指定索引库的存放位置Directory对象
         * 2指定一个分析器，对文档内容进行分析。
         */
        // 保存到硬盘上
        Directory directory = FSDirectory.open(Paths.get(INDEX_PATH));

        // 官方推荐分词器，对中文不友好
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);

        Query query = new TermQuery(new Term(field, text));
        indexWriter.deleteDocuments(query);
        indexWriter.close();
    }

    /**
     * 删除所有索引
     */
    private static void deleteAllIndex() throws IOException {
        // 第一步：创建一个indexWriter对象。
        Directory directory = FSDirectory.open(Paths.get(INDEX_PATH));
        // 官方推荐
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);
        indexWriter.deleteAll();
        indexWriter.close();
    }
    /**
     * 创建索引
     */
    public static void buildIndex() throws IOException {
        /*
         * 第一步：创建一个indexWriter对象
         * 1指定索引库的存放位置Directory对象
         * 2指定一个分析器，对文档内容进行分析。
         */
        // 保存到硬盘上
        Directory directory = FSDirectory.open(Paths.get(INDEX_PATH));

        // 官方推荐分词器，对中文不友好
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);

        // 第二步：通过IO读取磁盘上的文件信息
        File f = new File(TXT_PATH);
        File[] listFiles = f.listFiles();
        if(listFiles != null){
            for (File file : listFiles) {
                if(file.isFile()){
                    // 第三步：创建document对象, 并把文件信息添加到document对象中
                    Document document = new Document();
                    // 文件名称
                    String file_name = file.getName();
                    Field fileNameField = new TextField("fileName", file_name, Field.Store.YES);
                    // 文件路径
                    String file_path = file.getPath();
                    Field filePathField = new StoredField("filePath", file_path);
                    // 文件大小
                    long file_size = FileUtils.sizeOf(file);
                    //索引
                    Field fileSizeField1 = new LongPoint("fileSize", file_size);
                    //存储
                    Field fileSizeField2 = new StoredField("fileSize", file_size);

                    // 文件内容
                    String file_content = FileUtils.readFileToString(file, "UTF-8");
                    Field fileContentField = new TextField("fileContent", file_content, Field.Store.NO);

                    document.add(fileNameField);
                    document.add(fileSizeField1);
                    document.add(fileSizeField2);
                    document.add(filePathField);
                    document.add(fileContentField);
                    // 第四步：使用indexWriter对象将document对象写入索引库，此过程进行索引创建。并将索引和document对象写入索引库。
                    indexWriter.addDocument(document);
                }
            }
            // 第五步：关闭IndexWriter对象。
            indexWriter.close();
        }
    }
}
