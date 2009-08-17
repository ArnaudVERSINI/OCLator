package com.neptune.extracteurocl;

public final class ElementCreation {
	private static final class Tuple {
		private final String elementUn;
		private final String elementDeux;

		private Tuple(final String elementUn, final String elementDeux) {
			this.elementDeux = elementDeux;
			this.elementUn = elementUn;
		}
	}

	private static final ElementCreation INSTANCE = new ElementCreation();

	private static final String EPARAMETER = "eParameters";
	private static final String ECLASSIFIER = "eClassifiers";
	private static final String EPACKAGE = "ecore:EPackage";
	private static final String EOPERATION = "eOperations";
	private static final String DETAILS = "details";
	private static final String CONTENTS = "contents";

	public static String ePackageStandartCloseElement() {
		return "</ecore:EPackage>\n";
	}

	public static ElementCreation getInstance() {
		return INSTANCE;
	}

	/**
	 * Afin d'éviter l'instanciation.
	 */
	private ElementCreation() {

	}

	public String contentsEndElement() {
		return createEndElement(CONTENTS);
	}

	public String contentsStartElement(final String name, final String type) {
		final Tuple[] tuples = new Tuple[] {
				new Tuple("name", name),
				new Tuple("xsi:type", "ecore:EOperation"),
				new Tuple("eType", "#//" + type)
		};
		return createStartElement(CONTENTS, tuples);
	}

	public String createEndElement(final String name) {
		return "</" + name + ">\n";
	}

	public String createStartElement(final String name, final Tuple[] tuples) {
		String ret = "<" + name;
		for (final Tuple tuple : tuples) {
			ret += " " + tuple.elementUn + "=\"" + tuple.elementDeux + "\"";
		}

		ret += ">\n";
		return ret;
	}

	public String detailsStandartEndElement() {
		return createEndElement(DETAILS);
	}

	public String detailsStartElement(final String name, final String oclRequest,
			final String oclComment) {

		final String value = oclComment + "&#xD;&#xA;&lt;code>" + oclRequest
				+ "&#xD;&#xA;&lt;/code>";
		final Tuple[] tuples = new Tuple[2];
		tuples[0] = new Tuple("key", "documentation");
		tuples[1] = new Tuple("value", value);

		return createStartElement(DETAILS, tuples);
	}

	public String eClassifierStandartEndElement() {
		return createEndElement(ECLASSIFIER);
	}

	public String eClassifierStandartStartElement(final String eclassifierName) {

		final Tuple[] tuples = new Tuple[2];
		tuples[0] = new Tuple("name", eclassifierName);
		tuples[1] = new Tuple("xsi:type", "ecore:EClass");

		return createStartElement(ECLASSIFIER, tuples);
	}

	public String eOperationStandartEndElement() {
		return createEndElement(EOPERATION);
	}

	public String eOperationStandartStartElement(final String name,
			final String type) {

		final Tuple[] tuples = new Tuple[] {
			new Tuple("name", name),
			new Tuple("eType", "#//" + type)
		};

		return createStartElement(EOPERATION, tuples);
	}

	public String ePackageHeaderStartElement(final String packageName) {
		final Tuple[] tuples = new Tuple[] {
			new Tuple("name", packageName),
			new Tuple("xmi:version", "2.0"),
			new Tuple("xmlns:xmi", "http://www.omg.org/XMI"),
			new Tuple(
					"xmlns:ecore",
					"http://www.eclipse.org/emf/2002/Ecore"),
			new Tuple(
					"xmlns:xsi",
					"http://www.w3.org/2001/XMLSchema-instance")
		};

		return createStartElement(EPACKAGE, tuples);
	}

	public String ePackageStandartStartElement(final String packageName) {
		final Tuple[] tuples = new Tuple[1];
		tuples[0] = new Tuple("name", packageName);
		return createStartElement(EPACKAGE, tuples);
	}

	public String eParameterStandartEndElement() {
		return createEndElement(EPARAMETER);
	}

	public String eParameterStartElement(final String name, final String type) {
		final Tuple[] tuples = new Tuple[] {
				new Tuple("name", name),
				new Tuple("eType", "#//" + type)
		};
		return createStartElement(EPARAMETER, tuples);
	}

}
