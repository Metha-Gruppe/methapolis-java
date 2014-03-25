package micropolisj.util;

/*************************************************************************
 *  Compilation:  javac -classpath .:jl1.0.jar MP3.java         (OS X)
 *                javac -classpath .;jl1.0.jar MP3.java         (Windows)
 *  Execution:    java -classpath .:jl1.0.jar MP3 filename.mp3  (OS X / Linux)
 *                java -classpath .;jl1.0.jar MP3 filename.mp3  (Windows)
 *  
 *  Plays an MP3 file using the JLayer MP3 library.
 *
 *  Reference:  http://www.javazoom.net/javalayer/sources.html
 *
 *
 *  To execute, get the file jl1.0.jar from the website above or from
 *
 *      http://www.cs.princeton.edu/introcs/24inout/jl1.0.jar
 *
 *  and put it in your working directory with this file MP3.java.
 *
 *************************************************************************/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class MP3 extends PlaybackListener {
	private String filename;
	private AdvancedPlayer player;
	private boolean loop;
	private volatile Thread thread;

	// CONSTRUCTORS
	public MP3(String filename) {
		this(filename, false);
	}

	public MP3(URL url) {
		this(url, false);
	}

	public MP3(URL url, boolean loop) {
		this(url.toString().substring(5), loop);
//		this(url.toString(), loop);
	}

	public MP3(String filename, boolean loop) {
		this.filename = filename;
		this.player = null;
		this.loop = loop;
		this.thread = null;
	}

	// METHODS

	public void close() {
		if(player != null) {
			player.close();
		}

		if(thread != null) {
			thread.interrupt();
			thread = null;
		}
	}

	public AdvancedPlayer createPlayer() {
		close();
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename));
			AdvancedPlayer p = new AdvancedPlayer(bis);

			if(loop) {
				p.setPlayBackListener(new LoopListener());
			}
			else {
				p.setPlayBackListener(new NonLoopListener());
			}
			return p;
		}
		catch(Exception e) {
			System.out.println("Problem with file " + filename);
			System.out.println(e);
			return null;
		}
	}

	// play the MP3 file to the sound card
	public void play() {
		player = createPlayer();
		// run in new thread to play in background
		thread = new Thread() {
			public void run() {
				try {
					player.play();
				}
				catch(Exception e) {
					System.err.println(e);
				}
			}
		};
		thread.start();
	}

	private class LoopListener extends PlaybackListener {
		@Override
		public void playbackFinished(PlaybackEvent evt) {
			play();
		}
	}

	private class NonLoopListener extends PlaybackListener {
		@Override
		public void playbackFinished(PlaybackEvent evt) {
			thread.interrupt();
			thread = null;
		}
	}
}
