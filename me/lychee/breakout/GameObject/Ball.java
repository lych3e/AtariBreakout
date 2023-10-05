package me.lychee.breakout.GameObject;

import java.awt.Color;
import java.awt.Graphics2D;

import me.lychee.breakout.GameState.GameObject;
import me.lychee.breakout.Vec2;

public class Ball extends GameObject {
	protected int diameter;
	
	public Ball(int radius, Color color, Vec2 pos, Vec2 velocity) {
		this.diameter = radius * 2;
		this.color = color;
		this.pos = pos;
		this.velocity = velocity;
		this.bounds = new Vec2(diameter, diameter);
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int) pos.x, (int) pos.y, (int) diameter, (int) diameter);
	}

	@Override
	public void collide(GameObject other, boolean X, boolean Y) {
		if (other instanceof Paddle) return;
		
		if (X) velocity.x = -velocity.x;
		if (Y) velocity.y = -velocity.y;
	}

}
