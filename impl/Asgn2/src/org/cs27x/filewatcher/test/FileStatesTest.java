package org.cs27x.filewatcher.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.cs27x.filewatcher.FileState;
import org.cs27x.filewatcher.FileStates;
import org.cs27x.filewatcher.FileStatesImpl;
import org.junit.Before;
import org.junit.Test;

public class FileStatesTest {

	FileStates fileStates;
	@Before
	public void setUp() throws Exception {
		fileStates = new FileStatesImpl();
	}

	@Test
	public void testGetState() throws IOException {
		Path path1 = Paths.get("test-data/invariant/test1");
		if (!Files.exists(path1))
			Files.createFile(path1);
		//file exist and get state
		FileState fs1 = fileStates.getState(path1);
		assertNull("Filetime",fs1);
		
		//file missing and get state
		Files.delete(path1);
		FileState fs2 = fileStates.getState(path1);
		assertNull("Filetime",fs2);
		
		//File never exists
		Path path2 = Paths.get("test-data/invariant/test2");
		if (Files.exists(path2))
			Files.delete(path2);
		FileState fs3 = fileStates.getState(path1);
		assertNull("Filetime",fs3);
	}

	@Test
	public void testGetOrCreateState() throws IOException {
		Path path1 = Paths.get("test-data/invariant/test3");
		if (!Files.exists(path1))
			Files.createFile(path1);
		//file exist and get state
		FileState fs1 = fileStates.getOrCreateState(path1);
		assertEquals("size", -1, fs1.getSize());
		assertNull("Filetime",fs1.getLastModificationDate());
		
		//file missing and get state
		Files.delete(path1);
		FileState fs2 = fileStates.getOrCreateState(path1);
		assertEquals("size", -1, fs2.getSize());
		assertNull("Filetime",fs2.getLastModificationDate());
		
		//File never exists
		Path path2 = Paths.get("test-data/invariant/test4");
		if (Files.exists(path2))
			Files.delete(path2);
		FileState fs3 = fileStates.getOrCreateState(path1);
		assertEquals("size", -1, fs3.getSize());
		assertNull("Filetime",fs3.getLastModificationDate());	
	}

/*	@Test
	public void testInsert() {
		
	}

	@Test
	public void testFilter() {
		
	}*/

}
