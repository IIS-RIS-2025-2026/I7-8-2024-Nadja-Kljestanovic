package command;

import java.util.ArrayList;

import geometry.HexagonAdapter;
import geometry.Shape;
import mvc.DrawingController;

public class SelectCmd implements Command  {
	private Shape shape;
	private ArrayList<Shape> listOfSelectedShapes;
	private String textForSelect;
	private DrawingController controller;
	private boolean selectFlag;
	 
	public SelectCmd(Shape shape,ArrayList<Shape> listOfSelectedShapes,DrawingController controller,boolean selectFlag) {
		this.shape = shape;
		this.listOfSelectedShapes = listOfSelectedShapes;
		this.controller= controller;
		this.selectFlag = selectFlag;
	}

	@Override
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

	@Override
	public void unexecute() {
		 shape.setSelected(!shape.isSelected());
		 
		 if (shape.isSelected() == false) {
			 listOfSelectedShapes.remove(shape);
		 } else {
			 listOfSelectedShapes.add(shape);
		 }
		 return ;
	}
}
