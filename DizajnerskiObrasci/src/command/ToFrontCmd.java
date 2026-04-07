package command;

import java.util.ArrayList;
import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCmd implements Command {

	private DrawingModel model;
	private ArrayList<Shape> listOfSelectedShapes;
	private int currentIndex;

	public void setCurrentIndexCmd(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public ToFrontCmd(DrawingModel model, ArrayList<Shape> listOfSelectedShapes, int currentIndex) {
		this.model = model;
		this.listOfSelectedShapes = listOfSelectedShapes;
		this.currentIndex = currentIndex;
	}

	@Override
	public void execute() {
		if (canMoveForward()) {
			swapWithNext();
		}
	}

	@Override
	public void unexecute() {
		if (canMoveBackward()) {
			swapWithPrevious();
		}
	}

	//Provera za pomeranje unapred
	private boolean canMoveForward() {
		return currentIndex < model.getShapes().size() - 1;
	}

	//Provera za vraćanje unazad
	private boolean canMoveBackward() {
		return currentIndex < model.getShapes().size() && currentIndex >= 1;
	}

	//Zamena sa sledećim oblikom
	private void swapWithNext() {
		Shape temp = model.get(currentIndex);
		model.getShapes().set(currentIndex, model.getShapes().get(currentIndex + 1));
		model.getShapes().set(currentIndex + 1, temp);
	}

	//Zamena sa prethodnim oblikom
	private void swapWithPrevious() {
		Shape temp = model.get(currentIndex);
		model.getShapes().set(currentIndex, model.getShapes().get(currentIndex - 1));
		model.getShapes().set(currentIndex - 1, temp);
	}
}