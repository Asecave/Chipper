package com.asecave.chipper.compiled;

import java.util.LinkedList;
import java.util.List;

public class CompiledCableGrid {
	
	private List<CompiledBlock> outputs = new LinkedList<>();

	public void connectToInput(CompiledBlock cb) {
		outputs.add(cb);
	}

}
