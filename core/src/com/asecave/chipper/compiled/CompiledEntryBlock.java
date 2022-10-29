package com.asecave.chipper.compiled;

import com.asecave.chipper.Block;

public class CompiledEntryBlock {
	
	private CompiledCableGrid cable;
	private Block block;
	
	public CompiledEntryBlock(Block block) {
		this.block = block;
	}

	public void connectToCableGrid(CompiledCableGrid ccg) {
		cable = ccg;
	}

	public Block getBlock() {
		return block;
	}
}
