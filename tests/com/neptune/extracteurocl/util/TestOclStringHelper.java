package com.neptune.extracteurocl.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.neptune.extracteurocl.util.OclStringHelper;

public class TestOclStringHelper {
	@Test
	public void chaineContenantAucuneRequete() {
		assertEquals("La chaine devrait etre consideré comme bidon", false,
				OclStringHelper.isAnOcl("Chaine sans requete"));
	}

	@Test
	public void chaineContenantDuCode() {
		final String result = OclStringHelper
				.extractOclRequest("blabla<code>Voila d'autres mots</code>");
		assertEquals("La chaine devrait comprendre un commentaire",
				"Voila d'autres mots", result);
	}

	@Test
	public void chaineCOntenantPasDeCommentaire() {
		assertNull(OclStringHelper.extractOclComment("<code>Bla bla</code>"));
	}

	@Test
	public void chaineCOntenantPasDeRequete() {
		assertNull(OclStringHelper.extractOclRequest("Bla bla bla bla bla"));
	}

	@Test
	public void chaineContenantPasDeType() {
		assertNull(OclStringHelper.extractType("#//"));
	}

	@Test
	public void chaineContenantUnCommentaire() {
		final String result = OclStringHelper
				.extractOclComment("blabla<code>Voila d'autres mots</code>");
		assertEquals("La chaine devrait comprendre un code", "blabla", result);
	}

	@Test
	public void chaineContenantUneRequeteOcl() {
		assertEquals("La chaine devrait etre consider comme une requete", true,
				OclStringHelper.isAnOcl("<code>Voila une belle requete</code>"));
	}
}
