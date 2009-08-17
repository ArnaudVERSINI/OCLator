package com.neptune.extracteurocl.ui;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.DialogFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestInterfaceGraphique {
	
	private DialogFixture fixture;
	private OCLAtorDialog window;

	@BeforeClass
	public static void initThreadViolation() {
		FailOnThreadViolationRepaintManager.install();
		UserInterfaceUtil.nimbusThemeActivation();
	}
	
	@Before
	public void initWindow() {
		window = GuiActionRunner.execute(new GuiQuery<OCLAtorDialog>() {
			@Override
			protected OCLAtorDialog executeInEDT() throws Throwable {
				return new OCLAtorDialog(null);
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
	public void testInputFileButton() {
		fixture.button("button::inputFile").click();
		fixture.fileChooser().cancel();
	}
	@Test
	public void testOutputFileButton() {
		fixture.button("button::outputFile").click();
		fixture.fileChooser().cancel();
	}

	@Test
	public void undoDialog() {
		fixture.button("button::undo").click();
		fixture.requireNotVisible();
	}
	
	@Test
	public void output()
	throws IOException, InterruptedException {
		String intPutFileContent = defaultXmlInput();
		
		File inputFile = File.createTempFile("Plume", ".ocl");
		inputFile.deleteOnExit();
		
		File outputFile = File.createTempFile("Plume", ".ocl");
		outputFile.deleteOnExit();
		
		FileWriter inputWriter = new FileWriter(inputFile);
		inputWriter.write(intPutFileContent);
		inputWriter.close();
		
		fixture.button("button::inputFile").click();
		fixture.fileChooser().selectFile(inputFile).approve();
		
		fixture.button("button::outputFile").click();
		fixture.fileChooser().selectFile(outputFile).approve();
		
		fixture.button("button::ok").click();
		fixture.optionPane().yesButton().click();
		fixture.optionPane().okButton().click();
		
		BufferedReader outputReader = new BufferedReader(new FileReader(outputFile));
		
		String expected = expectedOclFileResult();
		String result = "";
		String line = outputReader.readLine();
		
		while(line != null) {
			result += line + "\n";
			line = outputReader.readLine();
		}
		
		assertEquals(expected, result.trim());
	}

	private static String expectedOclFileResult() {
		String expected = "-- An invariant constraint based on the following OCL expression:\n";
		expected += "context uml2::Element\n";
		expected += "def: not_own_self() : Boolean\n";
		expected += "not self.allOwnedElements()->includes(self)";
		return expected;
	}
	private static String defaultXmlInput() {
		String intPutFileContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		
		intPutFileContent += "<ecore:EPackage xmi:version=\"2.0\" ";
		intPutFileContent += "xmlns:xmi=\"http://www.omg.org/XMI\" ";
		intPutFileContent += "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ";
		intPutFileContent += "xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" ";
		intPutFileContent += "name=\"uml2\" ";
		intPutFileContent += "nsURI=\"http://www.eclipse.org/uml2/1.0.0/UML\" ";
		intPutFileContent += "nsPrefix=\"uml\">\n";
		
		intPutFileContent += "<eClassifiers ";
		intPutFileContent += "xsi:type=\"ecore:EClass\" ";
		intPutFileContent += "name=\"Element\" ";
		intPutFileContent += "abstract=\"true\" ";
		intPutFileContent += "eSuperTypes=\"../../../plugin/org.eclipse.emf.ecore/model/Ecore.ecore#//EModelElement\">";

		intPutFileContent +="<eOperations name=\"not_own_self\" eType=\"#//Boolean\">";
		intPutFileContent += "<eAnnotations source=\"http://www.eclipse.org/emf/2002/GenModel\">";
		intPutFileContent += "<details key=\"documentation\" value=\"An invariant constraint based on the following OCL expression:&#xD;&#xA;&lt;code>&#xD;&#xA;not self.allOwnedElements()->includes(self)&#xD;&#xA;&lt;/code>\"/>";
		intPutFileContent += "</eAnnotations>";
		intPutFileContent += "</eOperations>";
		intPutFileContent += "</eClassifiers>";
		intPutFileContent += "</ecore:EPackage>";
		return intPutFileContent;
	}

}
