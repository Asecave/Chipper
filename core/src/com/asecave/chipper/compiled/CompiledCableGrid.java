package com.asecave.chipper.compiled;

import java.util.LinkedList;
import java.util.List;

import com.asecave.chipper.CableTile;

public class CompiledCableGrid {
	
	private List<CompiledBlock> outputs = new LinkedList<>();
	private List<CableTile> tiles = new LinkedList<>();
	
	private int power;

	public void connectToInput(CompiledBlock cb) {
		outputs.add(cb);
	}

	public void induct(int power) {
		this.power = power;
		for (CompiledBlock cb : outputs) {
			cb.induct(power);
		}
	}
	
	public void updateRender() {
		for (CompiledBlock cb : outputs) {
			cb.updateRender();
		}
		for (CableTile ct : tiles) {
			ct.setPower(power);
		}
	}

	public void addTile(CableTile cableTile) {
		tiles.add(cableTile);
	}
	
	public int getPower() {
		return power;
	}
}
