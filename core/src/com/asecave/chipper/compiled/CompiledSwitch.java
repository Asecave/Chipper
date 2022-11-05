package com.asecave.chipper.compiled;

import com.asecave.chipper.blocks.Block;
import com.asecave.chipper.blocks.Switch;

public class CompiledSwitch extends CompiledEntryBlock {

	private Switch block;
	
	public CompiledSwitch(Switch block) {
		this.block = block;
	}

	@Override
	public void update() {
		if (block.getState()) {
			output.induct(1);
		} else {
			output.induct(0);
		}
	}

	@Override
	public Block getEntryBlock() {
		return block;
	}
}
