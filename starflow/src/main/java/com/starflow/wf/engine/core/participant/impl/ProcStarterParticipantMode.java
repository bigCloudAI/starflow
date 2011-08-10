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

package com.starflow.wf.engine.core.participant.impl;

import java.util.LinkedList;
import java.util.List;

import com.starflow.wf.engine.core.Constants;
import com.starflow.wf.engine.core.participant.ParticipantMode;
import com.starflow.wf.engine.event.AbstractFlowEvent;
import com.starflow.wf.engine.model.Participant;
import com.starflow.wf.engine.model.elements.ActivityXml;

/**
 * 环节参与者与流程启动者相同
 * 
 * @author libinsong1204@gmail.com
 * @version 1.0
 */
public class ProcStarterParticipantMode implements ParticipantMode{

	public List<Participant> creatParticipants(AbstractFlowEvent event, ActivityXml activityXml) {
		String userid = event.getProcessInstance().getCreator();
		Participant participant = new Participant();
		participant.setParticipant(userid);
		participant.setParticType(Constants.PARTICIPANT_TYPE_PERSON);
		
		List<Participant> list = new LinkedList<Participant>();
		list.add(participant);
		
		return list;
	}

}
