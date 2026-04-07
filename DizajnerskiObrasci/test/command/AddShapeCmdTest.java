package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

class AddShapeCmdTest {
	private AddShapeCmd addShapeCmd;
	private DrawingModel model;
	private Shape shape;

	@BeforeEach
	void setUp() {
		model = new DrawingModel();
		shape = new Point(10, 10);
		addShapeCmd = new AddShapeCmd(shape, model);
	}

	@Test
	void testExecuteAddsShapeToModel() {
		assertEquals(0, model.getShapes().size(), "Model treba da bude prazan pre izvršenja komande.");
		
		// Izvršavanje komande
		addShapeCmd.execute();
		
		// Asertacija
		assertTrue(model.getShapes().contains(shape), "Oblik treba da bude dodat u listu modela.");
		assertEquals(1, model.getShapes().size(), "Broj oblika u modelu treba da bude 1.");
	}

	@Test
	void testUnexecuteRemovesShapeFromModel() {
		addShapeCmd.execute();
		
		// Poništavanje komande (Undo)
		addShapeCmd.unexecute();
		
		// Asertacija
		assertFalse(model.getShapes().contains(shape), "Oblik treba da bude uklonjen iz modela nakon unexecute.");
		assertEquals(0, model.getShapes().size(), "Model treba da ostane prazan nakon poništavanja.");
	}
}