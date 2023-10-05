package me.lychee.breakout.GameObject;

import java.awt.Color;
import java.awt.Graphics2D;

import me.lychee.breakout.Main;
import me.lychee.breakout.Vec2;
import me.lychee.breakout.GameState.GameObject;

public class Brick extends GameObject {
	
	public Brick(Vec2 pos, Vec2 size, Color color) {
		this.velocity = new Vec2(0, 0);
		this.pos = pos;
		this.color = color;
		this.bounds = size;
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int) pos.x, (int) pos.y, (int) bounds.x, (int) bounds.y);
		g.setColor(new Color(~color.getRGB()));
		g.drawRect((int) pos.x, (int) pos.y, (int) bounds.x, (int) bounds.y);
	}

	@Override
	public void collide(GameObject other, boolean X, boolean Y) {
		if (other instanceof Ball) Main.state.obj.remove(this);
	}
	
}
