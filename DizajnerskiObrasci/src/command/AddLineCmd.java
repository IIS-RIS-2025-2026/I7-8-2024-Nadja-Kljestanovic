package command;

import geometry.Line;
import mvc.DrawingModel;

public class AddLineCmd implements Command {

	
	private Line line;
	private DrawingModel model;
	
	
	
	public AddLineCmd(Line line, DrawingModel model) {
		
		this.line = line;
		this.model = model;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.add(line);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.remove(line);
	}

}
