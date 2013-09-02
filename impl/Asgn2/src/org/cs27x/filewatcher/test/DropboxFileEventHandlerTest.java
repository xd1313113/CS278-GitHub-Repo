package org.cs27x.filewatcher.test;

import static java.nio.file.StandardWatchEventKinds.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;

import org.cs27x.dropbox.DropboxCmd;
import org.cs27x.dropbox.DropboxCmd.OpCode;
import org.cs27x.dropbox.DropboxProtocol;
import org.cs27x.dropbox.FileManager;
import org.cs27x.filewatcher.DropboxFileEventHandler;
import org.cs27x.filewatcher.FileEvent;
import org.cs27x.filewatcher.FileState;
import org.cs27x.filewatcher.FileStates;
import org.junit.Test;

public class DropboxFileEventHandlerTest {

	@Test
	public void testHandle() throws Exception {
		final FileState state = new FileState(0, FileTime.fromMillis(0));
		final FileTime changeTime = FileTime.fromMillis(0);
		
		Path path = Paths.get("evt.txt");

		FileStates states = mock(FileStates.class);
		FileManager mgr = mock(FileManager.class);
		DropboxProtocol protocol= mock(DropboxProtocol.class);
		
		
		
		//when(protocol.).thenReturn(changeTime);
		
		DropboxFileEventHandler evtHdler = new DropboxFileEventHandler(mgr,states,protocol);
		
		FileEvent evt_create = new FileEvent(ENTRY_CREATE,path);
		FileEvent evt_modify = new FileEvent(ENTRY_MODIFY,path);
		FileEvent evt_delete = new FileEvent(ENTRY_DELETE,path);
		
		when(states.filter(any(FileEvent.class))).thenReturn(evt_create);
		
		evtHdler.handle(evt_create);
		verify(protocol).addFile(path);
		
		when(states.filter(any(FileEvent.class))).thenReturn(evt_modify);
		evtHdler.handle(evt_modify);
		verify(protocol).updateFile(path);
		
		when(states.filter(any(FileEvent.class))).thenReturn(evt_delete);
		evtHdler.handle(evt_delete);
		verify(protocol).removeFile(path);
	}
	

}
