package com.neptune.extracteurocl.event;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.neptune.extracteurocl.event.FileExistEvent;

public class TestFileExistEvent {
	private static final String DEFAULT_FILE_NAME = "filename";
	private FileExistEvent event;

	@Test(expected=IllegalArgumentException.class)
	public void creationWithNull() {
		new FileExistEvent(null);
	}

	@Test
	public void getFileName() {
		assertEquals(DEFAULT_FILE_NAME, event.getFileName());
	}

	@Before
	public void initFileExistEvent() {
		event = new FileExistEvent(DEFAULT_FILE_NAME);
	}

	@Test
	public void setAndGetContinue() {
		assertEquals(true, event.isContinue());
		event.setContinue(false);
		assertEquals(false, event.isContinue());
		event.setContinue(true);
		assertEquals(true, event.isContinue());

	}

	@Test
	public void setAndGetOverWritte() {
		assertEquals(false, event.isOverwrite());
		event.setOverwrite(true);
		assertEquals(true, event.isOverwrite());
		event.setOverwrite(false);
		assertEquals(false, event.isOverwrite());

	}
}