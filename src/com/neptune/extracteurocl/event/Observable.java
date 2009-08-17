package com.neptune.extracteurocl.event;

import java.util.ArrayList;
import java.util.Collection;

/**
 * L'implémentation de la classe subject du pattern observer
 *
 * @author arnaud
 *
 * @param <Event>
 */
public class Observable<Event> {

	private final Collection<Observer<Event>> observers = new ArrayList<Observer<Event>>();

	public final void attach(final Observer<Event> listener) {
		if (!observers.contains(listener)) {
			observers.add(listener);
		}
	}

	public final void detach(final Observer<Event> listener) {
		if (observers.contains(listener)) {
			observers.remove(listener);
		}
	}

	public final void notify(final Event event) {
		for (final Observer<Event> observer : observers) {
			observer.update(event);
		}
	}
}
