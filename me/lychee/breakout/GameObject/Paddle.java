package me.lychee.breakout.GameObject;

import java.awt.Color;

import me.lychee.breakout.Main;
import me.lychee.breakout.Vec2;
import me.lychee.breakout.GameState.GameObject;

public class Paddle extends Brick {
	public int speed;
	public Paddle(Vec2 pos, Vec2 size, Color color, int speed) { 
		super(pos, size, color);
		this.speed = speed;
	}
	
	@Override
	public Vec2 v() {
		return new Vec2(Main.input.getNetMovement() * speed, 0);
	}
	
	@Override
	public void collide(GameObject other, boolean X, boolean Y) {
		double dx = other.pos().x - pos().x - bounds.x / 2;
		double x = dx / bounds().x;
		
		Vec2 v2 = other.v();
		Vec2 dv = v().clone().subtract(v2);
		
		v2.subtract(dv);
		
		double mag = dv.abs();
		
		other.v(new Vec2(v2.x + mag * Math.sin(x),
						 -v2.y - mag * Math.cos(x)));
		
	}

}
