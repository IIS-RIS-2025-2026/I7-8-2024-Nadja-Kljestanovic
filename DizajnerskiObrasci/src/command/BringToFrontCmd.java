package command;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {
	private DrawingModel model;
	private ArrayList<Shape> listOfSelectedShapes;
	private int oldIndex;
	
	private int currentIndex;
	
	public int getOldIndex() {
		return oldIndex;
	}
	
	public void setCurrentIndexCmd(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	
	

	
	
	public BringToFrontCmd(DrawingModel model,ArrayList<Shape> listOfSelectedShapes,int oldIndex,int currentIndex) {
		this.model = model;
		this.listOfSelectedShapes = listOfSelectedShapes;
		this.oldIndex = oldIndex;
		this.currentIndex = currentIndex;
		
	}
	@Override
	public void execute() {
		
		
		
		
			
				for (int j = currentIndex;j<model.getShapes().size()-1;j++) {
					
				Shape temp = model.get(j);
				model.getShapes().set(j, model.getShapes().get(j+1));
				model.getShapes().set(j+1,temp);
				
				
					if (j == model.getShapes().size()-1) {
						
						break;
					}
				}
			
		
		
		
	}
	@Override
	public void unexecute() {

		
		
		while (currentIndex < model.getShapes().size() && currentIndex > 0) {
			
			if (currentIndex == oldIndex) {
				break; // I ovaj uslov sam dodao ovamo
			}
				Shape temp = model.get(currentIndex);
				model.getShapes().set(currentIndex, model.getShapes().get(currentIndex-1));
				model.getShapes().set(currentIndex-1,temp);
				currentIndex--;
						
		}
		
		setCurrentIndexCmd(oldIndex);

	}
	
	

}
