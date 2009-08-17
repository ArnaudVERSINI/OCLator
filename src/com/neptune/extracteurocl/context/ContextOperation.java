package com.neptune.extracteurocl.context;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.model.EOperation;

/**
 * Contexte dans le cas d'une opération, sert principalement dans le cas des
 * requetes OCLs et des paramétres.
 *
 * @author Arnaud VERSINI
 * @author Jocelyne BARROT
 * @author Carine DONGMO
 *
 */
public final class ContextOperation
extends AbstractInternalContext < EOperation > {

	/**
	 * Création d'un nouveau contexte d'opération en le duplicant.
	 * @param dup Le contexte à dupliquer
	 * @param uri L'URI du contexte
	 * @param localName Le nom de la balise XML
	 * @param attr Les attributs
	 * @param attrs La liste des attributs
	 */
	protected ContextOperation(
			final ContextOperation dup,
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrs) {
		super(dup, uri, localName, attr, attrs);
	}

	/**
	 * Création d'un nouveau contexte.
	 * @param content L'opération associé.
	 * @param uri L'URI de la balise XML
	 * @param localName Le nom de la balise XML
	 * @param attr Les attributs XML
	 * @param attrsAttributes La liste des attributs XML
	 */
	public ContextOperation(
			final EOperation content,
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrsAttributes) {
		super(content, uri, localName, attr, attrsAttributes);
	}

	/**
	 * Duplication du contexte avec informations XML d'un nouvel element.
	 * @param uri L'URI de la balise XML
	 * @param localName Le nom de la balise XML
	 * @param attr Les attributs XML
	 * @param attrsAttributes La liste des attributs XML
	 * @return Le nouveau contexte
	 */
	@Override
	public ContextOperation duplicate(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrsAttributes) {
		return new ContextOperation(
				this,
				uri,
				localName,
				attr,
				attrsAttributes);
	}
}
