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

package com.starflow.wf.engine.handle;

import com.starflow.wf.engine.ProcessEngineException;

/**
 * 环节异常策略：进入异常状态，等待人工干预
 * 执行业务逻辑出现异常，重新抛出InterruptStrategyException异常。
 * 
 * @author libinsong1204@gmail.com
 * @version 1.0
 */
@SuppressWarnings("serial")
public class InterruptStrategyException extends ProcessEngineException {
	
	public InterruptStrategyException() {
		this("");
	}

	public InterruptStrategyException(String msg) {
		super(msg);
	}

}
