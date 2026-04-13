package mvc;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;
import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.RedoCmd;
import command.RemoveShapeCmd;
import command.SelectCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UndoCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import helpers.*;
import observer.DeactivateButtons;
import observer.DeactivateRedo;
import observer.DeactivateUndo;
import strategy.ObjectFileSaveLoad;
import strategy.SaveAndLoad;
import strategy.TextFileSaveAndLoad;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private String textForSelect;
	private Shape selectedShape;
	private ArrayList<Shape> listOfSelectedShapes = new ArrayList<Shape>();
	private Stack<Command> savedCommands = new Stack<Command>();
	private Point startPoint;
	private ArrayList<Command> commandList = new ArrayList<Command>();
	private Stack<String> undoCommandText = new Stack<String>();
	private PropertyChangeSupport propertyChangeSupport;
	private int selectListSize;
	private int commandListSize;
	private int savedCommandsSize;
	private int numberOfShapes;
	private int currentIndex;
	private DeactivateButtons db;
	private DeactivateUndo deactivateUndo;
	private DeactivateRedo deactivateRedo;
	ArrayList<String> stringArrayList;
	int counter = 0;
	private boolean selectFlag;
	ShapeHandler shapeHandler;
	LogHandler logHandler;
	LogParser logParser;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		propertyChangeSupport = new PropertyChangeSupport(this);

		db = new DeactivateButtons(frame);
		deactivateUndo = new DeactivateUndo(frame);
		deactivateRedo = new DeactivateRedo(frame);

		addListener(db);
		addListener(deactivateUndo);
		addListener(deactivateRedo);

		this.shapeHandler = new ShapeHandler(frame);
		this.logHandler = new LogHandler(frame);
		this.logParser = new LogParser();
	}

	public void mouseClicked(MouseEvent e) {
		String mode = frame.getSelectedButton();

		if ("Selection".equals(mode)) {
			handleSelection(e);
		} else {
			Shape newShape = shapeHandler.prepareNewShape(mode, e, startPoint);
			if (newShape != null) {
				executeAddCommand(newShape);
			} else if ("Line".equals(mode) && startPoint == null) {
				startPoint = new Point(e.getX(), e.getY());
			}
		}
		frame.repaint();
	}

	// Pomoćna metoda za mouseClicked
	private void executeAddCommand(Shape shape) {
		AddShapeCmd addCmd = new AddShapeCmd(shape, model);
		addCmd.execute();
		commandList.add(addCmd);

		logHandler.logAction("Added", shape);

		savedCommands.clear();
		logHandler.clearRedo();
		startPoint = null;

		setCommandListSize(commandList.size());
		setNumberOfShapes(model.getShapes().size());
	}

	// Pomoćna metoda za mouseClicked
	private void handleSelection(MouseEvent e) {
		for (int i = model.getShapes().size() - 1; i >= 0; i--) {
			Shape s = model.getShapes().get(i);

			if (s.contains(e.getX(), e.getY())) {
				boolean selectFlag = true;

				SelectCmd selectCmd = new SelectCmd(s, listOfSelectedShapes, this, selectFlag);
				selectCmd.execute();
				commandList.add(selectCmd);

				if (textForSelect != null) {
					logHandler.logText(textForSelect);
				}

				if (listOfSelectedShapes.size() == 1) {
					setSelectedShape(listOfSelectedShapes.get(0));
					setCurrentIndex(model.getShapes().indexOf(selectedShape));
				} else {
					setCurrentIndex(-1);
				}
				break;
			}
		}
		setSelectListSize(listOfSelectedShapes.size());
	}

	public void delete() {
		if (listOfSelectedShapes.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nothing is selected", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int option = JOptionPane.showConfirmDialog(null, "Do you want to delete selected shape(s)?", "Confirmation",
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			for (int i = listOfSelectedShapes.size() - 1; i >= 0; i--) {
				Shape selectedShape = listOfSelectedShapes.get(i);

				int index = model.getShapes().indexOf(selectedShape);

				if (index != -1) {
					RemoveShapeCmd removeCmd = new RemoveShapeCmd(selectedShape, model, listOfSelectedShapes, index);
					removeCmd.execute();
					commandList.add(removeCmd);
					logHandler.logDeletion(selectedShape);
				}
			}

			savedCommands.clear();
			logHandler.clearRedo();
			setCurrentIndex(-1);
			updateSizes();
			frame.repaint();
		}
	}

	public void modify() {
		if (listOfSelectedShapes.size() != 1) {
			JOptionPane.showMessageDialog(null, "Please select exactly one shape to modify.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Shape selected = listOfSelectedShapes.get(0);
		Shape updatedShape = shapeHandler.prepareUpdateShape(selected);

		if (updatedShape != null) {
			Command updateCmd = createUpdateCommand(selected, updatedShape);

			if (updateCmd != null) {
				updateCmd.execute();
				commandList.add(updateCmd);

				logHandler.logModification(selected);

				savedCommands.clear();
				logHandler.clearRedo();
				updateSizes();
				frame.repaint();
			}
		}
	}

	// Pomoćna metoda
	private Command createUpdateCommand(Shape oldS, Shape newS) {
		if (oldS instanceof Point)
			return new UpdatePointCmd((Point) oldS, (Point) newS);
		if (oldS instanceof Line)
			return new UpdateLineCmd((Line) oldS, (Line) newS);
		if (oldS instanceof Rectangle)
			return new UpdateRectangleCmd((Rectangle) oldS, (Rectangle) newS);
		if (oldS instanceof Circle)
			return new UpdateCircleCmd((Circle) oldS, (Circle) newS);
		if (oldS instanceof Donut)
			return new UpdateDonutCmd((Donut) oldS, (Donut) newS);
		if (oldS instanceof HexagonAdapter)
			return new UpdateHexagonCmd((HexagonAdapter) oldS, (HexagonAdapter) newS);
		return null;
	}

	public void undo() {
		if (commandList.isEmpty())
			return;

		Command command = commandList.get(commandList.size() - 1);

		if (command instanceof ToFrontCmd) {
			((ToFrontCmd) command).setCurrentIndexCmd(currentIndex);
			setCurrentIndex(currentIndex - 1);
		} else if (command instanceof ToBackCmd) {
			((ToBackCmd) command).setCurrentIndexCmd(currentIndex);
			setCurrentIndex(currentIndex + 1);
		} else if (command instanceof BringToFrontCmd) {
			((BringToFrontCmd) command).setCurrentIndexCmd(currentIndex);
			setCurrentIndex(((BringToFrontCmd) command).getOldIndex());
		} else if (command instanceof BringToBackCmd) {
			((BringToBackCmd) command).setCurrentIndexCmd(currentIndex);
			setCurrentIndex(((BringToBackCmd) command).getOldIndex());
		}

		UndoCmd undoCommand = new UndoCmd(command, commandList, savedCommands);
		undoCommand.execute();

		if (listOfSelectedShapes.size() == 1) {
			setSelectedShape(listOfSelectedShapes.get(0));
			setCurrentIndex(model.getShapes().indexOf(selectedShape));
		} else {
			setCurrentIndex(-1);
		}

		logHandler.logUndo();
		updateSizes();
		frame.repaint();
	}

	public void redo() {
		if (savedCommands.isEmpty())
			return;

		Command command = savedCommands.peek();

		if (command instanceof ToFrontCmd) {
			((ToFrontCmd) command).setCurrentIndexCmd(currentIndex);
			setCurrentIndex(currentIndex + 1);
		} else if (command instanceof ToBackCmd) {
			((ToBackCmd) command).setCurrentIndexCmd(currentIndex);
			setCurrentIndex(currentIndex - 1);
		} else if (command instanceof BringToFrontCmd) {
			((BringToFrontCmd) command).setCurrentIndexCmd(currentIndex);
			setCurrentIndex(model.getShapes().size() - 1);
		} else if (command instanceof BringToBackCmd) {
			((BringToBackCmd) command).setCurrentIndexCmd(currentIndex);
			setCurrentIndex(0);
		}

		RedoCmd redoCommand = new RedoCmd(command, commandList, savedCommands);
		redoCommand.execute();

		if (listOfSelectedShapes.size() == 1) {
			setSelectedShape(listOfSelectedShapes.get(0));
			setCurrentIndex(model.getShapes().indexOf(selectedShape));
		} else {
			setCurrentIndex(-1);
		}

		logHandler.logRedo();
		updateSizes();
		frame.repaint();
	}

	public void toFront() {
		if (listOfSelectedShapes.size() != 1)
			return;

		ToFrontCmd frontCmd = new ToFrontCmd(model, listOfSelectedShapes, currentIndex);
		frontCmd.execute();
		commandList.add(frontCmd);

		setCurrentIndex(currentIndex + 1);
		logHandler.logAction("toFront", selectedShape);

		savedCommands.clear();
		logHandler.clearRedo();
		updateSizes();
		frame.repaint();
	}

	public void toBack() {
		if (listOfSelectedShapes.size() != 1)
			return;

		ToBackCmd backCmd = new ToBackCmd(model, listOfSelectedShapes, currentIndex);
		backCmd.execute();

		commandList.add(backCmd);
		setCurrentIndex(currentIndex - 1);

		logHandler.logAction("toBack", selectedShape);
		savedCommands.clear();
		logHandler.clearRedo();

		updateSizes();
		frame.repaint();
	}

	public void bringToFront() {
		if (listOfSelectedShapes.size() != 1)
			return;

		BringToFrontCmd bringToFrontCmd = new BringToFrontCmd(model, listOfSelectedShapes, currentIndex, currentIndex);
		bringToFrontCmd.execute();

		commandList.add(bringToFrontCmd);
		setCurrentIndex(model.getShapes().size() - 1);

		logHandler.logAction("bringToFront", selectedShape);
		savedCommands.clear();
		logHandler.clearRedo();

		updateSizes();
		frame.repaint();
	}

	public void bringToBack() {
		if (listOfSelectedShapes.size() != 1)
			return;

		BringToBackCmd bringToBackCmd = new BringToBackCmd(model, listOfSelectedShapes, currentIndex, currentIndex);
		bringToBackCmd.execute();

		commandList.add(bringToBackCmd);
		setCurrentIndex(0);

		logHandler.logAction("bringToBack", selectedShape);
		savedCommands.clear();
		logHandler.clearRedo();

		updateSizes();
		frame.repaint();
	}

	public void saveFile(String filePath) {
		SaveAndLoad fileSave = new ObjectFileSaveLoad(model);
		fileSave.saveFile(filePath);

		SaveAndLoad saveTxtFile = new TextFileSaveAndLoad(frame);
		saveTxtFile.saveFile(filePath);
	}

	public void loadFile(String filePath) {
		if (filePath.endsWith(".txt")) {
			stringArrayList = new ArrayList<String>();
			SaveAndLoad loadTxtFile = new TextFileSaveAndLoad(stringArrayList);
			loadTxtFile.loadFile(filePath);

			frame.getArea().setText("");
			model.getShapes().clear();
			listOfSelectedShapes.clear();
			commandList.clear();
			savedCommands.clear();

			setCurrentIndex(-1);
			setSelectedShape(null);
			counter = 0;

			updateSizes();
			frame.getBtnNextCommand().setVisible(true);
			frame.repaint();
		} else {
			SaveAndLoad fileLoad = new ObjectFileSaveLoad(model);
			fileLoad.loadFile(filePath);
			frame.repaint();
		}
	}

	public void loadCommand() {
		if (counter >= stringArrayList.size()) {
			counter = 0;
			frame.getBtnNextCommand().setVisible(false);
			JOptionPane.showMessageDialog(null, "No more commands are loaded!");
			return;
		}

		String lineForLog = stringArrayList.get(counter);
		int option = JOptionPane.showConfirmDialog(null, "Execute: " + lineForLog, "Confirmation",
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			String[] words = lineForLog.split(" ");
			int[] n = logParser.parseNumbers(lineForLog);

			switch (words[0]) {
			case "Added":
				Shape newS = logParser.createShape(words[1], n);
				executeNewCommand(new AddShapeCmd(newS, model));
				break;

			case "Modify":
				Shape updatedS = logParser.createShape(words[1], n);
				Command updateCmd = createUpdateCommand(selectedShape, updatedS);
				if (updateCmd != null)
					executeNewCommand(updateCmd);
				break;

			case "Deleted":
				executeDeletionFromLog();
				break;

			case "Undo":
				undo();
				break;
			case "Redo":
				redo();
				break;
			case "toFront":
				toFront();
				break;
			case "toBack":
				toBack();
				break;
			case "bringToFront":
				bringToFront();
				break;
			case "bringToBack":
				bringToBack();
				break;

			case "Selected":
			case "Deselected":
				handleLogSelection(words[1], n);
				break;
			}

			frame.getArea().append(lineForLog + "\n");
			undoCommandText.add(lineForLog + "\n");
			counter++;
			updateSizes();
			frame.repaint();
		}
	}

	private void executeDeletionFromLog() {
		for (int i = listOfSelectedShapes.size() - 1; i >= 0; i--) {
			Shape selectedShape = listOfSelectedShapes.get(i);
			int index = model.getShapes().indexOf(selectedShape);

			if (index != -1) {
				RemoveShapeCmd removeCmd = new RemoveShapeCmd(selectedShape, model, listOfSelectedShapes, index);
				removeCmd.execute();
				commandList.add(removeCmd);
			}
		}
	}

	private void handleLogSelection(String type, int[] n) {
		Shape shapeFromLog = logParser.createShape(type, n);

		if (shapeFromLog == null)
			return;

		for (Shape shapeInModel : model.getShapes()) {
			if (shapeInModel.equals(shapeFromLog)) {

				String lastLine = stringArrayList.get(counter);
				boolean isSelectedAction = lastLine.startsWith("Selected");

				SelectCmd selectCmd = new SelectCmd(shapeInModel, listOfSelectedShapes, this, isSelectedAction);
				selectCmd.execute();
				commandList.add(selectCmd);

				if (listOfSelectedShapes.size() == 1) {
					setSelectedShape(listOfSelectedShapes.get(0));
					setCurrentIndex(model.getShapes().indexOf(selectedShape));
				} else {
					setCurrentIndex(-1);
				}
				break;
			}
		}
	}

	// Pomoćna metoda
	private void executeNewCommand(Command cmd) {
		cmd.execute();
		commandList.add(cmd);
	}

	// Pomoćna metoda
	private void updateSizes() {
		setCommandListSize(commandList.size());
		setNumberOfShapes(model.getShapes().size());
		setSelectListSize(listOfSelectedShapes.size());
		setSavedCommandsSize(savedCommands.size());
	}

	public int getSelectListSize() {
		return selectListSize;
	}

	public void setSelectListSize(int selectListSize) {
		propertyChangeSupport.firePropertyChange("selectListSize", this.selectListSize, selectListSize);
		this.selectListSize = selectListSize;
	}

	public void setNumberOfShapes(int numberOfShapes) {
		propertyChangeSupport.firePropertyChange("numberOfShapes", this.numberOfShapes, numberOfShapes);
		this.numberOfShapes = numberOfShapes;
	}

	public void setSavedCommandsSize(int savedCommandsSize) {
		propertyChangeSupport.firePropertyChange("savedCommandsSize", this.savedCommandsSize, savedCommandsSize);
		this.savedCommandsSize = savedCommandsSize;
	}

	public void setCommandListSize(int commandListSize) {
		propertyChangeSupport.firePropertyChange("commandListSize", this.commandListSize, commandListSize);
		this.commandListSize = commandListSize;

	}

	public void setCurrentIndex(int currentIndex) {
		propertyChangeSupport.firePropertyChange("currentIndex", this.currentIndex, currentIndex);
		this.currentIndex = currentIndex;
	}

	public void addListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public ArrayList<Shape> getListOfSelectedShapes() {
		return listOfSelectedShapes;
	}

	public void setListOfSelectedShapes(ArrayList<Shape> listOfSelectedShapes) {
		this.listOfSelectedShapes = listOfSelectedShapes;
	}

	public int getCommandListSize() {
		return commandListSize;
	}

	public int getNumberOfShapes() {
		return numberOfShapes;
	}

	public boolean isSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(boolean selectFlag) {
		this.selectFlag = selectFlag;
	}

	public String getTextForSelect() {
		return textForSelect;
	}

	public void setTextForSelect(String textForSelect) {
		this.textForSelect = textForSelect;
	}

}
