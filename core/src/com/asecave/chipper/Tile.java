package com.asecave.chipper;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Tile {

	protected static final int WIRE = 0;

	private final int type;
	protected Grid grid;

	public Tile(Grid grid, int type) {
		this.grid = grid;
		this.type = type;
	}

	public abstract void render(ShapeRenderer sr, int x, int y, int scale);
}
