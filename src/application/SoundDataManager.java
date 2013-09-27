package application;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.protocol.SourceCloneable;
import javax.media.protocol.URLDataSource;

import javax.media.protocol.DataSource;

public class SoundDataManager implements Runnable{

	String baseDir;

	ConcurrentSkipListMap<Integer, DataSource> _loadedSounds;
	LinkedBlockingQueue<Integer> _soundRequests;

	HashMap<Integer, String> _filenames;

	public SoundDataManager() {
		_loadedSounds = new ConcurrentSkipListMap<Integer, DataSource>();
		_soundRequests = new LinkedBlockingQueue<Integer>();
		baseDir = "D:\\Muzyka\\";
		_filenames = new HashMap<Integer, String>();
		_filenames.put(DataManager.S_SLASH, "05.Pray!.mp3");
	}

	private DataSource getSound(Integer soundNumber) {
		
		if (_loadedSounds.containsKey(soundNumber)) {
			return (DataSource) ((SourceCloneable) _loadedSounds
					.get(soundNumber)).createClone();
		}
		DataSource soundsrc = null;
		try {
			soundsrc = new URLDataSource(new URL("file:///" + baseDir
					+ _filenames.get(soundNumber)));
			soundsrc.connect();
			soundsrc.start();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DataSource clonesrc = Manager.createCloneableDataSource(soundsrc);
		if (soundsrc == null) System.out.println("Null clone");
		_loadedSounds.put(soundNumber, clonesrc);
		return (DataSource) ((SourceCloneable) clonesrc).createClone();
	}
	
	public Player getPlayer(Integer soundNumber) {
		Player pl = null;
		try {
			pl = Manager.createRealizedPlayer(new URL("file:///" + baseDir
						+ _filenames.get(soundNumber)));
		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotRealizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pl;
	}

	public void placeSoundRequest(Integer texNumber) {
		_soundRequests.add(texNumber);
	}

	public void run() {
		while (true) {
			while (!_soundRequests.isEmpty()) {
				Integer number = _soundRequests.poll();
				DataSource soundsrc = null;
				try {
					soundsrc = new URLDataSource(new URL("file://"
							+ baseDir + _filenames.get(number)));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				_loadedSounds.put(number, Manager.createCloneableDataSource(soundsrc));
			}
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {

			}
		}
	}
}
