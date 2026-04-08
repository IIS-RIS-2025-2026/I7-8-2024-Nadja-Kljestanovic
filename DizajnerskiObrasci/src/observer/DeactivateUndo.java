package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import mvc.DrawingFrame;

public class DeactivateUndo implements PropertyChangeListener {
	
	private final DrawingFrame frame;
	
	public DeactivateUndo(DrawingFrame frame) {
		this.frame = frame;
	}
	
	// Stara propertyChange metoda
	/*
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName() == "commandListSize") {
			this.commandListSize = (int)event.getNewValue();
			if (commandListSize > 0) {
				frame.getBtnUndo().setEnabled(true);
			}
			else {
				frame.getBtnUndo().setEnabled(false);
			}
		}
	}
	*/

	// Nova propertyChange metoda
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if ("commandListSize".equals(event.getPropertyName())) {
			int commandListSize = (int) event.getNewValue();
			frame.getBtnUndo().setEnabled(commandListSize > 0);
		}
	}
}