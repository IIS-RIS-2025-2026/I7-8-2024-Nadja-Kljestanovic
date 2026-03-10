package command;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command {

	
	private DrawingModel model;
	private ArrayList<Shape> listOfSelectedShapes;
	
	private int currentIndex;
	
	public void setCurrentIndexCmd(int currentIndex) {
		
		this.currentIndex = currentIndex;
		
	}
	
	public ToBackCmd(DrawingModel model,ArrayList<Shape> listOfSelectedShapes,int currentIndex) {
		this.model = model;
		this.listOfSelectedShapes = listOfSelectedShapes;
		this.currentIndex = currentIndex;
		
	}
	@Override
	public void execute() {
		
		
		
			
			
				Shape temp = model.get(currentIndex);
				
				model.getShapes().set(currentIndex, model.getShapes().get(currentIndex-1));
				
				model.getShapes().set(currentIndex-1,temp);
				
				
				
				
				
				
				
				
		
		
		
		
		
	}
	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
		
		if (currentIndex != model.getShapes().size()-1) {
			
			System.out.println("CurrentIndex is " + currentIndex);
			
				Shape temp = model.get(currentIndex);
				
				model.getShapes().set(currentIndex, model.getShapes().get(currentIndex+1));
				
				model.getShapes().set(currentIndex+1,temp);
			
		}
		
	}
	
	

}
