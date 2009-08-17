package com.neptune.extracteurocl.event;

/**
 * Evenement déclenché lors d'une tentative de remplacement d'un fichier.
 *
 * @author Arnaud VERSINI
 *
 */

public final class FileExistEvent {

	/**
	 * Nom du fichier génerant l'erreur.
	 */
	private String fileName;

	/**
	 * Sur écriture activé.
	 */
	private boolean overwrite = false;

	/**
	 * Arret complet du traitement.
	 */
	private boolean continueParsing = true;

	/**
	 * Constructeur initialisant par défaut à continue
	 * et pas de surécriture.
	 *
	 * @param fileName Nom du fichier à écrire.
	 */
	public FileExistEvent(final String fileName) {
		setFileName(fileName);
	}

	/**
	 * @return Le nom du fichier existant.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return True si le programme doit continuer le traitement.
	 */
	public boolean isContinue() {
		return continueParsing;
	}

	/**
	 * @return True si le programme doit écraser le fichier.
	 */
	public boolean isOverwrite() {
		return overwrite;
	}

	/**
	 * @param continueParsing Définit si le programme doit continuer
	 */
	public void setContinue(final boolean continueParsing) {
		this.continueParsing = continueParsing;
	}

	/**
	 * @param filename Nouveau nom du fichier à écrire.
	 */
	private void setFileName(final String filename) {
		if (filename == null) {
			throw new IllegalArgumentException();
		}
		fileName = filename;
	}

	/**
	 * @param overwrite Définit si le programme doit écraser
	 */
	public void setOverwrite(final boolean overwrite) {
		this.overwrite = overwrite;
	}
}
