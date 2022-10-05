package com.asecave.chipper;

import com.badlogic.gdx.Gdx;
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

		sr.set(ShapeType.Filled);
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				sr.setColor(Color.DARK_GRAY);
				sr.rect(x * scale, y * scale, scale, 1);
				sr.rect(x * scale, y * scale, 1, scale);
				int mx = (int) ((Gdx.input.getX() / Main.cam.viewportWidth * Gdx.graphics.getWidth()) + Main.cam.position.x - Gdx.graphics.getWidth() / 2);
				int my = (int) (Gdx.graphics.getHeight() - Gdx.input.getY() + Main.cam.position.y - Gdx.graphics.getHeight() / 2);
				if (mx >= x * scale && mx < x * scale + scale && my >= y * scale
						&& my < y * scale + scale) {
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
