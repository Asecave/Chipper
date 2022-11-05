package com.asecave.chipper.compiled;

public class CompiledNotGate extends CompiledBlock {

	@Override
	public void induct(int power) {
		int newPower = 0;
		switch (inputIndex) {
		case 1:
			newPower = Integer.MAX_VALUE - inputs[0].getPower();
			break;
		case 2:
			newPower = Integer.MAX_VALUE - (inputs[0].getPower() | inputs[1].getPower());
			break;
		case 3:
			newPower = Integer.MAX_VALUE - (inputs[0].getPower() | inputs[1].getPower() | inputs[2].getPower());
			break;
		}
		if (output.getPower() != newPower) {
			output.induct(newPower);
		}
	}
}

