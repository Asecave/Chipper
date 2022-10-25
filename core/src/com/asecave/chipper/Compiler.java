package com.asecave.chipper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Compiler {

	private Tile root;

	public void compile(Tile[][] tiles) {
		
		// ############ JFrame Debug Code ##############
		
		int debugScale = 20;
		
		JFrame frame = new JFrame("Compiler");
		frame.setSize(tiles.length * debugScale + 40, tiles[0].length * debugScale + 40);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.add(panel);
		frame.setVisible(true);
		Graphics2D g2d = (Graphics2D) panel.getGraphics();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				if (tiles[x][y] == null) {
					g2d.setColor(Color.DARK_GRAY);
					g2d.fillRect(x * debugScale, y * debugScale, debugScale, debugScale);
				} else if (tiles[x][y] instanceof Switch) {
					g2d.setColor(Color.GREEN);
					g2d.fillRect(x * debugScale, y * debugScale, debugScale, debugScale);
				}
			}
		}
		
		// ################## End ######################
		
		
		
		// Finding entry
		t: for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				if (tiles[x][y] != null) {
					if (tiles[x][y] instanceof Switch) {
						root = tiles[x][y];
						break t;
					}
				}
			}
		}
		
		
	}

}
