package gui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

class DlgPointTest {
	private DlgPoint dialog;

	@BeforeEach
	void setUp() {
		dialog = new DlgPoint();
	}

	@Test
	void testCheckIfNumberValid() {
		assertTrue(dialog.CheckIfNumber("100"));
		assertTrue(dialog.CheckIfNumber("0"));
	}

	@Test
	void testCheckIfNumberInvalid() {
		assertFalse(dialog.CheckIfNumber("abc"));
		assertFalse(dialog.CheckIfNumber(""));
		assertFalse(dialog.CheckIfNumber(null));
	}

	@Test
	void testInitialStates() {
		assertFalse(dialog.isCancelClicked(), "Inicijalno cancelClicked mora biti false.");
		assertFalse(dialog.isCommited(), "Inicijalno commited mora biti false.");
		assertEquals(Color.BLACK, dialog.getBtnColor().getBackground());
	}

	@Test
	void testSettersAndGetters() {
		dialog.setCommited(true);
		dialog.setCancelClicked(true);
		dialog.setColor(Color.RED);
		
		assertTrue(dialog.isCommited());
		assertTrue(dialog.isCancelClicked());
		assertEquals(Color.RED, dialog.getColor());
	}

	@Test
	void testTextFieldInput() {
		dialog.textX.setText("150");
		dialog.textY.setText("250");
		
		assertEquals("150", dialog.textX.getText());
		assertEquals("250", dialog.textY.getText());
	}
}