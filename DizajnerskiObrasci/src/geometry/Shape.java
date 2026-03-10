package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable,Serializable {

	private boolean selected;
	
	private Color borderColor;
	private Color fillColor;
	
    
	

	public Shape() {

	}
	
	public Shape(boolean selected) {
		this.selected = selected;
	}
	public Shape(boolean selected,Color borderColor) {
		this.selected = selected;
		this.borderColor=borderColor;
	}
	public Shape(boolean selected,Color borderColor,Color fillColor) {
		this(selected,borderColor);
		this.fillColor = fillColor;
	}

	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
//Comparable racuna udaljenost od koordinatnog pocetka
//string poredimo sa equals





} 