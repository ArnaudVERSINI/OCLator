package com.neptune.extracteurocl.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.neptune.extracteurocl.model.EPackages;

/**
 * Test du code du modéle de package.
 * @author Arnaud VERSINI
 */
public final class TestEPackage {
	/**
	 * Le package à tester.
	 */
	private EPackages op;

	@Test(expected=IllegalArgumentException.class)
	public void addNullClassifiers() {
		op.addClassifier(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void addNullPackages() {
		op.addPackage(null);
	}

	@Test
	public void addPackageAndCOunt() {
		assertEquals(0, op.countPackages());
		op.addPackage(new EPackages("toto", op));
		op.addPackage(new EPackages("tata", op));
		op.addPackage(new EPackages("tutu", op));
		assertEquals(3, op.countPackages());
	}

	/**
	 * Vérification du nom pleinement qualifié du package.
	 */
	@Test
	public void getFullQualifiedName() {
		op.setParent(new EPackages("titi", null));
		assertEquals("Le nom qualifié n'est pas correct",
				"titi::toto",
				op.getQualifiedName());
	}

	/**
	 * Initialisation du package à tester.
	 */
	@Before
	public void initialistionOperation() {
		op = new EPackages("toto", null);
	}

	/**
	 * Vérification du nom du package.
	 */
	@Test
	public void nomPackage() {
		assertEquals(
				"Le nom de l'operation est incorrect",
				"toto",
				op.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setNullPackageName() {
		op.setName(null);
	}

	@Test
	public void setPackageName() {
		op.setName("titi");
		assertEquals(
				"Le nom de l'operation est"
				+ " incorrect aprés changement",
				"titi",
				op.getName());
	}
}