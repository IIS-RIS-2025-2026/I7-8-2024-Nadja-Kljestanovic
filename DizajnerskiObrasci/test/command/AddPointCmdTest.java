package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Point;
import mvc.DrawingModel;

class AddPointCmdTest {
	private AddPointCmd addPointCmd;
	private DrawingModel model;
	private Point point;

	@BeforeEach
	void setUp() {
		model = new DrawingModel();
		point = new Point(10, 10);
		addPointCmd = new AddPointCmd(point, model);
	}

	@Test
	void testExecuteAddsPointToModel() {
		assertEquals(0, model.getShapes().size(), "Model treba da bude prazan pre izvršenja komande.");
		
		// Izvršavanje komande
		addPointCmd.execute();
		
		// Asertacija
		assertTrue(model.getShapes().contains(point), "Tačka treba da bude dodata u listu oblika modela.");
		assertEquals(1, model.getShapes().size(), "Broj oblika u modelu treba da bude 1.");
	}

	@Test
	void testUnexecuteRemovesPointFromModel() {
		addPointCmd.execute();
		// Poništavanje komande
		addPointCmd.unexecute();
		
		// Asertacija
		assertFalse(model.getShapes().contains(point), "Tačka treba da bude uklonjena iz modela nakon unexecute.");
		assertEquals(0, model.getShapes().size(), "Model treba da ostane prazan nakon poništavanja.");
	}
}