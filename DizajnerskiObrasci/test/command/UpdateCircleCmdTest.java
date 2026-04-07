package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Circle;
import geometry.Point;

class UpdateCircleCmdTest {
	private UpdateCircleCmd updateCircleCmd;
	private Circle circle;
	private Circle newState;

	@BeforeEach
	void setUp() {
		circle = new Circle(new Point(10, 10), 5);
		newState = new Circle(new Point(20, 20), 10);
		updateCircleCmd = new UpdateCircleCmd(circle, newState);
	}

	@Test
	void testExecuteUpdatesCircleState() {
		assertEquals(5, circle.getRadius(), "Poluprečnik treba da bude 5 pre izmene.");
		assertEquals(10, circle.getCenter().getX(), "X koordinata centra treba da bude 10 pre izmene.");
		
		// Izvršavanje komande
		updateCircleCmd.execute();
		
		// Asertacija
		assertEquals(10, circle.getRadius(), "Poluprečnik treba da bude ažuriran na 10.");
		assertEquals(20, circle.getCenter().getX(), "X koordinata centra treba da bude ažurirana na 20.");
		assertEquals(20, circle.getCenter().getY(), "Y koordinata centra treba da bude ažurirana na 20.");
	}

	@Test
	void testUnexecuteRestoresOriginalState() {
		updateCircleCmd.execute();
		// Poništavanje komande
		updateCircleCmd.unexecute();
		
		// Asertacija
		assertEquals(5, circle.getRadius(), "Poluprečnik treba da bude vraćen na 5.");
		assertEquals(10, circle.getCenter().getX(), "X koordinata centra treba da bude vraćena na 10.");
		assertEquals(10, circle.getCenter().getY(), "Y koordinata centra treba da bude vraćena na 10.");
	}
}