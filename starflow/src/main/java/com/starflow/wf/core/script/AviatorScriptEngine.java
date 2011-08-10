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

import java.io.Reader;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import com.googlecode.aviator.AviatorEvaluator;

/**
 * Aviator 表达式解析引擎
 * 
 * @author  libinsong1204@gmail.com
 * @date    2011-1-18 上午11:03:34
 * @version 
 */
public class AviatorScriptEngine extends AbstractScriptEngine implements
		Compilable {
	
	//缓存编译结果
	private boolean cached = true; 
	private final AviatorScriptEngineFactory factory;
	
	public AviatorScriptEngine(AviatorScriptEngineFactory factory) {
		this.factory = factory;
	}

	@Override
	public CompiledScript compile(String script) throws ScriptException {
		return new CompiledAviatorScript(this, AviatorEvaluator.compile(script, cached));
	}

	@Override
	public CompiledScript compile(Reader script) throws ScriptException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Bindings createBindings() {
		return new SimpleBindings();
	}

	@Override
	public Object eval(String script, ScriptContext context)
			throws ScriptException {
		return compile(script).eval(context);
	}

	@Override
	public Object eval(Reader script, ScriptContext context)
			throws ScriptException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ScriptEngineFactory getFactory() {
		return this.factory;
	}

	public boolean isCached() {
		return cached;
	}

	public void setCached(boolean cached) {
		this.cached = cached;
	}
}
