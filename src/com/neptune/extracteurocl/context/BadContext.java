package com.neptune.extracteurocl.context;

import org.xml.sax.Attributes;

/**
 * Contexte en cas d'erreur dans le fichier XML.
 *
 * Ce contexte est créé si un handler a envoyé une exception de type
 * IllegalParameterException ou OclExtractorInternalException
 *
 * @author Arnaud VERSINI
 * @author Jocelyne BARROT
 * @author Carine DONGMO
 */
public final class BadContext extends AbstractContext {

	/**
	 * Création d'un nouveau contexte mauvais.
	 * @param uri URI de la balise XML
	 * @param localName Nom de la balise XML
	 * @param attr Attributs
	 * @param attrsAttributes Liste des attributs
	 */
	public BadContext(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrsAttributes) {
		super(uri, localName, attr, attrsAttributes);
	}

	/**
	 * Duplication du contexte avec de nouvelles données XML.
	 * @param uri URI XML
	 * @param localName Nom de la balise XML
	 * @param attr Attributs XML
	 * @param attrsAttributes Liste des attributs XML
	 * @return Le nouveau BadContext
	 */
	@Override
	public BadContext duplicate(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrsAttributes) {
		return new BadContext(uri, localName, attr, attrsAttributes);
	}

	/**
	 * @return False
	 */
	public boolean isValidContext() {
		return false;
	}
}
