package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {

	private int x;
	private int y;

	//Stari konstruktori
	/*public Point() { }
	
	public Point(int x, int y) {
		this.x = x;
		this.setY(y);
	}
	
	public Point(int x, int y, boolean selected) {
		this(x, y);
		setSelected(selected);
	}
	
	public Point(int x, int y, boolean selected, Color color) {
		this(x, y);
		setSelected(selected);
		setBorderColor(color);
	}
	*/

	//Novi konstruktori
	public Point() {
		super(); //inicijalizacija shape objekta
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y; //ujednačen pristup
	}

	public Point(int x, int y, boolean selected) {
		this(x, y); //korišćenje prethodnog konstruktora
		setSelected(selected);
	}

	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected); //korišćenje prethodnog konstruktora
		setBorderColor(color);
	}

	// Stara metoda equals
	/*public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point pomocna = (Point) obj;
			if (this.x == pomocna.x && this.y == pomocna.y) {
				return true;
			}
		}
		return false;
	}
	*/

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true; //da li prosleđeni objekat i this objekat isti objekat u memoriji
		if (!(obj instanceof Point)) return false; //dodajemo if i negaciju kako bismo izbacili else deo
		Point temp = (Point) obj;
		return x == temp.x && y == temp.y;
	}

	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}

	// Stara metoda contains
	/*public boolean contains(int x, int y) {
		return this.distance(x, y) <= 2;
	}

	public boolean contains(Point clickPoint) {
		return this.distance(clickPoint.x, clickPoint.y) <= 2;
	}
	*/

	// Nova metoda contains
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 2;
	}

	public boolean contains(Point clickPoint) {
		return contains(clickPoint.getX(), clickPoint.getY()); //poziva se prethodna metoda, nema ponavljanja logike
	}
	
	// Poboljšana logika
	@Override
	public void draw(Graphics g) {
		g.setColor(getBorderColor());	
		g.drawLine(x - 2, y, x + 2, y); //vraćeno na +-2
		g.drawLine(x, y - 2, x, y + 2);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(x - 2, y - 2, 4, 4);
		}
	}

	// Stara move metoda
	/*public void moveTo(int x, int y) {
		setX(x);
		this.y=y;
	}
	public void moveBy(int x, int y) {
		setX(this.x + x);
		this.y+=y;
	}
	*/
	
	//Nova move metoda - korišćeno ujednačeno dodeljivanje vrednosti
	@Override
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void moveBy(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	// Stara compareTo metoda
	/*public int compareTo(Object obj) {
		if (obj instanceof Point) {
			Point shapeToCompare = (Point) obj;
			return (int) (this.distance(0, 0) - shapeToCompare.distance(0, 0));
		}
		return 0;
	}
	*/

	//Nova compareTo metoda
	//dodat double radi sigurnosti i preciznijeg merenja, eksplicitno kastovanje u int nije preporučljivo u CleanCode-u
	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Point) {
			Point temp = (Point) obj;
			return Double.compare(this.distance(0, 0), temp.distance(0, 0));
		}
		return 0;
	}
	
	public Point clone(Point point) {
		point.setX(this.getX());
		point.setY(this.getY());
		point.setBorderColor(this.getBorderColor());
		return point;
	}
	
	// Stara toString metoda
	/*@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", color=" + getBorderColor() + "]";
	}
	*/
	
	//Nova toSTring metoda
	@Override
	public String toString() {
		//prvo definisan šablon poruke, pa ubačene vrednosti
		return String.format("Point: x=%d, y=%d, color=%s", x, y, getBorderColor());
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}