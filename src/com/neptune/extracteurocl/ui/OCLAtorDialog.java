package com.neptune.extracteurocl.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.neptune.extracteurocl.control.OCLOneFileByClassifierWritter;
import com.neptune.extracteurocl.control.OCLOneFileWritter;
import com.neptune.extracteurocl.control.OCLWritter;
import com.neptune.extracteurocl.event.FileExistEvent;
import com.neptune.extracteurocl.event.Observer;
import com.neptune.extracteurocl.handler.ECoreXMLHandler;
import com.neptune.extracteurocl.model.EPackages;

/**
 * Dialogue pour demander le fichiers d'entrée, les sorties et les options.
 *
 * @author Arnaud VERSINI
 * @author Jocelyne BARROT
 * @author Carine DONGMO
 *
 */
public final class OCLAtorDialog
extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 5807463289849123773L;
	
	/**
	 * Taille par defaut des champs textes du dialogue.
	 */
	private static final int TEXTFIELD_SIZE = 25;

	/**
	 * Panneau de configuration de la sortie.
	 */
	private transient JPanel outputPanel;

	/**
	 * Panneau de configuration de l'entre.
	 */
	private transient JPanel inputFilePanel;
	/**
	 * Champ texte d'entrée du fichier ou dossier
	 * de sortie.
	 */
	private transient JTextField outputFileText;
	/**
	 * Champ texte d'entrée du fichier ou dossier
	 * d'entrée.
	 */
	private transient JTextField inputFileText;

	/**
	 * Bouton radio de selection du mode de sortie en fichier unique.
	 */
	private transient JRadioButton fileSelection;
	/**
	 * Bouton radio de selection du mode de sortie en plusieurs fichier.
	 */
	private transient JRadioButton directorySelection;

	/**
	 * Création du dialogue avec un parent.
	 * @param parent La frame parente.
	 */
	public OCLAtorDialog(final Frame parent) {
		super(parent, "OCLator, l'extracteur d'OCL");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		final BoxLayout frameBoxLayout
			= new BoxLayout(
					rootPane,
					BoxLayout.Y_AXIS);
		rootPane.setLayout(frameBoxLayout);
		addInputFilePanel();
		rootPane.add(Box.createRigidArea(new Dimension(0, 5)));
		addOutputPanel();
		rootPane.add(Box.createRigidArea(new Dimension(0, 5)));
		addButtonPanel();
		setResizable(false);
		pack();
	}

	/**
	 * Méthode utilisé par le constructeur pour
	 * initialiser le panneau de bouton ok et annuler.
	 */
	private void addButtonPanel() {
		final JPanel buttonPanel = new JPanel();
		final FlowLayout layout = new FlowLayout();
		buttonPanel.setLayout(layout);

		final JButton ok = new JButton("Ok");
		ok.setName("button::ok");
		final JButton undo = new JButton("Annuler");
		undo.setName("button::undo");
		buttonPanel.add(ok);
		buttonPanel.add(undo);

		undo.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				dispose();
			}
		});

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				onOk();
			}
		});

		rootPane.add(buttonPanel);
	}

	private void addInputFilePanel() {
		inputFilePanel = new JPanel();
		final TitledBorder title = BorderFactory
				.createTitledBorder("Fichier d'entré");
		inputFilePanel.setBorder(title);

		final BoxLayout inputBoxLayout = new BoxLayout(inputFilePanel,
				BoxLayout.X_AXIS);
		inputFilePanel.setLayout(inputBoxLayout);

		inputFileText = new JTextField(TEXTFIELD_SIZE);
		inputFilePanel.add(inputFileText);

		final JButton searchButtonInputFile = new JButton("...");
		searchButtonInputFile.setName("button::inputFile");
		inputFilePanel.add(searchButtonInputFile);

		searchButtonInputFile.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				onFileInputButton();
			}
		});

		rootPane.add(inputFilePanel);
	}

	private void addOutputFilePanel() {
		final JPanel outputFilePanel = new JPanel();

		final BoxLayout inputBoxLayout = new BoxLayout(
				outputFilePanel,
				BoxLayout.X_AXIS);
		outputFilePanel.setLayout(inputBoxLayout);

		outputFileText = new JTextField(TEXTFIELD_SIZE);
		outputFilePanel.add(outputFileText);

		final JButton searchButtonOutputFile = new JButton("...");
		searchButtonOutputFile.setName("button::outputFile");
		outputFilePanel.add(searchButtonOutputFile);


		searchButtonOutputFile.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				onFileOutputButton();
			}
		});

		outputPanel.add(outputFilePanel);
	}

	private void addOutputPanel() {
		outputPanel = new JPanel();
		final TitledBorder title = BorderFactory
				.createTitledBorder("Fichier de sortie");
		outputPanel.setBorder(title);

		final BoxLayout outputBoxLayout = new BoxLayout(outputPanel,
				BoxLayout.Y_AXIS);
		outputPanel.setLayout(outputBoxLayout);

		addOutputTypeSelectionDialog();
		addOutputFilePanel();

		rootPane.add(outputPanel);
	}

	private void addOutputTypeSelectionDialog() {
		final JPanel typeSelectionPanel = new JPanel();

		final BoxLayout inputBoxLayout = new BoxLayout(typeSelectionPanel,
				BoxLayout.Y_AXIS);
		typeSelectionPanel.setLayout(inputBoxLayout);

		final JLabel text = new JLabel("Sortie vers : ");
		fileSelection = new JRadioButton("Fichier", true);
		directorySelection = new JRadioButton("Répertoire", false);

		final ButtonGroup group = new ButtonGroup();

		typeSelectionPanel.add(text);
		typeSelectionPanel.add(fileSelection);
		typeSelectionPanel.add(directorySelection);

		group.add(fileSelection);
		group.add(directorySelection);

		outputPanel.add(typeSelectionPanel);
	}

	/**
	 *
	 */
	private void fileOutputTreatment() {
		try {

			final ECoreXMLHandler handler = loadECOREFile();

			OCLWritter oclSaver = null;
			if (fileSelection.isSelected()) {
				oclSaver = oneFileOCLWritterCreation(handler.getPackage());
			} else {
				oclSaver = oneFileByClassifierOCLWritterCreation(
						handler.getPackage());
			}
			oclSaver.saveOCLs();
			oclSaver.dispose();
			if (handler.isError()) {

				//!TODO A changer par un JOptionPane de base
				final ErrorDialog errorDialog = new ErrorDialog(
						this,
						"Une erreur s'est déroulé "
						+ "durant l'extraction des codes OCLs : ",
						handler.getErrorMessages());
				errorDialog.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this,
						"Extraction des codes OCLs",
						"L'extraction des codes OCLs"
								+ " s'est correctement déroulé",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}

		} catch (final IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(
					this,
					"Erreur d'entrée sortie : ",
					ex.getLocalizedMessage(),
					JOptionPane.ERROR_MESSAGE);
		} catch (final SAXException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(
					this,
					"Erreur dans le traitement du fichier XML :\n"
							+ ex.getLocalizedMessage(),
					"Fichier XML vraissemblablement mal formé",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Fonction appellé lors du chargement du fichier ECore.
	 * @return Le modéle du document ECore.
	 * @throws SAXException Erreur XML
	 * @throws IOException Erreur d'entrée sortie générale.
	 */
	private ECoreXMLHandler loadECOREFile()
	throws SAXException, IOException {
		final XMLReader xr = XMLReaderFactory.createXMLReader();
		final ECoreXMLHandler handler = new ECoreXMLHandler();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);

		final FileReader reader = new FileReader(inputFileText.getText());
		xr.parse(new InputSource(reader));
		return handler;
	}

	private OCLOneFileByClassifierWritter oneFileByClassifierOCLWritterCreation(
			final EPackages pack)
	throws IOException {

		final OCLOneFileByClassifierWritter oclSaver
				= new OCLOneFileByClassifierWritter(
						pack,
						outputFileText.getText());

		oclSaver.attachFileExist(new Observer<FileExistEvent>() {
			public void update(final FileExistEvent event) {
				onFileExist(event);
			}
		});
		return oclSaver;

	}

	private OCLOneFileWritter oneFileOCLWritterCreation(
			final EPackages pack)
	throws IOException {
		final OCLOneFileWritter oclSaver
				= new OCLOneFileWritter(
						pack,
						outputFileText.getText());

		oclSaver.attachFileExist(new Observer<FileExistEvent>() {
			public void update(final FileExistEvent event) {
				onFileExist(event);
			}
		});
		return oclSaver;
	}

	/**
	 * Gestion d ela présence d fichier sur le systéme de fichier.
	 * @param event L'évenemnt conduisant au probléme.
	 */
	private void onFileExist(
			final FileExistEvent event) {
		final int response = JOptionPane.showConfirmDialog(
				this,
				"Le fichier "
						+ event.getFileName()
						+ " existe déja, voulez vous l'écraser?",
				"Fichier existant",
				JOptionPane.YES_NO_CANCEL_OPTION);
		switch (response) {
		case JOptionPane.YES_OPTION:
			event.setOverwrite(true);
			event.setContinue(true);
			break;
		case JOptionPane.NO_OPTION:
			event.setOverwrite(false);
			event.setContinue(true);
			break;
		default:
			event.setContinue(false);
			event.setOverwrite(false);
		}
	}

	private void onFileInputButton() {
		final JFileChooser fileChooser = new JFileChooser();

		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			inputFileText.setText(
					fileChooser
					.getSelectedFile()
					.getAbsolutePath());
		}
	}

	private void onFileOutputButton() {
		int mode = 0;
		String dialogTitle = null;

		if (directorySelection.isSelected()) {
			mode = JFileChooser.DIRECTORIES_ONLY;
			dialogTitle = "Veuillez sélectionner le répertoire de sortie";
		} else if (fileSelection.isSelected()) {
			mode = JFileChooser.FILES_ONLY;
			dialogTitle = "Veuillez sélectionner le fichier de sortie";
		} else {
			return;
		}

		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(dialogTitle);
		fileChooser.setFileSelectionMode(mode);

		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			outputFileText.setText(fileChooser.getSelectedFile()
					.getAbsolutePath());
		}
	}

	private void onOk() {
		final File inputFile = new File(inputFileText.getText());

		if (!inputFile.exists() || !inputFile.canRead()) {
			JOptionPane.showMessageDialog(
							this,
							"Fichier en entrée inaccessible",
							"Le fichier en entrée que vous avez selectionné "
							+ " n'est pas lisible",
							JOptionPane.ERROR_MESSAGE);
			return;
		}
		fileOutputTreatment();
	}
}
