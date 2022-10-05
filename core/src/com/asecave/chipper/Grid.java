package com.asecave.chipper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Grid {

	Tile[][] tiles;

	public static int scale = 9;

	public Grid(int width, int height) {

		tiles = new Tile[width][height];

	}

	public void render(ShapeRenderer sr) {

		sr.set(ShapeType.Filled);
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				sr.setColor(Color.DARK_GRAY);
				sr.rect(x * scale, y * scale, scale, 1);
				sr.rect(x * scale, y * scale, 1, scale);
				Vector2 rel = Main.getRelativeCursor();
				float mx = (x + rel.x) / scale;
				float my = (y + rel.y) / scale;
				if (mx >= x && mx < x + 1 && my >= y && my < y + 1) {
					sr.setColor(Color.GRAY);
					sr.rect(x * scale + 1, y * scale + 1, scale - 1, scale - 1);
				}
			}
		}
		sr.rect(tiles.length * scale, 0, 1, tiles[0].length * scale);
		sr.rect(0, tiles[0].length * scale, tiles.length * scale, 1);

		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				if (tiles[x][y] != null) {
					tiles[x][y].render(sr, x, y, scale);
				}
			}
		}
	}
}
