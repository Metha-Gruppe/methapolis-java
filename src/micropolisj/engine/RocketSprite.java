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
	int destX;
	int destY;

	// NOTE: used for movement
	static int[] CDx = {
			0, 0, 6, 8, 6, 0, -6, -8, -6
	};
	static int[] CDy = {
			0, -8, -6, 0, 6, 8, 6, 0, -6
	};

	public RocketSprite(Micropolis engine, int xpos, int ypos, int xDest, int yDest) {
		super(engine, SpriteKind.ROC);

		setStart(xpos, ypos);
		setDestination(xDest, yDest);
		
		// size of the sprite image in pixels
		this.width = 48;
		this.height = 48;
		
		
		this.offx = -24;
		this.offy = -24;

		frame = getDir(x, y, destY, destX);
	}
	
	private void setStart(int x, int y)	{
		this.x = tilePosToPixel(x);
		this.y = tilePosToPixel(y);
	}
	
	private void setDestination(int x, int y){
		this.destX = tilePosToPixel(x);
		this.destY = tilePosToPixel(y);
	}

	public void moveImpl() {
		if(getDis(x, y, destX, destY) <= 6)	{
//			this.destroyTile(pixelToTilePos(x), pixelToTilePos(y));
			this.explodeSprite();
			this.city.sprites.remove(this);
		}
		
		int d = getDir(x, y, destX, destY);
		frame = turnTo(frame, d);

		this.x += CDx[frame];
		this.y += CDy[frame];
	}
}
