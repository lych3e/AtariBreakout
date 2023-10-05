package me.lychee.breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;

import me.lychee.breakout.GameState.GameObject;

public class Render extends JComponent {
	private static final long serialVersionUID = -2143189038369826800L;
	public final JFrame frame;
	public static final Dimension size = new Dimension(640, 400);
	
	public Render() {
		frame = new JFrame();
		frame.setSize(size);
		frame.setPreferredSize(size);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(Main.title);
		frame.setVisible(true);
		frame.createBufferStrategy(3);
		this.setBackground(Color.black);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(getBackground());
		g.fillRect(0, 0, (int) Render.size.getWidth(), (int) Render.size.getHeight());
		
		for (int i = 0; i < Main.state.obj.size(); i++) {
			GameObject o = Main.state.obj.get(i); o.render((Graphics2D) g);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
}
