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

package com.starflow.wf.engine.event;

import org.springframework.context.ApplicationContext;

import com.starflow.wf.engine.ProcessEngine;

/**
 * 
 * @author libinsong1204@gmail.com
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ProcessTerminalEvent extends AbstractFlowEvent {
	
	public ProcessTerminalEvent(ProcessEngine processEngine) {
		this(processEngine.getApplicationContext());
		this.setProcessEngine(processEngine);
	}

	private ProcessTerminalEvent(ApplicationContext source) {
		super(source);
	}
	
}
