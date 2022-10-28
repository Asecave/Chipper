package com.asecave.chipper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BusTile extends CableTile {

	public BusTile(Grid grid) {
		super(grid, Tile.BUS);
	}

	@Override
	public void render(ShapeRenderer sr, int x, int y, int scale) {
		sr.setColor(new Color(0.5f, 0f, 0f, 1f));
		sr.rect(x + scale / 2 - 2, y + scale / 2 - 2, 5, 5);
		if (isConnectedNorth()) {
			sr.rect(x + scale / 2 - 2, y + scale / 2 + 3, 5, scale / 2 - 3);
		}
		if (isConnectedSouth()) {
			sr.rect(x + scale / 2 - 2, y, 5, scale / 2 - 2);
		}
		if (isConnectedEast()) {
			sr.rect(x + scale / 2 + 3, y + scale / 2 - 2, scale / 2 - 3, 5);
		}
		if (isConnectedWest()) {
			sr.rect(x, y + scale / 2 - 2, scale / 2 - 2, 5);
		}
		if (bridge) {
			sr.setColor(Color.GRAY);
			sr.rect(x + scale / 2 - 3, y + scale / 2 - 3, 7, 1);
			sr.rect(x + scale / 2 - 3, y + scale / 2 + 3, 7, 1);
		}
	}
}
