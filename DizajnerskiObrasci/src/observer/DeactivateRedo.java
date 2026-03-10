package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class DeactivateRedo  implements PropertyChangeListener{
	
	private DrawingFrame frame;
	private int savedCommandsSize;
	
	public DeactivateRedo(DrawingFrame frame) {
		this.frame = frame;
	}
	
	
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

}
