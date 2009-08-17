package com.neptune.extracteurocl.handler;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.context.AbstractContext;
import com.neptune.extracteurocl.context.ContextPackage;
import com.neptune.extracteurocl.context.GlobalContext;
import com.neptune.extracteurocl.model.EPackages;
import com.neptune.extracteurocl.util.OclExtractorInternalException;

public class EPackageElementHandler implements IECoreElementHandler {

	public AbstractContext onStartElement(final String uri,
			final String localName, final String attr, final Attributes attrs,
			final GlobalContext context) throws OclExtractorInternalException {

		if (!context.isEmpty()
				&& !(context.lastElement() instanceof ContextPackage)) {
			throw new OclExtractorInternalException(
					"Le package doit soit être la base soit etre contenu dans un autre package");
		}

		ContextPackage contextPackage = null;
		if (!context.isEmpty()) {
			contextPackage = (ContextPackage) context.lastElement();
		}

		EPackages parent = null;

		if (contextPackage != null) {
			parent = contextPackage.getContent();
		}

		final EPackages pack = new EPackages(attrs.getValue("name"), parent);

		if (context.isEmpty()) {
			context.setMainPackage(pack);
		}

		if (parent != null) {
			parent.addPackage(pack);
		}

		return new ContextPackage(pack, uri, localName, attr, attrs);
	}
}
