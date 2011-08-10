package com.starflow.test.triggerevent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.starflow.wf.service.spi.IProcessTriggerEvent;

public class TestProcessTriggerEvent implements IProcessTriggerEvent {
	private static Logger logger = LoggerFactory.getLogger(TestProcessTriggerEvent.class);

	@Override
	public void afterComplete(long processInstId) {
		logger.info("after complete");
	}

	@Override
	public void afterStart(long processInstId) {
		logger.info("after start");
	}

	@Override
	public void beforeComplete(long processInstId) {
		logger.info("before complete");
	}

	@Override
	public void beforeStart(long processInstId) {
		logger.info("before start");
	}

}
