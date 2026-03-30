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
		line = newState.clone(line);
	}

	@Override
	public void unexecute() {
		line = original.clone(line);
	}

}
