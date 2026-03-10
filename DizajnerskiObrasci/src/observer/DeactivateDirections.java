package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class DeactivateDirections implements PropertyChangeListener{

	
	private DrawingFrame frame;
	private int numberOfShapes;
	
	public DeactivateDirections(DrawingFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName() == "numberOfShapes") {
			this.numberOfShapes = (int)event.getNewValue();
			
		}
		
	}
	
}
