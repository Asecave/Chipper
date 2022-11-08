package com.asecave.chipper.blocks;

import com.asecave.chipper.Grid;
import com.asecave.chipper.Tile;
import com.asecave.chipper.cables.WireTile;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Clock extends Block {

	private boolean state = false;

	public Clock(Grid grid) {
		super(grid, Tile.CLOCK);
		cableTile = new WireTile(grid);
		cableTile.setParent(this);
	}

	@Override
	public void render(ShapeRenderer sr, int x, int y, int scale) {
		super.render(sr, x, y, scale);

		sr.setColor(Color.GRAY);
		sr.set(ShapeType.Filled);
		sr.rect(x + 2, y + 2, scale - 3, scale - 3);
		sr.setColor(new Color(0.3f, 0.3f, 0.3f, 1f));
		sr.rect(x + 3, y + 3, scale - 5, scale - 5);
		
		sr.setColor(new Color(0.7f, 0.7f, 0.7f, 1f));
		sr.rect(x + 5, y + 7, 1, 2);
		sr.rect(x + 5, y + 6, 2, 1);
		sr.rect(x + 7, y + 6, 1, 3);
		sr.rect(x + 8, y + 8, 2, 1);
		sr.rect(x + 9, y + 6, 1, 2);
	}

	public void toggle() {
		state = !state;
	}

	public boolean getState() {
		return state;
	}
}
