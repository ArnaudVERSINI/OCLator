package com.neptune.extracteurocl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe interne servant à aider
 * le parser à extraire les chaines de caractéres.
 *
 * @author Arnaud VERSINI
 *
 */
public final class OclStringHelper {

	/**
	 * Pattern d'extraction des OCLs.
	 */
	private static final Pattern PAT_EXTRACT_OCL = Pattern.compile(
			"<code>.+</code>", Pattern.DOTALL | Pattern.MULTILINE);

	/**
	 * Pattern d'extraction des commentaires.
	 */
	private static final Pattern PAT_EXTRACT_OCL_COMMENT = Pattern.compile(
			".+<code>", Pattern.DOTALL | Pattern.MULTILINE);

	/**
	 * Pattern d'extraction des types.
	 */
	private static final Pattern PAT_EXTRACT_TYPE = Pattern.compile("#//.+",
			Pattern.DOTALL | Pattern.MULTILINE);

	/**
	 * Extrait les commentaires qui ne sont pas entre les balises &lt;code&gt;
	 * et &lt;/code&gt;.
	 *
	 * @param ocl La requete OCl à extraire
	 * @return Commentaire OCL
	 */
	public static String extractOclComment(final String ocl) {
		final Matcher matcher = PAT_EXTRACT_OCL_COMMENT.matcher(ocl);
		if (!matcher.find()) {
			return null;
		}
		String result = matcher.group();
		result = result.replace("<code>", "");
		return result.trim();
	}

	/**
	 * Extrait le code OCL compris entre les balises &lt;code&gt; et
	 * &lt;/code&gt;.
	 * @param ocl La chaine à parser
	 * @return La requête seule
	 */
	public static String extractOclRequest(final String ocl) {
		final Matcher matcher = PAT_EXTRACT_OCL.matcher(ocl);
		if (!matcher.find()) {
			return null;
		}
		String result = matcher.group();
		result = result.replace("<code>", "");
		result = result.replace("</code>", "");
		return result.trim();
	}

	/**
	 * Extraction du type de la variable passé en paramétre.
	 *
	 * @param type Le type brut.
	 * @return Le type net.
	 */
	public static String extractType(final String type) {
		final Matcher matcher = PAT_EXTRACT_TYPE.matcher(type);
		if (!matcher.find()) {
			return null;
		}
		String result = matcher.group();
		result = result.replace("#//", "");
		if ("Set".equals(result) || "Collection".equals(result)) {
			result += "(OclVoid)";
		}
		return result.trim();
	}

	/**
	 * Vérifi si la chaine de caractére contiens une requete OCL.
	 * @param chaineAtraiter La chaine pouvant contenir une requete OCL.
	 * @return True si elle contient une requete OCL.
	 */
	public static boolean isAnOcl(final String chaineAtraiter) {
		return PAT_EXTRACT_OCL.matcher(chaineAtraiter).find();
	}
}
