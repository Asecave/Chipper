package com.asecave.chipper.compiled;

import com.asecave.chipper.OrGate;

public class CompiledOrGate extends CompiledBlock {
	
	private CompiledCableGrid output;
	private CompiledCableGrid[] inputs;
	private OrGate orGate;
	
	public CompiledOrGate(OrGate orGate) {
		this.orGate = orGate;
		inputs = new CompiledCableGrid[3];
		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = new CompiledCableGrid();
		}
	}

	@Override
	public void induct(int power) {
		power = inputs[0].getPower() | inputs[1].getPower() | inputs[2].getPower();
		output.induct(power);
	}

	@Override
	public void updateRender() {
	}

	public void setOutputGrid(CompiledCableGrid output) {
		this.output = output;
	}
	
	public void addInputGrid(CompiledCableGrid input) {
		for (int i = 0; i < inputs.length; i++) {
			if (inputs[i] == null) {
				inputs[i] = input;
				return;
			}
		}
	}
}
