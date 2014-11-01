/**********************************************************************
Copyright (c) 2010 Asfun Net.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
**********************************************************************/
package net.asfun.jangod.base;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import net.asfun.jangod.tree.Node;
import net.asfun.jangod.tree.ParseResultManager;

public class Application {

	Map<String, Object> globalBindings = new Hashtable<String, Object>(5);
	Configuration config;
	ParseResultManager parseResultManager;
	boolean isMacroOn = true;
	
	public Application() {
		this(null);
	}

	public Application(Configuration config) {
		if (config == null) {
			config = new Configuration();
		}
		this.config = config;
		this.parseResultManager = new ParseResultManager(this);
	}
	
	public Map<String, Object> getGlobalBindings() {
		return globalBindings;
	}

	public void setGlobalBindings(Map<String, Object> globalBindings) {
		this.globalBindings = globalBindings;
	}
	
	public Configuration getConfiguration() {
		return config;
	}

	public Node getParseResult(String fullName, String encoding) throws IOException {
		return parseResultManager.getParseResult(fullName, encoding);
	}
}
