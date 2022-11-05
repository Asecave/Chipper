package com.asecave.chipper.compiled;

import com.asecave.chipper.OrGate;

public class CompiledOrGate extends CompiledBlock {
	
	private OrGate orGate;
	
	public CompiledOrGate(OrGate orGate) {
		this.orGate = orGate;
	}

	@Override
	public void induct(int power) {
		int newPower = inputs[0].getPower() | inputs[1].getPower() | inputs[2].getPower();
		if (output.getPower() != newPower) {
			output.induct(power);
		}
	}

	@Override
	public void updateRender() {
		output.updateRender();
	}
}
