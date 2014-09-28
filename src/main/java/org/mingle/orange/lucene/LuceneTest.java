/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.lucene;

import java.io.InputStreamReader;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class LuceneTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 被索引的文档
		Document doc = new Document();
		doc.add(new TextField("contents", 
				new InputStreamReader(LuceneTest.class.getResourceAsStream(""))));
	}

}
