/*
 * Copyright 2002-2007 the original author or authors.
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

package com.starflow.test;

import com.starflow.test.util.TestUtil;
import com.starflow.wf.engine.ProcessEngine;
import com.starflow.wf.engine.ProcessEngineBuilder;
import com.starflow.wf.engine.model.ProcessInstance;
import com.starflow.wf.engine.service.IActivityInstService;
import com.starflow.wf.engine.service.IProcessDefineService;
import com.starflow.wf.engine.service.IProcessInstanceService;
import com.starflow.wf.service.filter.LoggerProcessFilter;
import com.starflow.wf.service.filter.TransCtrlFilter;

/**
 * 串行流程模式，没有分支。
 * 开始->申请->审核->结束
 * 
 * @author bsli123@gmail.com
 *
 */
public class StartActivityExampleTest {
	public static void main(String[] args) {
		ProcessEngine processEngine = new ProcessEngineBuilder().buildProcessEngine();
		IProcessDefineService procDefService = processEngine.getProcessDefineService();
		IProcessInstanceService procInstService = processEngine.getProcessInstanceService();
		IActivityInstService activityInstService = processEngine.getActivityInstService();
		
		//清除测试数据
		TestUtil.cleanData(processEngine.getApplicationContext());
		
		//部署流程
		procDefService.deployProcessFile("classpath:flow/StartActivityExample.xml");
		
		//添加filter
		processEngine.addFilter(new LoggerProcessFilter());
		processEngine.addFilter(new TransCtrlFilter());

		long start = System.currentTimeMillis();
			
			//启动流程
		ProcessInstance processInstance = procInstService.createAndStartProcess("flow.StartActivityExample", "100001");
		activityInstService.startActivityInst(processInstance.getProcessInstId(), "Act_3");
		
		long end = System.currentTimeMillis();
		System.out.println("总用时：" + (end - start) + "毫秒");
		//总用时：13641毫秒
	}
}
