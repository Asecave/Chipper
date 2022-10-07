package com.asecave.chipper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class WireTile extends CableTile {

	public WireTile(Grid grid) {
		super(grid, Tile.WIRE);
	}

	@Override
	public void render(ShapeRenderer sr, int x, int y, int scale) {
		sr.setColor(Color.RED);
		sr.rect(x * scale + scale / 2 - 1, y * scale + scale / 2 - 1, 3, 3);
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
		if (bridge) {
			sr.setColor(Color.GRAY);
			sr.rect(x * scale + scale / 2 - 2, y * scale + scale / 2 - 2, 5, 1);
			sr.rect(x * scale + scale / 2 - 2, y * scale + scale / 2 + 2, 5, 1);
		}
	}
}
