package com.neptune.extracteurocl.model;

/**
 * Représente la classe du même nom du modéle eCore.
 * @author Arnaud VERSINI
 */
public class ENamedElement {

	private String name;

	public ENamedElement(final String name) {
		setName(name);
	}

	public final String getName() {
		return name;
	}

	public final void setName(final String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}
}
