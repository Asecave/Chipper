package com.asecave.chipper.compiled;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.asecave.chipper.cables.CableTile;

public class CompiledCableGrid extends CompiledTile {

	private CompiledBlock input = null;
	private List<CompiledBlock> outputs = new LinkedList<>();
	private List<CableTile> tiles = new LinkedList<>();

	private long lastUpdateTime = 0;

	private int power;

	public void addToOutputs(CompiledBlock cb) {
		outputs.add(cb);
	}

	@Override
	public void induct(int power) {
		this.power = power;
		for (CompiledBlock cb : outputs) {
			cb.induct(power);
		}
	}

	@Override
	public void updateRender() {
		if (lastUpdateTime < System.currentTimeMillis() - 10) {
			lastUpdateTime = System.currentTimeMillis();
			for (CompiledBlock cb : outputs) {
				cb.updateRender();
			}
			for (CableTile ct : tiles) {
				ct.setPower(power);
			}
		}
	}

	public void addTile(CableTile cableTile) {
		tiles.add(cableTile);
	}

	public int getPower() {
		return power;
	}

	public void setInput(CompiledBlock block) {
		input = block;
	}

	public boolean hasInput() {
		return input != null;
	}
	
	public void resetValueChanges() {
		for(CompiledBlock b : outputs) {
			b.resetValueChanges();
		}
	}
	
	public String print() {
		return Arrays.toString(outputs.toArray());
	}
}
