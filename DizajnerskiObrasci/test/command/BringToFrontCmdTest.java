package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

class BringToFrontCmdTest {
	private BringToFrontCmd bringToFrontCmd;
	private DrawingModel model;
	private ArrayList<Shape> selectedShapes;
	private Point shapeToMove;
	private Point shapeAtOne;
	private Point shapeAtTwo;

	@BeforeEach
	void setUp() {
		model = new DrawingModel();
		selectedShapes = new ArrayList<>();
		
		shapeToMove = new Point(0, 0);   
		shapeAtOne = new Point(10, 10);  
		shapeAtTwo = new Point(20, 20);  
		
		model.add(shapeToMove);
		model.add(shapeAtOne);
		model.add(shapeAtTwo);
		
		bringToFrontCmd = new BringToFrontCmd(model, selectedShapes, 0, 0);
	}

	@Test
	void testExecuteMovesShapeToFullFront() {
		assertEquals(0, model.getShapes().indexOf(shapeToMove), "Oblik treba da bude na indeksu 0 pre execute.");
		
		// Izvršavanje
		bringToFrontCmd.execute();
		
		// Asertacija
		int lastIndex = model.getShapes().size() - 1;
		assertEquals(lastIndex, model.getShapes().indexOf(shapeToMove), "Oblik treba da bude pomeren na sam kraj (indeks 2).");
		assertEquals(shapeAtOne, model.getShapes().get(0), "Oblik koji je bio na 1 treba da se pomeri na 0.");
		assertEquals(shapeAtTwo, model.getShapes().get(1), "Oblik koji je bio na 2 treba da se pomeri na 1.");
	}

	@Test
	void testUnexecuteRestoresShapeToOriginalIndex() {
		bringToFrontCmd.execute();
		bringToFrontCmd.setCurrentIndexCmd(model.getShapes().size() - 1);
		bringToFrontCmd.unexecute();
		
		// Asertacija
		assertEquals(0, model.getShapes().indexOf(shapeToMove), "Oblik treba da se vrati na svoju originalnu poziciju (indeks 0).");
		assertEquals(shapeAtOne, model.getShapes().get(1), "Ostali oblici treba da se vrate na svoja mesta.");
	}

	@Test
	void testExecuteWhenAlreadyAtFront() {
		int lastIndex = model.getShapes().size() - 1;
		BringToFrontCmd edgeCmd = new BringToFrontCmd(model, selectedShapes, lastIndex, lastIndex);
		edgeCmd.execute();
		
		// Asertacija: redosled ostaje isti
		assertEquals(lastIndex, model.getShapes().indexOf(shapeAtTwo), "Ako je oblik već na kraju, redosled ostaje isti.");
	}
}