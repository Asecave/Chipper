package com.asecave.chipper.cables;

import com.asecave.chipper.Grid;
import com.asecave.chipper.Tile;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class WireTile extends CableTile {

	public WireTile(Grid grid) {
		super(grid, Tile.WIRE);
	}

	@Override
	public void render(ShapeRenderer sr, int x, int y, int scale) {
		Color color = Color.RED.cpy();
		if ((power & 1) == 0) {
			color.add(-0.5f, -0.5f, -0.5f, 0f);
		}
		sr.setColor(color);
		sr.rect(x + scale / 2 - 1, y + scale / 2 - 1, 3, 3);
		if (isConnectedNorth()) {
			sr.rect(x + scale / 2 - 1, y + scale / 2 + 2, 3, scale / 2 - 2);
		}
		if (isConnectedSouth()) {
			sr.rect(x + scale / 2 - 1, y, 3, scale / 2 - 1);
		}
		if (isConnectedEast()) {
			sr.rect(x + scale / 2 + 2, y + scale / 2 - 1, scale / 2 - 2, 3);
		}
		if (isConnectedWest()) {
			sr.rect(x, y + scale / 2 - 1, scale / 2 - 1, 3);
		}
		if (bridge) {
			sr.setColor(Color.GRAY);
			sr.rect(x + scale / 2 - 2, y + scale / 2 - 2, 5, 1);
			sr.rect(x + scale / 2 - 2, y + scale / 2 + 2, 5, 1);
		}
	}
}
