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

package com.starflow.wf.engine.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.util.Assert;

import com.starflow.wf.engine.ProcessEngine;
import com.starflow.wf.engine.core.Constants;
import com.starflow.wf.engine.model.ProcessDefine;
import com.starflow.wf.engine.model.elements.ActivityXml;
import com.starflow.wf.engine.model.elements.FreeActXml;
import com.starflow.wf.engine.model.elements.TransitionXml;
import com.starflow.wf.engine.repository.IProcessDefineRepository;
import com.starflow.wf.engine.service.IFreeFlowService;

/**
 * 
 * @author libinsong1204@gmail.com
 * @version 1.0
 */
public class FreeFlowService implements IFreeFlowService {

	final private ProcessEngine processEngine;
	
	private IProcessDefineRepository procDefRep;
	
	public FreeFlowService(ProcessEngine processEngine) {
		Assert.notNull(processEngine);
		
		this.processEngine = processEngine;
		
		this.procDefRep = this.processEngine.getApplicationContext().getBean(IProcessDefineRepository.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isFreeActivity(long processDefId, String activityDefId) {
		ProcessDefine processDefine = this.procDefRep.findProcessDefine(processDefId);
		ActivityXml activityXml = processDefine.getProcessObject().getActivitys().get(activityDefId);
		return activityXml.getIsFreeActivity();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<FreeActXml> queryPossibleNextActsOfFreeActivity(long processDefId, String activityDefId) {
		ProcessDefine processDefine = this.procDefRep.findProcessDefine(processDefId);
		ActivityXml activityXml = processDefine.getProcessObject().getActivitys().get(activityDefId);
		String strategy = activityXml.getFreeRangeStrategy();
		
		//是否尽限为人工活动
		boolean isOnlyLimitedManualAct = activityXml.getIsOnlyLimitedManualActivity();
		
		List<FreeActXml> actEls = new ArrayList<FreeActXml>();
		if(Constants.Free_Act_strategy_three.equalsIgnoreCase(strategy)) {
			//在后继活动范围内自由
			List<TransitionXml> transitions = activityXml.getAfterTrans();
			for(TransitionXml tx : transitions) {
				String _actDefId = tx.getTo();
				ActivityXml ax = processDefine.getProcessObject().getActivitys().get(_actDefId);
				if(isOnlyLimitedManualAct && !Constants.ACT_TYPE_MANUL.equals(ax.getType())) {
					continue;
				}
				FreeActXml freeActXml = new FreeActXml();
				freeActXml.setId(ax.getId());
				freeActXml.setName(ax.getName());
				freeActXml.setType(ax.getType());
				actEls.add(freeActXml);
			}
		} else if(Constants.Free_Act_strategy_two.equalsIgnoreCase(strategy)) {
			//在指定活动列表范围内自由
			activityXml.getFreeActs();
		} else if(Constants.Free_Act_strategy_One.equalsIgnoreCase(strategy)) {
			//在流程范围内任意自由
			Collection<ActivityXml> collect = processDefine.getProcessObject().getActivitys().values();
			for(ActivityXml ax : collect) {
				if(isOnlyLimitedManualAct && !Constants.ACT_TYPE_MANUL.equals(ax.getType())) {
					continue;
				}
				FreeActXml freeActXml = new FreeActXml();
				freeActXml.setId(ax.getId());
				freeActXml.setName(ax.getName());
				freeActXml.setType(ax.getType());
				actEls.add(freeActXml);
			}
		} 
		
		return actEls;
	}

}
