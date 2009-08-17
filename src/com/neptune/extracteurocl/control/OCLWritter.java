package com.neptune.extracteurocl.control;

import java.io.IOException;

import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EOperation;
import com.neptune.extracteurocl.model.EPackages;
import com.neptune.extracteurocl.model.EParameter;
import com.neptune.extracteurocl.model.OCLRequest;

/**
 * Classe abstraite de sortie des reqêtes OCLs.
 *
 * @author Arnaud VERSINI
 *
 */
public abstract class OCLWritter
implements Disposable {

	/**
	 * Conversion d'un requete OCL en chaine de caractére.
	 * @param ocl La requete OCL à convertir
	 * @return La requête OCL convrtie en chaine de caractére
	 */
	public static final String convertOclToString(final OCLRequest ocl) {

		final EOperation parent = ocl.getParent();
		final String oclText = ocl.getOclText();
		final String oclComment = ocl.getOclComment();
		final String operationName = parent.getName();
		final String retValueType = parent.getRetValueType();
		final String classifierName = parent
				.getParent()
				.getFullQualifiedName();

		final StringBuilder res = new StringBuilder();
		res.append("-- ");
		res.append(oclComment);
		res.append("\n");

		res.append("context ");
		res.append(classifierName);
		res.append("\n");

		final StringBuilder parameterList = new StringBuilder();
		boolean prems = true;

		for (final EParameter parameter : parent) {
			if (prems) {
				prems = false;
			} else {
				parameterList.append(", ");
			}

			parameterList.append(parameter.getName());
			parameterList.append(" : ");
			parameterList.append(parameter.getType());
		}
		res.append("def: ");
		res.append(operationName);
		res.append("(");
		res.append(parameterList);
		res.append(") : ");
		res.append(retValueType);
		res.append("\n");

		res.append(oclText);
		res.append("\n\n");

		return res.toString();
	}

	/**
	 * Package à traiter.
	 */
	private EPackages pack;

	/**
	 * Constructeur de sortie de package.
	 * @param pack Le package à écrire
	 */
	public OCLWritter(final EPackages pack) {
		if (pack == null) {
			throw new IllegalArgumentException();
		}
		this.pack = pack;
	}

	/**
	 * Suppression des liens.
	 */
	public void dispose() {
		pack = null;
	}

	/**
	 * Fonction executé à chaque classifier parcouru.
	 * @param classifier Classifier à traiter
	 * @throws IOException Si erreur d'entrée sortie.
	 * @return true si l'on doit continuer
	 */
	public abstract boolean onClassifier(final EClassifier classifier)
	throws IOException;

	/**
	 * Fonction executé à chaque requete OCL.
	 * @param ocl Requete OCL à traiter.
	 * @throws IOException Exception levé en cas d'erreur d'entrée sortie.
	 */

	public abstract void onOCLRequest(OCLRequest ocl)
	throws IOException;

	/**
	 * Fonction executé lors du lancement de l'écriture.
	 * @throws IOException Erreur d'entrée sortie lors de l'initialisation
	 */
	public abstract void onSaveOCLStart()
	throws IOException;

	/**
	 * Sauvegarde des requêtes OCLs.
	 * @throws IOException Erreur d'entrée sortie lors de la sauvegarde
	 */
	public final void saveOCLs()
	throws IOException {
		onSaveOCLStart();
		traitementPackage(pack);
	}

	/**
	 * Traitement des packages, itératif et récursif pour les packages,
	 * seulement itératifd dans le cas des classifiers.
	 * @param pack Le package à traiter
	 * @throws IOException Si erreur d'entrée sortie
	 */
	public final void traitementPackage(final EPackages pack)
	throws IOException {
		for (final EPackages packb : pack.getPackagesIterable()) {
			traitementPackage(packb);
		}
		for (final EClassifier classe : pack.getClassifiersIterable()) {
			treatClassifier(classe);
		}
	}

	/**
	 * Traitement d'un classifier avec parcours.
	 * @param classe Le classifier à traiter
	 * @throws IOException Erreur d'entrée sortie
	 */
	private void treatClassifier(final EClassifier classe)
	throws IOException {
		if (classe.containsOcls() && onClassifier(classe)) {
			for (final EOperation operation : classe) {
				treateOperation(operation);
			}
		}
	}

	/**
	 * Traitement d'une opération avec parcours.
	 * @param operation L'opération à traiter
	 * @throws IOException Erreur d'entrée sortie
	 */
	private void treateOperation(final EOperation operation)
	throws IOException {
		for (final OCLRequest req : operation.getOcls()) {
			onOCLRequest(req);
		}
	}
}
