package com.asecave.chipper.blocks;

import com.asecave.chipper.Grid;
import com.asecave.chipper.Tile;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class XorGate extends Block {

	public XorGate(Grid grid) {
		super(grid, Tile.XOR);
	}

	@Override
	public void render(ShapeRenderer sr, int x, int y, int scale) {
		
		super.render(sr, x, y, scale);
		
		sr.setColor(Color.GRAY);
		sr.set(ShapeType.Filled);
		sr.rect(x + 1, y + 1, scale - 1, scale - 1);
		sr.setColor(new Color(0.3f, 0.3f, 0.3f, 1f));
		sr.rect(x + 3, y + 3, scale - 5, scale - 5);
		sr.setColor(new Color(0.4f, 0.4f, 0.4f, 1f));
		sr.rect(x + 4, y + 1, scale - 7, 1);
		sr.rect(x + scale - 1, y + 4, 1, scale - 7);
		sr.rect(x + 1, y + 4, 1, scale - 7);
		sr.setColor(new Color(0.7f, 0.7f, 0.7f, 1f));
		sr.rect(x + 4, y + scale - 1, scale - 7, 1);
		
		sr.rect(x + 7, y + 8, 1, 1);
		sr.rect(x + 6, y + 7, 1, 1);
		sr.rect(x + 5, y + 6, 1, 1);
		sr.rect(x + 8, y + 7, 1, 1);
		sr.rect(x + 9, y + 6, 1, 1);
	}

}
