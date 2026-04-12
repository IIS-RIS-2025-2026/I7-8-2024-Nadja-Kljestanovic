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
		assertTrue(dialog.isNumeric("10"), "Treba da vrati true za validan broj.");
		assertTrue(dialog.isNumeric("0"), "Treba da vrati true za nulu.");
	}

	@Test
	void testCheckIfNumberWithInvalidInput() {
		assertFalse(dialog.isNumeric("hex"), "Treba da vrati false za tekstualni unos.");
		assertFalse(dialog.isNumeric(""), "Treba da vrati false za prazan string.");
		assertFalse(dialog.isNumeric(null), "Treba da vrati false za null.");
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
		dialog.getTxtX().setText("100");
		dialog.getTxtY().setText("150");
		dialog.getTxtR().setText("40");
		
		assertEquals("100", dialog.getTxtX().getText());
		assertEquals("150", dialog.getTxtY().getText());
		assertEquals("40", dialog.getTxtR().getText());
	}

	@Test
	void testInitialButtonBackgrounds() {
		assertNotNull(dialog.getBtnColor());
		assertNotNull(dialog.getBtnFill());
	}
}