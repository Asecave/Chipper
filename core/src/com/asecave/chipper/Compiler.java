package com.asecave.chipper;

import com.asecave.chipper.compiled.CompiledBlock;
import com.asecave.chipper.compiled.CompiledCableGrid;
import com.asecave.chipper.compiled.CompiledEntryBlock;
import com.asecave.chipper.compiled.CompiledLamp;
import com.badlogic.gdx.utils.Array;

public class Compiler {

	private String errorMessage = null;

	public Array<CompiledEntryBlock> compile(Tile[][] tiles) {

		errorMessage = null;

		Array<CompiledEntryBlock> compiledEntryBlocks = new Array<CompiledEntryBlock>();

		Array<Block> startTiles = new Array<Block>();

		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				if (tiles[x][y] != null) {

					// Finding entries
					if (tiles[x][y] instanceof Switch) {
						startTiles.add((Block) tiles[x][y]);
					}

					// Resetting compilation status
					if (tiles[x][y] instanceof Block) {
						if (((Block) tiles[x][y]).cableTile != null) {
							((Block) tiles[x][y]).cableTile.resetCompiled();
						}
					}
					tiles[x][y].resetCompiled();
				}
			}
		}

		compiledEntryBlocks.clear();
		for (Block t : startTiles) {
			compiledEntryBlocks.add(build(t));
		}

		return compiledEntryBlocks;
	}

	private CompiledEntryBlock build(Block start) {
		CompiledCableGrid grid = new CompiledCableGrid();
		grid.addTile(start.cableTile);
		start.cableTile.setCompiled();
		for (CableTile ct : start.cableTile.getConnectedCables()) {
			compileTile(ct, grid);
		}
		CompiledEntryBlock ceb = new CompiledEntryBlock(start);
		ceb.connectToCableGrid(grid);
		return ceb;
	}

	private boolean compileTile(CableTile cable, CompiledCableGrid currentGrid) {

		if (cable.hasParent() && !cable.getParent().isCompiled()) {
			Block parent = cable.getParent();
			
			if (parent instanceof Switch) {
				errorMessage = "Cable can't have multiple inputs.";
				return false;
			} else if (parent instanceof Lamp) {
				currentGrid.connectToInput(new CompiledLamp((Lamp) parent));
				parent.setCompiled();
				return compileTile(cable, currentGrid);
			} else if (parent instanceof OrGate) {
				CompiledCableGrid newGrid = new CompiledCableGrid();
				
				parent.setCompiled();
			}
		} else {
			currentGrid.addTile(cable);
			cable.setCompiled();
			for (CableTile adj : cable.getConnectedCables()) {
				if (!adj.isCompiled()) {
					boolean success = compileTile(adj, currentGrid);
					if (!success) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
