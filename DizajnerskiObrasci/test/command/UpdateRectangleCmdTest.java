package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Rectangle;
import geometry.Point;
import java.awt.Color;

class UpdateRectangleCmdTest {
	private UpdateRectangleCmd updateRectangleCmd;
	private Rectangle originalRectangle;
	private Rectangle newStateRectangle;

	@BeforeEach
	void setUp() {
		originalRectangle = new Rectangle(new Point(10, 10), 50, 30);
		originalRectangle.setBorderColor(Color.BLACK);
		originalRectangle.setFillColor(Color.WHITE);
		newStateRectangle = new Rectangle(new Point(20, 20), 100, 60);
		newStateRectangle.setBorderColor(Color.RED);
		newStateRectangle.setFillColor(Color.BLUE);
		updateRectangleCmd = new UpdateRectangleCmd(originalRectangle, newStateRectangle);
	}

	@Test
	void testExecuteUpdatesRectangleProperties() {
		assertEquals(50, originalRectangle.getWidth(), "Širina treba da bude 50 pre execute.");
		assertEquals(Color.BLACK, originalRectangle.getBorderColor(), "Boja ivice treba da bude crna pre execute.");

		// Izvršavanje komande
		updateRectangleCmd.execute();
		
		// Asertacija
		assertEquals(20, originalRectangle.getUpperLeftPoint().getX(), "X koordinata treba da bude ažurirana na 20.");
		assertEquals(100, originalRectangle.getWidth(), "Širina treba da bude ažurirana na 100.");
		assertEquals(60, originalRectangle.getHeight(), "Visina treba da bude ažurirana na 60.");
		assertEquals(Color.RED, originalRectangle.getBorderColor(), "Boja ivice treba da bude ažurirana na crvenu.");
		assertEquals(Color.BLUE, originalRectangle.getFillColor(), "Boja unutrašnjosti treba da bude ažurirana na plavu.");
	}

	@Test
	void testUnexecuteRestoresOriginalProperties() {
		updateRectangleCmd.execute();
		// Poništavanje komande
		updateRectangleCmd.unexecute();
		
		// Asertacija
		assertEquals(10, originalRectangle.getUpperLeftPoint().getX(), "X koordinata treba da bude vraćena na 10.");
		assertEquals(50, originalRectangle.getWidth(), "Širina treba da bude vraćena na 50.");
		assertEquals(30, originalRectangle.getHeight(), "Visina treba da bude vraćena na 30.");
		assertEquals(Color.BLACK, originalRectangle.getBorderColor(), "Boja ivice treba da bude vraćena na crnu.");
		assertEquals(Color.WHITE, originalRectangle.getFillColor(), "Boja unutrašnjosti treba da bude vraćena na belu.");
	}
}