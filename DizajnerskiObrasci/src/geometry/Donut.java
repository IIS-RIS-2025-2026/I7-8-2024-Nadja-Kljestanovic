package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Objects;


public class Donut extends Shape  {
    private Point center = new Point();
	private int innerRadius;
	private int radius;
	Color color1;
	Color color2;
	
	public Donut() {
		super();
	}
	public Donut(Point center, int radius, int innerRadius) {
		this.center= center;
		this.radius= radius;
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center,radius,innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected,Color borderColor,Color fillColor) {
		this(center,radius,innerRadius,selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	}

	//Stara equals metoda
	/*public boolean equals(Object obj) {
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
	}*/
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Donut)) return false;
        Donut other = (Donut) obj;
        return radius == other.radius && 
               innerRadius == other.innerRadius && 
               Objects.equals(center, other.center);
    }
	
	public boolean contains(int x, int y) {
		double d = center.distance(x, y);
        return d <= radius && d >= innerRadius;
	}

	public boolean contains(Point clickPoint) {
		if (clickPoint == null) return false;
		return this.contains(clickPoint.getX(), clickPoint.getY());
	}

	public double area() {
		return (radius * radius * Math.PI) - innerRadius * innerRadius * Math.PI;
	}
	
	//Stara draw metoda
	/*public void draw(Graphics g) {
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
	}*/
	
	@Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D outer = new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);
        Ellipse2D inner = new Ellipse2D.Double(center.getX() - innerRadius, center.getY() - innerRadius, 2 * innerRadius, 2 * innerRadius);
        
        Area donutArea = new Area(outer);
        donutArea.subtract(new Area(inner));

        g2d.setColor(getFillColor());
        g2d.fill(donutArea);

        g2d.setColor(getBorderColor());
        g2d.draw(donutArea);

        if (isSelected()) {
            drawSelectionMarkers(g);
        }
    }

    private void drawSelectionMarkers(Graphics g) {
        g.setColor(Color.BLUE);
        drawMarker(g, center.getX(), center.getY());
        drawCircleMarkers(g, radius);
        drawCircleMarkers(g, innerRadius);
    }

    private void drawCircleMarkers(Graphics g, int r) {
        drawMarker(g, center.getX() - r, center.getY());
        drawMarker(g, center.getX() + r, center.getY());
        drawMarker(g, center.getX(), center.getY() - r);
        drawMarker(g, center.getX(), center.getY() + r);
    }

    private void drawMarker(Graphics g, int x, int y) {
        g.drawRect(x - 2, y - 2, 4, 4);
    }
	
	public int compareTo(Object obj) {
		if(obj instanceof Donut) {
			Donut temp = (Donut)obj;
			//return (int)(this.area() - temp.area());
			return Double.compare(this.area(), temp.area()); //preciznije
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
	
	//Stara toString mtoda
	/*public String toString() {
		return "Center=" + center + ", radius=" + radius + ", innerRadius=" + innerRadius 
				+ ", borderColor= " + getBorderColor() + ", fillColor= " + getFillColor();
	}*/
	
	//Nova toString metoda
	@Override
    public String toString() {
        return String.format("Donut: center=%s, radius=%d, innerRadius=%d, color=%s, fill=%s", 
                center, radius, innerRadius, getBorderColor(), getFillColor());
    }
	
	public void moveTo(int x, int y) {
		center.moveTo(x, y);
	}

	public void moveBy(int x, int y) {
		center.moveBy(x, y);
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
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
