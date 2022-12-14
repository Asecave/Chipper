package com.asecave.chipper.blocks;

import com.asecave.chipper.Grid;
import com.asecave.chipper.Tile;
import com.asecave.chipper.cables.WireTile;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Switch extends Block {
	
	private boolean state = false;

	public Switch(Grid grid) {
		super(grid, Tile.SWITCH);
		cableTile = new WireTile(grid);
		cableTile.setParent(this);
	}

	@Override
	public void render(ShapeRenderer sr, int x, int y, int scale) {
		
		super.render(sr, x, y, scale);
		
		sr.setColor(Color.GRAY);
		sr.set(ShapeType.Filled);
		sr.rect(x + 4, y + 3, scale - 7, scale - 5);
		
		if (state) {
			sr.setColor(Color.LIGHT_GRAY);
			sr.rect(x + 5, y + 5, scale - 9, scale - 8);
			sr.setColor(Color.LIGHT_GRAY.cpy().add(-0.15f, -0.15f, -0.15f, 1f));
			sr.rect(x + 5, y + 4, 5, 1);
		} else {
			sr.setColor(Color.DARK_GRAY);
			sr.rect(x + 5, y + 4, scale - 9, scale - 8);
			sr.setColor(Color.DARK_GRAY.cpy().add(0.15f, 0.15f, 0.15f, 1f));
			sr.rect(x + 5, y + 10, 5, 1);
		}
	}

	public void toggle() {
		state = !state;
	}
	
	public boolean getState() {
		return state;
	}
}
