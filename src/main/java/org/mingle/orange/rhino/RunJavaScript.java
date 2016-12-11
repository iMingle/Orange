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

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author mingle
 */
public class RunJavaScript {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        List<ScriptEngineFactory> list = factory.getEngineFactories();
        for (ScriptEngineFactory s : list) {
            System.out.println(s.getNames());
            // [nashorn, Nashorn, js, JS, JavaScript, javascript, ECMAScript, ecmascript]
        }
        
//        JavaScriptDomainInner script = new JavaScriptDomainInner();        // error
        JavaScriptDomain script = new JavaScriptDomain();
        engine.put("scriptDomain", script);
        
        try {
            engine.eval("print('Hello World!')");
            engine.eval("print(scriptDomain.say())");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

}

class JavaScriptDomainInner {
    private String name = "JavaScript";
    
    public String say() {
        return this.name;
    }
}
