package me.lychee.breakout;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

public class Input {
	private boolean leftKey = false, rightKey = false;
	public boolean hasProcessed = false;
	
	public Input() {
		InputMap i = Main.render.getInputMap();
		
		
		i.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "LEFT");
		i.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "unLEFT");
		
		i.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "RIGHT");
		i.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "unRIGHT");
		
		
		ActionMap a = Main.render.getActionMap();
		
		a.put("LEFT", new AbstractAction() {

			private static final long serialVersionUID = 3740096802164695887L;

			@Override
			public void actionPerformed(ActionEvent arg) {
				leftKey = true;
			}});
		
		a.put("RIGHT", new AbstractAction() {

			private static final long serialVersionUID = 3740096802164695887L;

			@Override
			public void actionPerformed(ActionEvent arg) {
				rightKey = true;
			}});
		
		a.put("unLEFT", new AbstractAction() {

			private static final long serialVersionUID = 3740096802164695887L;

			@Override
			public void actionPerformed(ActionEvent arg) {
				leftKey = false;
			}});
		
		a.put("unRIGHT", new AbstractAction() {

			private static final long serialVersionUID = 3740096802164695887L;

			@Override
			public void actionPerformed(ActionEvent arg) {
				rightKey = false;
			}});
	}
	
	public void reset() { leftKey = false; rightKey = false; }
	
	
	public int getNetMovement() {
		return (leftKey ? -1 : 0) + (rightKey ? 1 : 0);
	}

}
