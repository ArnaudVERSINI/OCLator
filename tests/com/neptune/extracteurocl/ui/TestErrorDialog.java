package com.neptune.extracteurocl.ui;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.DialogFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestErrorDialog {
	private DialogFixture fixture;
	private ErrorDialog window;
	
	@Before
	public void initWindow() {
		window = GuiActionRunner.execute(new GuiQuery<ErrorDialog>() {
			@Override
			protected ErrorDialog executeInEDT() throws Throwable {
				return new ErrorDialog(null, "Message", "Re message");
			}
		});
		fixture = new DialogFixture(window);
		fixture.show();
	}

	@After
	public void cleanWindow() {
		if (window.isVisible()) {
			fixture.close();
		}
		fixture.cleanUp();
	}

	
	@Test
	public void viewErrorDialog() {
		
	}
}
