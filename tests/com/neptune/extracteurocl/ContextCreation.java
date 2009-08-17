package com.neptune.extracteurocl;

/**
 * Création du contexte XML.
 * @author Arnaud VERSINI
 *
 */
public final class ContextCreation {

	/**
	 * Instance unique du singleton.
	 */
	private static final ContextCreation INSTANCE = new ContextCreation();

	/**
	 * @return L'instance du singleton.
	 */
	public static ContextCreation getInstance() {
		return INSTANCE;
	}

	private final ElementCreation elt = ElementCreation.getInstance();

	private ContextCreation() {
	}

	public String debutContexteContentsOperation() {
		return
				debutContexteStandartClassifier()
				+ elt.contentsStartElement("methode", "Boolean");
	}

	public String debutContexteStandartClassifier() {
		return debutContexteStandartPackage()
				+ elt.eClassifierStandartStartElement("Element");
	}

	public String debutContexteStandartOperation() {
		return debutContexteStandartClassifier()
				+ elt.eOperationStandartStartElement("methode", "Boolean");
	}

	public String debutContexteStandartPackage() {
		return elt.ePackageHeaderStartElement("toto");
	}

	public String debutStandartDetails() {
		return debutContexteStandartOperation()
				+ elt.detailsStartElement("request", "true", "comment");
	}

	public String debutStandartParameter() {
		return debutContexteStandartOperation()
				+ elt.eParameterStartElement("param", "Integer");
	}

	public String finContexteContentsOperation() {
		return elt.contentsEndElement() + finContexteStandartClassifier();

	}

	public String finContexteStandartClassifier() {

		return elt.eClassifierStandartEndElement()
				+ finContexteStandartPackage();
	}

	public String finContexteStandartDetails() {
		return elt.detailsStandartEndElement() + finContexteStandartOperation();
	}

	public String finContexteStandartOperation() {

		return elt.eOperationStandartEndElement()
				+ finContexteStandartClassifier();
	}

	public String finContexteStandartPackage() {
		return ElementCreation.ePackageStandartCloseElement();
	}

	public String finContexteStandartParameter() {
		return elt.eParameterStandartEndElement()
				+ finContexteStandartOperation();
	}
}
