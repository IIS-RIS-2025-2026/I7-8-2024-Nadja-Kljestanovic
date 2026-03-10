package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import hexagon.Hexagon;

public class HexagonAdapter extends Shape implements Serializable{
	
	private Hexagon hexagon= new Hexagon(1,1,1);
	// Ovo su samo vrednosti koje sam stavio da bi se napravio Hexagon je on nema prazan konstruktor
	
	
	public HexagonAdapter() {
		
	}
	public HexagonAdapter(Hexagon hexagon,boolean selected,Color borderColor,Color fillColor) {
		super(selected);
		this.hexagon=hexagon;
		
		this.hexagon.setBorderColor(borderColor);
		this.hexagon.setAreaColor(fillColor);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		hexagon.setX(x);
		hexagon.setY(y);
		
		
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		hexagon.setX(hexagon.getX()+x);
		hexagon.setY(hexagon.getY()+y);
		
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		/*if (arg0 instanceof Hexagon) {
			Hexagon shapeToCompare = (Hexagon) arg0;
			return shapeToCompare.
		}*/
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		
		hexagon.paint(g);
		
		if(isSelected()) {
			g.setColor(Color.blue);
			g.drawRect(hexagon.getX() + hexagon.getR() -2, hexagon.getY()-2, 4, 4);
			
			g.drawRect(hexagon.getX() - hexagon.getR() - 2, hexagon.getY()-2, 4, 4);
			
			g.drawRect(hexagon.getX() +(hexagon.getR()/2)-2, (int) (hexagon.getY() +(hexagon.getR()/1.15)) ,4, 4);
			g.drawRect(hexagon.getX() +(hexagon.getR()/2)-2, (int) (hexagon.getY() -(hexagon.getR()/1.15)), 4, 4);
			g.drawRect(hexagon.getX() -(hexagon.getR()/2)-2, (int) (hexagon.getY() +(hexagon.getR()/1.15)), 4, 4);
			g.drawRect(hexagon.getX() -(hexagon.getR()/2)-2, (int) (hexagon.getY() -(hexagon.getR()/1.15)), 4, 4);
			
			// Previse vremena sam proveo na namestanju ovoga da dobro izgleda
		
		}
		
		
		
	}
	
	public HexagonAdapter clone(HexagonAdapter hexagonAdapter) {
		
		
		hexagonAdapter.hexagon.setX(this.hexagon.getX());
		hexagonAdapter.hexagon.setY(this.hexagon.getY());
		hexagonAdapter.hexagon.setR(this.hexagon.getR());
		hexagonAdapter.hexagon.setBorderColor(this.hexagon.getBorderColor());
		hexagonAdapter.hexagon.setAreaColor(this.hexagon.getAreaColor());
		hexagonAdapter.setBorderColor(this.hexagon.getBorderColor());
		hexagonAdapter.setFillColor(this.hexagon.getAreaColor());
		
		
		return hexagonAdapter;
		
		
	}
	
	public Hexagon getHexagon() {
		return hexagon;
	}
	
	public String toString() {
		return hexagon.getX() + " " + hexagon.getY() + " " + hexagon.getR() + ", borderColor= " + getBorderColor() + ", fillColor= " + getFillColor();
	}

}
