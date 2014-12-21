/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.rhino;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
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
		
//		JavaScriptDomainInner script = new JavaScriptDomainInner();		// error
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