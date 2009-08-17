package com.neptune.extracteurocl.context;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.context.ContextClassifier;
import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EPackages;

public class TestContextClassifier
extends TestContext<EClassifier, ContextClassifier> {

	@Override
	public EClassifier createContent() {
		return new EClassifier("toto", new EPackages("titi", null));
	}

	@Override
	public ContextClassifier createContext(final EClassifier content, final String uri,
			final String localName, final String attr, final Attributes attrsAttributes) {
		return new ContextClassifier(content, TestContext.DEFAULT_URI,
				TestContext.DEFAULT_LOCALNAME, TestContext.DEFAULT_ATTR, attrs);
	}

	@Override
	public void verifyAttrs() {
	}
}
