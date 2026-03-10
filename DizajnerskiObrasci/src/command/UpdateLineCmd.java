package command;

import geometry.Line;

public class UpdateLineCmd implements Command {

	private Line line;
	private Line newState;
	private Line original = new Line();
	
	
	public UpdateLineCmd(Line line, Line newState) {
		
		this.line = line;
		this.newState = newState;
	}



	@Override
	public void execute() {
		
		original=line.clone(original);
		/*original.getStartPoint().setX(line.getStartPoint().getX());
		original.getStartPoint().setY(line.getStartPoint().getY());
		original.getEndPoint().setX(line.getEndPoint().getX());
		original.getEndPoint().setY(line.getEndPoint().getY());
		original.setColor(line.getColor());*/
		System.out.println("Not yet clones" + line.getStartPoint().getX());
		
		line = newState.clone(line);
		
		System.out.println("Clone" + line.getStartPoint().getX());
		/*line.getStartPoint().setX(newState.getStartPoint().getX());
		line.getStartPoint().setY(newState.getStartPoint().getY());
		line.getEndPoint().setX(newState.getEndPoint().getX());
		line.getEndPoint().setY(newState.getEndPoint().getY());
		line.setColor(newState.getColor());*/
		
		
		/*line.getEndPoint().setX(newState.getEndPoint().getX());
		line.setStartPoint(newState.getStartPoint());
		line.setEndPoint(newState.getEndPoint());   Treba na nivou primitivnih obelezja*/
		
		
	}
	
	

	@Override
	public void unexecute() {
		
		line = original.clone(line);
		/*line.getStartPoint().setX(original.getStartPoint().getX());
		line.getStartPoint().setY(original.getStartPoint().getY());
		line.getEndPoint().setX(original.getEndPoint().getX());
		line.getEndPoint().setY(original.getEndPoint().getY());
		line.setColor(original.getColor());*/

	}

}
