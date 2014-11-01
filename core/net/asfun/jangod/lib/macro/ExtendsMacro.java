package net.asfun.jangod.lib.macro;

import java.io.IOException;

import net.asfun.jangod.lib.Macro;
import net.asfun.jangod.parse.ParseException;
import net.asfun.jangod.parse.TokenParser;
import net.asfun.jangod.tree.Node;
import net.asfun.jangod.tree.TreeParser;
import net.asfun.jangod.tree.TreeRebuilder;
import net.asfun.jangod.util.HelperStringTokenizer;

public class ExtendsMacro implements Macro{

	final String MACRONAME = "extends";
	
	@Override
	public String getEndMacroName() {
		return null;
	}

	@Override
	public void refactor(Node current, String helpers, TreeRebuilder rebuilder)
			throws ParseException {
		String[] helper = new HelperStringTokenizer(helpers).allTokens();
		if( helper.length != 1) {
			throw new ParseException("Macro 'extends' expects 1 helper >>> " + helper.length);
		}
		String templateFile = rebuilder.resolveString(helper[0]);
		try {
			String fullName = current.application().getConfiguration().getResourceLocater()
					.getFullName(templateFile, rebuilder.getWorkspace(),
					rebuilder.getConfiguration().getWorkspace());
			//TODO STOP LOOP EXTENDS
			Node extendsRoot = new TreeParser(current.application()).parse(new TokenParser(
					current.application().getConfiguration().getResourceLocater().getString(
					fullName, rebuilder.getConfiguration().getEncoding())));
			extendsRoot = rebuilder.derive().refactor(extendsRoot);
			
			rebuilder.parent = extendsRoot;
			rebuilder.nodeRemove(current);
		} catch (IOException e) {
			throw new ParseException(e.getMessage());
		}
	}

	@Override
	public String getName() {
		return MACRONAME;
	}

}
