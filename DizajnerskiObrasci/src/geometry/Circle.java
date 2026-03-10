package geometry;


import java.awt.Color;
import java.awt.Graphics;


public class Circle extends Shape  {

	private Point center = new Point();
	
	protected int radius;
	
	Color color1;
	Color color2;
	

	
	public Circle() {
	}
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}
	public Circle(Point center, int radius, boolean selected,Color borderColor,Color fillColor) {
		this(center, radius,selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	}
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle pomocni = (Circle) obj;
			if (this.center.equals(pomocni.center) && this.radius == pomocni.radius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		return this.center.distance(x, y) <= radius;
	}

	public boolean contains(Point clickPoint) {
		return this.center.distance(clickPoint.getX(), clickPoint.getY()) <= radius;
	}

	public double area() {
		return radius * getRadius() * Math.PI;
	}
	public double circumference() {
		return 2 * radius * Math.PI;
	}
	
	public void draw(Graphics g) {
		g.setColor(getBorderColor());
		g.drawOval(center.getX()-radius, center.getY()-radius,
				2*radius, 2*radius);
		g.setColor(getFillColor());
		g.fillOval(center.getX()-radius + 1, center.getY()-radius + 1,
				2*radius -2, 2*radius -2);
	
	
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getX() - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - radius - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() + radius - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() - radius - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() + radius - 2, 4, 4);
		
		}
	}
	
	
	public void moveTo(int x, int y) {
		center.moveTo(x, y);		
	}

	
	public void moveBy(int x, int y) {
		center.moveBy(x, y);		
	}

	
	public int compareTo(Object obj) {
		if(obj instanceof Circle) {
			Circle shapeToCompare = (Circle)obj;
			return (int)(this.area() - shapeToCompare.area());
		}
		return 0;
	}
	
	public Circle clone(Circle circle) {
		
		circle.getCenter().moveTo(this.getCenter().getX(), this.getCenter().getY());
		
		try {
			circle.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		circle.setBorderColor(this.getBorderColor());
		circle.setFillColor(this.getFillColor());
		
		return circle;
		
	}
	
	
	
	public String toString() {
		// Center=(x,y), radius= radius
		return "Center=" + center + ", radius=" + radius + ", borderColor= " 
				+ getBorderColor() + ", fillColor= " + getFillColor();
	}
	
	
	/*public Color getColor2() {
		return color2;
	}
	public void setColor2(Color color2) {
		this.color2 = color2;
	}
	public Color getColor1() {
		return color1;
	}
	public void setColor1(Color color1) {
		this.color1 = color1;
	}*/
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) throws Exception {
		
			if (radius<=0) {
	        	throw new Exception("Poluprecnik mora biti > 0");
	        	
	        	
	        } 
			
		
			this.radius = radius;
	}
	
	

	

	
	
}