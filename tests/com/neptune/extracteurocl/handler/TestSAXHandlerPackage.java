package com.neptune.extracteurocl.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.neptune.extracteurocl.ElementCreation;
import com.neptune.extracteurocl.model.EPackages;

public class TestSAXHandlerPackage extends TestSAXHandler {
	private void checkStandartPackage(final String chaineATraiter)
			throws IOException, SAXException {
		launchParser(chaineATraiter);
		final EPackages pack = handler.getPackage();
		assertNotNull("Le package n'existe pas", pack);
		assertEquals("Le nom du package n'est pas correctement extrait",
				"toto", pack.getName());
		assertEquals(pack.countClassifiers(), 0);
		assertEquals(pack.countPackages(), 0);
	}

	@Test
	public void standartPackage() throws SAXException, IOException {

		final String chaineATraiter = ctx.debutContexteStandartPackage()
				+ ctx.finContexteStandartPackage();
		checkStandartPackage(chaineATraiter);
	}

	@Test
	public void standartPackageWithInternalPackage() throws IOException,
			SAXException {

		String chaineATraiter = elt.ePackageHeaderStartElement("pére");
		chaineATraiter += elt.ePackageStandartStartElement("filston");
		chaineATraiter += ElementCreation.ePackageStandartCloseElement();
		chaineATraiter += ElementCreation.ePackageStandartCloseElement();

		launchParser(chaineATraiter);

		final EPackages pere = handler.getPackage();
		assertNotNull("Package inéxistant", pere);

		final EPackages filston = pere.getPackagesIterable().iterator().next();
		assertNotNull("Package inéxistant", pere);

		assertEquals("Nom des packages incorrects", "pére", pere.getName());
		assertEquals("Nom des packages incorrects", "filston", filston
				.getName());
	}

	@Test
	public void standartPackageWithSupElement() throws SAXException,
			IOException {

		final String chaineATraiter = ctx.debutContexteStandartPackage() + baliseSup
				+ ctx.finContexteStandartPackage();
		checkStandartPackage(chaineATraiter);
	}

}
