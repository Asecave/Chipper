package com.asecave.chipper.compiled;

import com.asecave.chipper.Config;

public class CompiledOrGate extends CompiledBlock {

	@Override
	public void induct(int power) {
		int newPower = 0;
		switch (inputIndex) {
		case 1:
			newPower = inputs[0].getPower();
			break;
		case 2:
			newPower = inputs[0].getPower() | inputs[1].getPower();
			break;
		case 3:
			newPower = inputs[0].getPower() | inputs[1].getPower() | inputs[2].getPower();
			break;
		}
		System.out.println(power + "  " + getOutputCableGrid().print());
		if (output.getPower() != newPower && valueChanges < Config.maxLoopStackSize) {
			valueChanges++;
			output.induct(newPower);
		}
	}
}
