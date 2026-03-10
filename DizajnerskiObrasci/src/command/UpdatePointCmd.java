package command;

import geometry.Point;

public class UpdatePointCmd implements Command,Cloneable {

	private Point point;
	private Point newState;
	private Point original = new Point();
	
	
	
	
	
	public UpdatePointCmd(Point point, Point newState) {
		
		this.point = point;
		this.newState = newState;
		
		
	}
	
	

	@Override
	public void execute() {
		
	/*	original.setX(point.getX());
		original.setY(point.getY());
		original.setColor(point.getColor());*/
		original = point.clone(original);
		
		
		
		//System.out.println("x coordinate before" + point.getX());
		
		point = newState.clone(point);
		
		//System.out.println("x coordinate after" + point.getX());
		
		
	   
	}

	@Override
	public void unexecute() {
		
		
		point = original.clone(point);
		
		/*point.setX(original.getX());
		point.setY(original.getY());
		point.setColor(original.getColor());*/

	}

}
