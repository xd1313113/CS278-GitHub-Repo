package org.cs27x.dropbox;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.cs27x.dropbox.DropboxCmd.OpCode;
import org.cs27x.filewatcher.FileEvent;
import org.cs27x.filewatcher.FileStates;

public class DropboxProtocol {

	private final DropboxTransport transport_;
	
	private final DropboxCmdProcessor cmdProcessor_;

	public DropboxProtocol(DropboxTransport transport, FileStates states, FileManager filemgr) {
		transport_ = transport;
		cmdProcessor_ = new DropboxCmdProcessor(states,filemgr);
		transport_.addListener(cmdProcessor_);
		

	}

	public void connect(String initialPeer) {
		transport_.connect(initialPeer);
	}

	public void publish(DropboxCmd cmd) {
		transport_.publish(cmd);
	}

	public void addFile(Path p) {
		DropboxCmd cmd = new DropboxCmd();
		cmd.setOpCode(OpCode.ADD);
		cmd.setPath(p.getFileName().toString());

		try {

			try (InputStream in = Files.newInputStream(p)) {
				byte[] data = IOUtils.toByteArray(in);
				cmd.setData(data);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		publish(cmd);
	}

	public void removeFile(Path p) {
		DropboxCmd cmd = new DropboxCmd();
		cmd.setOpCode(OpCode.REMOVE);
		cmd.setPath(p.getFileName().toString());
		publish(cmd);
	}

	public void updateFile(Path p) {
		DropboxCmd cmd = new DropboxCmd();
		cmd.setOpCode(OpCode.UPDATE);
		cmd.setPath(p.getFileName().toString());
		try {

			try (InputStream in = Files.newInputStream(p)) {
				byte[] data = IOUtils.toByteArray(in);
				cmd.setData(data);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		publish(cmd);
	}
	
	public void doOperations(ArrayList<DropboxCmd> cmds,Path p){
		for(DropboxCmd cmd: cmds){
			cmd.setPath(p.getFileName().toString());

			if (cmd.getOpCode() == OpCode.ADD || cmd.getOpCode() == OpCode.UPDATE){
				try {

					try (InputStream in = Files.newInputStream(p)) {
						byte[] data = IOUtils.toByteArray(in);
						cmd.setData(data);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			publish(cmd);
		}		
	}


}
