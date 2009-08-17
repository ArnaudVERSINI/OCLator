package com.neptune.extracteurocl.context;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.context.ContextPackage;
import com.neptune.extracteurocl.model.EPackages;

public class TestContextPackage extends TestContext<EPackages, ContextPackage> {

	@Override
	public EPackages createContent() {
		return new EPackages("toto", null);
	}

	@Override
	public ContextPackage createContext(
			final EPackages content,
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrsAttributes) {
		return new ContextPackage(content, TestContext.DEFAULT_URI,
				TestContext.DEFAULT_LOCALNAME, TestContext.DEFAULT_ATTR, attrs);
	}

	@Override
	public void verifyAttrs() {
	}

}
