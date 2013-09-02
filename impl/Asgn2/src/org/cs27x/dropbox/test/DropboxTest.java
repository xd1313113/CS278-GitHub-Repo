package org.cs27x.dropbox.test;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.cs27x.dropbox.Dropbox;
import org.cs27x.dropbox.DropboxProtocol;
import org.cs27x.dropbox.HazelcastTransport;
import org.cs27x.filewatcher.FileReactor;
import org.junit.Before;
import org.junit.Test;

public class DropboxTest {
	HazelcastTransport transport;
	DropboxProtocol protocol;
	FileReactor fileReactor;
	private Dropbox db;
	Path path;
	@Before
	public void setUp() throws Exception {
		path = Paths.get("test-data/invariant/");
		transport = mock(HazelcastTransport.class);
		protocol = mock(DropboxProtocol.class);
		fileReactor = mock(FileReactor.class);
		db = new Dropbox(transport,protocol,fileReactor);	
	}

	@Test
	public void testConnect() throws Exception {
		db.connect("test");
		verify(transport).connect(anyString());
		verify(fileReactor).start();
	}

	@Test
	public void testConnected() {
		db.connected();
		verify(transport).isConnected();
	}

	@Test
	public void testDisconnect() {
		db.disconnect();
		verify(transport).disconnect();
	}

	@Test
	public void testAwaitConnect() throws InterruptedException {
		Long timeout = (long) (Math.random()*10000);
			db.awaitConnect(timeout);
		verify(transport).awaitConnect(anyLong());
	}

}
