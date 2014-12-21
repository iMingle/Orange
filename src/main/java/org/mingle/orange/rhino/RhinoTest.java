/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.rhino;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * JavaScript脚本引擎测试
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class RhinoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Context context = Context.enter();										// 获取环境设置
		Scriptable scriptable = context.initStandardObjects();					// 初始化本地对象
		scriptable.put("x", scriptable, new Integer(4));						// 输入参数设置
		scriptable.put("y", scriptable, new Integer(6));
		
		context.evaluateString(scriptable, "var result = x + y", "", 1, null);	// 执行
		System.out.println(scriptable.get("result", scriptable));				// 10.0
		
		Reader in = new InputStreamReader(RhinoTest.class.getResourceAsStream("/documents/rhino/rhino.js"));
		try {
			context.evaluateReader(scriptable, in, "read file", 1, null);
			System.out.println(scriptable.get("sum", scriptable));				// 25.0
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Context.exit();
	}

}
