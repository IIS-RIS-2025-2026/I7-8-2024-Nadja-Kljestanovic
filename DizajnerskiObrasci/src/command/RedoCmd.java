package command;

import java.util.ArrayList;
import java.util.Stack;

public class RedoCmd implements Command {
	private ArrayList<Command> commandList;
	private Stack<Command> savedCommands;
	private Command command;
	public RedoCmd(Command command,ArrayList<Command> commandList,Stack<Command> savedCommands){
		this.command = command; 
		this.commandList = commandList;
		this.savedCommands = savedCommands;
	}
	@Override
	public void execute() {
		savedCommands.peek().execute();
		commandList.add(savedCommands.peek());
		savedCommands.pop();
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		//commandList.remove(command);
	}

}
