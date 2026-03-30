package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;


public class Line extends Shape  {
	private Point startPoint = new Point();
	private Point endPoint = new Point();
	
	public Line() {
		super();
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	public Line(Point startPoint, Point endPoint, boolean selected,Color borderColor) {
		super(selected,borderColor);
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	//Stara metoda equals - treba izbegavati ungnježdene if else blokove
	/*public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line pomocna = (Line) obj;
			if (this.startPoint.equals(pomocna.startPoint) && this.endPoint.equals(pomocna.endPoint))
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
		if (!(obj instanceof Line)) return false;
		Line other = (Line) obj;
		return Objects.equals(startPoint, other.startPoint) && 
               Objects.equals(endPoint, other.endPoint);
	}

	public boolean contains(int x, int y) {
		return this.startPoint.distance(x, y) + this.endPoint.distance(x, y) - length() <= 2;
	}

	public boolean contains(Point clickPoint) {
		if (clickPoint == null) return false;
		return this.contains(clickPoint.getX(), clickPoint.getY());
	}

	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	public void draw(Graphics g) {
		g.setColor(getBorderColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(),
				endPoint.getX(), endPoint.getY());
		
		if(isSelected()==true) {
			g.setColor(Color.BLUE);
			g.drawRect(startPoint.getX()-2, startPoint.getY()-2, 4, 4);
			g.drawRect(endPoint.getX()-2, endPoint.getY()-2, 4, 4);		
		}
	}
	
	// Stara metoda toString
	/*public String toString() {
		// Teško za čitanje zbog stalnih navodnika i pluseva
		return startPoint + "-- >" + endPoint + ", borderColor= " + getBorderColor();
	}
	*/

	// Nova metoda toString
	@Override
	public String toString() {
		return String.format("%s --> %s, color=%s", startPoint, endPoint, getBorderColor());
	}
	
	@Override
	public void moveTo(int x, int y) {
		//loša logika jer se linija prenosi u jednu tačku
		//startPoint.moveTo(x, y);
		//endPoint.moveTo(x, y);
		int dx = x - startPoint.getX();
		int dy = y - startPoint.getY();
		startPoint.moveTo(x, y);
		endPoint.moveBy(dx, dy);
		
	}
	@Override
	public void moveBy(int x, int y) {
		startPoint.moveBy(x, y);
		endPoint.moveBy(x, y);
		
	}
	
	// Stara metoda comapreTo
	/*@Override
	public int compareTo(Object obj) {
		if (obj instanceof Line) {
			Line shapeToCompare = (Line) obj;
			// Problem: (int) kasting odseca decimale. 
			// Ako je razlika 0.5, vratiće 0 (pogrešno kaže da su iste dužine).
			return (int) (this.length() - shapeToCompare.length());
		}
		return 0;
	}
	*/

	//Nova metoda compareTo
	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Line) {
			Line temp = (Line) obj;
			return Double.compare(this.length(), temp.length());
		}
		return 0;
	}

	public Line clone(Line line) {
		line.getStartPoint().setX(this.getStartPoint().getX());
		line.getStartPoint().setY(this.getStartPoint().getY());
		line.getEndPoint().setX(this.getEndPoint().getX());
		line.getEndPoint().setY(this.getEndPoint().getY());
		line.setBorderColor(this.getBorderColor());
		return line;
	}
	
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getStartPoint() {
		return this.startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
}
