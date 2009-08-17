package com.neptune.extracteurocl.handler;

import com.neptune.extracteurocl.model.EClassifier;
import com.neptune.extracteurocl.model.EOperation;
import com.neptune.extracteurocl.model.EPackages;
import com.neptune.extracteurocl.model.EParameter;
import com.neptune.extracteurocl.model.OCLRequest;

public class OclWriterUtil {
	public static String getDefaultResult() {
		String expected = "-- un commentaire\n";
		expected += "context toto::titi\n";
		expected += "def: gertrude(toto : Boolean, titi : Integer)";
		expected += " : Boolean\n";
		expected += "true\n\n";
		return expected;
	}

	public static EPackages getOclWriterPackages() {
		EPackages pack = new EPackages("toto", null);
		final EClassifier classParent = new EClassifier("titi", pack);
		pack.addClassifier(classParent);
		final EOperation opParent = new EOperation(
				"gertrude",
				"Boolean",
				classParent);
		classParent.add(opParent);
		opParent.add(new EParameter("toto", "Boolean"));
		opParent.add(new EParameter("titi", "Integer"));
		OCLRequest ocl = new OCLRequest("true", "un commentaire", opParent);
		opParent.addOcl(ocl);
		return pack;
	}

}
