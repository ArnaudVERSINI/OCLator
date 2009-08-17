package com.neptune.extracteurocl.event;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.Before;
import org.junit.Test;

import com.neptune.extracteurocl.event.Observable;
import com.neptune.extracteurocl.event.Observer;

public class TestObservable {

	Observer<Object> obs;

	@SuppressWarnings("unchecked")
	@Before
	public void initObserver() {
		obs = createMock(Observer.class);
	}

	@Test
	public void observer() {
		final Observable<Object> observable = new Observable<Object>();
		final Object event = new Object();
		obs.update(event);
		replay(obs);

		observable.attach(obs);
		observable.notify(event);
		observable.detach(obs);

		// Ne doit plus rien notifier
		observable.notify(new Object());
		verify(obs);
	}

}
