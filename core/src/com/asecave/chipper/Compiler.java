package com.asecave.chipper;

import com.asecave.chipper.compiled.CompiledAndGate;
import com.asecave.chipper.compiled.CompiledBlock;
import com.asecave.chipper.compiled.CompiledCableGrid;
import com.asecave.chipper.compiled.CompiledEntryBlock;
import com.asecave.chipper.compiled.CompiledLamp;
import com.asecave.chipper.compiled.CompiledOrGate;
import com.asecave.chipper.compiled.CompiledSwitch;
import com.badlogic.gdx.utils.Array;

public class Compiler {

	private String errorMessage = null;

	Array<CompiledEntryBlock> compiledEntryBlocks = new Array<CompiledEntryBlock>();

	public Array<CompiledEntryBlock> compile(Tile[][] tiles) {

		errorMessage = null;

		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				if (tiles[x][y] != null) {

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

		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				if (tiles[x][y] != null) {
					if (!tiles[x][y].isCompiled()) {
						if (tiles[x][y] instanceof Block) {
							Block block = (Block) tiles[x][y];
							if (block.cableTile != null) {
								compileTile(block.cableTile, new CompiledCableGrid());
							}
						} else if (tiles[x][y] instanceof CableTile) {
							compileTile((CableTile) tiles[x][y], new CompiledCableGrid());
						} else {
							errorMessage = "Unknown Tile: " + tiles[x][y].toString();
							return null;
						}
					}
				}
			}
		}

		return compiledEntryBlocks;
	}

	private boolean compileTile(CableTile cable, CompiledCableGrid currentGrid) {

		if (cable.hasParent() && !cable.getParent().isCompiled()) {
			Block parent = cable.getParent();

			if (parent instanceof Switch) {

				if (currentGrid.hasInput()) {
					errorMessage = "Cable can't have multiple inputs.";
					return false;
				} else {
					CompiledSwitch swit = new CompiledSwitch((Switch) parent);
					swit.connectOutputToCableGrid(currentGrid);
					currentGrid.setInput(swit);
					parent.setCompiled(swit);
					compiledEntryBlocks.add(swit);
					return compileTile(cable, currentGrid);
				}

			} else if (parent instanceof Lamp) {

				CompiledLamp lamp = new CompiledLamp((Lamp) parent);
				currentGrid.addToOutputs(lamp);
				parent.setCompiled(lamp);
				return compileTile(cable, currentGrid);

			} else if (parent instanceof OrGate) {

				CompiledOrGate orGate = new CompiledOrGate();
				return compileLogicGate(cable, currentGrid, orGate);
				
			} else if (parent instanceof AndGate) {
				CompiledAndGate andGate = new CompiledAndGate();
				return compileLogicGate(cable, currentGrid, andGate);
			}
		} else {
			currentGrid.addTile(cable);
			cable.setCompiled(currentGrid);
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
	
	private boolean compileLogicGateOutput(CableTile outputCable, CompiledBlock gate) {
		
		if (outputCable != null) {
			if (!outputCable.isCompiled()) {
				CompiledCableGrid newGrid = new CompiledCableGrid();
				boolean success = compileTile(outputCable, newGrid);
				if (!success) {
					return false;
				}
			}
			CompiledCableGrid outGrid = (CompiledCableGrid) outputCable.getCompiledVariant();
			if (outGrid.hasInput()) {
				errorMessage = "Cable can't have multiple inputs.";
				return false;
			}
			outGrid.setInput(gate);
			gate.connectOutputToCableGrid(outGrid);
		}
		return true;
	}
	
	private boolean compileLogicGateInput(CableTile inputCable, CompiledBlock gate) {
		if (inputCable != null) {
			if (!inputCable.isCompiled()) {
				CompiledCableGrid newGrid = new CompiledCableGrid();
				boolean success = compileTile(inputCable, newGrid);
				if (!success) {
					return false;
				}
			}
			CompiledCableGrid inGrid = (CompiledCableGrid) inputCable.getCompiledVariant();
			inGrid.addToOutputs(gate);
			gate.addInputGrid(inGrid);
		}
		return true;
	}
	
	private boolean compileLogicGate(CableTile cable, CompiledCableGrid currentGrid, CompiledBlock gate) {
		cable.getParent().setCompiled(gate);
		cable.setCompiled(currentGrid);
		
		CableTile north = cable.getConnectedCableNorth();
		CableTile south = cable.getConnectedCableSouth();
		CableTile east = cable.getConnectedCableEast();
		CableTile west = cable.getConnectedCableWest();

		boolean success = compileLogicGateOutput(north, gate);
		success = success && compileLogicGateInput(south, gate);
		success = success && compileLogicGateInput(east, gate);
		success = success && compileLogicGateInput(west, gate);
		
		return success;
	}
}
