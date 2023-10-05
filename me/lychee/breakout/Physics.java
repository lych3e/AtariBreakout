package me.lychee.breakout;

import java.awt.Dimension;

import me.lychee.breakout.GameState.GameObject;
import me.lychee.breakout.GameObject.Ball;
import me.lychee.breakout.GameObject.Brick;
import me.lychee.breakout.GameObject.Paddle;

public class Physics {
	public static final int SPEED_LIMIT = 500;
	public static final int SQR_SPEED_LIMIT = SPEED_LIMIT * SPEED_LIMIT;
	
	public void loop(long dT_MS) {
		Dimension size = Render.size;
		
		int brickCount = 0;
		double dT = dT_MS / 1000D; // tick position
		for (int i = 0; i < Main.state.obj.size(); i++) {
			GameObject o = Main.state.obj.get(i);
			
			if ((o instanceof Brick) && !(o instanceof Paddle)) brickCount++;
			
			Vec2 pos = o.pos().add(o.v().clone().multiply(dT));
			Vec2 v = o.v();
			
			if (pos.x < 0) 
				{pos.x = 0; v.x = Math.abs(v.x);}
			else if (pos.x + o.bounds.x >= size.getWidth()) 
				{pos.x = size.getWidth() - o.bounds.x - 1; v.x = -Math.abs(v.x);}
			
			if (pos.y < 0) 
				{pos.y = 0; v.y = Math.abs(v.y);}
			else if (pos.y + o.bounds.y >= size.getHeight())  {
				if (o instanceof Ball) Main.state.end();
				pos.y = size.getHeight() - o.bounds.y - 1; v.y = -Math.abs(v.y);
			}
			
			o.pos(pos);
			o.v(v);
		}
		
		if (brickCount == 0) Main.state.end();
		
		// tick collisions
		for (int i = 0; i < Main.state.obj.size(); i++) {
			GameObject oi = Main.state.obj.get(i);
			for (int j = i + 1; j < Main.state.obj.size(); j++) {
				GameObject oj = Main.state.obj.get(j);
				boolean X = (oi.pos().x < oj.pos().x + oj.bounds().x) &&
							(oi.pos().x + oi.bounds().x > oj.pos().x);
				
				boolean Y = (oi.pos().y < oj.pos().y + oj.bounds().y) &&
							(oi.pos().y + oi.bounds().y > oj.pos().y);
				
				if (X & Y) {
					oi.collide(oj, X, Y);
					oj.collide(oi, X, Y);
				}
			}
	}
	}
}
