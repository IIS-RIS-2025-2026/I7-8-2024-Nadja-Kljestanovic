package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;


public class Donut extends Shape  {
    private Point center = new Point();
	private int innerRadius;
	private int radius;
	Color color1;
	Color color2;
	

	
	
	public Donut() {

	}
	public Donut(Point center, int radius, int innerRadius) {
		/*
		 * setCenter(center); // ovo je private pa mora ovako this.radius = radius; //
		 * ovo je protected pa moze ovako setSelected(selected); this.innerRadius =
		 * innerRadius;
		 */

		// drugi nacin
		this.center= center;
		this.radius= radius;
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		/*
		 * setCenter(center); // ovo je private pa mora ovako this.radius = radius; //
		 * ovo je protected pa moze ovako setSelected(selected); this.innerRadius =
		 * innerRadius;
		 */

		// drugi nacin
		this(center,radius,innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected,Color borderColor,Color fillColor) {
		
		this(center,radius,innerRadius,selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
		
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
			if (super.equals(pomocni) && innerRadius == pomocni.innerRadius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains(int x, int y) {
		return this.center.distance(x, y) <= radius
				&& this.getCenter().distance(x,y) >= innerRadius;
	}

	public boolean contains(Point clickPoint) {
		return this.center.distance(clickPoint.getX(), clickPoint.getY()) <= radius
				&& this.getCenter().distance(clickPoint.getX(), clickPoint.getY()) >= innerRadius;
	}

	public double area() {
		return (radius * getRadius() * Math.PI) - innerRadius * innerRadius * Math.PI;
	}
	
	public void draw(Graphics g) {
		/*g.setColor(color1);
		g.drawOval(center.getX()-radius, center.getY()-radius,
				2*radius, 2*radius);
		
		
		
		
		g.setColor(color2);
		g.fillOval(center.getX()-radius + 1, center.getY()-radius + 1,
				2*radius - 2, 2*radius - 2);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX()-innerRadius + 1, getCenter().getY()-innerRadius + 1,
				2*innerRadius - 2, 2*innerRadius - 2);
		g.setColor(color1);
		g.drawOval(getCenter().getX()-innerRadius, getCenter().getY()-innerRadius,
				2*innerRadius, 2*innerRadius);
				*/
				Ellipse2D ellipseInner = new Ellipse2D.Float(getCenter().getX()-innerRadius + 1, getCenter().getY()-innerRadius + 1,
				2*innerRadius - 2, 2*innerRadius - 2);
		Ellipse2D ellipseOuter = new Ellipse2D.Float(center.getX()-radius + 1, center.getY()-radius + 1,
				2*radius - 2, 2*radius - 2);
		Area out= new Area(ellipseOuter);
		Area in = new Area(ellipseInner);
		out.subtract(in);
		g.setColor(getFillColor());
		((Graphics2D) g).fill(out);
		
		
		
		if (isSelected()) {
			
			g.setColor(Color.BLUE);
			g.drawRect(center.getX() - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - radius - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() + radius - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() - radius - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() + radius - 2, 4, 4);
			g.setColor(Color.black);
			
			
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() - innerRadius - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() + innerRadius - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() - innerRadius - 2, 4, 4);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() + innerRadius - 2, 4, 4);
			g.setColor(Color.black);
		}
		
		
	}
	
	
	public int compareTo(Object obj) {
		if(obj instanceof Donut) {
			Donut shapeToCompare = (Donut)obj;
			return (int)(this.area() - shapeToCompare.area());
		}
		return 0;
	}
	
	public Donut clone(Donut donut) {
		donut.getCenter().setX(this.getCenter().getX());
		donut.getCenter().setY(this.getCenter().getY());
		donut.setRadius(this.getRadius());
		donut.setInnerRadius(this.getInnerRadius());
		donut.setBorderColor(this.getBorderColor());
		donut.setFillColor(this.getFillColor());
		
		return donut;
	}
	
	

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public String toString() {
		// Center=(x,y), radius= radius, innerRadius= innerRadius
		return "Center=" + center + ", radius=" + radius + ", innerRadius=" + innerRadius 
				+ ", borderColor= " + getBorderColor() + ", fillColor= " + getFillColor();
	}
	
	public void moveTo(int x, int y) {
		center.moveBy(x, y);
		
	}
	
	public void moveBy(int x, int y) {
		center.moveBy(x, y);
		
	}
	
	/*public Color getColor1() {
		return color1;
	}


	public void setColor1(Color color1) {
		this.color1 = color1;
	}


	public Color getColor2() {
		return color2;
	}


	public void setColor2(Color color2) {
		this.color2 = color2;
	}*/
	
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
	public int getRadius() {
		return radius;
	}
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center= center;
	}
}
