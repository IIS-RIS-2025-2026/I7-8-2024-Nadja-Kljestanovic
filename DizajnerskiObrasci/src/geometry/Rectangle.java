package geometry;

import java.awt.Color;
import java.awt.Graphics;


public class Rectangle extends Shape  {

	private Point upperLeftPoint = new Point();
	private int width;
	private int height;
	/*public Color color1;
	public Color color2;*/

	
		
	
	public Rectangle() {
	}
	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {
		this(upperLeftPoint, width, height);
		setSelected(selected);
	}
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected,Color borderColor,Color fillColor) {
		this(upperLeftPoint, width, height);
		setSelected(selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	}
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle pomocna = (Rectangle) obj;
			if (this.upperLeftPoint.equals(pomocna.upperLeftPoint) && this.width == pomocna.width
					&& this.height == pomocna.height)
				return true;
			else
				return false;
		} else
			return false;
	}

	public boolean contains(int x, int y) {
		return x >= upperLeftPoint.getX() && x <= upperLeftPoint.getX() + width && y >= upperLeftPoint.getY()
				&& y <= upperLeftPoint.getY() + height;
	}

	public boolean contains(Point clickPoint) {
		return clickPoint.getX() >= upperLeftPoint.getX() && clickPoint.getX() <= upperLeftPoint.getX() + width
				&& clickPoint.getY() >= upperLeftPoint.getY() && clickPoint.getY() <= upperLeftPoint.getY() + height;
	}

	public int area() {
		return width * height;
	}
	public int circumference() {
		return 2 * (width + height);
	}
	
	public void draw(Graphics g) {
		
		g.setColor(getBorderColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		
		g.setColor(getFillColor());
		
		g.fillRect(upperLeftPoint.getX() + 1, upperLeftPoint.getY() + 1, width -1 , height -1  );
		
		if(isSelected()) {
			g.setColor(Color.blue);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width  - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			
		
		}
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public String toString() {
		return "Upper left point:" + upperLeftPoint + ", width=" + width + ",height= " + height + ", borderColor= " + getBorderColor() 
		+ ", "+ getFillColor();
		
			
	}
	

	
	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);
		
		
	}
	
	public void moveBy(int x, int y) {
		upperLeftPoint.moveBy(x, y);
		
	}
	
	public int compareTo(Object obj) {

		if (obj instanceof Rectangle) {
			Rectangle shapeToCompare = (Rectangle)obj;
			return this.area() - shapeToCompare.area();
		}
		return 0;
	}
	
	public Rectangle clone(Rectangle rectangle) {
		
		rectangle.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
		rectangle.setWidth(this.getWidth());
		rectangle.setHeight(this.getHeight());
		rectangle.setBorderColor(this.getBorderColor());
		rectangle.setFillColor(this.getFillColor());
		
		return rectangle;
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
	
}
