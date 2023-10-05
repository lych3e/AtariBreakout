package me.lychee.breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

import me.lychee.breakout.GameObject.*;

public class GameState {
	public ArrayList<GameObject> obj = new ArrayList<GameObject>();
	
	public static final int TOP_SPACING = 50;
	public static final int LAYER_SPACING = 3;
	public static final int BLOCK_HEIGHT = 25;
	public static final int PADDLE_HEIGHT = 15;
	public static final int PADDLE_WIDTH = 120;
	public static final int BALL_RADIUS = 10;
	public static final int PADDLE_SPACING = 60;
	public static final int PADDLE_SPEED = 150;
	
	public static final int[] level = new int[] {
			7,
			8,
			12
	};
	
	public GameState() {
		Dimension size = Render.size;
		
		for (int l = 0; l < level.length; l++) {
			double brick_size = size.getWidth() / level[l];
			
			for (int i = 0; i < level[l]; i++) {
				obj.add(new Brick(new Vec2(i * (brick_size+1), TOP_SPACING + (LAYER_SPACING + BLOCK_HEIGHT) * l),
								  new Vec2(brick_size, BLOCK_HEIGHT),
								  Color.BLUE.darker()));
			}
		}
		
		obj.add(new Paddle(
					new Vec2((size.getWidth() - PADDLE_WIDTH) / 2, size.getHeight() - PADDLE_HEIGHT - PADDLE_SPACING),
					new Vec2(PADDLE_WIDTH, PADDLE_HEIGHT),
					Color.WHITE,
					PADDLE_SPEED
				));
		
		obj.add(new Ball(
				BALL_RADIUS, 
				Color.WHITE, 
				new Vec2(size.getWidth() / 2 - BALL_RADIUS, size.getHeight() / 2 - BALL_RADIUS),
				new Vec2(0, 30)
				));
	}
	
	public void end() {
		int maxScore = 0; for (int i = 0; i < level.length; i++) maxScore += level[i];
		
		int lostScore = 0; for (int i = 0; i < obj.size(); i++) 
								lostScore += ((obj.get(i) instanceof Brick) && 
											 !(obj.get(i) instanceof Paddle)) ? 1 : 0;
		
		int score = maxScore - lostScore;
		
		String percent = new DecimalFormat("#").format(100*score/(double)maxScore);
		
		String s = (lostScore == 0) ? ("the maximum score: " + maxScore+".") : ("a score of "+score+" out of "+maxScore+". That's "+percent+"%");
		
		System.out.println("Game Over! You got "+s);
		System.exit(0);
	}
	
	
	public static abstract class GameObject {
		protected Vec2 pos;
		protected Vec2 velocity;
		protected Color color;
		protected Vec2 bounds;
		
		public abstract void collide(GameObject other, boolean X, boolean Y);
		
		public abstract void render(Graphics2D g);
		
		public Vec2 bounds() {return bounds;}
		public void bounds(Vec2 bounds) {this.bounds = bounds;}
		
		public Vec2 pos() {return pos;}
		public void pos(Vec2 pos) {this.pos = pos;}
		
		public Vec2 v() {return velocity;}
		public void v(Vec2 v) {
			if (v.sqr() > Physics.SQR_SPEED_LIMIT) v.normalize().multiply(Physics.SPEED_LIMIT);
			this.velocity = v;
		}
	}
}
