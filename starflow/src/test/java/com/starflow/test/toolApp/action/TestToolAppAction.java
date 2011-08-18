package com.starflow.test.toolApp.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.starflow.wf.engine.model.ActivityInst;
import com.starflow.wf.engine.model.ProcessInstance;
import com.starflow.wf.service.spi.IToolAppAction;

public class TestToolAppAction implements IToolAppAction {
	private static Logger logger = LoggerFactory.getLogger(TestToolAppAction.class);

	@Override
	public Object execute(ProcessInstance processInstance, ActivityInst activityInst) {
		logger.info("流程实例：{}，环节实例：{}，自动归档", processInstance.getProcessInstId(), activityInst.getActivityInstId());
		return null;
	}

}
