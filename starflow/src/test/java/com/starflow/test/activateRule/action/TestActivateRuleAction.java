package com.starflow.test.activateRule.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.starflow.wf.engine.model.ActivityInst;
import com.starflow.wf.engine.model.ProcessInstance;
import com.starflow.wf.service.spi.IActivateRuleAction;

public class TestActivateRuleAction implements IActivateRuleAction {
	private static Logger logger = LoggerFactory.getLogger(TestActivateRuleAction.class);

	@Override
	public boolean execute(ProcessInstance processInstance, ActivityInst activityInst) {
		logger.info("待激活.......");
		return false;
	}

}
