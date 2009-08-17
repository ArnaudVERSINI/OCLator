package com.neptune.extracteurocl.control;

/**
 * Interface implementé par toutes les classes du modéle
 * pour permettre une mise à null des pointeurs.
 *
 * @author Arnaud VERSINI
 *
 */
public interface Disposable {

	/**
	 * Mise à Null des pointeurs et des listes.
	 */
	void dispose();
}
