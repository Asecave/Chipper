package com.asecave.chipper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
			cam.position.x -= Gdx.input.getDeltaX() * cam.zoom;
			cam.position.y += Gdx.input.getDeltaY() * cam.zoom;
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
		Vector2 prev = getRelativeCursor();
		cam.zoom += dir / 20f;
		Vector2 after = getRelativeCursor();
		cam.position.add(new Vector3(after.sub(prev), 0));
		
	}
	
	public static Vector2 getRelativeCursor() {
		float x = cam.position.x;
		float y = cam.position.y;
		float mx = (Gdx.input.getX() - Gdx.graphics.getWidth() / 2f) * cam.zoom;
		float my = ((Gdx.graphics.getHeight() - Gdx.input.getY()) - Gdx.graphics.getHeight() / 2f) * cam.zoom;
		
		return new Vector2(x + mx, y + my);
	}
}
