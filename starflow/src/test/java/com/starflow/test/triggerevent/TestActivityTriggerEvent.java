package com.starflow.test.triggerevent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.starflow.wf.service.spi.IActivityTriggerEvent;

public class TestActivityTriggerEvent implements IActivityTriggerEvent {
	private static Logger logger = LoggerFactory.getLogger(TestActivityTriggerEvent.class);

	@Override
	public void afterComplete(long processInstId, long activityInstId) {
		logger.info("after complete");
	}

	@Override
	public void afterStart(long processInstId, long activityInstId) {
		logger.info("after start");
	}

	@Override
	public void beforeComplete(long processInstId, long activityInstId) {
		logger.info("before complete");
	}

	@Override
	public void beforeStart(long processInstId, String activityDefId) {
		logger.info("before start");
	}
}
