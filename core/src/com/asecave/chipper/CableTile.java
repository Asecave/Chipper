package com.asecave.chipper;

public abstract class CableTile extends Tile {

	protected boolean connectedNorth = false;
	protected boolean connectedSouth = false;
	protected boolean connectedEast = false;
	protected boolean connectedWest = false;
	
	protected boolean bridge = false;

	public CableTile(Grid grid, int type) {
		super(grid, type);
	}

	public void connectNorth() {
		connectedNorth = true;
	}
	
	public void connectSouth() {
		connectedSouth = true;
	}
	
	public void connectEast() {
		connectedEast = true;
	}
	
	public void connectWest() {
		connectedWest = true;
	}
	
	public void disconnectNorth() {
		connectedNorth = false;
	}

	public void disconnectSouth() {
		connectedSouth = false;
	}

	public void disconnectEast() {
		connectedEast = false;
	}

	public void disconnectWest() {
		connectedWest = false;
	}

	public void toggleBridge() {
		bridge = !bridge;
	}

	public void setBridge(boolean bridge) {
		this.bridge = bridge;
	}
}
