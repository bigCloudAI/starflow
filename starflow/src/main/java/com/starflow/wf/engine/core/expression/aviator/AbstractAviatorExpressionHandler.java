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

package com.starflow.wf.engine.core.expression.aviator;

import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.starflow.wf.engine.ScriptEngineConstants;
import com.starflow.wf.engine.core.expression.IExpressionHandler;
import com.starflow.wf.engine.core.expression.RuntimeExpressionException;
import com.starflow.wf.engine.model.elements.TransitionXml;

/**
 * 
 * @author  libinsong1204@gmail.com
 * @date    2011-1-18 上午11:03:34
 * @version 
 */
abstract public class AbstractAviatorExpressionHandler implements IExpressionHandler {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean execute(ScriptEngineManager engineManager,
			TransitionXml transition, Map<String, Object> conditions) {
		ScriptEngine engine = engineManager.getEngineByName(ScriptEngineConstants.AVIATOR);
		
		//创建执行表达式
		String expression = buildExpression(transition, conditions);
		
		if(logger.isDebugEnabled())
			logger.debug("Transition Expressopn：{} Start", expression);
		Bindings variables= engine.createBindings();
		if(conditions != null) {
			for (Map.Entry<String, Object> m : conditions.entrySet()) {   
				variables.put(m.getKey(), m.getValue());
				
				if(logger.isDebugEnabled())
					logger.debug("    Transition Condition：{} = {}", m.getKey(), m.getValue());
			}
		}
		if(logger.isDebugEnabled())
			logger.debug("Transition Expressopn End");
		
		try {
			Object result = engine.eval(expression, variables);
			if(result instanceof Boolean && (Boolean)result) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			throw new RuntimeExpressionException("环节分支线表达式【"+expression+"】解析不正确", e);
		}
	}

	abstract public String buildExpression(TransitionXml transition, Map<String, Object> conditions);
}
