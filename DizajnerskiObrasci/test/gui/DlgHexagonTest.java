package gui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DlgHexagonTest {
	private DlgHexagon dialog;

	@BeforeEach
	void setUp() {
		dialog = new DlgHexagon();
	}

	@Test
	void testCheckIfNumberWithValidInput() {
		assertTrue(dialog.CheckIfNumber("10"), "Treba da vrati true za validan broj.");
		assertTrue(dialog.CheckIfNumber("0"), "Treba da vrati true za nulu.");
	}

	@Test
	void testCheckIfNumberWithInvalidInput() {
		assertFalse(dialog.CheckIfNumber("hex"), "Treba da vrati false za tekstualni unos.");
		assertFalse(dialog.CheckIfNumber(""), "Treba da vrati false za prazan string.");
		assertFalse(dialog.CheckIfNumber(null), "Treba da vrati false za null.");
	}

	@Test
	void testInitialCancelState() {
		assertFalse(dialog.isCancelClicked(), "CancelClicked inicijalno mora biti false.");
	}

	@Test
	void testSetCancelClicked() {
		dialog.setCancelClicked(true);
		assertTrue(dialog.isCancelClicked(), "CancelClicked mora postati true nakon setovanja.");
	}

	@Test
	void testTextFieldAccess() {
		dialog.getTxtFieldX().setText("100");
		dialog.getTxtFieldY().setText("150");
		dialog.getTxtFieldR().setText("40");
		
		assertEquals("100", dialog.getTxtFieldX().getText());
		assertEquals("150", dialog.getTxtFieldY().getText());
		assertEquals("40", dialog.getTxtFieldR().getText());
	}

	@Test
	void testInitialButtonBackgrounds() {
		assertNotNull(dialog.getBtnColor());
		assertNotNull(dialog.getBtnFill());
	}
}