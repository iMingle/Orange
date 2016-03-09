/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.lucene;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @since 1.0
 * @author Mingle
 */
public class LuceneTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 被索引的文档
        Document doc = new Document();
        // 存放lucene文件信息
        Directory directory = null;
        doc.add(new TextField("content", 
                new InputStreamReader(LuceneTest.class.getResourceAsStream(
                        "/documents/lucene/novel.txt"))));
        // 用来对查询语句进行词法分析和语言处理
//        Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new SmartChineseAnalyzer();
        // IndexWriter的配置文件
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, analyzer);
        try {
            StringBuilder builder = new StringBuilder((LuceneTest.class.getResource("/").getFile().toString()
                    + "documents/lucene/directory").substring(1));
            directory = FSDirectory.open(new File(builder.insert(2, "/").toString()));
            // 用来写索引文件
            IndexWriter writer = new IndexWriter(directory, conf);
            
            writer.addDocument(doc);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            // 用来读索引文件
            IndexReader reader = DirectoryReader.open(directory);
            // 搜索索引
            IndexSearcher searcher = new IndexSearcher(reader);
            // 词
            Term term = new Term("content", "小");
            // 词查询
            TermQuery query = new TermQuery(term);
            // 存储查询结果
            TopDocs topDocs = searcher.search(query, 3);
            
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            
            for (ScoreDoc scoreDoc : scoreDocs) {
                System.out.println(scoreDoc);
            }
            
            System.out.println(topDocs.getMaxScore());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
