package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Donut;
import geometry.Point;
import java.awt.Color;

class UpdateDonutCmdTest {
	private UpdateDonutCmd updateDonutCmd;
	private Donut originalDonut;
	private Donut newStateDonut;

	@BeforeEach
	void setUp() {
		originalDonut = new Donut(new Point(10, 10), 20, 10);
		originalDonut.setBorderColor(Color.BLACK);
		originalDonut.setFillColor(Color.WHITE);
		
		newStateDonut = new Donut(new Point(30, 30), 40, 15);
		newStateDonut.setBorderColor(Color.RED);
		newStateDonut.setFillColor(Color.BLUE);
		
		updateDonutCmd = new UpdateDonutCmd(originalDonut, newStateDonut);
	}

	@Test
	void testExecuteUpdatesDonutProperties() {
		assertEquals(20, originalDonut.getRadius(), "Spoljašnji poluprečnik treba da bude 20 pre execute.");
		assertEquals(10, originalDonut.getInnerRadius(), "Unutrašnji poluprečnik treba da bude 10 pre execute.");
		assertEquals(Color.BLACK, originalDonut.getBorderColor(), "Boja ivice treba da bude crna pre execute.");
		
		// Izvršavanje komande
		updateDonutCmd.execute();
		
		// Asertacija
		assertEquals(30, originalDonut.getCenter().getX(), "X koordinata treba da bude ažurirana na 30.");
		assertEquals(40, originalDonut.getRadius(), "Spoljašnji poluprečnik treba da bude ažuriran na 40.");
		assertEquals(15, originalDonut.getInnerRadius(), "Unutrašnji poluprečnik treba da bude ažuriran na 15.");
		assertEquals(Color.RED, originalDonut.getBorderColor(), "Boja ivice treba da bude ažurirana na crvenu.");
		assertEquals(Color.BLUE, originalDonut.getFillColor(), "Boja unutrašnjosti treba da bude ažurirana na plavu.");
	}

	@Test
	void testUnexecuteRestoresOriginalProperties() {
		updateDonutCmd.execute();
		// Poništavanje komande
		updateDonutCmd.unexecute();
		
		// Asertacija
		assertEquals(10, originalDonut.getCenter().getX(), "X koordinata treba da bude vraćena na 10.");
		assertEquals(20, originalDonut.getRadius(), "Spoljašnji poluprečnik treba da bude vraćen na 20.");
		assertEquals(10, originalDonut.getInnerRadius(), "Unutrašnji poluprečnik treba da bude vraćen na 10.");
		assertEquals(Color.BLACK, originalDonut.getBorderColor(), "Boja ivice treba da bude vraćena na crnu.");
		assertEquals(Color.WHITE, originalDonut.getFillColor(), "Boja unutrašnjosti treba da bude vraćena na belu.");
	}
}