package com.neptune.extracteurocl.handler;

import java.io.IOException;
import java.io.StringReader;

import org.junit.After;
import org.junit.Before;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.neptune.extracteurocl.ContextCreation;
import com.neptune.extracteurocl.ElementCreation;
import com.neptune.extracteurocl.handler.ECoreXMLHandler;

public class TestSAXHandler {

	public ContextCreation ctx = ContextCreation.getInstance();
	public ElementCreation elt = ElementCreation.getInstance();

	XMLReader xr = null;
	ECoreXMLHandler handler = null;

	public final String baliseSup = "<baliseSup></baliseSup>";

	@After
	public void dispose() {
		if (handler != null) {
			handler.dispose();
		}
		handler = null;
	}

	@Before
	public void initSaxHandler() throws SAXException {
		xr = XMLReaderFactory.createXMLReader();
		handler = new ECoreXMLHandler();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);
	}

	public void launchParser(final String data) throws IOException, SAXException {
		final StringReader reader = new StringReader(data);
		xr.parse(new InputSource(reader));
	}
}
