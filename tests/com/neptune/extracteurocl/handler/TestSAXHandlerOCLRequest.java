package com.neptune.extracteurocl.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EOperation;
import com.neptune.extracteurocl.model.EPackages;
import com.neptune.extracteurocl.model.OCLRequest;

public class TestSAXHandlerOCLRequest extends TestSAXHandler {

	public void checkStandartOCLRequest(final String xmlData) throws IOException,
			SAXException {
		launchParser(xmlData);
		final EPackages pack = handler.getPackage();
		assertNotNull("Le package n'existe pas", pack);

		assertEquals("Le nombre de classes est incorrect", 1, pack
				.countClassifiers());
		final EClassifier classifier = pack.getClassifiersIterator().next();
		assertNotNull("Le classifier n'existe pas", classifier);

		final EOperation operation = classifier.iterator().next();
		assertNotNull("L'opération n'existe pas", operation);

		final OCLRequest oclRequest = operation.getOcls().iterator().next();
		assertNotNull("La requete n'existe pas", oclRequest);
	}

	@Test
	public void oclInClassifier() throws IOException, SAXException {
		final String xmlData = ctx.debutContexteStandartClassifier()
				+ elt.detailsStartElement("name", "true", "comment")
				+ elt.detailsStandartEndElement()
				+ ctx.finContexteStandartClassifier();

		/*
		 * String expected = ContextPackage.class.getCanonicalName(); expected
		 * +=
		 * " : [EPackage key=\"documentation\" value=\"comment\n<code>true\n</code>\" ]"
		 * ;
		 *
		 * expected += ContextClassifier.class.toString(); expected +=
		 * " : [eClassifiers key=\"documentation\" value=\"comment\n<code>true\""
		 *
		 * </code>" ]
		 */
		launchParser(xmlData);
		assertEquals(true, handler.isError());
		System.out.println(handler.getErrorMessages());
	}

	@Test
	public void standartRequest() throws SAXException, IOException {
		checkStandartOCLRequest(ctx.debutStandartDetails()
				+ ctx.finContexteStandartDetails());
	}

	@Test
	public void standartRequestWithSup() throws SAXException, IOException {
		checkStandartOCLRequest(ctx.debutStandartDetails() + baliseSup
				+ ctx.finContexteStandartDetails());
	}
}
