package com.neptune.extracteurocl.handler;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.context.ContextClassifier;
import com.neptune.extracteurocl.context.ContextOperation;
import com.neptune.extracteurocl.context.GlobalContext;
import com.neptune.extracteurocl.context.IContext;
import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EOperation;
import com.neptune.extracteurocl.util.OclExtractorInternalException;
import com.neptune.extracteurocl.util.OclStringHelper;

/**
 * Gestionnaire de la balise Contents.
 * @author Arnaud VERSINI
 */
public class ContentsElementHandler
implements IECoreElementHandler {

	/**
	 * Message d'erreur sur la logique du fichier
	 * si le content n'est pas ok.
	 */
	private static final String LOGICAL_ERROR
			= "Une EOperation ne peut être contenu dans "
				+ "autre chose qu'un eClassifier";

	/**
	 * Fonction de traitement d'un evenement d'element.
	 * @return Le nouveau contexte associé à l'evenement
	 * @param uri L'URI de la balise XML associé
	 * @param localName le nom de la balise XML
	 * @param attr Atributs XML
	 * @param attrs Liste des attributs XML
	 * @param context Contexte global du parseur
	 * @throws OclExtractorInternalException Si erreur de contenu du fichier
	 */
	public final IContext onStartElement(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrs,
			final GlobalContext context)
	throws OclExtractorInternalException {
		if (!"ecore:EOperation".equals(attrs.getValue("xsi:type"))) {
			return context
					.lastElement()
					.duplicate(uri, localName, attr, attrs);
		}
		final boolean instanceOfContextClassifier
			= context.lastElement() instanceof ContextClassifier;
		if (context.isEmpty() || !instanceOfContextClassifier) {
			throw new OclExtractorInternalException(
					LOGICAL_ERROR);
		}
		final ContextClassifier contextClassifier
				= (ContextClassifier) context.lastElement();
		final EClassifier parent = contextClassifier.getContent();
		final String eType = OclStringHelper.extractType(attrs
				.getValue("eType"));

		final EOperation operation
				= new EOperation(
						attrs.getValue("name"),
						eType,
						parent);

		parent.add(operation);

		return new ContextOperation(
				operation,
				uri,
				localName,
				attr,
				attrs);
	}
}
