package me.lychee.breakout;

import java.awt.Dimension;

public class Vec2 implements Cloneable {
	public Vec2(double x, double y) {this.x = x; this.y = y;}
	public Vec2(Dimension d) {this.x = d.getWidth(); this.y = d.getHeight();}
	
	public double x = 0, y = 0;
	
	public Vec2 add(Vec2 other) {
		x += other.x;
		y += other.y;
		
		return this;
	}
	
	public Vec2 subtract(Vec2 other) {
		x -= other.x;
		y -= other.y;
		
		return this;
	}
	
	public Vec2 multiply(double d) {
		x *= d;
		y *= d;
		
		return this;
	}
	
	public double sqr() {
		return x*x + y*y;
	}
	
	public double abs() {
		return Math.sqrt(sqr());
	}
	
	public Vec2 normalize() {
		double h = abs();
		x /= h;
		y /= h;
		
		return this;
	}
	
	@Override public Vec2 clone() { return new Vec2(x, y); }
	@Override public String toString() {return "{ x: "+x+"; y: "+y+" }";}
}
