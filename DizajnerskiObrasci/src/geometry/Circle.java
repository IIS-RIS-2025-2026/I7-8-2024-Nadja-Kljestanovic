package geometry;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;


public class Circle extends Shape  {
	private Point center = new Point();
	protected int radius;
	
	public Circle() {
		super();
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
		this(center, radius, selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	}
	
	//Stara metoda equals
	/*public boolean equals(Object obj) {
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
	}*/
	
	//Nova metoda equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Circle)) return false;
		Circle other = (Circle) obj;
		return radius == other.radius && Objects.equals(center, other.center);
	}

	public boolean contains(int x, int y) {
		return this.center.distance(x, y) <= radius;
	}

	public boolean contains(Point clickPoint) {
		if (clickPoint == null) return false;
		return contains(clickPoint.getX(), clickPoint.getY()); //delegacija
	}

	public double area() {
		return radius * getRadius() * Math.PI;
	}
	
	public double circumference() {
		return 2 * radius * Math.PI;
	}
	
	//Stara draw metoda
	/*public void draw(Graphics g) {
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
	}*/
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getFillColor());
		g.fillOval(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);

		g.setColor(getBorderColor());
		g.drawOval(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);

		if (isSelected()) {
			drawSelectionMarkers(g);
		}
	}

	private void drawSelectionMarkers(Graphics g) {
		g.setColor(Color.BLUE);
		int x = center.getX();
		int y = center.getY();

		drawMarker(g, x, y);       
		drawMarker(g, x - radius, y);
		drawMarker(g, x + radius, y);
		drawMarker(g, x, y - radius);
		drawMarker(g, x, y + radius);
	}

	private void drawMarker(Graphics g, int x, int y) {
		g.drawRect(x - 2, y - 2, 4, 4);
	}
	
	public void moveTo(int x, int y) {
		center.moveTo(x, y);		
	}

	public void moveBy(int x, int y) {
		center.moveBy(x, y);		
	}
	
	public int compareTo(Object obj) {
		if(obj instanceof Circle) {
			Circle temp = (Circle)obj;
			//return (int)(this.area() - shapeToCompare.area());
			return Double.compare(this.area(), temp.area());
		}
		return 0;
	}
	
	public Circle clone(Circle circle) {
		circle.getCenter().moveTo(this.getCenter().getX(), this.getCenter().getY());
		try {
			circle.setRadius(this.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		circle.setBorderColor(this.getBorderColor());
		circle.setFillColor(this.getFillColor());
		return circle;
	}
	
	//Stara toString metoda
	/*public String toString() {
		return "Center=" + center + ", radius=" + radius + ", borderColor= " 
				+ getBorderColor() + ", fillColor= " + getFillColor();
	}*/
	
	//Nova toString metoda
	@Override
	public String toString() {
		return String.format("Center=%s, radius=%d, borderColor=%s, fillColor=%s", 
				center, radius, getBorderColor(), getFillColor());
	}
	
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