package com.neptune.extracteurocl.context;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.Attributes;

import com.neptune.extracteurocl.context.AbstractInternalContext;
import com.neptune.extracteurocl.model.ENamedElement;

public abstract class TestContext<EElement extends ENamedElement, ContextParam extends AbstractInternalContext<EElement>> {

	protected static final String DEFAULT_URI = "uri";
	protected static final String DEFAULT_ATTR = "attr";
	protected static final String DEFAULT_LOCALNAME = "localname";

	protected Attributes attrs = null;
	private ContextParam context = null;
	private EElement content;

	public abstract EElement createContent();

	public abstract ContextParam createContext(EElement content, String uri,
			String localName, String attr, Attributes attrsAttributes);

	@SuppressWarnings("unchecked")
	@Test
	public void duplicate() {

		final Attributes attrsDup = createMock(Attributes.class);
		expect(attrsDup.getLength()).andReturn(0);
		replay(attrsDup);

		final ContextParam context = (ContextParam) this.context.duplicate(
				TestContext.DEFAULT_URI, TestContext.DEFAULT_LOCALNAME,
				TestContext.DEFAULT_ATTR, attrsDup);

		assertEquals(
				"Le pointeur n'est pas le meme que celui passé aprés le set",
				content, context.getContent());

	}

	@Test(expected=IllegalArgumentException.class)
	public void getAndSetContent() {
		assertEquals(
				"Le pointeur n'est pas le meme que celui passé à l'origine",
				content, context.getContent());
		final EElement content = createContent();
		context.setContent(content);
		assertEquals(
				"Le pointeur n'est pas le meme que celui passé aprés le set",
				content,
				context.getContent());

		context.setContent(null);
	}

	@Test
	public void getterAndSetter() {
		assertEquals(DEFAULT_ATTR, context.getAttr());
		assertEquals(DEFAULT_URI, context.getUri());
		assertEquals(DEFAULT_LOCALNAME, context.getLocalName());
	}

	@Before
	public void initContextAndAttributes() {
		content = createContent();
		attrs = createMock(Attributes.class);
		expect(attrs.getLength()).andReturn(0);
		replay(attrs);
		context = createContext(content, DEFAULT_URI, DEFAULT_ATTR,
				DEFAULT_LOCALNAME, attrs);
		verify(attrs);
	}

	@After
	public abstract void verifyAttrs();
}
