package com.asecave.chipper;

import com.asecave.chipper.blocks.XorGate;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Hud {

	private boolean overToolbar = false;
	private boolean overCompile = false;

	public void render(ShapeRenderer sr) {

		int sw = Gdx.graphics.getWidth();
		int sh = Gdx.graphics.getHeight();
		int w = 400;
		int h = 50;

		sr.translate(sw / 2 - w / 2, 50, 0);

		int mx = Gdx.input.getX() - sw / 2 + w / 2;
		if (mx > 0 && mx < w && sh - Gdx.input.getY() > 50 && sh - Gdx.input.getY() < 50 + h) {
			overToolbar = true;
			sr.setColor(Color.GRAY);
			int x = (int) (mx / h);
			sr.rect(x * h, 0, h, h);
			if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				Main.INSTANCE.getGrid().placingTile = x;
			}
		} else {
			overToolbar = false;
		}

		sr.setColor(new Color(0f, 0f, 0f, 0.3f));
		sr.rect(0, 0, w, h);
		sr.setColor(Color.GRAY);
		sr.set(ShapeType.Line);
		sr.rect(0, 0, 0, 0, w, h, 1, 1, 0, Color.GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
		sr.rect(1, 1, 0, 0, w - 2, h - 2, 1, 1, 0, Color.GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
		sr.set(ShapeType.Filled);
		sr.setColor(Color.RED);
		sr.rect(h / 2 - 5, h / 2 - 5, 10, 10);
		sr.setColor(new Color(0.5f, 0f, 0f, 1f));
		sr.rect(h + h / 2 - 8, h / 2 - 8, 16, 16);
		sr.setColor(Color.LIGHT_GRAY);
		sr.rectLine(2 * h + h / 2 - 8, h / 2 - 4, 2 * h + h / 2 + 1, h / 2 + 5, 4);
		sr.rectLine(2 * h + h / 2 + 8, h / 2 - 4, 2 * h + h / 2 - 1, h / 2 + 5, 4);
		
		sr.rectLine(3 * h + h / 2 - 8, h / 2 + 4, 3 * h + h / 2 + 1, h / 2 - 5, 4);
		sr.rectLine(3 * h + h / 2 + 8, h / 2 + 4, 3 * h + h / 2 - 1, h / 2 - 5, 4);
		
		sr.rectLine(4 * h + 18, 18, 4 * h + 32, 32, 3);
		sr.rectLine(4 * h + 32, 18, 4 * h + 18, 32, 3);
		
		sr.rectLine(5 * h + h / 2 - 2, h / 2 - 2, 5 * h + h / 2 - 2, h / 2 + 12, 4);
		sr.rectLine(5 * h + h / 2 - 2, h / 2 - 10, 5 * h + h / 2 - 2, h / 2 - 6, 4);
		
		sr.rectLine(6 * h + h / 2 - 12, h / 2, 6 * h + h / 2 - 4, h / 2, 3);
		sr.rectLine(6 * h + h / 2 - 5, h / 2 - 1, 6 * h + h / 2 + 4, h / 2 + 8, 3);
		sr.rectLine(6 * h + h / 2 + 4, h / 2, 6 * h + h / 2 + 12, h / 2, 3);
		
		sr.set(ShapeType.Line);
		sr.circle(7 * h + h / 2, h / 2, 10);
		sr.circle(7 * h + h / 2, h / 2, 9);
		sr.circle(7 * h + h / 2, h / 2, 8);
		sr.set(ShapeType.Filled);
		sr.rectLine(7 * h + 18, 18, 7 * h + 32, 32, 3);
		sr.rectLine(7 * h + 32, 18, 7 * h + 18, 32, 3);

		sr.translate(-(sw / 2 - w / 2), -50, 0);

		sr.translate(sw - h - 50, 50, 0);
		mx = Gdx.input.getX() - sw + h + 50;
		if (mx > 0 && mx < h && sh - Gdx.input.getY() > 50 && sh - Gdx.input.getY() < 50 + h) {
			overCompile = true;
			sr.setColor(Color.GRAY);
			sr.rect(0, 0, h, h);
			if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				Main.INSTANCE.getGrid().compile();
			}
		} else {
			overCompile = false;
		}

		sr.setColor(new Color(0f, 0f, 0f, 0.3f));
		sr.rect(0, 0, h, h);
		sr.set(ShapeType.Line);
		sr.rect(0, 0, 0, 0, h, h, 1, 1, 0, Color.GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
		sr.rect(1, 1, 0, 0, h - 2, h - 2, 1, 1, 0, Color.GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
		sr.set(ShapeType.Filled);
		int triHeight = 22;
		int triLength = 22;
		sr.setColor(Color.FOREST);
		sr.triangle(h / 2 - triLength / 2, h / 2 - triHeight / 2, h / 2 - triLength / 2, h / 2 + triHeight / 2,
				h / 2 + triLength / 2, h / 2);
		sr.translate(-(sw - h - 50), -50, 0);

		if (overToolbar || overCompile) {
			Main.INSTANCE.getGrid().canPlace = false;
		} else {
			Main.INSTANCE.getGrid().canPlace = true;
		}
	}
}
