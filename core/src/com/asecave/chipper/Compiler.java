package com.asecave.chipper;

import com.asecave.chipper.compiled.CompiledCableGrid;
import com.asecave.chipper.compiled.CompiledEntryBlock;
import com.badlogic.gdx.utils.Array;

public class Compiler {

	public Array<CompiledEntryBlock> compile(Tile[][] tiles) {

		Array<CompiledEntryBlock> compiledEntryBlocks = new Array<CompiledEntryBlock>();

		Array<Block> startTiles = new Array<Block>();

		// Finding entry
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				if (tiles[x][y] != null) {
					if (tiles[x][y] instanceof Switch) {
						startTiles.add((Block) tiles[x][y]);
					}
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
		CompiledCableGrid grid = null;
		if (start.cableTile.getCompiledCableGrid() != null) {
			grid = start.cableTile.getCompiledCableGrid();
		} else {
			grid = new CompiledCableGrid();
		}
		compileTile(start.cableTile, grid);
		CompiledEntryBlock ceb = new CompiledEntryBlock(start);
		ceb.connectToCableGrid(grid);
		return ceb;
	}

	private void compileTile(CableTile cable, CompiledCableGrid currentGrid) {
		
		if (cable.hasParent()) {
			Block parent = cable.getParent();
			if (parent instanceof Switch) {
				currentGrid.addTile(cable);
				cable.setCompiled();
				for (CableTile adj : cable.getConnectedCables()) {
					if (!adj.isCompiled()) {
						compileTile(adj, currentGrid);
					}
				}
			}
		} else {
			currentGrid.addTile(cable);
			cable.setCompiled();
			for (CableTile adj : cable.getConnectedCables()) {
				if (!adj.isCompiled()) {
					compileTile(adj, currentGrid);
				}
			}
		}
	}
}
