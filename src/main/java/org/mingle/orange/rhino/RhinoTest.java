/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
 */

package org.mingle.orange.rhino;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * JavaScript脚本引擎测试
 * 
 * @author Mingle
 */
public class RhinoTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Context context = Context.enter();                                        // 获取环境设置
        Scriptable scriptable = context.initStandardObjects();                    // 初始化本地对象
        scriptable.put("x", scriptable, new Integer(4));                        // 输入参数设置
        scriptable.put("y", scriptable, new Integer(6));
        
        context.evaluateString(scriptable, "var result = x + y", "", 1, null);    // 执行
        System.out.println(scriptable.get("result", scriptable));                // 10.0
        
        Reader in = new InputStreamReader(RhinoTest.class.getResourceAsStream("/documents/rhino/rhino.js"));
        try {
            context.evaluateReader(scriptable, in, "read file", 1, null);
            System.out.println(scriptable.get("sum", scriptable));                // 25.0
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Context.exit();
    }

}
