package com.starflow.test.util;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestUtil {
	public static void cleanData(ApplicationContext applicationContext) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate)applicationContext.getBean(JdbcTemplate.class);
		jdbcTemplate.update("delete from WF_ACTIVITYINST");
		jdbcTemplate.update("delete from WF_PARTICIPANT");
		jdbcTemplate.update("delete from WF_PRIMARYKEY where name in ('processDefId', 'processInstId'," +
				"'activityInstId', 'workItemId', 'participantId', 'transCtrlId')");
		jdbcTemplate.update("delete from WF_PROCESSDEFINE");
		jdbcTemplate.update("delete from WF_PROCESSINST");
		jdbcTemplate.update("delete from WF_WORKITEM");
		jdbcTemplate.update("delete from WF_PROCESSDEFINE");
		jdbcTemplate.update("delete from WF_TRANSCTRL");
	}
}
