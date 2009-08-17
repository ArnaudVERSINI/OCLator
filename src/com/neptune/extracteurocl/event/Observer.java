package com.neptune.extracteurocl.event;

/**
 * Implémentation de la classe Observer du pattern du même nom
 *
 * @author Arnaud VERSINI
 *
 * @param <Event>
 *            L'evenement produit
 */
public interface Observer<Event> {

	public void update(Event event);
}
