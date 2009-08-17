package com.neptune.extracteurocl.model;

/**
 * Classe de description des Classifier eCore.
 * @author Arnaud VERSINI
 *
 */
public final class EClassifier
extends EContainer < EOperation > {

	/**
	 * Package parent de la classe.
	 */
	private EPackages parent;

	/**
	 * Constructeur d'un classifier à partir de son nom et de son parent.
	 * @param name Nom du classifier
	 * @param parent Parent du classifier
	 */
	public EClassifier(final String name, final EPackages parent) {
		super(name);
		setParent(parent);
	}

	/**
	 * Vérifi si l'élement courant contiens des  requêtes OCL.
	 * @return True si des requêtes OCL sont présentes.
	 */
	public boolean containsOcls() {
		boolean contain = false;
		for (final EOperation op : this) {
			if (op.containsOcls()) {
				contain = true;
				break;
			}
		}
		return contain;
	}

	/**
	 * @return Le nom pleinement qualifié de la classe
	 */
	public String getFullQualifiedName() {
		return getParent().getQualifiedName() + "::" + getName();
	}

	/**
	 * @return Le package auquel cette classe appartiens
	 */
	public EPackages getParent() {
		return parent;
	}

	/**
	 * Redefinis le parent de la classe.
	 * @param parent Le nouveau parent
	 */
	public void setParent(final EPackages parent) {
		if (parent == null) {
			throw new IllegalArgumentException();
		}
		this.parent = parent;
	}
}
