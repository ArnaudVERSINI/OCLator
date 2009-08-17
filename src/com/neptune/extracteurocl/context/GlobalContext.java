package com.neptune.extracteurocl.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import com.neptune.extracteurocl.control.Disposable;
import com.neptune.extracteurocl.model.EPackages;

/**
 * Contexte global contenant la pile des différents elements interpretés et le
 * package principal.
 * @author Arnaud VERSINI
 * @author Jocelyne BARROT
 * @author Carine DONGMO
 */
public final class GlobalContext implements Disposable {

	/**
	 * Pile des contextes pour le parcours de l'arbre?
	 */
	private final transient Stack < IContext > contextsStack
			= new Stack < IContext > ();

	/**
	 * Package principal du modéle eCore.
	 */
	private transient EPackages mainPackage;

	/**
	 * Liste des erreurs durant le chargement.
	 */
	private final Collection < String > errorMessages
			= new ArrayList < String > ();


	/**
	 * Ajout d'une nouvelle erreur.
	 */
	public void appendError() {
		errorMessages.add(getError());
	}

	/**
	 * Dispose pour casser les liens.
	 */
	public void dispose() {
		if (mainPackage != null) {
			mainPackage.dispose();
		}
		mainPackage = null;
	}

	/**
	 * Retourne une chaine de caractére avec les erreur.
	 * @return La chaine de caractére avec l'ensemble des erreur.
	 */
	public String getError() {
		final StringBuilder ret = new StringBuilder();
		for (final IContext cont : contextsStack) {
			ret.append(cont.convertToString());
			ret.append("\n");
		}
		return ret.toString();
	}

	/**
	 * @return Une chaine de caractére contenant l'ensemble des erreur.
	 */
	public String getErrors() {
		final StringBuilder ret = new StringBuilder();
		for (final String error : errorMessages) {
			ret.append(error);
			ret.append("\n");
		}
		return ret.toString();
	}

	/**
	 * @return Le package principal
	 */
	public EPackages getPackagePrincipal() {
		return mainPackage;
	}

	/**
	 * @return true si la pile de contexte est vide
	 */
	public boolean isEmpty() {
		return contextsStack.empty();
	}

	/**
	 * @return True si le contexte contient des erreur
	 */
	public boolean isError() {
		return errorMessages.size() != 0;
	}

	/**
	 * @return Le dernier element de la pile sans le depiler
	 */
	public IContext lastElement() {
		return contextsStack.lastElement();
	}

	/**
	 * @return Le dernier élement de la pile en le depilant
	 */
	public IContext pop() {
		return contextsStack.pop();
	}

	/**
	 * @param context Le contexte à pousser
	 */
	public void push(final IContext context) {
		if (context == null) {
			throw new IllegalArgumentException();
		}
		contextsStack.push(context);
	}

	/**
	 * Redefinition du package principal.
	 * @param mainPackage Le nouveau package principal
	 */
	public void setMainPackage(final EPackages mainPackage) {
		this.mainPackage = mainPackage;
	}
}
