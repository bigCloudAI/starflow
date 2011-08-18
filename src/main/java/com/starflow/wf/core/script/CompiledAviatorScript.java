/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.starflow.wf.core.script;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.googlecode.aviator.Expression;

/**
 * 
 * @author  libinsong1204@gmail.com
 * @date    2011-1-18 上午11:03:34
 * @version 
 */
public class CompiledAviatorScript extends CompiledScript {
	
	private final AviatorScriptEngine engine;
	private final Expression expression;
	
	CompiledAviatorScript(AviatorScriptEngine engine, Expression expression) {
	    this.engine = engine;
	    this.expression = expression;
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public Object eval(ScriptContext context) throws ScriptException {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			for (Iterator it = context.getScopes().iterator(); it.hasNext(); ) { 
				int scope = ((Integer)it.next()).intValue();
				Bindings bindings = context.getBindings(scope);
				Set keys = bindings.keySet();
				
				for(Object key : keys) {
					map.put((String)key, bindings.get(key));
				}
		    }
			return this.expression.execute(map);
	    } catch (Exception e) {
	      throw new ScriptException(e);
	    }
	}

	@Override
	public ScriptEngine getEngine() {
		return this.engine;
	}

}
