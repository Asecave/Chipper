package com.asecave.chipper.compiled;

public abstract class CompiledBlock {
	
	private CompiledCableGrid ccg;

	public void connectOutputToCableGrid(CompiledCableGrid ccg) {
		this.ccg = ccg;
	}

	protected CompiledCableGrid getOutputCableGrid() {
		return ccg;
	}
	
	public abstract void induct(int power);
	
	public abstract void updateRender();
}
