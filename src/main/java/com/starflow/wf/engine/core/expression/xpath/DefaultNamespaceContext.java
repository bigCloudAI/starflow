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

package com.starflow.wf.engine.core.expression.xpath;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPathFactory;

/**
 * An implementation of {@link NamespaceContext} which uses a simple Map where
 * the keys are the prefixes and the values are the URIs
 *
 * @version $Revision: 881690 $
 */
public class DefaultNamespaceContext implements NamespaceContext {

    private final Map<String, String> map;
    private final NamespaceContext parent;

    public DefaultNamespaceContext() {
        this(XPathFactory.newInstance());
    }

    public DefaultNamespaceContext(XPathFactory factory) {
        this.parent = factory.newXPath().getNamespaceContext();
        this.map = new HashMap<String, String>();
    }

    public DefaultNamespaceContext(NamespaceContext parent, Map<String, String> map) {
        this.parent = parent;
        this.map = map;
    }

    /**
     * A helper method to make it easy to create newly populated instances
     */
    public DefaultNamespaceContext add(String prefix, String uri) {
        map.put(prefix, uri);
        return this;
    }

    public String getNamespaceURI(String prefix) {
        String answer = map.get(prefix);
        if (answer == null && parent != null) {
            return parent.getNamespaceURI(prefix);
        }
        return answer;
    }

    public String getPrefix(String namespaceURI) {
        for (Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, String> entry = iter.next();
            if (namespaceURI.equals(entry.getValue())) {
                return (String) entry.getKey();
            }
        }
        if (parent != null) {
            return parent.getPrefix(namespaceURI);
        }
        return null;
    }

    public Iterator<String> getPrefixes(String namespaceURI) {
        Set<String> set = new HashSet<String>();
        for (Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, String> entry = iter.next();
            if (namespaceURI.equals(entry.getValue())) {
                set.add(entry.getKey());
            }
        }
        if (parent != null) {
            Iterator<String> iter = cast(parent.getPrefixes(namespaceURI));
            while (iter.hasNext()) {
                set.add(iter.next());
            }
        }
        return set.iterator();
    }
    
    @SuppressWarnings("unchecked")
	private static <T> Iterator<T> cast(Iterator<?> p) {
        return (Iterator<T>) p;
    }
}
