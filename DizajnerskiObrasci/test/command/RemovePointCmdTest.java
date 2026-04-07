package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Point;
import mvc.DrawingModel;

class RemovePointCmdTest {
	private RemovePointCmd removePointCmd;
	private DrawingModel model;
	private Point point;

	@BeforeEach
	void setUp() {
		model = new DrawingModel();
		point = new Point(10, 10);
		model.add(point);
		
		removePointCmd = new RemovePointCmd(point, model);
	}

	@Test
	void testExecuteRemovesPointFromModel() {
		assertTrue(model.getShapes().contains(point), "Tačka treba da bude u modelu pre brisanja.");
		assertEquals(1, model.getShapes().size(), "Model treba da ima jedan element.");
		
		// Izvršavanje komande
		removePointCmd.execute();
		
		// Asertacija
		assertFalse(model.getShapes().contains(point), "Tačka treba da bude uklonjena iz modela.");
		assertEquals(0, model.getShapes().size(), "Model treba da bude prazan nakon brisanja.");
	}

	@Test
	void testUnexecuteAddsPointBackToModel() {
		removePointCmd.execute();
		// Poništavanje brisanja (Undo)
		removePointCmd.unexecute();
		
		// Asertacija
		assertTrue(model.getShapes().contains(point), "Tačka treba da bude vraćena u model nakon unexecute.");
		assertEquals(1, model.getShapes().size(), "Model treba ponovo da ima jedan element.");
	}
}