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
public class RocketSprite extends Sprite {
	private int destX;
	private int destY;
	private double speedFactor;
	private boolean soundPlaying;

	// NOTE: used for movement
	static int[] CDx = {
			0, 0, 6, 8, 6, 0, -6, -8, -6
	};
	static int[] CDy = {
			0, -8, -6, 0, 6, 8, 6, 0, -6
	};

	// CONSTRUCTORS
	public RocketSprite(Micropolis engine, int xpos, int ypos, int xDest, int yDest) {
		this(engine, xpos, ypos, xDest, yDest, 2);
	}

	public RocketSprite(Micropolis engine, int xpos, int ypos, int xDest, int yDest, double speedFactor) {
		super(engine, SpriteKind.ROC);

		setStart(xpos, ypos);
		setDestination(xDest, yDest);

		this.speedFactor = speedFactor;
		this.soundPlaying = false;

		// size of the sprite image in pixels
		this.width = 64;
		this.height = 64;

		this.offx = -32;
		this.offy = -32;

		frame = getDir(x, y, destY, destX);
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
	public static int getDir(int orgX, int orgY, int desX, int desY) {
		final int Gdtab[] = {
				// 1 == north, 3 == east, 5 == south, 7 == west
				0, 3, 2, 1, 3, 4, 5, 7, 6, 5, 7, 8, 1
		};
		int dispX = desX - orgX;
		int dispY = desY - orgY;

		int z;
		// going left
		if(dispX < 0) {
			// going up
			if(dispY < 0) {
				z = 11;
			}
			else {
				z = 8;
			}
		}
		// going right
		else {
			// going up
			if(dispY < 0) {
				z = 2;
			}
			else {
				z = 5;
			}
		}

		dispX = Math.abs(dispX);
		dispY = Math.abs(dispY);

		if(dispX <= 6 || dispY <= 6) {
			if(dispX < dispY)
				z++;
			else if(dispY < dispX)
				z--;
		}

		if(z >= 1 && z <= 12) {
			return Gdtab[z];
		}

		return 0;
	}

	private int stepsTilBoom() {
		int absY = Math.abs(destY - y);
		int absX = Math.abs(destX - x);
		int diffMin = Math.min(absX, absY);
		int diffMax = Math.max(absX, absY);
		return (int) ((diffMin / 12 + (diffMax - diffMin) / 8) / speedFactor);
	}

	public void moveImpl() {
		double secondsTilBoom = stepsTilBoom() / city.simSpeed.getAnimationsPerSecond();
		double soundDuration = 3.5;

		// play sound
		if(secondsTilBoom <= soundDuration && !soundPlaying) {
			city.makeSound(x, y, Sound.DUBSPLOSION);
			soundPlaying = true;
		}

		if(getDis(x, y, destX, destY) <= 6 * speedFactor) {
			// this.destroyTile(pixelToTilePos(x), pixelToTilePos(y));
			this.explodeSprite();
			this.city.sprites.remove(this);
		}

		int d = getDir(x, y, destX, destY);
		frame = turnTo(frame, d);

		this.x += CDx[frame] * speedFactor;
		this.y += CDy[frame] * speedFactor;
	}
}
