package com.neptune.extracteurocl.handler;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.context.ContextClassifier;
import com.neptune.extracteurocl.context.ContextPackage;
import com.neptune.extracteurocl.context.GlobalContext;
import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EPackages;
import com.neptune.extracteurocl.util.OclExtractorInternalException;

/**
 * Classe traitant des évenements liés aux Classifiers.
 * @author Arnaud VERSINI
 */
public final class EClassifierElementHandler
implements IECoreElementHandler {

	/**
	 * Interception des évenements liés aux classifiers.
	 * @param uri URI de la balise XML
	 * @param localName Nom de la balise XML
	 * @param attr Attributs XML
	 * @param attrs Liste des attributs XML
	 * @param context Contexte du parseur eCore
	 * @throws OclExtractorInternalException Si erreur dans le fichier XML
	 * @return Le nouveau contexte
	 */
	public ContextClassifier onStartElement(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrs,
			final GlobalContext context)
	throws OclExtractorInternalException {

		boolean empty = context.isEmpty();
		boolean instanceOfContextPackage
			= !(context.lastElement() instanceof ContextPackage);
		if (empty || instanceOfContextPackage) {
			throw new OclExtractorInternalException(
					"Les classifiers doivent être dans un package");
		}

		final ContextPackage contextPackage
				= (ContextPackage) context.lastElement();
		final EPackages parent = contextPackage.getContent();

		final EClassifier classifier
				= new EClassifier(
						attrs.getValue("name"),
						parent);

		parent.addClassifier(classifier);

		return new ContextClassifier(
				classifier,
				uri,
				localName,
				attr,
				attrs);
	}
}
