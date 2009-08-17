package com.neptune.extracteurocl.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.neptune.extracteurocl.model.EParameter;

public class TestEParameter {
	private EParameter op;

	@Before
	public void initialistionOperation() {
		op = new EParameter("toto", "titi");
	}

	@Test
	public void nomParameter() {
		assertEquals("Le nom du paramétre est incorrect", "toto", op.getName());
	}

	@Test
	public void setNullParameterName() {
		try {
			op.setName(null);
			fail("Le nom du parametre ne devrait pas pouvroir être null");
		} catch (final IllegalArgumentException ex) {

		}
	}

	@Test
	public void setNullParameterType() {
		try {
			op.setType(null);
			fail("Le type du parametre ne devrait pas pouvroir être null");
		} catch (final IllegalArgumentException ex) {

		}
	}

	@Test
	public void setParameterName() {
		op.setName("titi");
		assertEquals("Le nom du paramétre est incorrect aprés changement",
				"titi", op.getName());
	}

	@Test
	public void setParameterType() {
		op.setType("Real");
		assertEquals("Le type du paramétre est incorrect aprés changement",
				"Real", op.getType());
	}
}