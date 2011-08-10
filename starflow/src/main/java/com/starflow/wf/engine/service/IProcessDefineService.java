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

package com.starflow.wf.engine.service;

import java.util.List;
import java.util.Map;

import com.starflow.wf.engine.model.elements.ActivityXml;
import com.starflow.wf.engine.model.elements.OperationXml;

/**
 * 
 * @author libinsong1204@gmail.com
 * @version 1.0
 */
public interface IProcessDefineService {
	
	/**
	 * 部署流程定义
	 * 
	 * @param xml
	 */
	public void deployProcessXML(String xml);
	
	/**
	 * 部署流程定义
	 * 
	 * @param resourceLocation
	 */
	public void deployProcessFile(String resourceLocation);
	
	/**
	 * 删除流程定义数据
	 * 
	 * @param processDefId
	 */
	public void deleteProcessDefine(long processDefId);
	
	/**
	 * 发布流程定义
	 * 
	 * @param processDefName
	 * @param processDefId
	 */
	public void publishProcessDefine(String processDefName, long processDefId);
	
	/**
	 * 获取流程扩展属性
	 * 
	 * @param processDefId 流程定义ID
	 * @return Map<String, String>
	 */
	public Map<String, String> getProcessProperties(Long processDefId);
	
	/**
	 * 获取环节扩展属性
	 * 
	 * @param processDefId 流程定义ID
	 * @param activityDefId 环节定义ID
	 * @return Map<String, String>
	 */
	public Map<String, String> getActivityProperties(Long processDefId, String activityDefId);
	
	/**
	 * 获取环节操作信息
	 * 
	 * @param processDefId 流程定义ID
	 * @param activityDefId 环节定义ID
	 * @return Map<String, String>
	 */
	public List<OperationXml> getActivityOperations(final Long processDefId, final String activityDefId);
	
	/**
	 * 获取环节模板信息
	 * 
	 * @param processDefId 流程定义ID
	 * @param activityDefId 环节定义ID
	 * @return String
	 */
	public String getActivityAction(final Long processDefId, final String activityDefId);
	
	/**
	 * 查找当前环节的所有前一步环节
	 * 
	 * @param processDefId 流程定义ID
	 * @param activityDefId 环节定义ID
	 * @return List<ActivityXml>
	 */
	public List<ActivityXml> findBeforeActivities(final Long processDefId, final String activityDefId);
	
	/**
	 * 查找当前环节的所有后一步环节
	 * 
	 * @param processDefId 流程定义ID
	 * @param activityDefId 环节定义ID
	 * @return List<ActivityXml>
	 */
	public List<ActivityXml> findAfterActivities(final Long processDefId, final String activityDefId);
}
