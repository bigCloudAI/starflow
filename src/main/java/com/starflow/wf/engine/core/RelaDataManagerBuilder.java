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

package com.starflow.wf.engine.core;

import com.starflow.wf.engine.Configuration;
import com.starflow.wf.engine.core.data.DataSourceRelaDataManager;
import com.starflow.wf.engine.core.data.NoSqlRelaDataManager;
import com.starflow.wf.engine.core.data.RelaDataManager;
import com.starflow.wf.engine.core.data.ThreadLocalRelaDataManager;

public class RelaDataManagerBuilder {
	private static final String RELA_DATA_MANAGER_TYPE = "com.starflow.wf.relaData.type";
	
	private static RelaDataManager relaDataManager;
	
	public static RelaDataManager buildRelaDataManager() {
		if(relaDataManager == null) {
			synchronized (RelaDataManagerBuilder.class) {
				if(relaDataManager == null) {
					Configuration configuration = Configuration.getInstance();
					String type = configuration.getProperty(RELA_DATA_MANAGER_TYPE);
					
					if("datasource".equals(type))
						relaDataManager = new DataSourceRelaDataManager();
					else if("nosql".equals(type))
						relaDataManager = new NoSqlRelaDataManager();
					else
						relaDataManager = new ThreadLocalRelaDataManager();
				}
			}
		}
		
		return relaDataManager;
	}
}
