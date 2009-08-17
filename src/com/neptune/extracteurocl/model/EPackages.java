package com.neptune.extracteurocl.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.neptune.extracteurocl.control.Disposable;

/**
 * Représentation d'un Package.
 * @author Arnaud VERSINI
 *
 */
public final class EPackages
extends ENamedElement
implements Disposable {

	/**
	 * Liste des sous packages.
	 */
	private final Collection < EPackages > packages
			= new ArrayList < EPackages > ();

	/**
	 * Liste des Classifiers contenu dans ce package.
	 */
	private final Collection < EClassifier > classifiers
			= new ArrayList < EClassifier > ();

	/**
	 * Package parent ou null si orphelin.
	 */
	private EPackages parent;

	/**
	 * Création d'un package orphelin.
	 * @param name Nom du package
	 */
	public EPackages(final String name) {
		this(name, null);
	}

	/**
	 * Création d'un package avec parent.
	 * @param name Nom du package
	 * @param parent Parent ou null
	 */
	public EPackages(final String name, final EPackages parent) {
		super(name);
		setParent(parent);
	}

	public void addClassifier(final EClassifier classifier) {
		if (classifier == null) {
			throw new IllegalArgumentException();
		}
		classifiers.add(classifier);
	}

	public void addPackage(final EPackages pack) {
		if (pack == null) {
			throw new IllegalArgumentException();
		}
		packages.add(pack);
	}

	public int countClassifiers() {
		return classifiers.size();
	}

	public int countPackages() {
		return packages.size();
	}

	public void dispose() {
		for (final EClassifier classifier : classifiers) {
			classifier.dispose();
		}
		classifiers.clear();
		for (final EPackages pack : packages) {
			pack.dispose();
		}
		packages.clear();
	}

	public Iterable<EClassifier> getClassifiersIterable() {
		return new Iterable<EClassifier>() {
			public Iterator<EClassifier> iterator() {
				return classifiers.iterator();
			}
		};
	}

	public Iterator<EClassifier> getClassifiersIterator() {
		return classifiers.iterator();
	}

	public Iterable<EPackages> getPackagesIterable() {
		return new Iterable<EPackages>() {
			public Iterator<EPackages> iterator() {
				return packages.iterator();
			}
		};
	}

	public EPackages getParent() {
		return parent;
	}

	public String getQualifiedName() {
		String name = getName();
		if (getParent() != null) {
			name = parent.getQualifiedName() + "::" + name;
		}
		return name;
	}

	public void setParent(final EPackages parent) {
		// Une des seules valeures nullable
		this.parent = parent;
	}
}
