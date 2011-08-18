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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

/**
 * 
 * @author  libinsong1204@gmail.com
 * @date    2011-1-18 上午11:03:34
 * @version 
 */
public class AviatorScriptEngineFactory implements ScriptEngineFactory {
	
	private static final List<String> extensions = Collections.unmodifiableList(Arrays.asList(new String[] { }));
	private static final List<String> mimeTypes = Collections.unmodifiableList(Arrays.asList(new String[] { }));
	private static final List<String> names = Collections.unmodifiableList(Arrays.asList(new String[] { "Aviator", "aviator"}));

	@Override
	public String getEngineName() {
		return "Aviator";
	}

	@Override
	public String getEngineVersion() {
		return "1.0.1";
	}

	@Override
	public List<String> getExtensions() {
		return extensions;
	}

	@Override
	public String getLanguageName() {
		return "A high performance expression evaluator for java";
	}

	@Override
	public String getLanguageVersion() {
		return "1.0.1";
	}

	@Override
	public String getMethodCallSyntax(String obj, String m, String... args) {
		return null;
	}

	@Override
	public List<String> getMimeTypes() {
		return mimeTypes;
	}

	@Override
	public List<String> getNames() {
		return names;
	}

	@Override
	public String getOutputStatement(String toDisplay) {
		return toDisplay;
	}

	@Override
	public Object getParameter(String key) {
		return null;
	}

	@Override
	public String getProgram(String... statements) {
		return null;
	}

	@Override
	public ScriptEngine getScriptEngine() {
		return new AviatorScriptEngine(this);
	}

}
