package command;

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

	public ToBackCmd(DrawingModel model, ArrayList<Shape> listOfSelectedShapes, int currentIndex) {
		this.model = model;
		this.listOfSelectedShapes = listOfSelectedShapes;
		this.currentIndex = currentIndex;
	}

	@Override
	public void execute() {
		if (canMoveBack()) {
			swapWithPrevious();
		}
	}

	@Override
	public void unexecute() {
		if (canMoveForward()) {
			swapWithNext();
		}
	}

	//Proverava uslov za pomeranje nazad
	private boolean canMoveBack() {
		return currentIndex > 0;
	}

	//Provera za vraćanje napred
	private boolean canMoveForward() {
		return currentIndex < model.getShapes().size() - 1;
	}

	//Zamena sa prethodnim
	private void swapWithPrevious() {
		Shape temp = model.get(currentIndex);
		model.getShapes().set(currentIndex, model.getShapes().get(currentIndex - 1));
		model.getShapes().set(currentIndex - 1, temp);
		currentIndex--; 
	}

	//Zamena sa sledećim
	private void swapWithNext() {
		Shape temp = model.get(currentIndex - 1);
		model.getShapes().set(currentIndex - 1, model.getShapes().get(currentIndex));
		model.getShapes().set(currentIndex, temp);
	}
}