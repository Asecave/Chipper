package com.asecave.chipper.compiled;

import com.asecave.chipper.Block;
import com.asecave.chipper.Switch;

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
	
	public void update() {
		int power = 0;
		if (block instanceof Switch) {
			Switch s = (Switch) block;
			if (s.getState()) {
				power = 1;
			}
		}
		cable.induct(power);
	}

	public void updateRender() {
		cable.updateRender();
	}
}
