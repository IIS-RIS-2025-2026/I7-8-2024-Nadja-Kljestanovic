package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import mvc.DrawingFrame;

public class DeactivateRedo implements PropertyChangeListener {
	
	private final DrawingFrame frame;
	
	public DeactivateRedo(DrawingFrame frame) {
		this.frame = frame;
	}
	
	// Stara propertyChange metoda
	/*
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName() == "savedCommandsSize") {
			savedCommandsSize = (int)event.getNewValue();
			if (savedCommandsSize > 0) {
				frame.getBtnRedo().setEnabled(true);
			} else {
				frame.getBtnRedo().setEnabled(false);
			}
		}
	}
	*/

	// Nova propertyChange metoda
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if ("savedCommandsSize".equals(event.getPropertyName())) {
			int savedCommandsSize = (int) event.getNewValue();
			frame.getBtnRedo().setEnabled(savedCommandsSize > 0);
		}
	}
}