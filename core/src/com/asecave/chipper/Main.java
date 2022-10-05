package com.asecave.chipper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {

	private ShapeRenderer sr;
	public static OrthographicCamera cam;
	private InputAdapter input = new InputAdapter() {
		public boolean scrolled(float amountX, float amountY) {
			Main.this.scrolled((int) amountY);
			return true;
		};
	};

	private Grid grid;

	@Override
	public void create() {
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);

		cam = new OrthographicCamera();

		grid = new Grid(100, 100);

		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1f);

		if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
			cam.position.x -= Gdx.input.getDeltaX() * (cam.viewportWidth / Gdx.graphics.getWidth());
			cam.position.y += Gdx.input.getDeltaY() * (cam.viewportHeight / Gdx.graphics.getHeight());
		}

		cam.update();

		sr.setProjectionMatrix(cam.combined);
		sr.begin();
		grid.render(sr);
		sr.end();

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		sr.dispose();
	}

	@Override
	public void resize(int width, int height) {
		cam.viewportWidth = width;
		cam.viewportHeight = height;
	}

	private void scrolled(int dir) {
		float ar = cam.viewportWidth / cam.viewportHeight;
		if (cam.viewportWidth + dir * 100 > 10 && cam.viewportWidth + dir * 100 <= Gdx.graphics.getWidth()) {
			cam.viewportWidth += dir * 100;
			cam.viewportHeight += (dir * 100) / ar;
		}
	}
}
