package com.neptune.extracteurocl.ui;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class UserInterfaceUtil {

	public static void nimbusThemeActivation() {
		try {
			for (
					final LookAndFeelInfo info
					: UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
