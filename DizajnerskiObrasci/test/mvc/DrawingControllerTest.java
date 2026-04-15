package mvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import geometry.Point;
import geometry.Shape;

class DrawingControllerTest {

	private DrawingController controller;

	@Mock
	private DrawingModel model;
	@Mock
	private DrawingFrame frame;
	@Mock
	private MouseEvent mouseEvent;
	@Mock
	private javax.swing.JTextArea mockArea;
	@Mock
	private javax.swing.JButton mockButton;
	@Mock
	private javax.swing.JButton btnUndo;
	@Mock
	private javax.swing.JButton btnRedo;
	@Mock
	private javax.swing.JButton btnDelete;
	@Mock
	private javax.swing.JButton btnModify;
	@Mock
	private javax.swing.JButton btnToFront;
	@Mock
	private javax.swing.JButton btnToBack;
	@Mock
	private javax.swing.JButton btnBringToFront;
	@Mock
	private javax.swing.JButton btnBringToBack;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		when(frame.getArea()).thenReturn(mockArea);
		when(frame.getBtnNextCommand()).thenReturn(mockButton);

		when(frame.getBtnUndo()).thenReturn(btnUndo);
		when(frame.getBtnRedo()).thenReturn(btnRedo);
		when(frame.getBtnDelete()).thenReturn(btnDelete);
		when(frame.getBtnModify()).thenReturn(btnModify);
		when(frame.getBtnToFront()).thenReturn(btnToFront);
		when(frame.getBtnToBack()).thenReturn(btnToBack);
		when(frame.getBtnBringToFront()).thenReturn(btnBringToFront);
		when(frame.getBtnBringToBack()).thenReturn(btnBringToBack);

		when(model.getShapes()).thenReturn(new ArrayList<>());

		controller = new DrawingController(model, frame);
	}

	@Test
	void testMouseClickedAddPoint() {
		when(frame.getSelectedButton()).thenReturn("Point");
		when(mouseEvent.getX()).thenReturn(150);
		when(mouseEvent.getY()).thenReturn(50);

		// Izvršavanje
		controller.mouseClicked(mouseEvent);

		verify(frame, atLeastOnce()).repaint();
		assertTrue(controller.getCommandListSize() > 0);
	}

	@Test
	void testHandleSelectionFound() {
		when(frame.getSelectedButton()).thenReturn("Selection");
		when(mouseEvent.getX()).thenReturn(10);
		when(mouseEvent.getY()).thenReturn(10);

		Point p = new Point(10, 10);
		ArrayList<Shape> shapes = new ArrayList<>();
		shapes.add(p);

		when(model.getShapes()).thenReturn(shapes);

		controller.mouseClicked(mouseEvent);

		assertEquals(1, controller.getSelectListSize());
		assertEquals(p, controller.getSelectedShape());
	}

	@Test
	void testToFrontSuccess() {
		Shape s = new Point(10, 10);
		ArrayList<Shape> selected = new ArrayList<>();
		selected.add(s);

		controller.setListOfSelectedShapes(selected);
		controller.setSelectedShape(s);
		controller.setCurrentIndex(0);

		controller.toFront();

		verify(frame, atLeastOnce()).repaint();
	}

	@Test
	void testUndoWithNoCommands() {
		assertDoesNotThrow(() -> controller.undo());
		verify(frame, never()).repaint();
	}

	@Test
	void testRedoWithNoCommands() {
		assertDoesNotThrow(() -> controller.redo());
		verify(frame, never()).repaint();
	}

	@Test
	void testGettersAndSetters() {
		controller.setTextForSelect("Log text");
		assertEquals("Log text", controller.getTextForSelect());

		controller.setSelectFlag(true);
		assertTrue(controller.isSelectFlag());
	}
}