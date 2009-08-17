package com.neptune.extracteurocl.context;

import org.xml.sax.Attributes;

/**
 * Interface utilisé principalement pour puvoir tester
 * avec EasyMock.
 * Contient toutes les méthodes des classifiers.
 *
 * @author Arnaud VERSINI
 * @author Jocelyne BARROT
 * @author Carine DONGMO
 *
 */
public interface IContext {

	/**
	 * fonction équivalente avec toString, mais toString n'est
	 * pas redéfinissable avec EasyMock.
	 * @return La chaine de caractére représentant l'objet.
	 */
	String convertToString();

	/**
	 * Duplication du contexte actuel.
	 * @param uri URI de la balise XML
	 * @param localName Nom de la balise XML
	 * @param attr Chaine de caractére des attributs
	 * @param attrsAttributes Liste des attributs
	 * @return le nouveau contexte.
	 */
	IContext duplicate(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrsAttributes);

	/**
	 * @return les attributs de la balise XML associé
	 */
	String getAttr();

	/**
	 * @return Le nom de la balise XML associé
	 */
	String getLocalName();

	/**
	 *
	 * @return L'URI de la balise associé
	 */
	String getUri();

	/**
	 * @return validité du contexte
	 */
	boolean isValidContext();
}
