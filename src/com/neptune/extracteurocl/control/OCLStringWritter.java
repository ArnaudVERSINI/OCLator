package com.neptune.extracteurocl.control;

import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EPackages;
import com.neptune.extracteurocl.model.OCLRequest;

/**
 * Classe retournant une chaine de caractére contenant tout les OCLs les uns à
 * la suite, cette classe servira de base de travail pour les classes de sortie
 * fichier
 *
 * @author Arnaud VERSINI
 *
 */
public final class OCLStringWritter extends OCLWritter {

	/**
	 * String builder dans lequel l'ensemble des OCLs sera stock�
	 */
	private StringBuilder chaineAEcrire = new StringBuilder();

	/**
	 * Constructeur prenant en paramétre un package déja chargé par le parseur
	 *
	 * @param pack
	 *            Le EPackage à traiter
	 */
	public OCLStringWritter(final EPackages pack) {
		super(pack);
	}

	/**
	 * Simple mise à null des réferences
	 */
	@Override
	public void dispose() {
		super.dispose();
		chaineAEcrire = null;
	}

	/**
	 * Evenement non utilisé
	 *
	 * @param classifier Classifier que l'on devrait traiter
	 * @return True pour continuer
	 */
	@Override
	public boolean onClassifier(final EClassifier classifier) {
		return true;
	}

	/**
	 * Traitement lancé par la classe parente pour le traitement
	 * de chaque code OCL du modéle.
	 *
	 * @param ocl La requete OCL à traiter
	 */

	@Override
	public void onOCLRequest(final OCLRequest ocl) {
		chaineAEcrire.append(OCLWritter.convertOclToString(ocl));
	}

	/**
	 * Aucune initialisation nécessaire
	 */
	@Override
	public void onSaveOCLStart() {
	}

	/**
	 * Conversion de l'ensemble des codes OCLs avec leur contexte
	 * en chaine de caractére.
	 *
	 * @return Chaine avec les codes OCLs
	 */

	@Override
	public String toString() {
		return chaineAEcrire.toString();
	}
}
