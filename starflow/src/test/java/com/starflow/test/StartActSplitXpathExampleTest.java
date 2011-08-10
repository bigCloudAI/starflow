package com.starflow.test;

import java.util.HashMap;
import java.util.Map;

import com.starflow.test.util.TestUtil;
import com.starflow.wf.engine.ProcessEngine;
import com.starflow.wf.engine.ProcessEngineBuilder;
import com.starflow.wf.engine.core.RelaDataManagerBuilder;
import com.starflow.wf.engine.core.data.RelaDataManager;
import com.starflow.wf.engine.model.ProcessInstance;
import com.starflow.wf.engine.service.IProcessDefineService;
import com.starflow.wf.engine.service.IProcessInstanceService;
import com.starflow.wf.engine.service.IWorkItemService;
import com.starflow.wf.service.filter.LoggerProcessFilter;
import com.starflow.wf.service.filter.TransCtrlFilter;

/**
 * 流程开始环节进行分支，使用复杂表达式计算，来判断走哪条分支。
 * 		 固话处理班	 
 * 开始->        ->归档->结束
 *       电话处理班
 * 
 * @author bsli123@gmail.com
 *
 */
public class StartActSplitXpathExampleTest {
	public static void main(String[] args) {
		ProcessEngine processEngine = new ProcessEngineBuilder().buildProcessEngine();
		IProcessDefineService procDefService = processEngine.getProcessDefineService();
		IProcessInstanceService procInstService = processEngine.getProcessInstanceService();
		IWorkItemService workItemService = processEngine.getWorkItemService();
		
		//清除测试数据
		TestUtil.cleanData(processEngine.getApplicationContext());
		
		//部署流程
		procDefService.deployProcessFile("classpath:flow/StartActSplitXpathExample.xml");
		
		//添加filter
		processEngine.addFilter(new LoggerProcessFilter());
		processEngine.addFilter(new TransCtrlFilter());

		long start = System.currentTimeMillis();
		
		//启动流程
		ProcessInstance processInstance = procInstService.createProcess("flow.StartActSplitXpathExample", "100001");
		
		Map<String, Object> conditions = new HashMap<String, Object>();
		//宽带处理班
		conditions.put("flag", "<flag>ADSL</flag>"); 
		RelaDataManager relaDataManager = RelaDataManagerBuilder.buildRelaDataManager();
		long processInstId = processInstance.getProcessInstId();
		String activityDefId = "act_start";
		relaDataManager.setExpressConditions(processInstId, activityDefId, conditions);
		
		//创建流程
		procInstService.startProcess(processInstance.getProcessInstId());
		
		workItemService.finishWorkItem(1l, "100001");
		workItemService.finishWorkItem(2l, "100001");
		
		long end = System.currentTimeMillis();
		System.out.println("总用时：" + (end - start) + "毫秒");
		//总用时：13641毫秒
	}
}
