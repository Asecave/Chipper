package com.asecave.chipper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Lamp extends Block {
	
	private boolean state = false;

	public Lamp(Grid grid) {
		super(grid, Tile.LAMP);
		cableTile = new WireTile(grid);
	}

	@Override
	public void render(ShapeRenderer sr, int x, int y, int scale) {
		super.render(sr, x, y, scale);
		
		if (state) {
			sr.setColor(Color.DARK_GRAY.cpy().add(0.1f, 0.1f, 0.1f, 1f));
			sr.rect(x + 1, y + 1, scale - 1, scale - 1);
			sr.setColor(Color.WHITE.cpy().add(0f, 0f, -0.2f, 0f));
			sr.rect(x + 2, y + 2, scale - 3, scale - 3);
		} else {
			sr.setColor(Color.DARK_GRAY.cpy().add(0.1f, 0.1f, 0.1f, 1f));
			sr.rect(x + 1, y + 1, scale - 1, scale - 1);
			sr.setColor(Color.DARK_GRAY);
			sr.rect(x + 2, y + 2, scale - 3, scale - 3);
		}
	}
}
