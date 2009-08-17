package com.neptune.extracteurocl.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EOperation;
import com.neptune.extracteurocl.model.EPackages;
import com.neptune.extracteurocl.model.EParameter;

public class TestSaxHandlerParameter extends TestSAXHandler {

	@Test
	public void operationStandartAvecParametre() throws SAXException,
			IOException {
		verificationParameterStandart(ctx.debutStandartParameter()
				+ ctx.finContexteStandartParameter());
	}

	@Test
	public void operationStandartAvecParametreBaliseSup() throws SAXException,
			IOException {
		verificationParameterStandart(ctx.debutStandartParameter()
				+ baliseSup + ctx.finContexteStandartParameter());
	}

	public void verificationParameterStandart(final String chaineATraiter)
			throws IOException, SAXException {

		launchParser(chaineATraiter);
		final EPackages pack = handler.getPackage();
		assertNotNull("Le package n'existe pas", pack);

		assertEquals("Le nombre de classes est incorrect", 1, pack
				.countClassifiers());
		final EClassifier classifier = pack.getClassifiersIterator().next();
		assertNotNull("Le classifier n'existe pas", classifier);

		final EOperation operation = classifier.iterator().next();
		assertNotNull("L'opération n'existe pas", operation);

		final EParameter parameter = operation.iterator().next();
		assertNotNull("Le paramétre n'existe pas", operation);

		assertEquals("Le nom du paramétre est incorrect", "param", parameter
				.getName());
		assertEquals("Le type du paramétre est incorrect", "Integer", parameter
				.getType());
	}
}
