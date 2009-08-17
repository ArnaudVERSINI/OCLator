package com.neptune.extracteurocl.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.neptune.extracteurocl.control.Disposable;

/**
 * Cette classe est purement utiliraire permet
 * de créer un classe contenant plusieurs objets d'un même type.
 *
 * @author Arnaud VERSINI
 *
 * @param <Content> Le contenu de l'element courant
 */
public class EContainer < Content extends Disposable >
extends ENamedElement
implements Disposable, Iterable < Content > {

	/**
	 * Collection contenant le contenu de l'élement courant.
	 */
	private Collection < Content > content
			= new ArrayList < Content > ();

	/**
	 * Costructeur avec nom d'un container.
	 * @param name Nom du container
	 */
	public EContainer(final String name) {
		super(name);
	}

	/**
	 * Ajout d'un element.
	 * @param element L'élement à ajouter
	 */
	public void add(final Content element) {
		if (element == null) {
			throw new IllegalArgumentException();
		}
		content.add(element);
	}

	/**
	 * Mise à zero recursive des réferences.
	 */
	@Override
	public void dispose() {
		for (final Content disposable : content) {
			disposable.dispose();
		}
		content.clear();
		content = null;
	}

	/**
	 * @return L'itérateur sur tout els sous éléments.
	 */
	public Iterator<Content> iterator() {
		return content.iterator();
	}

}
