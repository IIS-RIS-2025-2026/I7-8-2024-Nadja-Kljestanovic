package command;

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

	public BringToFrontCmd(DrawingModel model, ArrayList<Shape> listOfSelectedShapes, int oldIndex, int currentIndex) {
		this.model = model;
		this.listOfSelectedShapes = listOfSelectedShapes;
		this.oldIndex = oldIndex;
		this.currentIndex = currentIndex;
	}

	//Nova execute metoda
	@Override
	public void execute() {
		int lastIndex = model.getShapes().size() - 1;
		for (int j = currentIndex; j < lastIndex; j++) {
			swapWithNext(j);
		}
	}

	//Nova unexecute metoda
	@Override
	public void unexecute() {
		if (isAlreadyAtOldIndex()) {
			return;
		}

		moveShapeBackToOriginalIndex();
		setCurrentIndexCmd(oldIndex);
	}

	//Vraća oblik na originalni indeks
	void moveShapeBackToOriginalIndex() {
		while (canMoveBackwards()) {
			if (currentIndex == oldIndex) {
				break;
			}
			swapWithPrevious(currentIndex);
			currentIndex--;
		}
	}

	//Proverava da li je oblik već na staroj poziciji
	private boolean isAlreadyAtOldIndex() {
		return currentIndex == oldIndex;
	}

	//Provera granice
	private boolean canMoveBackwards() {
		return currentIndex < model.getShapes().size() && currentIndex > 0;
	}

	//Zamena mesta sa sledećim oblikom
	private void swapWithNext(int index) {
		Shape temp = model.get(index);
		model.getShapes().set(index, model.getShapes().get(index + 1));
		model.getShapes().set(index + 1, temp);
	}

	//Zamena mesta sa prethodnim oblikom
	private void swapWithPrevious(int index) {
		Shape temp = model.get(index);
		model.getShapes().set(index, model.getShapes().get(index - 1));
		model.getShapes().set(index - 1, temp);
	}
}