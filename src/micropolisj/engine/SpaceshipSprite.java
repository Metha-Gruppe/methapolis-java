// This file is part of MicropolisJ.
// Copyright (C) 2013 Jason Long
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// MicropolisJ is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine;

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

	// NOTE: used for movement
	static int[] CDx = {
			0, 10
	};
	static int[] CDy = {
			0, 0
	};

	// CONSTRUCTORS
	public SpaceshipSprite(Micropolis engine, int xpos, int ypos, int xDest, int yDest) {
		this(engine, xpos, ypos, xDest, yDest, 2);
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

	// custom: override to make it move more smooth
//	public static int getDir(int orgX, int orgY, int desX, int desY) {
//		return 1;
//	}

	public void moveImpl() {
//		double secondsTilBoom = stepsTilBoom() / city.simSpeed.getAnimationsPerSecond();
//		double soundDuration = 3.25;

		// play sound
		if(!soundPlaying) {
			System.out.println("dubstep!");
			city.makeSound(x, y, Sound.DUBSPLOSION);
			soundPlaying = true;
		}

		if(getDis(x, y, destX, destY) <= 6 * speedFactor) {
			// this.destroyTile(pixelToTilePos(x), pixelToTilePos(y));
			this.explodeSprite();
			this.city.sprites.remove(this);
		}

		this.x += CDx[frame] * speedFactor;
		this.y += CDy[frame] * speedFactor;
	}
}
