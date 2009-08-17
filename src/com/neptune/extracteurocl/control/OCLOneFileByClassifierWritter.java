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
 *
 * @author Arnaud VERSINI
 *
 */
public final class OCLOneFileByClassifierWritter
extends OCLWritter {

	/**
	 * Flux de sortie.
	 */
	private transient FileWriter output = null;
	/**
	 * Doit ont continuer la sortie.
	 */
	private transient boolean continue_ = true;
	/**
	 * Répertoire de sortie.
	 */
	private final transient String outputDirectory;
	/**
	 * Gestionnaire d'évenement.
	 */
	private final transient Observable < FileExistEvent > obsExist
			= new Observable < FileExistEvent > ();

	/**
	 * Initialisation de la sortie sur un fichier
	 * du pack passé en paramétre.
	 *
	 * @param pack Le pack à sortir
	 * @param outputDirectory Répertoire de sortie
	 */
	public OCLOneFileByClassifierWritter(
			final EPackages pack,
			final String outputDirectory) {
		super(pack);
		final File output = new File(outputDirectory);
		if (!output.isDirectory()) {
			throw new IllegalArgumentException();
		}

		this.outputDirectory = output.getAbsoluteFile().toString();
	}

	/**
	 * Ajoute un listener pour les évenements.
	 * @param listener Le listener
	 */
	public void attachFileExist(
			final Observer < FileExistEvent > listener) {
		obsExist.attach(listener);
	}

	/**
	 * Enleve l'évenment associé.
	 * @param listener Le listener à retirer
	 */
	public void detachFileExist(
			final Observer < FileExistEvent > listener) {
		obsExist.detach(listener);
	}

	/**
	 * Suppression de l'ensemble des pointeurs.
	 */
	@Override
	public void dispose() {
		super.dispose();
		try {
			if (output != null) {
				output.close();
			}
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Ouverture du fichier correspondant à la classe.
	 * @param classifier Classifier à enregistrer
	 * @throws IOException Si erreur lors de l'ouverture du fichier
	 * @return True si aucun probléme, exception sinon
	 */
	@Override
	public boolean onClassifier(final EClassifier classifier)
	throws IOException {
		boolean continueClassifier = false;
		if (output != null) {
			output.close();
		}
		output = null;
		// Si l'utilisateur a decidé de terminer on arrtete la
		if (continue_) {
			continueClassifier = true;
			final File outputFileName = new File(
					outputDirectory
					+ "/" + classifier.getName() + ".ocl");

			if (outputFileName.exists()) {
				String fileName = outputFileName.toString();
				final FileExistEvent event
						= new FileExistEvent(fileName);
				obsExist.notify(event);
				if (!event.isContinue()) {
					continue_ = false;
					continueClassifier = false;
				} else if (!event.isOverwrite()) {
					continueClassifier = false;
				}
			}

			if (continueClassifier) {
				output = new FileWriter(outputFileName, false);
			}
		}
		return continueClassifier;
	}

	/**
	 * Ajout de la requete OCL.
	 * @param ocl La requete OCL à ajouter
	 * @throws IOException Erreur lors de l'écriture
	 */
	@Override
	public void onOCLRequest(final OCLRequest ocl)
	throws IOException {
		if (output != null) {
			output.append(OCLWritter.convertOclToString(ocl));
		}
	}

	/**
	 * Fonction executé lors du démarage,
	 * ne fait rien, le fichier étant ouvert par le début du traitement
	 * du classifier.
	 */
	@Override
	public void onSaveOCLStart() {
	}
}
