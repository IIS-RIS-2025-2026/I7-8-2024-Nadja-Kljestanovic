package observer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeEvent;
import mvc.DrawingFrame;

class DeactivateRedoTest {
	private DeactivateRedo observer;
	private DrawingFrame frame;

	@BeforeEach
	void setUp() {
		frame = new DrawingFrame();
		observer = new DeactivateRedo(frame);
	}

	@Test
	void testPropertyChangeEnablesRedo() {
		PropertyChangeEvent event = new PropertyChangeEvent(this, "savedCommandsSize", 0, 1);
		
		observer.propertyChange(event);
		
		assertTrue(frame.getBtnRedo().isEnabled(), "Redo dugme treba da bude omogućeno.");
	}

	@Test
	void testPropertyChangeDisablesRedo() {
		frame.getBtnRedo().setEnabled(true);
		PropertyChangeEvent event = new PropertyChangeEvent(this, "savedCommandsSize", 1, 0);
		
		observer.propertyChange(event);
		
		assertFalse(frame.getBtnRedo().isEnabled(), "Redo dugme treba da bude onemogućeno.");
	}
}