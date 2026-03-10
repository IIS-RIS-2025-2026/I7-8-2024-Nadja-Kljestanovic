package geometry;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel{
 
	public static void main(String args[]) {
		JFrame frame = new JFrame("Drawing");
		frame.setSize(800, 600);
		Drawing panelDrawing = new Drawing();
		frame.getContentPane().add(panelDrawing);
		frame.setVisible(true);
	}
	public void paint(Graphics g) {
		/*Point p = new Point(200,200);
		p.draw(g);
		g.setColor(Color.red);
		Point startPoint = new Point(300,400);
		Linija line = new Linija(startPoint, new Point(800,900));
		line.draw(g);
		g.setColor(Color.black);
		Donut donut = new Donut(new Point(350,450), 50, 25, true);
		donut.draw(g);
		Rectangle r1= new Rectangle(new Point (300,300),200,200);
		g.setColor(Color.blue);
		r1.draw(g);*/
		
		/*Iterator<Shape> it = shapes.iterator();
		while(it.hasNext()) {
			Shape sh = it.next();
		System.out.println(sh);
		System.out.println(sh);
		}
		it= shapes.iterator();*/
// Zadatak 1
		Point p1= new Point(300,300);
		Line l1 = new Line (new Point (100,100),new Point (150,150));
		Circle c1= new Circle (new Point(20,80),20);
		
	    Donut d1= new Donut(new Point (40,40),30,10);
		Rectangle r1= new Rectangle(new Point (50,50),60,80);
		
		ArrayList<Shape> shapes1= new ArrayList<Shape>();
		shapes1.add(p1);
		shapes1.add(l1);
		shapes1.add(c1);
		shapes1.add(d1);
		shapes1.add(r1);
		
		Iterator <Shape> it = shapes1.iterator();
		while (it.hasNext()) {
		Shape sh = it.next();
		 sh.moveBy(10, 0);
		 System.out.println(sh);
		}
		 //2 zadatak
		 shapes1.get(3).draw(g);
		 
		 shapes1.get(shapes1.size() - 1).draw(g);
		 shapes1.remove(1);
		 shapes1.get(1).draw(g);
		 shapes1.get(3).draw(g);
		 shapes1.add(3,l1);
		 
		 // Exception
		//Exception
			try {
				c1.setRadius(-10);
				System.out.println("try");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			it = shapes1.iterator();
			while (it.hasNext()) {
				Shape gf = it.next();
				gf.moveBy(10, 0);
				gf.setSelected(true);
				gf.draw(g);
			}
	}
	
}
