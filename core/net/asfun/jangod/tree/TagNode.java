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
package net.asfun.jangod.tree;

import java.io.IOException;
import java.io.Writer;

import net.asfun.jangod.base.Application;
import net.asfun.jangod.interpret.InterpretException;
import net.asfun.jangod.interpret.JangodInterpreter;
import net.asfun.jangod.lib.Tag;
import net.asfun.jangod.lib.TagLibrary;
import net.asfun.jangod.parse.ParseException;
import net.asfun.jangod.parse.TagToken;

public class TagNode extends Node{

	private static final long serialVersionUID = 2405693063353887509L;

	private TagToken master;
	String endName = null;
	
	public TagNode(Application app, TagToken token) throws ParseException{
		super(app);
		master = token;
		Tag tag = TagLibrary.getTag(master.getTagName());
		if ( tag == null ) {
			throw new ParseException("Can't find tag >>> " + master.getTagName());
		}
		endName = tag.getEndTagName();
	}

	@Override
	public void render(JangodInterpreter interpreter, Writer writer)
			throws InterpretException, IOException {
		interpreter.setLevel(level);
		Tag tag = TagLibrary.getTag(master.getTagName());
		tag.interpreter(children(), master.getHelpers(), interpreter, writer);
	}

	@Override
	public String toString() {
		return master.toString();
	}
	
	@Override
	public String getName() {
		return master.getTagName();
	}
	
	@Override
	public Node clone() {
		try {
			Node clone = new TagNode(app, master);
			clone.children = this.children.clone(clone);
			return clone;
		} catch (ParseException e) {
			throw new InternalError();
		}
	}
}
