package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import hexagon.Hexagon;

public class HexagonAdapter extends Shape implements Serializable{
	
	private static final double HEXAGON_HEIGHT_FACTOR = 1.15;
	private Hexagon hexagon= new Hexagon(0, 0, 0);
	
	public HexagonAdapter() {
		super();
	}
	
	public HexagonAdapter(Hexagon hexagon,boolean selected,Color borderColor,Color fillColor) {
		super(selected, borderColor, fillColor);
		this.hexagon = hexagon;
		this.hexagon.setBorderColor(borderColor);
		this.hexagon.setAreaColor(fillColor);
	}

	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
	}

	@Override
	public void moveBy(int x, int y) {
		hexagon.setX(hexagon.getX()+x);
		hexagon.setY(hexagon.getY()+y);
	}

	//Stara metoda compareTo
	/*@Override
	public int compareTo(Object arg0) {
		return 0;
	}*/
	
	//Nova metoda compareTo
	@Override
	public int compareTo(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter other = (HexagonAdapter) obj;
			return Integer.compare(this.hexagon.getR(), other.hexagon.getR());
		}
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	//Stara draw metoda
	/*@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		if(isSelected()) {
			g.setColor(Color.blue);
			g.drawRect(hexagon.getX() + hexagon.getR() - 2, hexagon.getY() - 2, 4, 4);
			g.drawRect(hexagon.getX() - hexagon.getR() - 2, hexagon.getY() - 2, 4, 4);
			g.drawRect(hexagon.getX() + (hexagon.getR() / 2) - 2, (int) (hexagon.getY() + (hexagon.getR( )/ 1.15)) ,4, 4);
			g.drawRect(hexagon.getX() + (hexagon.getR() / 2) - 2, (int) (hexagon.getY() - (hexagon.getR() / 1.15)), 4, 4);
			g.drawRect(hexagon.getX() - (hexagon.getR() / 2) - 2, (int) (hexagon.getY() + (hexagon.getR() / 1.15)), 4, 4);
			g.drawRect(hexagon.getX() - (hexagon.getR() / 2) - 2, (int) (hexagon.getY() - (hexagon.getR() / 1.15)), 4, 4);
		}
	}*/
	
	//Nova draw metoda - princip jedne odgovornosti - SRP
	@Override
    public void draw(Graphics g) {
        hexagon.paint(g);
        if (isSelected()) {
            drawSelectionMarkers(g);
        }
    }
    private void drawSelectionMarkers(Graphics g) {
        g.setColor(Color.BLUE);
        int x = hexagon.getX();
        int y = hexagon.getY();
        int r = hexagon.getR();
        
        int verticalOffset = (int) (r / HEXAGON_HEIGHT_FACTOR);
        int halfR = r / 2;
        drawMarker(g, x + r, y);
        drawMarker(g, x - r, y);
        drawMarker(g, x + halfR, y + verticalOffset);
        drawMarker(g, x + halfR, y - verticalOffset);
        drawMarker(g, x - halfR, y + verticalOffset);
        drawMarker(g, x - halfR, y - verticalOffset);
    }
    
    private void drawMarker(Graphics g, int x, int y) {
        g.drawRect(x - 2, y - 2, 4, 4);
    }
	
	public HexagonAdapter clone(HexagonAdapter hexagonAdapter) {
		hexagonAdapter.hexagon.setX(this.hexagon.getX());
		hexagonAdapter.hexagon.setY(this.hexagon.getY());
		hexagonAdapter.hexagon.setR(this.hexagon.getR());
		hexagonAdapter.setBorderColor(this.hexagon.getBorderColor());
		hexagonAdapter.setFillColor(this.hexagon.getAreaColor());
		return hexagonAdapter;
	}
	
	// Stara metoda toString
	/*public String toString() {
		return hexagon.getX() + " " + hexagon.getY() + " " + hexagon.getR() + ", borderColor= " + getBorderColor() + ", fillColor= " + getFillColor();
	}
	*/

	//Nova metoda toString
	@Override
	public String toString() {
		return String.format("Hexagon: x=%d, y=%d, r=%d, color=%s, fill=%s", 
				hexagon.getX(), hexagon.getY(), hexagon.getR(), getBorderColor(), getFillColor());
	}
	
	public Hexagon getHexagon() {
		return hexagon;
	}
	
	@Override
	public void setBorderColor(Color color) {
	    super.setBorderColor(color);      // menjanje u Shape
	    this.hexagon.setBorderColor(color); // menjanje u Hexagon biblioteci
	}
	
	@Override
	public void setFillColor(Color color) {
	    super.setFillColor(color);
	    this.hexagon.setAreaColor(color);
	}
}
