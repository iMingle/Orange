/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

import java.util.List;

/**
 * 搜索引擎最短摘要的生成
 * 
 * @since 1.8
 * @author Mingle
 */
public class SearchDigestGenerate {
	/**
	 * 目标网页的分词结果
	 */
	private final List<String> target;
	/**
	 * 搜索关键字
	 */
	@SuppressWarnings("unused")
	private final List<String> search;
	
	public SearchDigestGenerate(List<String> target, List<String> search) {
		this.target = target;
		this.search = search;
	}

	public String generateDigest(List<String> search) {
		if (search == null) return "";
		if (search.size() == 0) return "";
		
		StringBuilder result = new StringBuilder();
		int targetLength = target.size() + 1;	// 设置目标长度为总长度+1
		int begin = 0;	// 初始指针
		int end = 0;	// 结束指针
		int length = target.size();
		int digestBegin = 0;	// 目标摘要的起始位置
		int digestEnd = 0;	// 目标摘要的结束位置
		
		while (true) {
			// 假设包含所有的关键字,并且没有越界
			while (!isAllExisted(search) && end < length)
				end++;
			// 假设找到一段包含所有关键字信息的字符串
			while (isAllExisted(search)) {
				if (end - begin < targetLength) {
					targetLength = end - begin;
					digestBegin = begin;
					digestEnd = end - 1;
				}
				begin++;
			}
			
			if (end >= length)
				break;
		}
		
		for (int i = digestBegin; i < digestEnd; i++)
			result.append(target.get(i));
		
		return result.toString();
	}

	/**
	 * 是否包含所有的搜索关键字
	 * 
	 * @param search
	 * @return
	 */
	private boolean isAllExisted(List<String> search) {
		return target.containsAll(search);
	}
}