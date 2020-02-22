/*
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
 * limitations under the License.
 */

package org.orange.util.expression;

import com.googlecode.aviator.AviatorEvaluator;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mingle
 */
public class AviatorTest {
    public static void aviator() throws NoSuchMethodException, IllegalAccessException {
        int[] a = new int[]{3, 4};
        Map<String, Object> env = new HashMap<>(4);
        env.put("a", a);

        AviatorEvaluator.execute("1 + 2 + 3");
        AviatorEvaluator.execute("a[1] + 100", env);
        AviatorEvaluator.execute("'a[1]=' + a[1]", env);

        // 求数组长度
        AviatorEvaluator.execute("count(a)", env);

        // 求数组总和
        AviatorEvaluator.execute("reduce(a, +, 0)", env);

        // 检测数组每个元素都在 0 <= e < 10 之间
        AviatorEvaluator.execute("seq.every(a, seq.and(seq.ge(0), seq.lt(10)))", env);

        // Lambda 求和
        AviatorEvaluator.execute("reduce(a, lambda(x,y) -> x + y end, 0)", env);

        // 导入 String 类实例方法作为自定义函数
        AviatorEvaluator.addInstanceFunctions("s", String.class);
        AviatorEvaluator.execute("s.indexOf('hello', 'l')");
        AviatorEvaluator.execute("s.replaceAll('hello', 'l', 'x')");

        // 导入静态方法作为自定义函数
        AviatorEvaluator.addStaticFunctions("sutil", StringUtils.class);
        AviatorEvaluator.execute("sutil.isBlank('hello')");
    }
}
