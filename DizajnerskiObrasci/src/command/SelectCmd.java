package command;

import java.util.ArrayList;
import geometry.HexagonAdapter;
import geometry.Shape;
import mvc.DrawingController;

public class SelectCmd implements Command {
	private Shape shape;
	private ArrayList<Shape> listOfSelectedShapes;
	private DrawingController controller;
	private boolean selectFlag;
	private String textForSelect;

	public SelectCmd(Shape shape, ArrayList<Shape> listOfSelectedShapes, DrawingController controller, boolean selectFlag) {
		this.shape = shape;
		this.listOfSelectedShapes = listOfSelectedShapes;
		this.controller = controller;
		this.selectFlag = selectFlag;
	}

	//Stara execute emtoda
	/*@Override
	public void execute() {
		 if (shape.isSelected() == false) {
			 listOfSelectedShapes.add(shape);
			 if (shape instanceof HexagonAdapter) {
				 textForSelect = "Selected " +  "Hexagon " + shape;
			 } else {
				 textForSelect = "Selected " + shape.getClass().getSimpleName()+ " " + shape;
			 }
			
			 controller.setTextForSelect(textForSelect);
		 } else {
			 listOfSelectedShapes.remove(shape);
			 if (shape instanceof HexagonAdapter) {
				 textForSelect = "Deselected " +  "Hexagon " + shape;
			 } else {
				 textForSelect = "Deselected " + shape.getClass().getSimpleName()+ " " + shape;
			 }
			 controller.setTextForSelect(textForSelect);
		 }
		 shape.setSelected(!shape.isSelected());
		 return ;
	}
	*/
	
	//Nova execute meotda
	@Override
	public void execute() {
		if (!shape.isSelected()) {
			selectShape();
		} else {
			deselectShape();
		}
		
		toggleShapeSelectionState();
		controller.setTextForSelect(textForSelect);
	}
	
	//Stara unexecute emtoda
	/*@Override
	public void unexecute() {
		 shape.setSelected(!shape.isSelected());
		 
		 if (shape.isSelected() == false) {
			 listOfSelectedShapes.remove(shape);
		 } else {
			 listOfSelectedShapes.add(shape);
		 }
		 return ;
	}
	*/

	@Override
	public void unexecute() {
		toggleShapeSelectionState();

		if (!shape.isSelected()) {
			listOfSelectedShapes.remove(shape);
		} else {
			listOfSelectedShapes.add(shape);
		}
	}

	//Dodavanje selektovanog oblika u listu
	private void selectShape() {
		listOfSelectedShapes.add(shape);
		textForSelect = "Selected " + getShapeName() + " " + shape;
	}

	//Brisanje deselektovanog oblika
	private void deselectShape() {
		listOfSelectedShapes.remove(shape);
		textForSelect = "Deselected " + getShapeName() + " " + shape;
	}

	//Selektovanje/deselektovanje 
	private void toggleShapeSelectionState() {
		shape.setSelected(!shape.isSelected());
	}

	//Dobijanje imena oblika
	private String getShapeName() {
		if (shape instanceof HexagonAdapter) {
			return "Hexagon";
		}
		return shape.getClass().getSimpleName();
	}
}