package com.asecave.chipper;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Block extends Tile {

	public CableTile cableTile;

	public Block(Grid grid, int type) {
		super(grid, type);
	}

	@Override
	public void render(ShapeRenderer sr, int x, int y, int scale) {

		if (cableTile != null) {
			cableTile.render(sr, x, y, scale);
		}
	}
}
