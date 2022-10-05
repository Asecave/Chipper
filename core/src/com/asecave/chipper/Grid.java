package com.asecave.chipper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class Grid {

	Tile[][] tiles;

	public static int scale = 14;

	public Grid(int width, int height) {

		tiles = new Tile[width][height];

	}

	public void render(ShapeRenderer sr) {

		if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
			Vector3 rel = Main.getRelativeCursor().scl(1f / scale);
			if (rel.x >= 0 && rel.x < tiles.length && rel.y >= 0 && rel.y < tiles[0].length) {
				tiles[(int) rel.x][(int) rel.y] = new WireTile(this);
			}
		}
		
		sr.set(ShapeType.Filled);
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				sr.setColor(Color.DARK_GRAY);
				sr.rect(x * scale, y * scale, scale, 1);
				sr.rect(x * scale, y * scale, 1, scale);
				Vector3 rel = Main.getRelativeCursor().scl(1f / scale);
				if (rel.x >= x && rel.x < x + 1 && rel.y >= y && rel.y < y + 1) {
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
	
	public Tile getMouseTile() {
		Vector3 rel = Main.getRelativeCursor().scl(1f / scale);
		if (rel.x >= 0 && rel.x < tiles.length && rel.y >= 0 && rel.y < tiles[0].length) {
			return tiles[(int) rel.x][(int) rel.y];
		}
		return null;
	}
}
