package com.neptune.extracteurocl.model;

import com.neptune.extracteurocl.control.Disposable;

/**
 * Représentation d'un paramétre d'opération.
 * @author Arnaud VERSINI
 *
 */
public final class EParameter
extends ENamedElement
implements Disposable {
	/**
	 * Le type du paramétre.
	 */
	private String type;

	/**
	 * Construction d'un nouveau paramétre.
	 * @param name Nom du paramétre
	 * @param type Type du paramétre
	 */
	public EParameter(final String name, final String type) {
		super(name);
		setType(type);
	}

	/**
	 * Suppression des liens mémoire.
	 */
	public void dispose() {
	}

	/**
	 * @return Le type du paramétre
	 */
	public String getType() {
		return type;
	}

	/**
	 * Redéfinit le type du paramétre.
	 * @param type Le nouveau type
	 */
	public void setType(final String type) {
		if (type == null) {
			throw new IllegalArgumentException();
		}
		this.type = type;
	}
}
