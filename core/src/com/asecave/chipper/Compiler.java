package com.asecave.chipper;

import com.asecave.chipper.compiled.CompiledCableGrid;
import com.asecave.chipper.compiled.CompiledEntryBlock;
import com.badlogic.gdx.utils.Array;

public class Compiler {

	public Array<CompiledEntryBlock> compile(Tile[][] tiles) {

		// ############ JFrame Debug Code ##############

//		int debugScale = 20;
//		
//		JFrame frame = new JFrame("Compiler");
//		frame.setSize(tiles.length * debugScale + 16, tiles[0].length * debugScale + 39);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JPanel panel = new JPanel();
//		frame.add(panel);
//		frame.setVisible(true);
//		Graphics2D g2d = (Graphics2D) panel.getGraphics();
//		
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		for (int x = 0; x < tiles.length; x++) {
//			for (int y = 0; y < tiles[0].length; y++) {
//				if (tiles[x][y] == null) {
//					g2d.setColor(Color.DARK_GRAY);
//					g2d.fillRect(x * debugScale, y * debugScale, debugScale, debugScale);
//				} else if (tiles[x][y] instanceof Switch) {
//					g2d.setColor(Color.GREEN);
//					g2d.fillRect(x * debugScale, y * debugScale, debugScale, debugScale);
//				}
//			}
//		}

		// ################## End ######################

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

//		CompiledCableGrid ccg = new CompiledCableGrid();
//		CompiledBlock cb = new CompiledBlock();
//		ccg.connectToInput(cb);
//		CompiledCableGrid ccg2 = new CompiledCableGrid();
//		cb.connectOutputToCableGrid(ccg2);
//		
//		CompiledEntryBlock ceb = new CompiledEntryBlock();
//		ceb.connectToCableGrid(ccg);

		return compiledEntryBlocks;
	}

	private CompiledEntryBlock build(Block start) {
		CompiledCableGrid grid = new CompiledCableGrid();
		compileTile(start, grid);
		CompiledEntryBlock ceb = new CompiledEntryBlock(start);
		ceb.connectToCableGrid(grid);
		return ceb;
	}

	private void compileTile(Tile t, CompiledCableGrid currentGrid) {
		if (t instanceof Block) {
			Block block = (Block) t;
			if (block instanceof Switch) {
				compileTile(block.cableTile, currentGrid);
			}
		} else if (t instanceof CableTile) {
			CableTile cable = (CableTile) t;
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
