package com.asecave.chipper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {

	public static Main INSTANCE;

	private ShapeRenderer sr;
	public static OrthographicCamera cam;
	private OrthographicCamera hudCam;
	private InputAdapter input = new InputAdapter() {
		public boolean scrolled(float amountX, float amountY) {
			Main.this.scrolled((int) amountY);
			return true;
		};
	};

	private Grid grid;
	private Hud hud;

	@Override
	public void create() {
		INSTANCE = this;
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);

		cam = new OrthographicCamera();
		hudCam = new OrthographicCamera();

		grid = new Grid(10, 10);
		hud = new Hud();

		cam.position.add(Grid.scale * grid.getWidth() / 2, Grid.scale * grid.getHeight() / 2, 0);

		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1f);

		if (Gdx.input.isButtonPressed(Buttons.MIDDLE)) {
			cam.position.x -= Gdx.input.getDeltaX() * cam.zoom;
			cam.position.y += Gdx.input.getDeltaY() * cam.zoom;
		}

		cam.update();
		hudCam.update();

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		sr.setProjectionMatrix(cam.combined);
		sr.begin();
		grid.render(sr);
		sr.setProjectionMatrix(hudCam.combined);
		hud.render(sr);
		sr.end();

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		sr.dispose();
		grid.clockThreadRunning = false;
	}

	@Override
	public void resize(int width, int height) {
		cam.viewportWidth = width;
		cam.viewportHeight = height;
		hudCam.viewportWidth = width;
		hudCam.viewportHeight = height;
		hudCam.position.x = Gdx.graphics.getWidth() / 2;
		hudCam.position.y = Gdx.graphics.getHeight() / 2;
	}

	private void scrolled(int dir) {
		Vector2 prev = getRelativeCursor();
		if (cam.zoom + dir / 20f >= 0.05f && cam.zoom + dir / 20f <= 1f) {
			cam.zoom += dir / 20f;
		}
		Vector2 after = getRelativeCursor();
		cam.position.add(new Vector3(after.sub(prev), 0));

	}

	public static Vector2 getRelativeCursor() {
		Vector3 v = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		return new Vector2(v.x, v.y);
	}

	public Grid getGrid() {
		return grid;
	}
}
