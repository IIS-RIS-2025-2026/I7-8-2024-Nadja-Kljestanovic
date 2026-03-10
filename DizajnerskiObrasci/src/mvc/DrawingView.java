package mvc;


import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;

public class DrawingView extends JPanel {
	
	
	// DrawingModel model = new DrawingModel() Ne moze ovo, razlikuje se model koji se instanciorao ovamo i u glavnoj aplikaciji
	
	// Izuzetak od pocetne mvc arhitekture
	// null vrednost modela
	
	DrawingModel model = new DrawingModel();

	// DrawingModel model;
	
	
	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public void paint(Graphics g) {
		Iterator<Shape> it = model.getShapes().iterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}
		// Zbog loseg uticaja na performanse zbog neprekidnog i
		//repaint();
	
		
	}
	

}
