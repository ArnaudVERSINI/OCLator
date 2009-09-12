package com.neptune.extracteurocl.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DirectoryUtil {
	private static final ArrayList<File> dirToDelete = new ArrayList<File>();
	
	static {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				for(File directory : dirToDelete) {
					recursiveDelete(directory);
				}
			}
		});
	}
	
	public static final void recursiveDelete(File directory) {
		if(directory.isDirectory()) {
			for(final File fileToDelete : directory.listFiles()) {
				recursiveDelete(fileToDelete);
			}
		}
		directory.delete();
	}
	
	public static File createTempDirectory(String prefix, String suffix)
	throws IOException {
		File tempDirectory = File.createTempFile(prefix, suffix);
		if(!tempDirectory.delete()) {
			throw new IOException("Cannot delete the temp File");
		}
		if(!tempDirectory.mkdir()) {
			throw new IOException("Cannot create the temp Directory");
		}
		dirToDelete.add(tempDirectory);
		return tempDirectory;
	}
}
