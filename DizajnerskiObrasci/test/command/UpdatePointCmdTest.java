package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Point;
import java.awt.Color;

class UpdatePointCmdTest {
	private UpdatePointCmd updatePointCmd;
	private Point originalPoint;
	private Point newStatePoint;

	@BeforeEach
	void setUp() {
		originalPoint = new Point(10, 10);
		originalPoint.setBorderColor(Color.BLACK);
		newStatePoint = new Point(20, 20);
		newStatePoint.setBorderColor(Color.RED);
		updatePointCmd = new UpdatePointCmd(originalPoint, newStatePoint);
	}

	@Test
	void testExecuteUpdatesPointProperties() {
		assertEquals(10, originalPoint.getX(), "X koordinata treba da bude 10 pre execute.");
		assertEquals(Color.BLACK, originalPoint.getBorderColor(), "Boja treba da bude crna pre execute.");
		
		// Izvršavanje komande
		updatePointCmd.execute();
		
		// Asertacija
		assertEquals(20, originalPoint.getX(), "X koordinata treba da bude ažurirana na 20.");
		assertEquals(20, originalPoint.getY(), "Y koordinata treba da bude ažurirana na 20.");
		assertEquals(Color.RED, originalPoint.getBorderColor(), "Boja treba da bude ažurirana na crvenu.");
	}

	@Test
	void testUnexecuteRestoresOriginalProperties() {
		updatePointCmd.execute();
		// Poništavanje komande
		updatePointCmd.unexecute();
		
		// Asertacija
		assertEquals(10, originalPoint.getX(), "X koordinata treba da bude vraćena na 10.");
		assertEquals(10, originalPoint.getY(), "Y koordinata treba da bude vraćena na 10.");
		assertEquals(Color.BLACK, originalPoint.getBorderColor(), "Boja treba da bude vraćena na crnu.");
	}
}