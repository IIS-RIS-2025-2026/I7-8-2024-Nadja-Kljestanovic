package observer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeEvent;
import mvc.DrawingFrame;

class DeactivateButtonsTest {
	private DeactivateButtons observer;
	private DrawingFrame frame;

	@BeforeEach
	void setUp() {
		frame = new DrawingFrame();
		observer = new DeactivateButtons(frame);
	}

	@Test
	void testSingleSelectionEnablesModifyAndDelete() {
		observer.propertyChange(new PropertyChangeEvent(this, "currentIndex", -1, 0));
		observer.propertyChange(new PropertyChangeEvent(this, "numberOfShapes", 0, 2));
		observer.propertyChange(new PropertyChangeEvent(this, "selectListSize", 0, 1));

		assertTrue(frame.getBtnModify().isEnabled());
		assertTrue(frame.getBtnDelete().isEnabled());
		assertFalse(frame.getBtnToBack().isEnabled());
		assertTrue(frame.getBtnToFront().isEnabled());
	}

	@Test
	void testMultipleSelectionDisablesModify() {
		observer.propertyChange(new PropertyChangeEvent(this, "selectListSize", 1, 2));
		
		assertFalse(frame.getBtnModify().isEnabled(), "Modify dugme mora biti isključeno za više od jednog oblika.");
		assertTrue(frame.getBtnDelete().isEnabled(), "Delete dugme ostaje uključeno za više oblika.");
	}

	@Test
	void testNoSelectionDisablesAll() {
		observer.propertyChange(new PropertyChangeEvent(this, "selectListSize", 1, 0));
		
		assertFalse(frame.getBtnModify().isEnabled());
		assertFalse(frame.getBtnDelete().isEnabled());
		assertFalse(frame.getBtnToBack().isEnabled());
	}
}