package com.asecave.chipper;

import org.w3c.dom.events.MouseEvent;

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
	private OrthographicCamera cam;
	private InputAdapter input = new InputAdapter() {
		public boolean scrolled(float amountX, float amountY) {
			
			return true;
		};
	};
	
	private Grid grid;
	
	@Override
	public void create () {
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);
		
		cam = new OrthographicCamera();
		
		grid = new Grid(100, 100);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1f);
		
		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			cam.position.x -= Gdx.input.getDeltaX();
			cam.position.y += Gdx.input.getDeltaY();
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
	public void dispose () {
		sr.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		cam.viewportWidth = width;
		cam.viewportHeight = height;
	}
}
