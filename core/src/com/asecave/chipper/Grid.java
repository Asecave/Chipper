package com.asecave.chipper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Grid {

	private Tile[][] tiles;

	public static int scale = 14;

	private Vector2 startPlace;
	private Vector2 exactStartPlace;
	private Vector2 startDelete;
	private boolean flippedPlacement = false;
	private boolean flippedDetermined = false;
	public boolean canPlace = true;

	private Compiler compiler;

	public int placingTile = Tile.WIRE;

	public Grid(int width, int height) {

		tiles = new Tile[width][height];

		compiler = new Compiler();
	}

	public void render(ShapeRenderer sr) {

		if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			placingTile = 0;
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
			placingTile = 1;
		}

		drawGrid(sr);

		if (getPlacingTile() instanceof CableTile) {
			checkLinePlacement(sr);
		} else {
			checkBlockPlacement(sr);
		}

		checkDelete(sr);

		renderTiles(sr);

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			flippedPlacement = !flippedPlacement;
		}
	}

	private void renderTiles(ShapeRenderer sr) {
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				if (tiles[x][y] != null && Main.cam.frustum.boundsInFrustum(x * scale, y * scale, 0, scale, scale, 0)) {
					tiles[x][y].render(sr, x * scale, y * scale, scale);
				}
			}
		}
	}

	private void checkBlockPlacement(ShapeRenderer sr) {
		if (canPlace) {

			if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				Vector2 coords = getCursorGridCoords();
				if (coordsInBounds(coords)) {
					if (tiles[(int) coords.x][(int) coords.y] == null) {
						tiles[(int) coords.x][(int) coords.y] = getPlacingTile();
					}
				}
			}
		}
	}

	private void checkDelete(ShapeRenderer sr) {
		if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
			Vector2 coords = getCursorGridCoords();
			if (coordsInBounds(coords)) {
				if (startDelete == null) {
					startDelete = coords;
				}
				sr.setColor(Color.GRAY);
				Vector2 end = getCursorGridCoords().scl(scale).add(scale / 2, scale / 2);
				Vector2 start = startDelete.cpy().scl(scale).add(scale / 2, scale / 2);
				sr.line(start.x, start.y, end.x, start.y);
				sr.line(end.x, start.y, end.x, end.y);
				sr.line(start.x, start.y, start.x, end.y);
				sr.line(start.x, end.y, end.x, end.y);
			}
		} else {
			if (startDelete != null) {
				Vector2 end = getCursorGridCoords();
				if (coordsInBounds(end)) {
					int minX = (int) Math.min(startDelete.x, end.x);
					int minY = (int) Math.min(startDelete.y, end.y);
					int maxX = (int) Math.max(startDelete.x, end.x);
					int maxY = (int) Math.max(startDelete.y, end.y);
					for (int x = minX; x <= maxX; x++) {
						for (int y = minY; y <= maxY; y++) {
							tiles[x][y] = null;
							if (coordsInBounds(x + 1, y) && tiles[x + 1][y] instanceof CableTile) {
								((CableTile) tiles[x + 1][y]).disconnectWest();
								((CableTile) tiles[x + 1][y]).setBridge(false);
							}
							if (coordsInBounds(x - 1, y) && tiles[x - 1][y] instanceof CableTile) {
								((CableTile) tiles[x - 1][y]).disconnectEast();
								((CableTile) tiles[x - 1][y]).setBridge(false);
							}
							if (coordsInBounds(x, y + 1) && tiles[x][y + 1] instanceof CableTile) {
								((CableTile) tiles[x][y + 1]).disconnectSouth();
								((CableTile) tiles[x][y + 1]).setBridge(false);
							}
							if (coordsInBounds(x, y - 1) && tiles[x][y - 1] instanceof CableTile) {
								((CableTile) tiles[x][y - 1]).disconnectNorth();
								((CableTile) tiles[x][y - 1]).setBridge(false);
							}
							if (coordsInBounds(x + 1, y) && tiles[x + 1][y] instanceof Block) {
								if (((Block) tiles[x + 1][y]).cableTile != null) {
									((Block) tiles[x + 1][y]).cableTile.disconnectWest();
								}
							}
							if (coordsInBounds(x - 1, y) && tiles[x - 1][y] instanceof Block) {
								if (((Block) tiles[x - 1][y]).cableTile != null) {
									((Block) tiles[x - 1][y]).cableTile.disconnectEast();
								}
							}
							if (coordsInBounds(x, y + 1) && tiles[x][y + 1] instanceof Block) {
								if (((Block) tiles[x][y + 1]).cableTile != null) {
									((Block) tiles[x][y + 1]).cableTile.disconnectSouth();
								}
							}
							if (coordsInBounds(x, y - 1) && tiles[x][y - 1] instanceof Block) {
								if (((Block) tiles[x][y - 1]).cableTile != null) {
									((Block) tiles[x][y - 1]).cableTile.disconnectNorth();
								}
							}
						}
					}
					startDelete = null;
				}
			}
		}
	}

	private void checkLinePlacement(ShapeRenderer sr) {
		if (canPlace) {

			if (Gdx.input.isButtonPressed(Buttons.LEFT)) {

				Vector2 coords = getCursorGridCoords();
				if (coordsInBounds(coords)) {
					if (startPlace == null) {
						startPlace = coords;
						exactStartPlace = Main.getRelativeCursor();
					}

					if (!flippedDetermined) {
						Vector2 exactEnd = Main.getRelativeCursor();
						if (exactStartPlace.dst(exactEnd) > 5) {
							if (Math.abs(exactStartPlace.x - exactEnd.x) > Math.abs(exactStartPlace.y - exactEnd.y)) {
								flippedPlacement = true;
							} else {
								flippedPlacement = false;
							}
							flippedDetermined = true;
						}
					}

					sr.setColor(Color.RED);
					Vector2 end = getCursorGridCoords().scl(scale).add(scale / 2, scale / 2);
					Vector2 start = startPlace.cpy().scl(scale).add(scale / 2, scale / 2);
					if (flippedPlacement) {
						sr.line(start.x, start.y, end.x, start.y);
						sr.line(end.x, start.y, end.x, end.y);
					} else {
						sr.line(start.x, start.y, start.x, end.y);
						sr.line(start.x, end.y, end.x, end.y);
					}
				}
			} else {
				if (startPlace != null) {

					Vector2 end = getCursorGridCoords();
					if (coordsInBounds(end)) {
						CableTile startTile = null;
						if (tiles[(int) startPlace.x][(int) startPlace.y] instanceof CableTile) {
							startTile = (CableTile) tiles[(int) startPlace.x][(int) startPlace.y];
						}
						if (end.equals(startPlace) && startTile != null && startTile.connectedNorth
								&& startTile.connectedEast && startTile.connectedSouth && startTile.connectedWest) {
							startTile.toggleBridge();
						} else {
							if (tiles[(int) startPlace.x][(int) startPlace.y] == null) {
								tiles[(int) startPlace.x][(int) startPlace.y] = (CableTile) getPlacingTile();
							}
							int prevPlacedX = (int) startPlace.x;
							int prevPlacedY = (int) startPlace.y;
							if (flippedPlacement) {
								if (end.x > startPlace.x) {
									for (int x = 1; x < end.x - startPlace.x + 1; x++) {
										int px = x + (int) startPlace.x;
										int py = (int) startPlace.y;
										if (tiles[px][py] == null) {
											tiles[px][py] = getPlacingTile();
										}
										connectWire(prevPlacedX, prevPlacedY, px, py);
										prevPlacedX = px;
										prevPlacedY = py;
									}
								} else {
									for (int x = (int) (startPlace.x - end.x - 1); x >= 0; x--) {
										int px = x + (int) end.x;
										int py = (int) startPlace.y;
										if (tiles[px][py] == null) {
											tiles[px][py] = getPlacingTile();
										}
										connectWire(prevPlacedX, prevPlacedY, px, py);
										prevPlacedX = px;
										prevPlacedY = py;
									}
								}
								if (end.y > startPlace.y) {
									for (int y = 1; y < end.y - startPlace.y + 1; y++) {
										int px = (int) end.x;
										int py = y + (int) startPlace.y;
										if (tiles[px][py] == null) {
											tiles[px][py] = getPlacingTile();
										}
										connectWire(prevPlacedX, prevPlacedY, px, py);
										prevPlacedX = px;
										prevPlacedY = py;
									}
								} else {
									for (int y = (int) (startPlace.y - end.y - 1); y >= 0; y--) {
										int px = (int) end.x;
										int py = y + (int) end.y;
										if (tiles[px][py] == null) {
											tiles[px][py] = getPlacingTile();
										}
										connectWire(prevPlacedX, prevPlacedY, px, py);
										prevPlacedX = px;
										prevPlacedY = py;
									}
								}
							} else {
								if (end.y > startPlace.y) {
									for (int y = 1; y < end.y - startPlace.y + 1; y++) {
										int px = (int) startPlace.x;
										int py = y + (int) startPlace.y;
										if (tiles[px][py] == null) {
											tiles[px][py] = getPlacingTile();
										}
										connectWire(prevPlacedX, prevPlacedY, px, py);
										prevPlacedX = px;
										prevPlacedY = py;
									}
								} else {
									for (int y = (int) (startPlace.y - end.y - 1); y >= 0; y--) {
										int px = (int) startPlace.x;
										int py = y + (int) end.y;
										if (tiles[px][py] == null) {
											tiles[px][py] = getPlacingTile();
										}
										connectWire(prevPlacedX, prevPlacedY, px, py);
										prevPlacedX = px;
										prevPlacedY = py;
									}
								}
								if (end.x > startPlace.x) {
									for (int x = 1; x < end.x - startPlace.x + 1; x++) {
										int px = x + (int) startPlace.x;
										int py = (int) end.y;
										if (tiles[px][py] == null) {
											tiles[px][py] = getPlacingTile();
										}
										connectWire(prevPlacedX, prevPlacedY, px, py);
										prevPlacedX = px;
										prevPlacedY = py;
									}
								} else {
									for (int x = (int) (startPlace.x - end.x - 1); x >= 0; x--) {
										int px = x + (int) end.x;
										int py = (int) end.y;
										if (tiles[px][py] == null) {
											tiles[px][py] = getPlacingTile();
										}
										connectWire(prevPlacedX, prevPlacedY, px, py);
										prevPlacedX = px;
										prevPlacedY = py;
									}
								}
							}
						}
					}
					startPlace = null;
					flippedDetermined = false;
				}
			}
		}
	}

	private Tile getPlacingTile() {
		switch (placingTile) {
		case Tile.WIRE:
			return new WireTile(this);
		case Tile.BUS:
			return new BusTile(this);
		case Tile.AND:
			return new AndGate(this);
		case Tile.OR:
			return new OrGate(this);
		case Tile.SWITCH:
			return new Switch(this);
		}
		return null;
	}

	private void drawGrid(ShapeRenderer sr) {
		sr.set(ShapeType.Filled);
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				if (Main.cam.frustum.boundsInFrustum(x * scale, y * scale, 0, scale, scale, 0)) {
					sr.setColor(Color.DARK_GRAY);
					sr.rect(x * scale, y * scale, scale, 1);
					sr.rect(x * scale, y * scale, 1, scale);
					if (canPlace) {
						Vector2 rel = getCursorGridCoords();
						if (rel.x >= x && rel.x < x + 1 && rel.y >= y && rel.y < y + 1) {
							sr.setColor(new Color(0.3f, 0.3f, 0.3f, 1f));
							sr.rect(x * scale + 1, y * scale + 1, scale - 1, scale - 1);
						}
					}
				}
			}
		}
		sr.rect(tiles.length * scale, 0, 1, tiles[0].length * scale);
		sr.rect(0, tiles[0].length * scale, tiles.length * scale, 1);
	}

	private void connectWire(int t1x, int t1y, int t2x, int t2y) {
		Tile t1 = tiles[t1x][t1y];
		Tile t2 = tiles[t2x][t2y];

		CableTile c1 = null;
		CableTile c2 = null;
		if (t1 != null) {
			if (t1 instanceof CableTile) {
				c1 = (CableTile) t1;
			} else if (t1 instanceof Block) {
				c1 = ((Block) t1).cableTile;
			}
		}
		if (t2 != null) {
			if (t2 instanceof CableTile) {
				c2 = (CableTile) t2;
			} else if (t2 instanceof Block) {
				c2 = ((Block) t2).cableTile;
			}
		}
		if (t2x - t1x == 1) {
			if (c1 != null && c2 != null && c1.getClass() == c2.getClass()) {
				c1.connectEast();
				c2.connectWest();
			}
		}
		if (t2x - t1x == -1) {
			if (c1 != null && c2 != null && c1.getClass() == c2.getClass()) {
				c1.connectWest();
				c2.connectEast();
			}
		}
		if (t2y - t1y == 1) {
			if (c1 != null && c2 != null && c1.getClass() == c2.getClass()) {
				c1.connectNorth();
				c2.connectSouth();
			}
		}
		if (t2y - t1y == -1) {
			if (c1 != null && c2 != null && c1.getClass() == c2.getClass()) {
				c1.connectSouth();
				c2.connectNorth();
			}
		}
	}

	private Vector2 getCursorGridCoords() {
		Vector2 v = Main.getRelativeCursor().scl(1f / scale);
		v.x = (int) v.x;
		v.y = (int) v.y;
		return v;
	}

	public void compile() {

		compiler.compile(tiles);
	}

	public Tile getMouseTile() {
		return getTile(getCursorGridCoords());
	}

	private boolean coordsInBounds(Vector2 coords) {
		return coordsInBounds((int) coords.x, (int) coords.y);
	}

	private boolean coordsInBounds(int x, int y) {
		return x >= 0 && x < tiles.length && y >= 0 && y < tiles[0].length;
	}

	private Tile getTile(Vector2 coords) {
		if (coordsInBounds(coords)) {
			return tiles[(int) coords.x][(int) coords.y];
		}
		return null;
	}
}
