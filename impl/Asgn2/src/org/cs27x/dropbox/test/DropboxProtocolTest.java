package org.cs27x.dropbox.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.cs27x.dropbox.DropboxCmd;
import org.cs27x.dropbox.DropboxCmd.OpCode;
import org.cs27x.dropbox.DropboxProtocol;
import org.cs27x.dropbox.DropboxTransport;
import org.cs27x.dropbox.FileManager;
import org.cs27x.filewatcher.FileStates;
import org.junit.Before;
import org.junit.Test;


public class DropboxProtocolTest {
	FileStates fileStates;
	FileManager fileManager;
	DropboxTransport transport;
	Path path;

	@Before
	public void setUp() throws Exception {
		fileStates = mock(FileStates.class);
		fileManager = mock(FileManager.class);
		transport = mock(DropboxTransport.class);
		path = Paths.get("protocoltest.txt");
		
		FileOutputStream fos = new FileOutputStream("protocoltest.txt");
		fos.write("hello".getBytes());
		fos.close();
	}

	@Test
	public void testDoOperations() {
		ArrayList<DropboxCmd> cmds = new ArrayList<DropboxCmd>();
		cmds.add( new DropboxCmd(OpCode.ADD));
		cmds.add( new DropboxCmd(OpCode.UPDATE));
		cmds.add( new DropboxCmd(OpCode.REMOVE));
				
		DropboxProtocol protocol = new  DropboxProtocol(transport,fileStates,fileManager);
		protocol.doOperations(cmds, path);
		
		assertArrayEquals("Add", "hello".getBytes(), cmds.get(0).getData());
		assertArrayEquals("update", "hello".getBytes(), cmds.get(1).getData());
		
		for(DropboxCmd cmd:cmds){
			verify(transport).publish(cmd);
		}
	}

}
