package com.asecave.chipper;

public abstract class Block extends Tile {

	public int connectedWireType = -1;
	
	public Block(Grid grid, int type) {
		super(grid, type);
	}

}
