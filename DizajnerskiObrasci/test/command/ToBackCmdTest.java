package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

class ToBackCmdTest {
	private ToBackCmd toBackCmd;
	private DrawingModel model;
	private ArrayList<Shape> selectedShapes;
	private Point shapeToMove;
	private Point otherShape;

	@BeforeEach
	void setUp() {
		model = new DrawingModel();
		selectedShapes = new ArrayList<>();
		
		otherShape = new Point(10, 10);
		shapeToMove = new Point(20, 20);
		
		model.add(otherShape);
		model.add(shapeToMove);
		
		toBackCmd = new ToBackCmd(model, selectedShapes, 1);
	}

	@Test
	void testExecuteMovesShapeOneStepBack() {
		assertEquals(1, model.getShapes().indexOf(shapeToMove), "Oblik treba da bude na indeksu 1 pre pomeranja.");
		
		// Izvršavanje pomeranja unazad
		toBackCmd.execute();
		
		// Asertacija
		assertEquals(0, model.getShapes().indexOf(shapeToMove), "Oblik treba da se pomeri na indeks 0.");
		assertEquals(otherShape, model.getShapes().get(1), "Drugi oblik treba da se pomeri napred na indeks 1.");
	}

	@Test
	void testUnexecuteRestoresShapePosition() {
		toBackCmd.execute();
		
		// Poništavanje (Undo)
		toBackCmd.unexecute();
		
		// Asertacija
		assertEquals(1, model.getShapes().indexOf(shapeToMove), "Oblik treba da se vrati na originalni indeks 1.");
		assertEquals(otherShape, model.getShapes().get(0), "Drugi oblik treba da se vrati na indeks 0.");
	}

	@Test
	void testExecuteAtBeginningDoesNothing() {
		ToBackCmd edgeCmd = new ToBackCmd(model, selectedShapes, 0);

		// Izvršavanje
		edgeCmd.execute();
		
		// Asertacija: redosled u modelu mora ostati nepromenjen
		assertEquals(0, model.getShapes().indexOf(otherShape), "Oblik na početku ne sme da se pomeri dalje unazad.");
	}
}