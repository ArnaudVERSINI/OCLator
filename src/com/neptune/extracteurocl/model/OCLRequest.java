package com.neptune.extracteurocl.model;

/**
 * Représentation dans le modéle d'une requete OCL.
 * @author Arnaud VERSINI
 *
 */
public final class OCLRequest {

	/**
	 * Corps de la requête OCL.
	 */
	private String oclText;
	/**
	 * Commentaire associé.
	 */
	private String oclComment;
	/**
	 * Parent à qui appartient la requête OCL.
	 */
	private EOperation parent;

	/**
	 * Création d'une nouvelle requête OCL.
	 * @param oclText Corps de la requete
	 * @param oclComment Commentaire
	 * @param parent Opération parente
	 */
	public OCLRequest(
			final String oclText,
			final String oclComment,
			final EOperation parent) {
		setOclComment(oclComment);
		setOclText(oclText);
		setParent(parent);
	}

	/**
	 * @return Le commentaire associé à la requete OCL
	 */
	public String getOclComment() {
		return oclComment;
	}

	/**
	 * @return Le corps de la requete OCL
	 */
	public String getOclText() {
		return oclText;
	}

	/**
	 * @return Le parent de la requete OCL.
	 */
	public EOperation getParent() {
		return parent;
	}

	/**
	 * Redéfinit le commentaire de la requete OCL.
	 * @param oclComment Le nouveau commentaire
	 */
	public void setOclComment(final String oclComment) {
		if (oclComment == null) {
			throw new IllegalArgumentException();
		}
		this.oclComment = oclComment;
	}

	/**
	 * Redéfinit le corps de la requete OCL.
	 * @param oclText Le nouveau corps
	 */
	public void setOclText(final String oclText) {
		if (oclText == null) {
			throw new IllegalArgumentException();
		}
		this.oclText = oclText;
	}

	/**
	 * Redéfinit le parent de la requete OCL.
	 * @param parent Le nouveau parent
	 */
	public void setParent(final EOperation parent) {
		if (parent == null) {
			throw new IllegalArgumentException();
		}
		this.parent = parent;
	}
}
