package observer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeEvent;
import mvc.DrawingFrame;

class DeactivateUndoTest {
	private DeactivateUndo observer;
	private DrawingFrame frame;

	@BeforeEach
	void setUp() {
		frame = new DrawingFrame();
		observer = new DeactivateUndo(frame);
	}

	@Test
	void testPropertyChangeEnablesUndo() {
		PropertyChangeEvent event = new PropertyChangeEvent(this, "commandListSize", 0, 1);
		
		observer.propertyChange(event);
		
		assertTrue(frame.getBtnUndo().isEnabled(), "Undo dugme treba da bude omogućeno kada je lista veća od 0.");
	}

	@Test
	void testPropertyChangeDisablesUndo() {
		frame.getBtnUndo().setEnabled(true);
		PropertyChangeEvent event = new PropertyChangeEvent(this, "commandListSize", 1, 0);
		
		observer.propertyChange(event);
		
		assertFalse(frame.getBtnUndo().isEnabled(), "Undo dugme treba da bude onemogućeno kada je lista prazna.");
	}

	@Test
	void testIgnoreOtherProperties() {
		frame.getBtnUndo().setEnabled(false);
		PropertyChangeEvent event = new PropertyChangeEvent(this, "someOtherProperty", 0, 1);
		
		observer.propertyChange(event);
		
		assertFalse(frame.getBtnUndo().isEnabled(), "Stanje dugmeta se ne sme menjati za nepoznata svojstva.");
	}
}