package com.asecave.chipper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class WireTile extends Tile {

	private boolean connectedNorth = false;
	private boolean connectedSouth = false;
	private boolean connectedEast = false;
	private boolean connectedWest = false;

	public WireTile(Grid grid) {
		super(grid, Tile.WIRE);
	}

	@Override
	public void render(ShapeRenderer sr, int x, int y, int scale) {
		sr.setColor(Color.BLUE);
		sr.rect(x * scale + scale / 2 - 1, y * scale + scale / 2 - 1, 3, 3);
		sr.setColor(Color.RED);
		if (connectedNorth) {
			sr.rect(x * scale + scale / 2 - 1, y * scale + scale / 2 + 2, 3, scale / 2 - 2);
		}
		if (connectedSouth) {
			sr.rect(x * scale + scale / 2 - 1, y * scale, 3, scale / 2 - 1);
		}
		if (connectedEast) {
			sr.rect(x * scale + scale / 2 + 2, y * scale + scale / 2 - 1, scale / 2 - 2, 3);
		}
		if (connectedWest) {
			sr.rect(x * scale, y * scale + scale / 2 - 1, scale / 2 - 1, 3);
		}
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
}
