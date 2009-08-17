package com.neptune.extracteurocl.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.neptune.extracteurocl.context.ContextPackage;
import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EPackages;

public class TestSAXHandlerClassifier extends TestSAXHandler {

	public void checkStandartClassifier(final String xmlData) throws IOException,
			SAXException {
		launchParser(xmlData);

		final EPackages pack = handler.getPackage();
		assertNotNull("Le package n'existe pas", pack);

		assertEquals("Le nombre de classes est incorrect", 1, pack
				.countClassifiers());
		final EClassifier classifier = pack.getClassifiersIterator().next();
		assertEquals("Nom de classifier incorrect", "Element", classifier
				.getName());
	}

	@Test
	public void classifierInClassifier() throws IOException, SAXException {
		final String xmlData = ctx.debutContexteStandartClassifier()
				+ elt.eClassifierStandartStartElement("classifier")
				+ elt.eClassifierStandartEndElement()
				+ ctx.finContexteStandartClassifier();

		checkStandartClassifier(xmlData);

		String errorMessageExpected = ContextPackage.class.getCanonicalName()
				+ " : [EPackage name=\"toto\" version=\"2.0\" ]\n";
		errorMessageExpected += "com.neptune.extracteurocl.context.ContextClassifier : [eClassifiers name=\"Element\" type=\"ecore:EClass\" ]\n";
		errorMessageExpected += "com.neptune.extracteurocl.context.BadContext : [eClassifiers name=\"classifier\" type=\"ecore:EClass\" ]";

		assertEquals(errorMessageExpected, handler.getErrorMessages().trim());
	}

	@Test
	public void standartClassifier() throws SAXException, IOException {

		checkStandartClassifier(ctx.debutContexteStandartClassifier()
				+ ctx.finContexteStandartClassifier());
	}

	@Test
	public void standartClassifierWithSupElement() throws SAXException,
			IOException {

		checkStandartClassifier(ctx.debutContexteStandartClassifier()
				+ baliseSup + ctx.finContexteStandartClassifier());
	}

	@Test
	public void twoClassifier() throws IOException, SAXException {
		final String xmlData = ctx.debutContexteStandartPackage()
				+ elt.eClassifierStandartStartElement("Primo")
				+ elt.eClassifierStandartEndElement()
				+ elt.eClassifierStandartStartElement("Deuxio")
				+ ctx.finContexteStandartClassifier();

		launchParser(xmlData);

		final EPackages pack = handler.getPackage();
		assertNotNull("Le package n'existe pas", pack);

		assertEquals("Le nombre de classes est incorrect", 2, pack
				.countClassifiers());
		final Iterator<EClassifier> iterator = pack.getClassifiersIterator();
		final EClassifier classifierPrimo = iterator.next();
		final EClassifier classifierDeuxio = iterator.next();
		assertEquals("Nom de classifier incorrect", "Primo", classifierPrimo
				.getName());
		assertEquals("Nom de classifier incorrect", "Deuxio", classifierDeuxio
				.getName());
	}

}
