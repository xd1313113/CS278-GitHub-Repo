package org.cs27x.dropbox.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.cs27x.dropbox.DefaultFileManager;
import org.junit.Before;
import org.junit.Test;

public class DefaultFileManagerTest {
	
	@Before
	public void  setUp() throws Exception{
		
	}
	public DefaultFileManager fileManager = new DefaultFileManager(Paths.get("foo"));
	public String data = "Junit test practise";

	@Test
	public void testExists() {
		createFile("foo");
		assertTrue("File exists", fileManager.exists(Paths.get("foo")));
	}
	
	@Test
	public void testWrite() throws IOException{
		createFile("foo");
		fileManager.write(Paths.get("foo"), data.getBytes(), true);
		
		BufferedReader br = new BufferedReader(new FileReader("foo"));
		assertEquals("Write operation is OK", data.toString(), br.readLine());
		br.close();
	}
	
	@Test
	public void testDelete() throws IOException{
		createFile("foo");
		assertTrue("File exits",fileManager.exists(Paths.get("foo")));
		fileManager.delete(Paths.get("foo"));
		assertFalse("File deleted",fileManager.exists(Paths.get("foo")));
	}

	@Test 
	public void testResolve(){
		Path path= Paths.get("foo");
		path = path.resolve("foo");
		assertEquals(path,fileManager.resolve("foo"));
	}

	public void createFile(String filename) {
		
		File file = new File(filename);
		if(!file.exists()) {
		    try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
