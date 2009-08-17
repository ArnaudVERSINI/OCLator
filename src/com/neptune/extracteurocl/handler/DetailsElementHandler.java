package com.neptune.extracteurocl.handler;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.context.ContextOperation;
import com.neptune.extracteurocl.context.GlobalContext;
import com.neptune.extracteurocl.context.IContext;
import com.neptune.extracteurocl.model.EOperation;
import com.neptune.extracteurocl.model.OCLRequest;
import com.neptune.extracteurocl.util.OclExtractorInternalException;
import com.neptune.extracteurocl.util.OclStringHelper;

/**
 *
 * @author Arnaud
 *
 */
public final class DetailsElementHandler
implements IECoreElementHandler {

	/**
	 * Traitement de la balise Details.
	 * Traitement à l'ouverture d'un balise de type details en ajoutant une
	 * requête OCL si :
	 * -Présence d'une requête OCL dans l'attribut value
	 * -Contexte précedent de type Operation
	 *
	 * @param uri URI de la balise XML
	 * @param localName Nom de la balise XML
	 * @param attr Attributs XML
	 * @param attrs Liste des attributs XML
	 * @param context Contexte du parseur ECore
	 * @throws OclExtractorInternalException Erreur interne de la forme
	 * 			du document.
	 * @return Nouveau contexte
	 */
	@Override
	public IContext onStartElement(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrs,
			final GlobalContext context)
	throws OclExtractorInternalException {

		if (!OclStringHelper.isAnOcl(attrs.getValue("value"))) {
			return context
					.lastElement()
					.duplicate(uri, localName, attr, attrs);
		}

		if (!(context.lastElement() instanceof ContextOperation)) {
			throw new OclExtractorInternalException(
					"Une requéte doit oblogatoirement"
							+ " être dans une opération");
		}

		final ContextOperation opContext
				= (ContextOperation) context.lastElement();
		final EOperation actualOperation
				= opContext.getContent();

		final String reqOclComplete = attrs.getValue("value");

		final String reqOcl
				= OclStringHelper.extractOclRequest(reqOclComplete);
		final String commentaireOcl
				= OclStringHelper.extractOclComment(reqOclComplete);
		final OCLRequest oclRequest
				= new OCLRequest(
						reqOcl,
						commentaireOcl,
						actualOperation);
		opContext.getContent().addOcl(oclRequest);

		return opContext;
	}
}
