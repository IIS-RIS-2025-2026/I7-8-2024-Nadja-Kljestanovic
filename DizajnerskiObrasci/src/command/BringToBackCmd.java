package command;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {

	
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

	
	
	public BringToBackCmd(DrawingModel model,ArrayList<Shape> listOfSelectedShapes,int oldIndex,int currentIndex) {
		this.model = model;
		this.listOfSelectedShapes = listOfSelectedShapes;
		this.oldIndex = oldIndex;
		this.currentIndex = currentIndex;
		
	}
	@Override
	public void execute() {
		
		
		
		
		
		while (currentIndex < model.getShapes().size() && currentIndex > 0) {
			
			System.out.println("Current index at beggining" + currentIndex);
			
			Shape temp = model.get(currentIndex);
			
			model.getShapes().set(currentIndex, model.getShapes().get(currentIndex-1));
			
			model.getShapes().set(currentIndex-1,temp);
			
			currentIndex--;
			
			System.out.println("Current index at end " + currentIndex);
		}
			
			
			
			
			
		
		
		
	}
	@Override
	public void unexecute() {
		
		
		
		
		
		if (currentIndex != model.getShapes().size()-1) {
			
				for (int j = currentIndex;j<oldIndex;j++) {
					
				if (j==oldIndex) {
					setCurrentIndexCmd(j);
					break;
				}
				Shape temp = model.get(j);
				
				model.getShapes().set(j, model.getShapes().get(j+1));
				
				model.getShapes().set(j+1,temp);
				
				System.out.println("Broj:" + j);
				
				if (j == model.getShapes().size()-1) {
					setCurrentIndexCmd(j);
					break;
					}
				}
				
				setCurrentIndexCmd(oldIndex);
				
			
		
		}

	}
	
	

}
