package com.neptune.extracteurocl.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.neptune.extracteurocl.context.BadContext;
import com.neptune.extracteurocl.context.GlobalContext;
import com.neptune.extracteurocl.control.Disposable;
import com.neptune.extracteurocl.model.EPackages;
import com.neptune.extracteurocl.util.OclExtractorInternalException;

/**
 * Rediraction des evenementsXML vers le dispatcher.
 *
 * @author arnaud
 *
 */
public final class ECoreXMLHandler
extends DefaultHandler
implements Disposable {
	/**
	 * Contexte global de l'état du parser.
	 */
	private final transient GlobalContext context = new GlobalContext();
	/**
	 * Dispatcher d'évenement SAX.
	 */
	private final transient ECoreDispatcher dispatcher
			= ECoreDispatcher.getInstance();

	/**
	 * Suppression du contexte.
	 */
	public void dispose() {
		context.dispose();
	}

	/**
	 * Traitement de la terminaison des évenements.
	 * @param uri URI d ela balise XML
	 * @param localName Nom de la balise XML
	 * @param qName Je ne sais pas
	 */
	@Override
	public void endElement(
			final String uri,
			final String localName,
			final String qName) {
		if (!context.isEmpty()) {
			context.pop();
		}
	}

	/**
	 * @return La liste des messages d'erreur
	 */
	public String getErrorMessages() {
		return context.getErrors();
	}

	/**
	 * @return Le package principal
	 */
	public EPackages getPackage() {
		return context.getPackagePrincipal();
	}

	/**
	 * @return True si le parsing a entrainé des erreurs
	 */
	public boolean isError() {
		return context.isError();
	}

	@Override
	public void startElement(
			final String uri,
			final String localName,
			final String attr,
			final Attributes attrs)
	throws SAXException {
		try {
			if (!context.isEmpty() && !context
					.lastElement()
					.isValidContext()) {
				context.push(
						context.lastElement().duplicate(
								uri,
								localName,
								attr,
								attrs));
			} else {
				dispatcher.onStartElement(
						uri,
						localName,
						attr, attrs,context);
			}
		} catch (final OclExtractorInternalException e) {
			onError(uri, localName, attr, attrs);
		} catch (final IllegalArgumentException ex) {
			onError(uri, localName, attr, attrs);
		}
	}

	private void onError(final String uri, final String localName,
			final String attr, final Attributes attrs) {
		context.push(new BadContext(uri, localName, attr, attrs));
		context.appendError();
	}
}
