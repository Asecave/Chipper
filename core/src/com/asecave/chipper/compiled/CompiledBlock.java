package com.asecave.chipper.compiled;

public abstract class CompiledBlock extends CompiledTile {

	protected CompiledCableGrid output;
	protected CompiledCableGrid[] inputs;
	private int inputIndex = 0;

	public CompiledBlock() {
		inputs = new CompiledCableGrid[3];
		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = new CompiledCableGrid();
		}
	}
	
	public void addInputGrid(CompiledCableGrid input) {
		inputs[inputIndex] = input;
		inputIndex++;
	}

	public void connectOutputToCableGrid(CompiledCableGrid output) {
		this.output = output;
	}

	protected CompiledCableGrid getOutputCableGrid() {
		return output;
	}
}
