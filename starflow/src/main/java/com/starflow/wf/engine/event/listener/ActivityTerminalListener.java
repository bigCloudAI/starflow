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

package com.starflow.wf.engine.event.listener;

import java.util.Date;

import com.starflow.wf.engine.ProcessEngine;
import com.starflow.wf.engine.StarFlowState;
import com.starflow.wf.engine.event.ActivityTerminalEvent;
import com.starflow.wf.engine.model.ActivityInst;
import com.starflow.wf.service.filter.ProcessFilter;

/**
 * 
 * @author libinsong1204@gmail.com
 * @version 1.0
 */
public class ActivityTerminalListener extends AbstractProcessListener {
	
	@Override
	public void activityTerminal(ActivityTerminalEvent event) {
		ProcessEngine processEngine = event.getProcessEngine();
		ActivityInst activityInst = ((ActivityTerminalEvent)event).getActivityInst();
		
		Date finalTime = new Date();
		event.getWorkItemRep().updateActWorkItemStateAndFinalTime(activityInst.getActivityInstId(), StarFlowState.WORKITEM_STOPPED, finalTime);
		event.getActInstRep().updateActivityStateAndFinalTime(activityInst.getActivityInstId(), StarFlowState.ACT_INST_STOPPED, finalTime);
		
		//执行环节终止filter
		for(ProcessFilter filter : processEngine.getProcessFilters()) {
			filter.activityTerminal(event);
		}
	}
}
