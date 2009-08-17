package com.neptune.extracteurocl.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EOperation;
import com.neptune.extracteurocl.model.EPackages;

public class TestSAXHandlerOperation extends TestSAXHandler {

	public void executeStandartOperationTest(final String chaineATraiter)
			throws IOException, SAXException {
		launchParser(chaineATraiter);
		final EPackages pack = handler.getPackage();
		assertNotNull("Le package n'existe pas", pack);

		assertEquals("Le nombre de classes est incorrect", 1, pack
				.countClassifiers());
		final EClassifier classifier = pack.getClassifiersIterator().next();

		final EOperation op = classifier.iterator().next();
		assertEquals("Nom de l'operation incorrect", op.getName(), "methode");
		assertEquals("Le type de l'opération est incorrect", op
				.getRetValueType(), "Boolean");
	}

	@Test
	public void standartOperation() throws IOException, SAXException {
		final String chaineATraiter = ctx.debutContexteContentsOperation()

		+ ctx.finContexteContentsOperation();
		executeStandartOperationTest(chaineATraiter);
	}

	// Verifi que le handler ait le même comportement si il contiens une balise
	// inconnu
	@Test
	public void standartOperationWithSupElement() throws IOException,
			SAXException {
		final String chaineATraiter = ctx.debutContexteContentsOperation()
				+ baliseSup + ctx.finContexteContentsOperation();
		executeStandartOperationTest(chaineATraiter);
	}

}
