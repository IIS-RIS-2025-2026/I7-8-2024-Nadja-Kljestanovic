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
	
	public BringToBackCmd(DrawingModel model, ArrayList<Shape> listOfSelectedShapes, int oldIndex, int currentIndex) {
		this.model = model;
		this.listOfSelectedShapes = listOfSelectedShapes;
		this.oldIndex = oldIndex;
		this.currentIndex = currentIndex;
		
	}
	//Nova execute metoda
	@Override
	public void execute() {
		while (currentIndex < model.getShapes().size() && currentIndex > 0) {
			swapWithPrevious(currentIndex);
			currentIndex--;
		}
			
	}
	
	//Nova unexecute metoda
	@Override
	public void unexecute() {
		if (isAlreadyAtTargetOrEnd()) {
			return;
		}

		moveShapeForwardToOriginalIndex();
		setCurrentIndexCmd(oldIndex);
	}
	
	//Vraća oblik na originalnu poziciju
	private void moveShapeForwardToOriginalIndex() {
		for (int j = currentIndex; j < oldIndex; j++) {
			swapWithNext(j);

			if (isLastElementInList(j)) {
				break;
			}
		}
	}

	//Provera da li je oblik već na mestu ili na kraju liste
	private boolean isAlreadyAtTargetOrEnd() {
		return currentIndex == oldIndex || currentIndex == model.getShapes().size() - 1;
	}

	private boolean isLastElementInList(int index) {
		return index == model.getShapes().size() - 1;
	}

	//Pomočna metoda za execute
	private void swapWithPrevious(int index) {
		Shape temp = model.get(index);
		model.getShapes().set(index, model.getShapes().get(index - 1));
		model.getShapes().set(index - 1, temp);
	}

	//Pomoćna metoda za unexecute
	private void swapWithNext(int index) {
		Shape temp = model.get(index);
		model.getShapes().set(index, model.getShapes().get(index + 1));
		model.getShapes().set(index + 1, temp);
	}
}
