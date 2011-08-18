package com.starflow.test.toolApp.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.starflow.wf.engine.model.ActivityInst;
import com.starflow.wf.engine.model.ProcessInstance;
import com.starflow.wf.service.spi.IToolAppAction;

public class TestToolAppTransactionAction implements IToolAppAction {
	private static Logger logger = LoggerFactory.getLogger(TestToolAppTransactionAction.class);

	@Override
	public Object execute(ProcessInstance processInstance, ActivityInst activityInst) {
		int a = 1;
		if(a!=1) {
			logger.info("流程实例：{}，环节实例：{}，自动归档", processInstance.getProcessInstId(), activityInst.getActivityInstId());
			return null;
		} else {
			logger.info("流程实例：{}，环节实例：{}，自动归档", processInstance.getProcessInstId(), activityInst.getActivityInstId());
			throw new RuntimeException("数据库操作失败");
		}
	}

}
