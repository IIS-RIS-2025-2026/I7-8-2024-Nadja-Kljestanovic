package mvc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import java.awt.Dimension;

class DrawingFrameTest {

    private DrawingFrame frame;

    @BeforeEach
    void setUp() {
        frame = new DrawingFrame();
    }

    @Test
    void testInitialSetup() {
        assertEquals("Djakovic Nikola IT 24/2021", frame.getTitle());
        assertNotNull(frame.getView(), "View ne sme biti null.");
        assertNotNull(frame.getArea(), "Log area (JTextArea) ne sme biti null.");
    }

    @Test
    void testDefaultColors() {
        assertEquals(Color.BLACK, frame.getBorderColor(), "Podrazumevana boja ivice treba da bude crna.");
        assertEquals(Color.WHITE, frame.getFillColor(), "Podrazumevana boja popune treba da bude bela.");
    }

    @Test
    void testButtonInitialStates() {
        assertFalse(frame.getBtnModify().isEnabled(), "Modify dugme treba da bude isključeno na početku.");
        assertFalse(frame.getBtnDelete().isEnabled(), "Delete dugme treba da bude isključeno na početku.");
        assertFalse(frame.getBtnUndo().isEnabled(), "Undo dugme treba da bude isključeno na početku.");
        assertFalse(frame.getBtnRedo().isEnabled(), "Redo dugme treba da bude isključeno na početku.");
    }

    @Test
    void testComponentDimensions() {
        Dimension expectedDim = new Dimension(100, 50);
        assertEquals(expectedDim, frame.getBtnModify().getPreferredSize());
        assertEquals(expectedDim, frame.getBtnDelete().getPreferredSize());
    }

    @Test
    void testZOrderButtonsDisabled() {
        assertFalse(frame.getBtnToFront().isEnabled());
        assertFalse(frame.getBtnToBack().isEnabled());
        assertFalse(frame.getBtnBringToFront().isEnabled());
        assertFalse(frame.getBtnBringToBack().isEnabled());
    }
}