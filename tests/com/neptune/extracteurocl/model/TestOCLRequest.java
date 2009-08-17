package com.neptune.extracteurocl.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EOperation;
import com.neptune.extracteurocl.model.EPackages;
import com.neptune.extracteurocl.model.OCLRequest;

public final class TestOCLRequest {
	private OCLRequest op;

	@Before
	public void initialistionOperation() {
		final EPackages packageParent = new EPackages("toto", null);
		final EClassifier classifierParent = new EClassifier("titi", packageParent);
		final EOperation operationParent = new EOperation("tata", "Boolean",
				classifierParent);

		op = new OCLRequest("toto", "titi", operationParent);
	}

	@Test(expected=IllegalArgumentException.class)
	public void onstructWithNullParent() {
		new OCLRequest(
				"texte bidon",
				"commentaire bidon",
				null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void setNullOclComment() {
			op.setOclComment(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void setNullOclParent() {
		op.setParent(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void setNullOclText() {
		op.setOclText(null);
	}

	@Test
	public void setOCLText() {
		op.setOclText("titi");
		assertEquals("Le nom du paramétre est incorrect aprés changement",
				"titi", op.getOclText());
	}

	@Test
	public void texteOCL() {
		assertEquals("Le nom du paramétre est incorrect", "toto", op
				.getOclText());
	}
}