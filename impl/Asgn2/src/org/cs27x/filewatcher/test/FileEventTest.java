package org.cs27x.filewatcher.test;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent.Kind;

import org.cs27x.filewatcher.FileEvent;
import org.junit.Before;
import org.junit.Test;

public class FileEventTest {
	
	Kind<?> kind;
	Path path;
	FileEvent fileEvent;

	@Before
	public void setUp() throws Exception {
		kind = mock(Kind.class);
		path = Paths.get("foo");
		fileEvent = new FileEvent(kind,path);	
	}

	@Test
	public void testGetFile() {
		assertEquals("foo",fileEvent.getFile().toString());
	}

	@Test
	public void testGetEventType() {
		when(kind.name()).thenReturn("test");
		assertEquals("test",fileEvent.getEventType().name());
	}

}
