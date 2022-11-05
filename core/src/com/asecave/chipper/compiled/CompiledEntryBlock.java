package com.asecave.chipper.compiled;

import com.asecave.chipper.Block;

public abstract class CompiledEntryBlock extends CompiledBlock {
	
	public abstract void update();
	
	@Override
	public void induct(int power) {
	}
	
	@Override
	public void updateRender() {
		output.updateRender();
	}
	
	public abstract Block getEntryBlock();
}
