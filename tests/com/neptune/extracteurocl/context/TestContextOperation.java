package com.neptune.extracteurocl.context;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.context.ContextOperation;
import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EOperation;
import com.neptune.extracteurocl.model.EPackages;

public class TestContextOperation extends
		TestContext<EOperation, ContextOperation> {

	@Override
	public EOperation createContent() {
		final EPackages packageParent = new EPackages("name", null);
		final EClassifier parent = new EClassifier("class", packageParent);
		return new EOperation("toto", "Boolean", parent);
	}

	@Override
	public ContextOperation createContext(
			final EOperation content,
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrsAttributes) {
		return new ContextOperation(
				content,
				TestContext.DEFAULT_URI,
				TestContext.DEFAULT_LOCALNAME,
				TestContext.DEFAULT_ATTR, attrs);
	}

	@Override
	public void verifyAttrs() {
	}
}
