package com.neptune.extracteurocl.handler;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.neptune.extracteurocl.control.OCLOneFileByClassifierWritter;
import com.neptune.extracteurocl.control.OCLOneFileWritter;
import com.neptune.extracteurocl.control.OCLStringWritter;
import com.neptune.extracteurocl.control.OCLWritter;
import com.neptune.extracteurocl.event.FileExistEvent;
import com.neptune.extracteurocl.event.Observer;
import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EOperation;
import com.neptune.extracteurocl.model.EPackages;
import com.neptune.extracteurocl.model.EParameter;
import com.neptune.extracteurocl.model.OCLRequest;
import com.neptune.extracteurocl.util.DirectoryUtil;

public class TestOCLWritter {
	private EPackages pack;
	private OCLRequest ocl;
	
	@Test
	public void convertOclToString() {
		final String result = OCLWritter.convertOclToString(ocl);
		assertEquals(
				"La requete n'a pas été correctement convertis"
				+ " en chaine de caractére",
				OclWriterUtil.getDefaultResult(),
				result);
	}

	@Before
	public void initDefaultPackage() {
		pack = new EPackages("toto", null);
		final EClassifier classParent = new EClassifier("titi", pack);
		pack.addClassifier(classParent);
		final EOperation opParent = new EOperation(
				"gertrude",
				"Boolean",
				classParent);
		classParent.add(opParent);
		opParent.add(new EParameter("toto", "Boolean"));
		opParent.add(new EParameter("titi", "Integer"));
		ocl = new OCLRequest("true", "un commentaire", opParent);
		opParent.addOcl(ocl);
	}

	@Test
	public void oclStringWritter()
	throws IOException {
		final OCLStringWritter writter = new OCLStringWritter(pack);
		writter.saveOCLs();
		assertEquals(
				"La requete n'a pas été correctement"
						+ " convertis en chaine de caractére",
				OclWriterUtil.getDefaultResult(),
				writter.toString());
		writter.dispose();
	}

	@Test
	public void oclOneFileWriter()
	throws IOException {
		File tempFile = File.createTempFile("PlumeOneFile", ".tmp");
		final OCLOneFileWritter writter = new OCLOneFileWritter(pack, tempFile.getAbsolutePath());
		writter.attachFileExist(new Observer<FileExistEvent>() {
			@Override
			public void update(FileExistEvent event) {
				event.setOverwrite(true);
				event.setContinue(true);
				
			}
		});
		writter.saveOCLs();
		writter.dispose();
		tempFile.deleteOnExit();
		BufferedReader reader = new BufferedReader(new FileReader(tempFile));
		
		String result = "";
		String allLines = "";
		while((result = reader.readLine()) != null) {
			allLines += "\n" + result;
		}
		assertEquals(
				OclWriterUtil.getDefaultResult().trim(),
				allLines.trim());
	}
	
	@Test
	public void oclMultipleFilesWriter() throws IOException {
		File tempFile = DirectoryUtil.createTempDirectory("PlumeOneFile", "dir");
		final OCLOneFileByClassifierWritter writter = new OCLOneFileByClassifierWritter(pack, tempFile.getAbsolutePath());
		writter.attachFileExist(new Observer<FileExistEvent>() {
			@Override
			public void update(FileExistEvent event) {
				Assert.fail();
			}
		});
		writter.saveOCLs();
		writter.dispose();
		BufferedReader reader = new BufferedReader(new FileReader(tempFile.getAbsolutePath() + File.separator + "titi.ocl"));
		
		String result = "";
		String allLines = "";
		while((result = reader.readLine()) != null) {
			allLines += "\n" + result;
		}
		assertEquals(
				OclWriterUtil.getDefaultResult().trim(),
				allLines.trim());

	}

	@Test
	public void oclStringWritterInternalPackage()
	throws IOException {
		final EPackages sousPackages
				= new EPackages("sousPackage", pack);
		pack.addPackage(sousPackages);

		final EClassifier classifiers
				= new EClassifier("classifier", sousPackages);
		sousPackages.addClassifier(classifiers);

		final EOperation operation = new EOperation(
				"operation",
				"Boolean",
				classifiers);
		classifiers.add(operation);

		final OCLRequest ocl2 = new OCLRequest("true", "un commentaire", operation);
		operation.addOcl(ocl2);

		String expected = "-- un commentaire\n";
		expected += "context toto::sousPackage::classifier\n";
		expected += "def: operation() : Boolean\n";
		expected += "true\n\n";
		expected += OclWriterUtil.getDefaultResult();

		final OCLStringWritter writter = new OCLStringWritter(pack);
		writter.saveOCLs();

		assertEquals(expected, writter.toString());
	}
}
