package com.neptune.extracteurocl.handler;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.context.AbstractContext;
import com.neptune.extracteurocl.context.ContextClassifier;
import com.neptune.extracteurocl.context.ContextOperation;
import com.neptune.extracteurocl.context.GlobalContext;
import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EOperation;
import com.neptune.extracteurocl.util.OclExtractorInternalException;
import com.neptune.extracteurocl.util.OclStringHelper;

public class EOperationElementHandler implements IECoreElementHandler {

	public AbstractContext onStartElement(final String uri,
			final String localName, final String attr, final Attributes attrs,
			final GlobalContext context) throws OclExtractorInternalException {

		if (context.isEmpty()
				|| !(context.lastElement() instanceof ContextClassifier)) {
			throw new OclExtractorInternalException(
					"L'opération doit être contenue dans un classifier");
		}

		final ContextClassifier contextClassifier = (ContextClassifier) context
				.lastElement();
		final EClassifier parent = contextClassifier.getContent();
		final String eType = OclStringHelper.extractType(attrs
				.getValue("eType"));

		final EOperation operation = new EOperation(attrs.getValue("name"),
				eType, parent);

		parent.add(operation);

		return new ContextOperation(operation, uri, localName, attr, attrs);
	}
}
