package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.HexagonAdapter;
import hexagon.Hexagon;
import java.awt.Color;

class UpdateHexagonCmdTest {
	private UpdateHexagonCmd updateHexagonCmd;
	private HexagonAdapter originalHexagon;
	private HexagonAdapter newStateHexagon;

	@BeforeEach
	void setUp() {
		Hexagon hex1 = new Hexagon(10, 10, 10);
		originalHexagon = new HexagonAdapter(hex1, false, Color.BLACK, Color.WHITE);
		Hexagon hex2 = new Hexagon(50, 50, 20);
		newStateHexagon = new HexagonAdapter(hex2, false, Color.RED, Color.BLUE);
		updateHexagonCmd = new UpdateHexagonCmd(originalHexagon, newStateHexagon);
	}

	@Test
	void testExecuteUpdatesHexagonProperties() {
		assertEquals(10, originalHexagon.getHexagon().getX(), "X koordinata treba da bude 10 pre execute.");
		assertEquals(Color.BLACK, originalHexagon.getBorderColor(), "Boja ivice treba da bude crna pre execute.");
		
		// Izvršavanje komande
		updateHexagonCmd.execute();
		
		// Asertacija
		assertEquals(50, originalHexagon.getHexagon().getX(), "X koordinata treba da bude ažurirana na 50.");
		assertEquals(50, originalHexagon.getHexagon().getY(), "Y koordinata treba da bude ažurirana na 50.");
		assertEquals(20, originalHexagon.getHexagon().getR(), "Radijus treba da bude ažuriran na 20.");
		assertEquals(Color.RED, originalHexagon.getBorderColor(), "Boja ivice treba da bude ažurirana na crvenu.");
		assertEquals(Color.BLUE, originalHexagon.getFillColor(), "Boja unutrašnjosti treba da bude ažurirana na plavu.");
	}

	@Test
	void testUnexecuteRestoresOriginalProperties() {
		updateHexagonCmd.execute();
		// Poništavanje komande
		updateHexagonCmd.unexecute();
		
		// Asertacija
		assertEquals(10, originalHexagon.getHexagon().getX(), "X koordinata treba da bude vraćena na 10.");
		assertEquals(10, originalHexagon.getHexagon().getY(), "Y koordinata treba da bude vraćena na 10.");
		assertEquals(10, originalHexagon.getHexagon().getR(), "Radijus treba da bude vraćen na 10.");
		assertEquals(Color.BLACK, originalHexagon.getBorderColor(), "Boja ivice treba da bude vraćena na crnu.");
		assertEquals(Color.WHITE, originalHexagon.getFillColor(), "Boja unutrašnjosti treba da bude vraćena na belu.");
	}
}