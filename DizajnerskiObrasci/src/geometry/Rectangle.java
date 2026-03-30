package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;


public class Rectangle extends Shape  {

	private Point upperLeftPoint = new Point();
	private int width;
	private int height;

	public Rectangle() {
		super();
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
		this(upperLeftPoint, width, height, selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	}
	
	//Stara metoda equals
	/*public boolean equals(Object obj) {
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
	*/

	//Nova metoda equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Rectangle)) return false;
		Rectangle other = (Rectangle) obj;
		return width == other.width && 
               height == other.height && 
               Objects.equals(upperLeftPoint, other.upperLeftPoint);
	}

	public boolean contains(int x, int y) {
		return x >= upperLeftPoint.getX() && 
				x <= upperLeftPoint.getX() + width && 
				y >= upperLeftPoint.getY() && 
				y <= upperLeftPoint.getY() + height;
	}

	public boolean contains(Point clickPoint) {
		if (clickPoint == null) return false;
		return contains(clickPoint.getX(), clickPoint.getY());
	}

	public int area() {
		return width * height;
	}
	public int circumference() {
		return 2 * (width + height);
	}
	
	//Stara metoda draw
	/*public void draw(Graphics g) {
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
	}*/
	
	//Nova metoda draw - razdvojena
	@Override
	public void draw(Graphics g) {
		g.setColor(getFillColor());
		g.fillRect(upperLeftPoint.getX() + 1, upperLeftPoint.getY() + 1, width - 1, height - 1);
		g.setColor(getBorderColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		if (isSelected()) {
			drawSelectionMarkers(g);
		}
	}

	private void drawSelectionMarkers(Graphics g) {
		g.setColor(Color.BLUE);
		int x = upperLeftPoint.getX();
		int y = upperLeftPoint.getY();

		drawMarker(g, x, y);         
		drawMarker(g, x + width, y);
		drawMarker(g, x, y + height);
		drawMarker(g, x + width, y + height);
	}

	private void drawMarker(Graphics g, int x, int y) {
		g.drawRect(x - 2, y - 2, 4, 4);
	}
	
	//Stara toString metoda
	/*public String toString() {
		return "Upper left point:" + upperLeftPoint + ", width=" + width + ",height= " + height + ", borderColor= " + getBorderColor() 
		+ ", "+ getFillColor();	
	}*/
	
	//Nova toString metoda
	@Override
	public String toString() {
		return String.format("Upper left point=%s, width=%d, height=%d, border color=%s, fill color=%s", 
				upperLeftPoint, width, height, getBorderColor(), getFillColor());
	}
	
	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);
	}
	
	public void moveBy(int x, int y) {
		upperLeftPoint.moveBy(x, y);
	}
	
	public int compareTo(Object obj) {

		if (obj instanceof Rectangle) {
			Rectangle temp = (Rectangle)obj;
			//return this.area() - temp.area();
			return Integer.compare(this.area(), temp.area()); //preciznije
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
}
