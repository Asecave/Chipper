package com.asecave.chipper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Grid {
	
	Tile[][] tiles;
	
	private int scale = 10;

	public Grid(int width, int height) {
		
		tiles = new Tile[width][height];
	
	}
	
	public void render(ShapeRenderer sr) {
		
		sr.setColor(Color.DARK_GRAY);
		sr.set(ShapeType.Filled);
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				sr.rect(x * scale, y * scale, scale, 1);
				sr.rect(x * scale, y * scale, 1, scale);
			}
		}
		
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				if (tiles[x][y] != null) {
					tiles[x][y].render(sr, x, y, scale);
				}
			}
		}
	}
}
