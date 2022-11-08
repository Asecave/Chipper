package com.asecave.chipper.compiled;

import com.asecave.chipper.blocks.Block;
import com.asecave.chipper.blocks.Clock;

public class CompiledClock extends CompiledEntryBlock {
	
	private Clock block;

	public CompiledClock(Clock block) {
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
