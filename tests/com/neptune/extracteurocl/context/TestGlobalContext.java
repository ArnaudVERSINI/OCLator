package com.neptune.extracteurocl.context;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import com.neptune.extracteurocl.context.GlobalContext;
import com.neptune.extracteurocl.context.IContext;

public final class TestGlobalContext {

	private GlobalContext globalcontext;

	@Test(expected = IllegalArgumentException.class)
	public void addNullContexte() {
		globalcontext.push(null);
	}

	@Test
	public void getErrors() {
		final String valueContextOne = "La valeur du contexte 1";
		final String valueContextTwo = "La valeur du contexte 2";

		final IContext contextErrorOne = createMock(IContext.class);
		final IContext contextErrorTwo = createMock(IContext.class);

		expect(
				contextErrorOne
				.convertToString())
				.andReturn(valueContextOne)
				.times(2);
		expect(
				contextErrorTwo
				.convertToString())
				.andReturn(valueContextTwo);

		replay(contextErrorOne);
		replay(contextErrorTwo);

		globalcontext.push(contextErrorOne);
		globalcontext.appendError();
		assertEquals(valueContextOne, globalcontext.getErrors().trim());
		assertEquals(true, globalcontext.isError());

		globalcontext.push(contextErrorTwo);
		assertEquals(true, globalcontext.isError());

		globalcontext.appendError();
		assertEquals(
				valueContextOne + "\n\n"
					+ valueContextOne + "\n"
					+ valueContextTwo,
				globalcontext.getErrors().trim());
		verify(contextErrorOne);
	}

	@Before
	public void initGlobalContext() {
		globalcontext = new GlobalContext();

	}

	@Test
	public void pushAndPop() {
		final IContext context = createMock(IContext.class);
		replay(context);
		globalcontext.push(context);
		assertSame(context, globalcontext.lastElement());
		assertSame(context, globalcontext.pop());
		verify(context);
	}

}
