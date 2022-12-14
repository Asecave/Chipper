package com.asecave.chipper;

import com.asecave.chipper.compiled.CompiledTile;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Tile {

	protected static final int WIRE = 0;
	protected static final int BUS = 1;
	public static final int AND = 2;
	public static final int OR = 3;
	public static final int XOR = 4;
	public static final int NOT = 5;
	public static final int SWITCH = 6;
	public static final int LAMP = 7;
	public static final int CLOCK = 8;

	public final int type;
	protected Grid grid;
	private CompiledTile compiledTile = null;

	public Tile(Grid grid, int type) {
		this.grid = grid;
		this.type = type;
	}

	public abstract void render(ShapeRenderer sr, int x, int y, int scale);
	
	public boolean isCompiled() {
		return compiledTile != null;
	}
	
	public void setCompiled(CompiledTile tile) {
		compiledTile = tile;
	}
	
	public void resetCompiled() {
		compiledTile = null;
	}
	
	public CompiledTile getCompiledVariant() {
		return compiledTile;
	}
}
