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

	// NOTE: used for movement
	static int[] CDx = {
			0, 10
	};
	static int[] CDy = {
			0, 0
	};

	// CONSTRUCTORS
	public SpaceshipSprite(Micropolis engine, int xpos, int ypos) {
		this(engine, xpos, ypos, 2000, ypos, 2.5);
	}

	public SpaceshipSprite(Micropolis engine, int xpos, int ypos, int xDest, int yDest, double speedFactor) {
		super(engine, SpriteKind.SPACESHIP);

		setStart(xpos, ypos);
		setDestination(xDest, yDest);

		this.speedFactor = speedFactor;
		this.soundPlaying = false;

		// size of the sprite image in pixels
		this.width = 96;
		this.height = 96;

		this.offx = -48;
		this.offy = -48;

		frame = 1;
	}

	private void setStart(int x, int y) {
		this.x = tilePosToPixel(x);
		this.y = tilePosToPixel(y);
	}

	private void setDestination(int x, int y) {
		this.destX = tilePosToPixel(x);
		this.destY = tilePosToPixel(y);
	}

	public void moveImpl() {
//		double secondsTilBoom = stepsTilBoom() / city.simSpeed.getAnimationsPerSecond();
//		double soundDuration = 3.25;

		// play sound
		if(!soundPlaying) {
			sound = new MP3(MainWindow.class.getResource("/sounds/" + Sound.SPACESHIP_LAUNCH.getWavName() + ".mp3"));
			sound.play();
			soundPlaying = true;
		}

		if(getDis(x, y, destX, destY) <= 10 * speedFactor) {
			this.explodeSprite();
			this.city.sprites.remove(this);
		}

		this.x += CDx[frame] * speedFactor;
		this.y += CDy[frame] * speedFactor;
	}
}
