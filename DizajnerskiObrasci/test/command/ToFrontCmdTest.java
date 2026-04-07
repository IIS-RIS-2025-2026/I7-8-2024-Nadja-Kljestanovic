package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

class ToFrontCmdTest {
	private ToFrontCmd toFrontCmd;
	private DrawingModel model;
	private ArrayList<Shape> selectedShapes;
	private Point shapeToMove;
	private Point nextShape;

	@BeforeEach
	void setUp() {
		model = new DrawingModel();
		selectedShapes = new ArrayList<>();
		
		shapeToMove = new Point(10, 10); 
		nextShape = new Point(20, 20);   
		
		model.add(shapeToMove);
		model.add(nextShape);
		
		toFrontCmd = new ToFrontCmd(model, selectedShapes, 0);
	}

	@Test
	void testExecuteMovesShapeOneStepForward() {
		assertEquals(0, model.getShapes().indexOf(shapeToMove), "Oblik treba da bude na indeksu 0 pre pomeranja.");
		
		// Izvršavanje pomeranja unapred
		toFrontCmd.execute();
		
		// Asertacija
		assertEquals(1, model.getShapes().indexOf(shapeToMove), "Oblik treba da se pomeri na indeks 1.");
		assertEquals(nextShape, model.getShapes().get(0), "Drugi oblik treba da se pomeri nazad na indeks 0.");
	}

	@Test
	void testUnexecuteRestoresShapePosition() {
		toFrontCmd.execute();
		toFrontCmd.setCurrentIndexCmd(1);
		
		// Poništavanje (Undo)
		toFrontCmd.unexecute();
		
		// Asertacija
		assertEquals(0, model.getShapes().indexOf(shapeToMove), "Oblik treba da se vrati na originalni indeks 0.");
		assertEquals(1, model.getShapes().indexOf(nextShape), "NextShape treba da se vrati na indeks 1.");
	}

	@Test
	void testExecuteAtEndDoesNothing() {
		ToFrontCmd edgeCmd = new ToFrontCmd(model, selectedShapes, 1);
		
		// Izvršavanje
		edgeCmd.execute();
		
		// Asertacija
		assertEquals(1, model.getShapes().indexOf(nextShape), "Oblik na kraju ne sme da se pomeri dalje unapred.");
	}
}