package com.asecave.chipper;

public abstract class Gate extends Tile {

	public int connectedWireType = -1;
	
	public Gate(Grid grid, int type) {
		super(grid, type);
	}

}
