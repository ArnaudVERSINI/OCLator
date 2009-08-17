package com.neptune.extracteurocl.context;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.model.ENamedElement;

/**
 * Classe génerique de contexte contenant un type de EElement.
 *
 * @author Arnaud VERSINI
 * @author Jocelyne BARROT
 * @author Carine DONGMO
 *
 * @param <EElement> Le type d'element qu'il doit poouvoir contenit
 */
public abstract class AbstractInternalContext < EElement extends ENamedElement >
extends AbstractContext {

	/**
	 * Le contenu du contexte.
	 */
	private EElement content;

	/**
	 *
	 * @param dup Context dont le contenu est à dupliquer
	 * @param uri URI de la balise XML
	 * @param localName Nom de la balis XML
	 * @param attr Attributs de la balise XML
	 * @param attrs Atributs de la balise XML
	 */
	protected AbstractInternalContext(
			final AbstractInternalContext < EElement > dup,
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrs) {
		super(uri, localName, attr, attrs);
		setContent(dup.content);
	}

	/**
	 * Création d'un nouveau contexte à partir de nouvelles informations.
	 * @param content Element du modéle associé
	 * @param uri URI de la balise XML
	 * @param localName Nom de la balise XML
	 * @param attr Attributs XML
	 * @param attrsAttributes Listed es attributs XML
	 */
	public AbstractInternalContext(
			final EElement content,
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrsAttributes) {
		super(uri, localName, attr, attrsAttributes);
		setContent(content);
	}

	/**
	 * @return the content
	 */
	public final EElement getContent() {
		return content;
	}

	/**
	 * Définit si le contexte est valide.
	 * @return True
	 */
	@Override
	public final boolean isValidContext() {
		return true;
	}

	/**
	 * @param content Le nouveau contenu
	 */
	public final void setContent(final EElement content) {
		if (content == null) {
			throw new IllegalArgumentException();
		}
		this.content = content;
	}

}
