package com.neptune.extracteurocl.handler;

import java.util.HashMap;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.context.GlobalContext;
import com.neptune.extracteurocl.context.IContext;
import com.neptune.extracteurocl.util.OclExtractorInternalException;

/**
 * Le dispatcher d'evenement, dispatche les evénements
 * en fonction du nom de la balise.
 *
 * @author Arnaud VERSINI
 *
 */
public final class ECoreDispatcher {

	/**
	 * Instance du singleton.
	 */
	private static final ECoreDispatcher INSTANCE = new ECoreDispatcher();

	/**
	 * @return L'instance du singleton
	 */
	public static ECoreDispatcher getInstance() {
		return INSTANCE;
	}

	/**
	 * Mappage des balises XML avec leur traitement.
	 */
	private final HashMap<String, IECoreElementHandler> handlers = new HashMap<String, IECoreElementHandler>();

	/**
	 * Initialisation des hanlders à la création.
	 */
	private ECoreDispatcher() {
		handlers.put("eClassifiers", new EClassifierElementHandler());
		handlers.put("eOperations", new EOperationElementHandler());
		handlers.put("EPackage", new EPackageElementHandler());
		handlers.put("details", new DetailsElementHandler());
		handlers.put("eParameters", new EParameterElementHandler());
		handlers.put("contents", new ContentsElementHandler());
	}

	/**
	 * Fonction executé lors de la rencontre de chaque élement.
	 * @param uri URI de la balise
	 * @param localName No mde la balise
	 * @param attr Attributs XML
	 * @param attrs Liste des attributs XML
	 * @param context Context associé
	 * @throws OclExtractorInternalException En cas d'erreur interne
	 */
	public void onStartElement(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrs,
			final GlobalContext context)
	throws OclExtractorInternalException {

		IContext contextFinal = null;

		final boolean contains = handlers.containsKey(localName);
		if (!contains && !context.isEmpty()) {
			contextFinal = context
					.lastElement()
					.duplicate(uri, localName, attr, attrs);
		}

		if (contains) {
			contextFinal = handlers
				.get(localName)
				.onStartElement(
						uri,
						localName,
						attr,
						attrs,
						context);
		}

		context.push(contextFinal);
	}
}
