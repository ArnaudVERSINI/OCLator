package com.neptune.extracteurocl.context;

import java.util.ArrayList;
import java.util.Collection;

import org.xml.sax.Attributes;

/**
 * Description du contexte actuel. Contient les informations sur la balise XML.
 * Doit être dérivé pour être utilisable.
 *
 * @author Arnaud VERSINI
 * @author Jocelyne BARROT
 * @author Carine DONGMO
 *
 */
public abstract class AbstractContext implements IContext {

	/**
	 * Classe interne pour le stockage d'un attribut.
	 */
	private static final class Attribute {
		/**
		 * Nom de l'attribut.
		 */
		private final String name;
		/**
		 * Valeur de l'attribut.
		 */
		private final String value;

		/**
		 * Constructeur de Attribute avec arguments par defaut.
		 * @param name Nom de l'atrtibut
		 * @param value Valeur de l'attribut
		 */
		private Attribute(
				final String name,
				final String value) {
			this.name = name;
			this.value = value;
		}
	}

	/**
	 * URI de la balise XML associé.
	 */
	private final transient String uri;

	/**
	 * Nom de la balise XML associé.
	 */
	private transient String localName;

	/**
	 * Nom local de la balise XML associé.
	 */
	private final transient  String attr;

	/**
	 * Attributs de la balise XML associé.
	 */
	private final transient Collection < Attribute > attributes
			= new ArrayList < Attribute > ();

	/**
	 * Initialisation à partir des informations XML.
	 * @param uri Je ne sais pas
	 * @param localName Nom de la balise
	 * @param attr Attributs de la balise XML
	 * @param attrs Liste d'attributs XML
	 */
	public AbstractContext(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrs) {
		this.uri = uri;
		setLocalName(localName);
		this.attr = attr;

		setAttributes(attrs);
	}

	/**
	 * Retourne toutes les informations disponible sur le context en cours :
	 * Le nom de la classe du context,
	 * le nom de la balise associé
	 * et la valeur des différents attributs.
	 * @return La requête convertie en chaine de caractére
	 */
	public final String convertToString() {
		final StringBuilder ret = new StringBuilder();
		ret.append(this.getClass().getCanonicalName());
		ret.append(" : [");
		ret.append(getLocalName());
		ret.append(" ");

		for (final Attribute attr : attributes) {
			ret.append(attr.name);
			ret.append("=\"");
			ret.append(attr.value);
			ret.append("\" ");
		}
		ret.append("]");
		return ret.toString();
	}

	/**
	 * Création d'un nouveau contexte à partir du contexte courant.
	 * @param uri Je ne sais pas
	 * @param localName Nom de la balise
	 * @param attr Je ne sais pas
	 * @param attrsAttributes Attributs XML
	 * @return Le nouveau contexte
	 */
	public abstract IContext duplicate(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrsAttributes);

	/**
	 * Getter de attr.
	 * @return attr L'attribut associé
	 */
	public final String getAttr() {
		return attr;
	}

	/**
	 * @return the localName
	 */
	public final String getLocalName() {
		return localName;
	}

	/**
	 * Getter de l'URI de la balise associé.
	 * @return l'URI associé
	 */
	@Override
	public final String getUri() {
		return uri;
	}

	/**
	 * Definit les attributs du context.
	 * @param attrs Liste des attributs
	 */
	private void setAttributes(final Attributes attrs) {
		for (int i = 0; i < attrs.getLength(); i++) {
			final Attribute attribute = new Attribute(
					attrs.getLocalName(i),
					attrs.getValue(i));
			attributes.add(attribute);
		}
	}

	/**
	 * Définit le nom en verificant qu'il ne soit pas null.
	 * @param localName Nom local de la balise XML
	 */
	private void setLocalName(final String localName) {
		if (localName == null) {
			throw new IllegalArgumentException();
		}
		this.localName = localName;
	}

	/**
	 * @return la conversion en chaine de caractére
	 */
	@Override
	public final String toString() {
		return convertToString();
	}
}
