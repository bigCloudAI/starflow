/**
 * Copyright (c) 2011, SuZhou USTC Star Information Technology CO.LTD
 * All Rights Reserved.
 */

package com.starflow.test;

import org.junit.Before;

import com.starflow.test.util.TestUtil;
import com.starflow.wf.engine.ProcessEngine;
import com.starflow.wf.engine.ProcessEngineBuilder;
import com.starflow.wf.engine.service.IActivityInstService;
import com.starflow.wf.engine.service.IProcessDefineService;
import com.starflow.wf.engine.service.IProcessInstanceService;
import com.starflow.wf.engine.service.IWorkItemService;
import com.starflow.wf.service.filter.LoggerProcessFilter;
import com.starflow.wf.service.filter.TransCtrlFilter;

/**
 *
 *
 * @author   bsli@starit.com.cn
 * @Date	 2011-8-10 上午11:48:39
 */
public abstract class AbstractFlowTest {
	protected IProcessInstanceService procInstService;
	protected IWorkItemService workItemService;
	protected IProcessDefineService procDefService;
	protected IActivityInstService activityInstService;
	
	@Before
	public void init() {
		ProcessEngine processEngine = new ProcessEngineBuilder().buildProcessEngine();
		procDefService = processEngine.getProcessDefineService();
		procInstService = processEngine.getProcessInstanceService();
		workItemService = processEngine.getWorkItemService();
		activityInstService = processEngine.getActivityInstService();
		
		//清除测试数据
		TestUtil.cleanData(processEngine.getApplicationContext());
		
		//添加filter
		processEngine.addFilter(new LoggerProcessFilter());
		processEngine.addFilter(new TransCtrlFilter());
	}
}

