package com.asecave.chipper;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Tile {

	protected static final int WIRE = 0;
	protected static final int BUS = 1;
	public static final int AND = 2;
	public static final int OR = 3;
	public static final int SWITCH = 4;
	public static final int LAMP = 5;

	public final int type;
	protected Grid grid;
	private boolean isCompiled = false;

	public Tile(Grid grid, int type) {
		this.grid = grid;
		this.type = type;
	}

	public abstract void render(ShapeRenderer sr, int x, int y, int scale);
	
	public boolean isCompiled() {
		return isCompiled;
	}
	
	public void setCompiled() {
		isCompiled = true;
	}
	
	public void resetCompiled() {
		isCompiled = false;
	}
}
