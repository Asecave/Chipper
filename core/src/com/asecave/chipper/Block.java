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
	
	public Tile[] getConnectedTiles() {
		if (cableTile == null) {
			return new Tile[0];
		} else {
			int connectionCount = 0;
			if (cableTile.isConnectedNorth())
				connectionCount++;
			if (cableTile.isConnectedSouth())
				connectionCount++;
			if (cableTile.isConnectedEast())
				connectionCount++;
			if (cableTile.isConnectedWest())
				connectionCount++;
			Tile[] connected = new Tile[connectionCount];
			int i = 0;
			if (cableTile.isConnectedNorth()) {
				connected[i] = cableTile.getConnectedCableNorth();
				i++;
			}
			if (cableTile.isConnectedSouth()) {
				connected[i] = cableTile.getConnectedCableSouth();
				i++;
			}
			if (cableTile.isConnectedEast()) {
				connected[i] = cableTile.getConnectedCableEast();
				i++;
			}
			if (cableTile.isConnectedWest()) {
				connected[i] = cableTile.getConnectedCableWest();
				i++;
			}
			return connected;
		}
	}
}
