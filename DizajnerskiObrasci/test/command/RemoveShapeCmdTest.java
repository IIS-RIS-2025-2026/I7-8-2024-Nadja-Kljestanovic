package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

class RemoveShapeCmdTest {
	private RemoveShapeCmd removeShapeCmd;
	private DrawingModel model;
	private ArrayList<Shape> selectedShapes;
	private Point shape;

	@BeforeEach
	void setUp() {
		model = new DrawingModel();
		selectedShapes = new ArrayList<>();
		shape = new Point(10, 10);
		shape.setSelected(true);
		model.add(shape);
		selectedShapes.add(shape);
		
		removeShapeCmd = new RemoveShapeCmd(shape, model, selectedShapes, 0);
	}

	@Test
	void testExecuteRemovesShapeFromModelAndSelectedList() {
		assertTrue(model.getShapes().contains(shape), "Oblik treba da bude u modelu pre brisanja.");
		assertTrue(selectedShapes.contains(shape), "Oblik treba da bude u listi selektovanih pre brisanja.");
		
		// Izvršavanje brisanja
		removeShapeCmd.execute();
		
		// Asertacija
		assertFalse(model.getShapes().contains(shape), "Oblik treba da bude uklonjen iz modela.");
		assertFalse(selectedShapes.contains(shape), "Oblik treba da bude uklonjen iz liste selektovanih.");
		assertEquals(0, model.getShapes().size(), "Model treba da bude prazan.");
	}

	@Test
	void testUnexecuteRestoresShapeAtCorrectIndex() {
		// Dodajemo još jedan oblik
		Point secondShape = new Point(20, 20);
		model.add(secondShape);
		
		// Izvršavamo brisanje prvog oblika
		removeShapeCmd.execute();
		
		// Poništavanje brisanja (Undo)
		removeShapeCmd.unexecute();
		
		// Asertacija
		assertEquals(0, model.getShapes().indexOf(shape), "Oblik treba da bude vraćen na tačno navedeni indeks (0).");
		assertTrue(selectedShapes.contains(shape), "Oblik treba ponovo da bude u listi selektovanih.");
	}
}