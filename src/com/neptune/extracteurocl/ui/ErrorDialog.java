package com.neptune.extracteurocl.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Dialogue d'affichage des erreurs.
 * @author Arnaud VERSINI
 * @author Jocelyne BARROT
 * @author Carine DONGMO
 */
public class ErrorDialog
extends JDialog {

	/**
	 * Taille par defaut de la boite de dialogue.
	 */
	private static final int DEFAULT_SIZE = 200;
	/**
	 * Dimension minimales.
	 */
	private static final Dimension MINIMUM_SIZE
			= new Dimension(DEFAULT_SIZE, DEFAULT_SIZE);

	/**
	 * Création d'un dialogue avec message d'erreur.
	 * @param owner Fenetre parente.
	 * @param message Titre
	 * @param errorMessage Message d'erreur
	 */
	public ErrorDialog(
			final Window owner,
			final String message,
			final String errorMessage) {
		super(owner);
		setModal(true);
		setLayout(new BorderLayout());
		final JLabel label = new JLabel(message);
		add(label, BorderLayout.NORTH);
		final JTextField myArea = new JTextField(errorMessage);
		myArea.setEditable(false);
		myArea.setMinimumSize(MINIMUM_SIZE);
		add(myArea, BorderLayout.CENTER);

		final JButton ok = new JButton("Ok");
		add(ok, BorderLayout.SOUTH);

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				dispose();
			}
		});

		pack();
	}
}
