package com.neptune.extracteurocl.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EOperation;
import com.neptune.extracteurocl.model.EPackages;

public class TestEOperation {
	private EOperation op;

	@Test(expected=IllegalArgumentException.class)
	public void constructWithNullParent() {
		new EOperation("", "ret", null);
	}

	@Before
	public void initialistionOperation() {
		final EPackages packageParent = new EPackages("name", null);
		final EClassifier parent = new EClassifier("class", packageParent);
		op = new EOperation("toto", "Boolean", parent);
	}

	@Test
	public void nomOperation() {
		assertEquals("Le nom de l'operation est incorrect", "toto", op
				.getName());
	}

	@Test(expected=IllegalArgumentException.class)
	public void setNullOperationName() {
		op.setName(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void setNullOperationType() {
		op.setRetValueType(null);
		fail("Le type de retour d'opération ne devrait pas pouvroir être null");
	}

	@Test
	public void setOperationName() {
		op.setName("titi");
		assertEquals("Le nom de l'operation est incorrect aprés changement",
				"titi", op.getName());
	}

	@Test
	public void setRetValueType() {
		op.setRetValueType("machin");
		assertEquals("Le nom de l'operation est incorrect aprés changement",
				"machin", op.getRetValueType());
	}

	@Test
	public void typeOperation() {
		assertEquals("Le nom de l'operation est incorrect", "Boolean", op
				.getRetValueType());
	}
}