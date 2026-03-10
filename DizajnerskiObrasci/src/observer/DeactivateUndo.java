package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class DeactivateUndo implements PropertyChangeListener{
	
	private DrawingFrame frame;
	
	private int commandListSize;
	
	public DeactivateUndo(DrawingFrame frame) {
		this.frame = frame;
	}
	

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
	
	

}
