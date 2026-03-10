package command;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import geometry.HexagonAdapter;
import geometry.Shape;
import mvc.DrawingController;
import mvc.DrawingModel;

public class SelectCmd implements Command  {
	private Shape shape;
	

	private ArrayList<Shape> listOfSelectedShapes;
	private String textForSelect;
	private DrawingController controller;
	private boolean selectFlag;
	 
	public SelectCmd(Shape shape,ArrayList<Shape> listOfSelectedShapes,DrawingController controller,boolean selectFlag) {
		//super();
		
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
	/*	for (int i = 0; i<model.getShapes().size();i++) {
			 System.out.println(model.getShapes().size());
			 if (model.getShapes().get(i).contains(e.getX(),e.getY())) {
				 Shape shape = model.getShapes().get(i);
				 shape.setSelected(false);
				 
			 }
		 }
		*/
		 
				
			 
			
				 
				 
					
					 shape.setSelected(!shape.isSelected());
					 
					 if (shape.isSelected() == false) {
						 listOfSelectedShapes.remove(shape);
					 } else {
						 listOfSelectedShapes.add(shape);
					 }
					
				 
				 return ;
				
			
				 
				 
			 
			
		 
		
		
	}
}
