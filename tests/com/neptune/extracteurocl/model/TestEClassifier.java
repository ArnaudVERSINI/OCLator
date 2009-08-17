package com.neptune.extracteurocl.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EPackages;

public class TestEClassifier {
	private EClassifier op;

	@Test(expected=IllegalArgumentException.class)
	public void addNullOperation() {
			op.add(null);
	}

	@Test
	public void getFullQualifiedName() {
		assertEquals("Le nom qualifié du classifier est incorrect",
				"titi::toto", op.getFullQualifiedName());
	}

	@Before
	public void initialistionOperation() {
		op = new EClassifier("toto", new EPackages("titi", null));
	}

	@Test
	public void nomOperation() {
		assertEquals("Le nom de l'operation est incorrect", "toto", op
				.getName());
	}

	@Test
	public void setClassifierName() {
		op.setName("titi");
		assertEquals("Le nom de la classe est incorrect aprés changement",
				"titi", op.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setNullClassifierName() {
		op.setName(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void setNullClassifierParent() {
		op.setParent(null);
	}

}