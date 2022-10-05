package com.asecave.chipper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Hud {

	public void render(ShapeRenderer sr) {
		
		sr.setColor(Color.GRAY);
		Tile[] tiles = {
				new WireTile(null)
		};
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].render(sr, i * 50 + 100, 100, 20);
		}
	}
}
