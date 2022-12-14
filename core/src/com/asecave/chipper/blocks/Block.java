package com.asecave.chipper.blocks;

import com.asecave.chipper.Grid;
import com.asecave.chipper.Tile;
import com.asecave.chipper.cables.CableTile;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Block extends Tile {

	public CableTile cableTile;
	private int rotation = 0;

	public Block(Grid grid, int type) {
		super(grid, type);
	}

	@Override
	public void render(ShapeRenderer sr, int x, int y, int scale) {

		if (cableTile != null) {
			cableTile.render(sr, x, y, scale);
		}
	}
	
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	
	public int getRotation() {
		return rotation;
	}
}
