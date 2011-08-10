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

package com.starflow.wf.engine.core.split;

import java.util.ArrayList;
import java.util.List;

import com.starflow.wf.engine.ProcessEngineException;
import com.starflow.wf.engine.core.ActivityTypeFactory;
import com.starflow.wf.engine.core.activity.ActivityType;
import com.starflow.wf.engine.event.AbstractFlowEvent;
import com.starflow.wf.engine.event.support.ActivityStartRuleUtil;
import com.starflow.wf.engine.event.support.EventUtil;
import com.starflow.wf.engine.model.ActivityInst;
import com.starflow.wf.engine.model.elements.ActivityXml;
import com.starflow.wf.service.filter.ProcessFilter;
import com.starflow.wf.service.filter.TransCtrlFilter;

/**
 * 全部分支（AND）
 * 
 * @author libinsong1204@gmail.com
 * @version 1.0
 */
public class AllSplitMode extends AbstractSplitMode {
	public List<ActivityInst> createNextActInsts(AbstractFlowEvent event, List<ActivityXml> activityXmls) {
		List<ActivityInst> actInsts = new ArrayList<ActivityInst>();
		
		if(activityXmls.size()<1)
			throw new ProcessEngineException("满足条件的分支节点，无法创建下一环节");
		
		//多路分支
		for(ActivityXml activityXml : activityXmls) {
			String actType = activityXml.getType();
			ActivityType type = ActivityTypeFactory.buildActivityType(actType);

			if(isStartAct(event, activityXml)) {
				ActivityInst activityInst = type.createActivity(event, activityXml);
				
				if(ActivityStartRuleUtil.isStartActivity(event, activityInst))
					EventUtil.publishActivityStartEvent(event, activityInst, activityXml);
				
				actInsts.add(activityInst);
			} else {
				//下个环节没有满足聚合条件，不能启动，只保存运行轨迹
				for(ProcessFilter filter : event.getProcessEngine().getProcessFilters()) {
					if(filter instanceof TransCtrlFilter) {
						((TransCtrlFilter)filter).saveTransCtrlCanNotAct(event, activityXml);
					}
				}
			}
		}
		
		return actInsts;
	}
	
}
