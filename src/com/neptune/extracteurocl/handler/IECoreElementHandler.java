package com.neptune.extracteurocl.handler;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.context.GlobalContext;
import com.neptune.extracteurocl.context.IContext;
import com.neptune.extracteurocl.util.OclExtractorInternalException;

/**
 * Interface implementé par toutes classe voulant
 * intercepter les evenements XML.
 * @author Arnaud VERSINI
 *
 */
public interface IECoreElementHandler {

	/**
	 * Fonction executé lors de la découverte
	 * de l'ouverture d'une balise XML.
	 * @param uri URI de la balise XML
	 * @param localName Nom de la balise XML
	 * @param attr Attributs de la balise XML
	 * @param attrs Liste des attributs de la balise XML
	 * @param context Contexte du parseur
	 * @return Le nouveau contexte
	 * @throws OclExtractorInternalException Sio erreur dans le doc
	 */
	IContext onStartElement(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrs,
			GlobalContext context)
	throws OclExtractorInternalException;
}