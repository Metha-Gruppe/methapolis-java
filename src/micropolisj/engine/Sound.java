// This file is part of MicropolisJ.
// Copyright (C) 2013 Jason Long
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// MicropolisJ is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine;

import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Enumerates the various sounds that the city may produce.
 * The engine is not responsible for actually playing the sound. That task
 * belongs to the front-end (i.e. the user interface).
 */
public enum Sound
{
	EXPLOSION_LOW ("explosion-low"),
	EXPLOSION_HIGH("explosion-high"),
	EXPLOSION_BOTH("explosion-low"),
	UHUH          ("bop"),
	SORRY         ("bop"),
	BUILD         ("layzone"),
	BULLDOZE      (null),
	HONKHONK_LOW  ("honkhonk-low"),
	HONKHONK_MED  ("honkhonk-med"),
	HONKHONK_HIGH ("honkhonk-high"),
	HONKHONK_HI   ("honkhonk-hi"),
	SIREN         ("siren"),
	HEAVYTRAFFIC  ("heavytraffic"),
	MONSTER       ("zombie-roar-5"),
	// CUSTOM
	DUBSPLOSION   ("dubsplosion"),
	SPACESHIP_START   ("dubsplosion"),
	BG_MUSIC      ("methapolis");

	String wavName;
	
	
	private Sound(String wavName)	{
		this.wavName = wavName;
	}

	public URL getAudioFile()	{
		return getAudioFile("wav");
	}
	
	/**
	 * @param fileEnding (String) - no dot!
	 * @return
	 */
	public URL getAudioFile(String fileEnding)	{
		String n2 = "/sounds/" + wavName + "." + fileEnding;
		URL u = Sound.class.getResource(n2);
		return u;
	}
	
	public static void playSound(Sound sound) {
		playSound(sound, "wav");
	}
	
	public static void playSound(Sound sound, String fileEnding) {
		System.out.println("playing a sound");
		URL afile = sound.getAudioFile(fileEnding);
		if(afile == null)	{
			return;			
		}
		
		System.out.println(afile);

		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(afile));
			clip.start();
		}
		catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public String getWavName()	{
		return wavName;
	}
}
