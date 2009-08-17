package com.neptune.extracteurocl.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Arnaud VERSINI
 *
 */
public final class EOperation
extends EContainer<EParameter> {

	/**
	 * Liste des requetes OCLs associés à l'opération.
	 */
	private final Collection < OCLRequest > oclRequests
			= new ArrayList < OCLRequest >();

	/**
	 * Classe auquel la méthode est rataché..
	 */
	private EClassifier parent;

	/**
	 * Type de valeur de retour.
	 */
	private String retValueType;

	/**
	 * Création d'une EOperation
	 * avec un pére, un nom et une valeur de retour.
	 * @param name Nom de l'EOperation
	 * @param retValueType Type de la valeur de retour de l'operation
	 * @param parent classifier ayant cette opéartion
	 */
	public EOperation(
			final String name,
			final String retValueType,
			final EClassifier parent) {
		super(name);
		setParent(parent);
		setRetValueType(retValueType);
	}

	public void addOcl(final OCLRequest request) {
		oclRequests.add(request);
	}

	public boolean containsOcls() {
		return oclRequests.size() > 0;
	}

	public Collection<OCLRequest> getOcls() {
		return oclRequests;
	}

	public EClassifier getParent() {
		return parent;
	}

	public String getRetValueType() {
		return retValueType;
	}

	private void setParent(final EClassifier parent) {
		if (parent == null) {
			throw new IllegalArgumentException();
		}
		this.parent = parent;
	}

	public void setRetValueType(final String retValueType) {
		if (retValueType == null) {
			throw new IllegalArgumentException();
		}
		this.retValueType = retValueType;
	}
}
