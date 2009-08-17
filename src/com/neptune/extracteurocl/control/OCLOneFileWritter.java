package com.neptune.extracteurocl.control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.neptune.extracteurocl.event.FileExistEvent;
import com.neptune.extracteurocl.event.Observable;
import com.neptune.extracteurocl.event.Observer;
import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EPackages;
import com.neptune.extracteurocl.model.OCLRequest;

/**
 * Création d'un fichier contenant l'ensemble des requêtes OCL.
 * @author Arnaud VERSINI
 */
public final class OCLOneFileWritter extends OCLWritter {

	/**
	 * Flux de sortie.
	 */
	private transient FileWriter output;

	/**
	 * Nom du fichier de sortie.
	 */
	private final String outputFileName;

	/**
	 * Observateur d'évenement.
	 */
	private final Observable < FileExistEvent > obsExist
			= new Observable < FileExistEvent > ();

	/**
	 * Initialisation de la sortie sur un fichier
	 * du pack passé en paramétre.
	 * @param pack Le pack à sortir
	 * @param filename Nom du fichier de sortie
	 * @throws IOException En cas d'erruer lors de l'écriture
	 */
	public OCLOneFileWritter(final EPackages pack, final String filename)
	throws IOException {
		super(pack);
		outputFileName = filename;

	}

	/**
	 * Ajout d'un gestionnaire d'evenement.
	 * @param observer Le nouvel intercepteur
	 */
	public void attachFileExist(
			final Observer < FileExistEvent > observer) {
		obsExist.attach(observer);
	}

	/**
	 * Suppression d'un gestionnaire d'evenemet.
	 * @param listener Le nouvel ecouteur
	 */
	public void detachFileExist(
			final Observer < FileExistEvent > listener) {
		obsExist.detach(listener);
	}

	/**
	 * Mise à null des pointeurs.
	 */
	@Override
	public void dispose() {
		super.dispose();
		try {
			if(output != null) {
				output.close();
			}
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Retourne simplement que l'on peut continuer le traitement.
	 * @param classifier Le classifier
	 * @return True
	 */
	@Override
	public boolean onClassifier(final EClassifier classifier) {
		return true;
	}

	/**
	 * Sortie de la requete OCL dans le filewritter.
	 * @param ocl Requête OCL à écrire
	 * @exception IOException Si erreur lors de l'écriture
	 */
	@Override
	public void onOCLRequest(final OCLRequest ocl)
	throws IOException {
		if (output != null) {
			output.append(OCLWritter.convertOclToString(ocl));
		}
	}

	/**
	 * Fonction d'ouverture du fichier unique de sortie.
	 * @throws IOException Erreur lors de l'ouverture du fichier
	 */
	@Override
	public void onSaveOCLStart()
	throws IOException {
		final File outputFile = new File(outputFileName);
		if (outputFile.exists()) {
			final FileExistEvent event
					= new FileExistEvent(outputFileName);
			obsExist.notify(event);
			// N'écrivant qu'un seul fichier,
			//l'abnnulation du tout ou seulement
			//du fichier courant ont les meme consequances
			if (!event.isContinue() || !event.isOverwrite()) {
				return;
			}
		}
		output = new FileWriter(outputFileName, false);
	}
}
