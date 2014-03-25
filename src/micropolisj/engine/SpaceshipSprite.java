// This file is part of MicropolisJ.
// Copyright (C) 2013 Jason Long
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// MicropolisJ is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine;

import micropolisj.gui.MainWindow;
import micropolisj.util.MP3;

/**
 * Implements the airplane. The airplane appears if the city contains an
 * airport. It first takes off, then flies around randomly, occassionally
 * crashing.
 */
public class SpaceshipSprite extends Sprite {
	private int destX;
	private int destY;
	private double speedFactor;
	private boolean soundPlaying;
	private MP3 sound;
	private int prevDist;

	// CONSTRUCTORS
	public SpaceshipSprite(Micropolis engine, int xpos, int ypos) {
		this(engine, xpos, ypos, 1.0);
	}

	public SpaceshipSprite(Micropolis engine, int xpos, int ypos, double speedFactor) {
		super(engine, SpriteKind.SPACESHIP);

		this.speedFactor = speedFactor;
		this.soundPlaying = false;
		this.prevDist = Integer.MAX_VALUE;
		x = xpos;
		y = ypos;

		// size of the sprite image in pixels
		this.width = 96;
		this.height = 96;

		this.offx = -48;
		this.offy = -48;

		MainWindow window = engine.mainWindow;

		// the 2 is indeed a magic number: it makes the spaceship vanish ~1sec
		// after flying off the map
		int diff = Math.max(window.getWidth() - xpos, window.getHeight() - ypos) * 2;

		destX = x + diff;
		destY = y + diff;

		frame = 1;
	}

	public void moveImpl() {
		// play sound
		if(!soundPlaying && speedFactor > 1.05) {
			sound = new MP3(MainWindow.class.getResource("/sounds/" + Sound.SPACESHIP_LAUNCH.getWavName() + ".mp3"));
			sound.play();
			soundPlaying = true;
		}

		int dist = getDis(x, y, destX, destY);

		if(dist > prevDist) {
			System.out.println("exploding spaceship");
			remove();
		}

		prevDist = dist;

		this.x += 10 * speedFactor;
		this.y += 10 * speedFactor;
		speedFactor *= 1.03;
	}
}
