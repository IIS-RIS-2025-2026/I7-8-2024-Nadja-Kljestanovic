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
    void testIsNumeric() {
        assertTrue(dialog.isNumeric("50"));
        assertFalse(dialog.isNumeric("abc"));
        assertFalse(dialog.isNumeric(""));
        assertFalse(dialog.isNumeric(null));
    }

    @Test
    void testInitialState() {
        assertFalse(dialog.isCommited(), "Inicijalno commited treba da bude false.");
        assertFalse(dialog.isCancelClicked(), "Inicijalno cancelClicked treba da bude false.");
        assertEquals(Color.BLACK, dialog.getPointColor(), "Inicijalna boja treba da bude crna.");
    }

    @Test
    void testSettersAndGetters() {
        dialog.setPointColor(Color.GREEN);
        dialog.setCommited(true);
        
        assertEquals(Color.GREEN, dialog.getPointColor());
        assertTrue(dialog.isCommited());
    }

    @Test
    void testTextFields() {
        dialog.getTxtX().setText("15");
        dialog.getTxtY().setText("25");
        
        assertEquals("15", dialog.getTxtX().getText());
        assertEquals("25", dialog.getTxtY().getText());
    }

    @Test
    void testCancelAction() {
        dialog.setCancelClicked(true);
        assertTrue(dialog.isCancelClicked());
    }
}