package com.neptune.extracteurocl.context;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.model.EPackages;

/**
 * Contexte dans le cas d'un package, sert dans le cas du traitement d'un
 * Classifier ou d'un package.
 *
 * @author Arnaud VERSINI
 * @author Jocelyne BARROT
 * @author Carine DONGMO
 *
 */
public final class ContextPackage extends AbstractInternalContext<EPackages> {

	/**
	 * Création d'un nouveau contexte de package en le duplicant.
	 * @param dup Le contexte à dupliquer
	 * @param uri L'URI du contexte
	 * @param localName Le nom de la balise XML
	 * @param attr Les attributs
	 * @param attrs La liste des attributs
	 */

	protected ContextPackage(
			final ContextPackage dup,
			final String uri,
			final String localName,
			final String attr, final Attributes attrs) {
		super(dup, uri, localName, attr, attrs);
	}

	/**
	 * Création d'un nouveau contexte.
	 * @param content le package associé.
	 * @param uri L'URI de la balise XML
	 * @param localName Le nom de la balise XML
	 * @param attr Les attributs XML
	 * @param attrsAttributes La liste des attributs XML
	 */
	public ContextPackage(
			final EPackages content,
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
	public ContextPackage duplicate(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrsAttributes) {
		return new ContextPackage(
				this,
				uri,
				localName,
				attr,
				attrsAttributes);
	}
}
