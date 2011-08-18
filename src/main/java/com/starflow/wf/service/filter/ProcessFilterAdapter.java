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

package com.starflow.wf.service.filter;

import com.starflow.wf.engine.event.AbstractFlowEvent;
import com.starflow.wf.engine.event.ActivityFinishEvent;
import com.starflow.wf.engine.event.ActivityStartEvent;
import com.starflow.wf.engine.event.ActivityTerminalEvent;
import com.starflow.wf.engine.event.ProcessFinishEvent;
import com.starflow.wf.engine.event.ProcessStartEvent;
import com.starflow.wf.engine.event.ProcessTerminalEvent;
import com.starflow.wf.engine.model.ActivityInst;
import com.starflow.wf.engine.model.ProcessInstance;

abstract public class ProcessFilterAdapter implements ProcessFilter {

	public void activityComplete(ActivityFinishEvent event) {
	}
	
	public void activityTerminal(ActivityTerminalEvent event) {
	}
	
	public void activityCreate(AbstractFlowEvent event, ActivityInst destActInst) {
	}

	public void activityStart(ActivityStartEvent event, ActivityInst destActInst) {
	}

	public void processCreate(ProcessInstance processInstance) {
	}

	public void processComplete(ProcessFinishEvent event) {
	}

	public void processStart(ProcessStartEvent event) {
	}

	public void processTerminal(ProcessTerminalEvent event) {
	}
}
