package command;

import java.awt.Color;

import geometry.Line;
import geometry.Point;
import mvc.DrawingModel;

public class TestCommand {

	public static void main(String[] args) {
		
		DrawingModel model = new DrawingModel();
		Point p1 = new Point(10,10,false,Color.RED);
		Point p2 = new Point(20,20,false,Color.black);
		
		// Testiranje dodavanja tacke
		
		/*AddPointCmd addPointCmd = new AddPointCmd(p1,model);
		
		addPointCmd.execute();
		System.out.println(model.getShapes().size()); // ----> 1
		
		addPointCmd.unexecute();
		System.out.println(model.getShapes().size()); // ----> 0
		
		addPointCmd.execute();
		System.out.println(model.getShapes().size()); // ----> 1
		
		
		// Testiranje brisanja tacke
		
		RemovePointCmd removePointCmd = new RemovePointCmd(p1,model);
		
		removePointCmd.execute();
		
		System.out.println(model.getShapes().size()); // --> 0
		
		removePointCmd.unexecute();
		
		System.out.println(model.getShapes().size()); // --> 1
		
		// Testiranje modifikacije tacke
		
		UpdatePointCmd updatePointCmd = new UpdatePointCmd(p1,p2);
		
		updatePointCmd.execute();
		
		System.out.println(p1);
		
		
		
		updatePointCmd.unexecute();
		System.out.println(p1);
		
		*/
		Point point1 = new Point(30,30,false,Color.BLACK);
		Point point2 = new Point(40,40,false,Color.GREEN);
		Point point3 = new Point(50,50,false,Color.DARK_GRAY);
		
		
		
		Line line1 = new Line(point1,point2,false,Color.BLUE);
		Line line2 = new Line(point1,point3,false,Color.YELLOW);
		
		AddLineCmd addLineCmd = new AddLineCmd(line1,model);
		
		addLineCmd.execute();
		System.out.println(model.getShapes().size()); // --> 1
		
		addLineCmd.unexecute();
		System.out.println(model.getShapes().size()); // --> 0
		
		addLineCmd.execute();
		System.out.println(model.getShapes().size()); // --> 1
		
		RemoveLineCmd removeLineCmd = new RemoveLineCmd(line1,model);
		
		removeLineCmd.execute(); 
		System.out.println(model.getShapes().size()); // --> 0
		
		removeLineCmd.unexecute(); 
		System.out.println(model.getShapes().size()); // --> 1
		
		UpdateLineCmd updateLineCmd = new UpdateLineCmd(line1,line2);
		
		updateLineCmd.execute();
		
		System.out.println(line1);
		
		point3.setX(100);
		
		System.out.println(line1);
		
		/*updateLineCmd.unexecute();
		
		System.out.println(line1);
		
		
		
		System.out.println(line1);*/
		
		
		
		
		
		
	}

}
