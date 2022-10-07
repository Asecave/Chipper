package com.asecave.chipper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class AndGate extends Gate {

	public AndGate(Grid grid) {
		super(grid, Tile.AND);
	}

	@Override
	public void render(ShapeRenderer sr, int x, int y, int scale) {
		sr.setColor(Color.GRAY);
		sr.set(ShapeType.Filled);
		sr.rect(x * scale + 1, y * scale + 1, scale - 1, scale - 1);
		sr.setColor(new Color(0.3f, 0.3f, 0.3f, 1f));
		sr.rect(x * scale + 3, y * scale + 3, scale - 5, scale - 5);
		sr.setColor(new Color(0.4f, 0.4f, 0.4f, 1f));
		sr.rect(x * scale + 4, y * scale + 1, scale - 7, 1);
		sr.rect(x * scale + scale - 1, y * scale + 4, 1, scale - 7);
		sr.rect(x * scale + 1, y * scale + 4, 1, scale - 7);
		sr.setColor(new Color(0.7f, 0.7f, 0.7f, 1f));
		sr.rect(x * scale + 4, y * scale + scale - 1, scale - 7, 1);
	}

}
