package com.neptune.extracteurocl.handler;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.neptune.extracteurocl.ElementCreation;
import com.neptune.extracteurocl.context.BadContext;
import com.neptune.extracteurocl.context.ContextClassifier;
import com.neptune.extracteurocl.context.ContextPackage;

public class TestSAXHandlerError extends TestSAXHandler {

	@Test
	public void packageDansClassifier() throws IOException, SAXException {
		final String chaineATraiter = ctx.debutContexteStandartClassifier()
				+ elt.ePackageStandartStartElement("package")
				+ ElementCreation.ePackageStandartCloseElement()
				+ ctx.finContexteStandartClassifier();
		launchParser(chaineATraiter);

		// Les retours chariot autour ne sont pas importants
		final String error = handler.getErrorMessages().trim();
		String expected = ContextPackage.class.getCanonicalName()
				+ " : [EPackage name=\"toto\" version=\"2.0\" ]\n";
		expected += ContextClassifier.class.getCanonicalName()
				+ " : [eClassifiers name=\"Element\" type=\"ecore:EClass\" ]\n";
		expected += BadContext.class.getCanonicalName()
				+ " : [EPackage name=\"package\" ]";
		assertEquals(true, handler.isError());
		assertEquals("Rendu de l'erreur incorrect", expected, error);
	}

	@Test
	public void packageDansClassifierAvecBaliseSupp() throws IOException,
			SAXException {
		final String chaineATraiter = ctx.debutContexteStandartClassifier()
				+ elt.ePackageStandartStartElement("package") + baliseSup
				+ ElementCreation.ePackageStandartCloseElement()
				+ ctx.finContexteStandartClassifier();

		launchParser(chaineATraiter);

		// Les retours chariot autour ne sont pas importants
		final String error = handler.getErrorMessages().trim();
		String expected = ContextPackage.class.getCanonicalName()
				+ " : [EPackage name=\"toto\" version=\"2.0\" ]\n";
		expected += ContextClassifier.class.getCanonicalName()
				+ " : [eClassifiers name=\"Element\" type=\"ecore:EClass\" ]\n";
		expected += BadContext.class.getCanonicalName()
				+ " : [EPackage name=\"package\" ]";
		assertEquals(true, handler.isError());
		assertEquals("Rendu de l'erreur incorrect", expected, error);
	}
}
