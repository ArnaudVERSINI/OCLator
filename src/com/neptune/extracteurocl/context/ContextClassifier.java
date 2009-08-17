package com.neptune.extracteurocl.context;

import org.xml.sax.Attributes;

import com.neptune.extracteurocl.model.EClassifier;

/**
 * Contexte pour le traitement des EClassifier.
 * Utilisé principalement dans le traitement des EOperations.
 * @author Arnaud VERSINI
 * @author Jocelyne BARROT
 * @author Carine DONGMO
 */
public final class ContextClassifier
extends AbstractInternalContext < EClassifier > {

	/**
	 * Constructeur de duplication avec paramétres XML.
	 * @param dup L'original
	 * @param uri La nouvelle URI
	 * @param localName Le nouveau nom de balise
	 * @param attr Les attributs XML
	 * @param attrs La liste des attributs XML
	 */
	private ContextClassifier(
			final ContextClassifier dup,
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrs) {
		super(dup, uri, localName, attr, attrs);
	}

	/**
	 * Création d'un contexte de classifier.
	 * @param content Le classifier du contexte
	 * @param uri L'URI de la balise XML
	 * @param localName Le nom de la balise XML
	 * @param attr Les attributs XML
	 * @param attrsAttributes La liste des attributs XML
	 */
	public ContextClassifier(
			final EClassifier content,
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrsAttributes) {
		super(content, uri, localName, attr, attrsAttributes);
	}

	/**
	 * Duplication du contexte courant avec de nouvelles données XML. 
	 * @param uri URI de l'element XML
	 * @param localName Nom de la balise XML
	 * @param attr Attributs de la balise XML
	 * @param attrsAttributes Liste des attributs
	 * @return Le nouveau Contexte dupliqué
	 */
	@Override
	public ContextClassifier duplicate(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrsAttributes) {
		return new ContextClassifier(this, uri, localName, attr,
				attrsAttributes);
	}
}
