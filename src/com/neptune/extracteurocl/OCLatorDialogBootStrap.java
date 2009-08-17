package com.neptune.extracteurocl;

import javax.swing.SwingUtilities;

import com.neptune.extracteurocl.ui.OCLAtorDialog;
import com.neptune.extracteurocl.ui.UserInterfaceUtil;

/**
 * Execution du programme.
 * @author Arnaud VERSINI
 * @author Jocelyne BARROT
 * @author Carine DONGMO
 */
public final class OCLatorDialogBootStrap {
	static {
		UserInterfaceUtil.nimbusThemeActivation();
	}
	/**
	 * Pour éviter l'instanciation.
	 */
	private OCLatorDialogBootStrap() {
	}

	/**
	 * Bootstrap de la fenetre de dialogue.
	 * @param args Argument du programme.
	 */
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new OCLAtorDialog(null).setVisible(true);
			}
		});
	}
}
