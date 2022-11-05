package com.asecave.chipper.compiled;

import com.asecave.chipper.blocks.Lamp;

public class CompiledLamp extends CompiledBlock {

	private Lamp lamp;
	
	public CompiledLamp(Lamp lamp) {
		this.lamp = lamp;
	}

	@Override
	public void induct(int power) {
		lamp.setState(power > 0);
	}

	@Override
	public void updateRender() {
		
	}
}
