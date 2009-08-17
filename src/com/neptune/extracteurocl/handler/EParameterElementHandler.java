package com.neptune.extracteurocl.handler;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.context.AbstractContext;
import com.neptune.extracteurocl.context.ContextOperation;
import com.neptune.extracteurocl.context.GlobalContext;
import com.neptune.extracteurocl.model.EOperation;
import com.neptune.extracteurocl.model.EParameter;
import com.neptune.extracteurocl.util.OclExtractorInternalException;
import com.neptune.extracteurocl.util.OclStringHelper;

public class EParameterElementHandler implements IECoreElementHandler {

	public AbstractContext onStartElement(final String uri,
			final String localName, final String attr, final Attributes attrs,
			final GlobalContext context) throws OclExtractorInternalException {

		if (context.isEmpty()
				|| !(context.lastElement() instanceof ContextOperation)) {
			throw new OclExtractorInternalException(
					"Les paramétres doivent être associés à une opération");
		}

		final ContextOperation contextOperation = (ContextOperation) context
				.lastElement();
		final EOperation parent = contextOperation.getContent();

		final String name = attrs.getValue("name");
		final String type = OclStringHelper
				.extractType(attrs.getValue("eType"));
		final EParameter parameter = new EParameter(name, type);

		parent.add(parameter);

		return contextOperation;
	}
}
