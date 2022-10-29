package com.asecave.chipper;

public abstract class CableTile extends Tile {

	protected CableTile connectedCableNorth = null;
	protected CableTile connectedCableSouth = null;
	protected CableTile connectedCableEast = null;
	protected CableTile connectedCableWest = null;
	
	private Block parent;

	protected boolean bridge = false;

	public CableTile(Grid grid, int type) {
		super(grid, type);
	}

	public void connectNorth(CableTile tile) {
		connectedCableNorth = tile;
	}

	public void connectSouth(CableTile tile) {
		connectedCableSouth = tile;
	}

	public void connectEast(CableTile tile) {
		connectedCableEast = tile;
	}

	public void connectWest(CableTile tile) {
		connectedCableWest = tile;
	}

	public void disconnectNorth() {
		connectedCableNorth = null;
	}

	public void disconnectSouth() {
		connectedCableSouth = null;
	}

	public void disconnectEast() {
		connectedCableEast = null;
	}

	public void disconnectWest() {
		connectedCableWest = null;
	}

	public void toggleBridge() {
		bridge = !bridge;
	}

	public void setBridge(boolean bridge) {
		this.bridge = bridge;
	}

	public CableTile getConnectedCableNorth() {
		return connectedCableNorth;
	}

	public CableTile getConnectedCableSouth() {
		return connectedCableSouth;
	}

	public CableTile getConnectedCableEast() {
		return connectedCableEast;
	}

	public CableTile getConnectedCableWest() {
		return connectedCableWest;
	}
	
	public boolean isConnectedNorth() {
		return connectedCableNorth != null;
	}
	
	public boolean isConnectedSouth() {
		return connectedCableSouth != null;
	}
	
	public boolean isConnectedEast() {
		return connectedCableEast != null;
	}

	public boolean isConnectedWest() {
		return connectedCableWest != null;
	}
	
	public void setParent(Block parent) {
		this.parent = parent;
	}
	
	public Block getParent() {
		return parent;
	}
}
