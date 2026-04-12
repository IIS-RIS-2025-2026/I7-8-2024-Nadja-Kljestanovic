package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Scanner;
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
import gui.DlgCircle;
import gui.DlgDonut;
import gui.DlgHexagon;
import gui.DlgLine;
import gui.DlgPoint;
import gui.DlgRectangle;
import hexagon.Hexagon;
import observer.DeactivateButtons;
import observer.DeactivateRedo;
import observer.DeactivateUndo;
import strategy.ObjectFileSaveLoad;
import strategy.SaveAndLoad;
import strategy.TextFileSaveAndLoad;

public class DrawingController  {
	
	
	
	
	private DrawingModel model;
	private DrawingFrame frame;
	private DlgPoint dlgPoint;
	private DlgLine dlgLine;
	private DlgRectangle dlgRectangle;
	private DlgCircle dlgCircle;
	private DlgDonut dlgDonut;
	private DlgHexagon dlgHexagon;
	private String textForSelect;
	
	
	
	public String getTextForSelect() {
		return textForSelect;
	}
	

	public void setTextForSelect(String textForSelect) {
		this.textForSelect = textForSelect;
	}
	

	private Shape selectedShape;

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}


	private ArrayList<Shape> listOfSelectedShapes = new ArrayList<Shape>();
	private Stack<Command> savedCommands = new Stack<Command>();
	//private ArrayList<Shape> undoRedoShapeList = new ArrayList<Shape>();
	private Shape newShape = null;
	private Point startPoint;
	
	private ArrayList<Command> commandList = new ArrayList<Command>();
	private Command command;
	
    
    private Stack<String> undoCommandText = new Stack<String>();
    
    private Stack<String> redoCommandText = new Stack<String>();
    
    
    String appendText;
    
    String appendTextRedo;
    
    
    
    
    
    
    
   
    

	private PropertyChangeSupport propertyChangeSupport;
	
	private int selectListSize;
	
	
	private int commandListSize;
	
	
	private int savedCommandsSize;
	
	
	private int numberOfShapes;
	
	private int currentIndex;
	
	private int oldIndex;
	
	
	   
	
	private DeactivateButtons db;
	
	private DeactivateUndo deactivateUndo;
	
	private DeactivateRedo deactivateRedo;
	
	
	
	

	ArrayList<String> stringArrayList;
	
	Shape oldShape;
	

	
	int counter = 0;
	
	int[] numbers ;

	

	


	public int getSavedCommandsSize() {
		return savedCommandsSize;
	}

	

	public int getCommandListSize() {
		return commandListSize;
	}

	

	public DrawingController(DrawingModel model,DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		propertyChangeSupport = new PropertyChangeSupport(this);
		
		db = new DeactivateButtons(frame);
		deactivateUndo = new DeactivateUndo(frame);
		deactivateRedo = new DeactivateRedo(frame);
		
		addListener(db);
		addListener(deactivateUndo);
		addListener(deactivateRedo);
		
	}
	
	
	
	private boolean selectFlag;
	
	public void setSelectFlag(boolean selectFlag) {
		this.selectFlag = selectFlag;
	}
	
	public void mouseClicked(MouseEvent e) {
		
	
		
		
		
		if (frame.getSelectedButton() == "Selection") {
			
			
			
			
			for (int i = model.getShapes().size() - 1; i>=0;i--) {
				 if (model.getShapes().get(i).contains(e.getX(),e.getY()) == true) {
					 setSelectFlag(model.getShapes().get(i).contains(e.getX(),e.getY()));
					 Shape shape = model.getShapes().get(i);
					 SelectCmd select = new SelectCmd(shape,listOfSelectedShapes,this,selectFlag);
					
					 select.execute();
					 
					
					 
					 commandList.add(select);
						
						
					
						
						
						
					if (listOfSelectedShapes.size() == 1) {
							setSelectedShape(listOfSelectedShapes.get(0));
							setCurrentIndex(model.getShapes().indexOf(selectedShape));
							frame.getArea().append(textForSelect + "\n");
							undoCommandText.add(textForSelect + "\n");
							
									
					} else {
							setCurrentIndex(-1);
							frame.getArea().append(textForSelect + "\n");
							undoCommandText.add(textForSelect + "\n");
					}
					 
					
					 break;
				 }
			}
			
				
			
			setSelectListSize(listOfSelectedShapes.size());
			
					
					
					
				
					
					
				
					
				
					
					
		
			
			
		}
		
		
		else if (frame.getSelectedButton() == "Point") {
			
			   dlgPoint= new DlgPoint();
			   dlgPoint.setModal(true);
			   dlgPoint.getTxtX().setText(Integer.toString(e.getX()));
			   dlgPoint.getTxtY().setText(Integer.toString(e.getY()));
			   dlgPoint.getTxtX().setEnabled(false);
			   dlgPoint.getTxtY().setEnabled(false);
			   dlgPoint.getBtnColor().setBackground(frame.getBtnBorderColor().getBackground());
			   dlgPoint.setVisible(true);
			   
			  
			   if (dlgPoint.isCancelClicked() == true) {
				   newShape = null;
			   } else {
				   drawPoint(e);
			   }
				   
			   
			
		}
		else if (frame.getSelectedButton() == "Line") {
			 if (startPoint == null) {
				   startPoint = new Point(e.getX(),e.getY());
				   newShape = null;
			   }
			 else {
				 dlgLine = new DlgLine();
				 dlgLine.setModal(true);
				 dlgLine.getTxtX1().setText(Integer.toString(startPoint.getX()));
				 dlgLine.getTxtY1().setText(Integer.toString(startPoint.getY()));
				 dlgLine.getTxtX2().setText(Integer.toString(e.getX()));
				 dlgLine.getTxtY2().setText(Integer.toString(e.getY()));
				 dlgLine.getTxtX1().setEnabled(false);
				 dlgLine.getTxtY1().setEnabled(false);
				 dlgLine.getTxtX2().setEnabled(false);
				 dlgLine.getTxtY2().setEnabled(false);
				 dlgLine.getBtnColor().setBackground(frame.getBtnBorderColor().getBackground());
				 dlgLine.setVisible(true);
				 
				 if (dlgLine.isCancelClicked() == true) {
					 newShape = null;
					 startPoint = null;
				 } else {
					 drawLine(e);
					 
				 }
				 
			 }
			
			 
		}
		else if (frame.getSelectedButton() == "Rectangle") {
			dlgRectangle = new DlgRectangle();
			dlgRectangle.setModal(true);
			dlgRectangle.getTxtX().setText(Integer.toString(e.getX()));
			dlgRectangle.getTxtY().setText(Integer.toString(e.getY()));
			dlgRectangle.getTxtX().setEnabled(false);
			dlgRectangle.getTxtY().setEnabled(false);
			dlgRectangle.getTxtWidth().setText("0");
			dlgRectangle.getTxtHeight().setText("0");
			dlgRectangle.getBtnColor().setBackground(frame.getBtnBorderColor().getBackground());
			dlgRectangle.getBtnFill().setBackground(frame.getBtnFillColor().getBackground());
			dlgRectangle.setVisible(true);
			
			if (dlgRectangle.isCancelClicked() == true) {
				newShape = null;
			} else {
				drawRectangle(e);
			}
		}
		else if (frame.getSelectedButton() == "Circle") {
			dlgCircle = new DlgCircle();
			dlgCircle.setModal(true);
			dlgCircle.getTxtX().setText(Integer.toString(e.getX()));
			dlgCircle.getTxtY().setText(Integer.toString(e.getY()));
			dlgCircle.getTxtX().setEnabled(false);
			dlgCircle.getTxtY().setEnabled(false);
			dlgCircle.getTxtRadius().setText("0");
			dlgCircle.getBtnColor().setBackground(frame.getBtnBorderColor().getBackground());
			dlgCircle.getBtnFill().setBackground(frame.getBtnFillColor().getBackground());
			dlgCircle.setVisible(true);
			
			if (dlgCircle.isCancelClicked() == true) {
				newShape = null;
			} else {
				drawCircle(e);
			}
		}
		else if (frame.getSelectedButton() == "Donut") {
			dlgDonut = new DlgDonut();
			dlgDonut.setModal(true);
			dlgDonut.getTxtX().setText(Integer.toString(e.getX()));
			dlgDonut.getTxtY().setText(Integer.toString(e.getY()));
			dlgDonut.getTxtX().setEnabled(false);
			dlgDonut.getTxtY().setEnabled(false);
			dlgDonut.getTxtOuterRadius().setText("0");
			dlgDonut.getTxtInnerRadius().setText("0");
			dlgDonut.setBorderColor(frame.getBtnBorderColor().getBackground());
			dlgDonut.setFillColor(frame.getBtnFillColor().getBackground());
			dlgDonut.setVisible(true);
			if (dlgDonut.isCancelClicked() == true) {
				newShape = null;
			} else {
				drawDonut(e);
			}
		}
		
		else if (frame.getSelectedButton() == "Hexagon") {
			dlgHexagon = new DlgHexagon();
			dlgHexagon.setModal(true);
			dlgHexagon.getTxtX().setText(Integer.toString(e.getX()));
			dlgHexagon.getTxtY().setText(Integer.toString(e.getY()));
			dlgHexagon.getTxtX().setEnabled(false);
			dlgHexagon.getTxtY().setEnabled(false);
			dlgHexagon.getTxtR().setText("0");
			dlgHexagon.getBtnColor().setBackground(frame.getBtnBorderColor().getBackground());
			dlgHexagon.getBtnFill().setBackground(frame.getBtnFillColor().getBackground());
			dlgHexagon.setVisible(true);
			if (dlgHexagon.isCancelClicked() == true) {
				newShape = null;
			} else {
				drawHexagon(e);
			}
		}
		
		
		if (newShape != null && frame.getSelectedButton() != "Selection" ) {
			AddShapeCmd addShape = new AddShapeCmd(newShape,model);
			addShape.execute(); // ovamo mogu staviti AddShapeCmd
			commandList.add(addShape);
			
			
			
			setCommandListSize(commandList.size());
			setNumberOfShapes(model.getShapes().size());
			
		
			
			
			
			
			// dodaj setCurrentIndex
			
			if (newShape.getClass().getSimpleName().contentEquals("HexagonAdapter")) {
				frame.getArea().append("Added " + "Hexagon " + newShape + "\n");
				undoCommandText.add("Added " + "Hexagon " + newShape + "\n");
			} else {
				frame.getArea().append("Added " + newShape.getClass().getSimpleName() + " " + newShape + "\n");
				undoCommandText.add("Added " + newShape.getClass().getSimpleName() + " " + newShape + "\n");
			}
			
			savedCommands.clear();
			
		}
		
		setSavedCommandsSize(savedCommands.size());
		
		
		
		frame.repaint();
		
	}
	
	public void drawHexagon(MouseEvent e) {
		int R= Integer.parseInt(dlgHexagon.getTxtR().getText());
		Color borderCol = dlgHexagon.getBtnColor().getBackground();
		Color fillCol = dlgHexagon.getBtnFill().getBackground();
		Hexagon hexagon= new Hexagon(e.getX(),e.getY(),R);
		newShape = new HexagonAdapter(hexagon,false,borderCol,fillCol);
		
	}
	
	public void drawPoint(MouseEvent e) {
		
		newShape = new Point(e.getX(),e.getY(),false,dlgPoint.getBtnColor().getBackground());
		
	}
	public void drawLine(MouseEvent e) {
		Color borderCol = dlgLine.getBtnColor().getBackground();
		newShape = new Line(startPoint,new Point(e.getX(),e.getY()),false,borderCol);
		startPoint = null;
	}
	public void drawRectangle(MouseEvent e) {
		Color borderCol = dlgRectangle.getBtnColor().getBackground();
		Color fillCol = dlgRectangle.getBtnFill().getBackground();
		int width = Integer.parseInt(dlgRectangle.getTxtWidth().getText());
		int height = Integer.parseInt(dlgRectangle.getTxtWidth().getText());
		newShape = new Rectangle(new Point(e.getX(),e.getY()),width,height,false,borderCol,fillCol);
	}
	public void drawCircle(MouseEvent e) {
		Color borderCol = dlgCircle.getBtnColor().getBackground();
		Color fillCol = dlgCircle.getBtnFill().getBackground();
		int radius = Integer.parseInt(dlgCircle.getTxtRadius().getText());
		
		newShape = new Circle(new Point(e.getX(),e.getY()),radius,false,borderCol,fillCol);
	}
	public void drawDonut(MouseEvent e) {
		Color borderCol = dlgDonut.getBorderColor();
		Color fillCol = dlgDonut.getFillColor();
		int outerRadius = Integer.parseInt(dlgDonut.getTxtOuterRadius().getText());
		int innerRadius = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
		newShape = new Donut(new Point(e.getX(),e.getY()),outerRadius,innerRadius,false,borderCol,fillCol);
		
	}
	
	public Shape getSelectedShape() {
		return selectedShape;
	}
	
	public void delete() {
		
		
		if (listOfSelectedShapes.size() > 0) {
			int option =JOptionPane.showConfirmDialog(null, "Do you want to delete this shape?","Confirmation dialogue",JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				
				
				for (int i = model.getShapes().size()-1; i >= 0;i--) {
					Shape shape = model.getShapes().get(i);
					for (int j = 0; j < listOfSelectedShapes.size(); j++) {
						Shape selectedShape = listOfSelectedShapes.get(j);
						if (selectedShape.equals(shape)) {
							
							int index = model.getShapes().indexOf(shape);
							 RemoveShapeCmd removeShape = new RemoveShapeCmd(selectedShape,model,listOfSelectedShapes,index);
							 removeShape.execute(); 
							 commandList.add(removeShape);
							 
							 
							 if (selectedShape instanceof HexagonAdapter) {
								 frame.getArea().append("Deleted " + "Hexagon " + selectedShape + "\n");
								 undoCommandText.add("Deleted " +  "Hexagon  " + selectedShape + "\n");
							 } else {
								 frame.getArea().append("Deleted " + selectedShape.getClass().getSimpleName() + " " + selectedShape + "\n");
								 undoCommandText.add("Deleted " + selectedShape.getClass().getSimpleName() + " " + selectedShape + "\n");
							 }
							
							
						}
						
					}
					
					
				}
				
			
				setNumberOfShapes(model.getShapes().size());
				
				
				setCurrentIndex(-1);
				setSelectListSize(listOfSelectedShapes.size());
				
				savedCommands.clear();
				
				setSavedCommandsSize(savedCommands.size());
				
				
				
			}
			
		}
			
		else {
			
				JOptionPane.showMessageDialog(null,"Nothing is selected", "Error message", JOptionPane.ERROR_MESSAGE);
			
		}
		
		frame.repaint();
	
		
		
	}
	
	public void modify() {
		
		Shape updatedShape;
		
		
		if (listOfSelectedShapes.size() == 1) {
			selectedShape = listOfSelectedShapes.get(0);
			if (selectedShape instanceof Point) {
				Point point = (Point) selectedShape;
				dlgPoint = new DlgPoint();		
				
				int x = point.getX();
				int y = point.getY();
				dlgPoint.getTxtX().setText(Integer.toString(x));
				dlgPoint.getTxtY().setText(Integer.toString(y));
				dlgPoint.getBtnColor().setBackground(((Point) selectedShape).getBorderColor());
				
				
			
				dlgPoint.setModal(true);
				dlgPoint.setVisible(true);
				
				int xNew = Integer.parseInt(dlgPoint.getTxtX().getText());
				int yNew = Integer.parseInt(dlgPoint.getTxtY().getText());
				Color newBorderColor = dlgPoint.getBtnColor().getBackground();
				
				if (dlgPoint.isCancelClicked()== true) {
					
					dlgPoint.setVisible(false);
				}
				else {

				
				
				Point newPoint= new Point(xNew,yNew,true,newBorderColor);
			
				UpdatePointCmd updatePoint = new UpdatePointCmd(point,newPoint);
				
				updatePoint.execute();
				
				updatedShape = newPoint;
				
				
				
				
				commandList.add(updatePoint);
				setCommandListSize(commandList.size());
				
			frame.repaint();
				}
				
				
				
			}
			else if (selectedShape instanceof Line) {
				Line line = (Line) selectedShape;
				dlgLine = new DlgLine();
				
				
				dlgLine.getTxtX1().setText(Integer.toString(line.getStartPoint().getX()));
				dlgLine.getTxtY1().setText(Integer.toString(line.getStartPoint().getY()));
				dlgLine.getTxtX2().setText(Integer.toString(line.getEndPoint().getX()));
				dlgLine.getTxtY2().setText(Integer.toString(line.getEndPoint().getY()));
				dlgLine.getBtnColor().setBackground(((Line) selectedShape).getBorderColor());
				
				
				
				dlgLine.setModal(true);
				dlgLine.setVisible(true);
				
				int x1New = Integer.parseInt(dlgLine.getTxtX1().getText());
				int y1New = Integer.parseInt(dlgLine.getTxtY1().getText());
				int x2New = Integer.parseInt(dlgLine.getTxtX2().getText());
				int y2New = Integer.parseInt(dlgLine.getTxtY2().getText());
				Color newBorderColor = dlgLine.getBtnColor().getBackground();
				
				Point newPoint1 = new Point(x1New,y1New,false);
				Point newPoint2 = new Point(x2New,y2New,false);
				if (dlgLine.isCancelClicked()== true) {
					
					dlgLine.setVisible(false);
				}
				else {
				
				
					Line newLine = new Line(newPoint1,newPoint2,true,newBorderColor);
					UpdateLineCmd updateLine = new UpdateLineCmd(line,newLine);
					updateLine.execute();
					
					updatedShape = newLine;
					
					commandList.add(updateLine);
					setCommandListSize(commandList.size());
					
					
					
				
				}
			}
			else if (selectedShape instanceof Rectangle) {
				Rectangle rectangle = (Rectangle) selectedShape;
				
				DlgRectangle dlg = new DlgRectangle();
				
				dlg.getTxtX().setText(Integer.toString(rectangle.getUpperLeftPoint().getX()));
				dlg.getTxtY().setText(Integer.toString(rectangle.getUpperLeftPoint().getY()));
				dlg.getTxtWidth().setText(Integer.toString(rectangle.getWidth()));
				dlg.getTxtHeight().setText(Integer.toString(rectangle.getHeight()));
				dlg.getBtnColor().setBackground(((Rectangle) selectedShape).getBorderColor());
				dlg.getBtnFill().setBackground(((Rectangle) selectedShape).getFillColor());
                
				dlg.setModal(true);
				dlg.setVisible(true);
				if (dlg.isCancelClicked()== true) {
					
					dlg.setVisible(false);
				}
				
				else  {
					
					int xNew = Integer.parseInt(dlg.getTxtX().getText());
					int yNew = Integer.parseInt(dlg.getTxtY().getText());
					int width= Integer.parseInt(dlg.getTxtWidth().getText());
				    int height = Integer.parseInt(dlg.getTxtHeight().getText());
				    Color c1New=dlg.getBtnColor().getBackground();
				    Color c2New=dlg.getBtnFill().getBackground();
				    
				  //  rectangle.getUpperLeftPoint().moveTo(xNew, yNew);
					Rectangle newRectangle = new Rectangle(new Point(xNew,yNew),width,height,true,c1New,c2New);
					UpdateRectangleCmd updateRectangle = new UpdateRectangleCmd(rectangle,newRectangle);
					updateRectangle.execute();
					
					updatedShape = newRectangle;
					commandList.add(updateRectangle);
					setCommandListSize(commandList.size());
								
				}		
				
			}
			else if (selectedShape instanceof Circle) {
				Circle circle = (Circle) selectedShape;
				DlgCircle dlg= new DlgCircle();
				
				dlg.getTxtX().setText(Integer.toString(circle.getCenter().getX()));
				dlg.getTxtY().setText(Integer.toString(circle.getCenter().getY()));
				dlg.getTxtRadius().setText(Integer.toString(circle.getRadius()));
				dlg.getBtnColor().setBackground(((Circle) selectedShape).getBorderColor());
				dlg.getBtnFill().setBackground(((Circle) selectedShape).getFillColor());
				
				dlg.setModal(true);
				dlg.setVisible(true);
				if (dlg.isCancelClicked()== true) {
					
					dlg.setVisible(false);
				}
				else {
					
					int xNew = Integer.parseInt(dlg.getTxtX().getText());
					int yNew = Integer.parseInt(dlg.getTxtY().getText());
					int radius = Integer.parseInt(dlg.getTxtRadius().getText());
					Color c1New = dlg.getBtnColor().getBackground();
					Color c2New = dlg.getBtnFill().getBackground();
				
					
					
					Circle newCircle = new Circle(new Point(xNew,yNew),radius,true,c1New,c2New);
					UpdateCircleCmd updateCircle = new UpdateCircleCmd(circle,newCircle);
					updateCircle.execute();
					
					updatedShape = newCircle;
					
					commandList.add(updateCircle);
					setCommandListSize(commandList.size());
					
					
				
					
					
				
				
				}
			}
			else if (selectedShape instanceof Donut) {
				Donut donut = (Donut)selectedShape;
				DlgDonut dlg = new DlgDonut();
				
				dlg.getTxtX().setText(Integer.toString(donut.getCenter().getX()));
				dlg.getTxtY().setText(Integer.toString(donut.getCenter().getY()));
				dlg.getTxtOuterRadius().setText(Integer.toString(donut.getRadius()));
				dlg.getTxtInnerRadius().setText(Integer.toString(donut.getInnerRadius()));
				dlg.setBorderColor(((Donut) selectedShape).getBorderColor());
				dlg.setFillColor(((Donut) selectedShape).getFillColor());
				
				
				
				dlg.setModal(true);
				dlg.setVisible(true);
				if (dlg.isCancelClicked()== true) {
					
					dlg.setVisible(false);
				}
				else {
				
					
					int xNew = Integer.parseInt(dlg.getTxtX().getText());
					int yNew = Integer.parseInt(dlg.getTxtY().getText());
					int outerRadius = Integer.parseInt(dlg.getTxtOuterRadius().getText());
					int innerRadius = Integer.parseInt(dlg.getTxtInnerRadius().getText());
					Color c1New = dlg.getBorderColor();
					Color c2New = dlg.getFillColor();	
					
				
					
					Point newCenter = new Point(xNew,yNew);
					Donut newDonut = new Donut(newCenter, outerRadius,innerRadius, true,c1New,c2New);
					UpdateDonutCmd updateDonut = new UpdateDonutCmd(donut,newDonut);
					updateDonut.execute();
					
					updatedShape = newDonut;
					commandList.add(updateDonut);
					setCommandListSize(commandList.size());
					
				
				
				}
			}
			
			else if (selectedShape instanceof HexagonAdapter) {
				HexagonAdapter hexagonAdapter = (HexagonAdapter)selectedShape;
				DlgHexagon dlg = new DlgHexagon();
				
				dlg.getTxtX().setText(Integer.toString(hexagonAdapter.getHexagon().getX()));
				dlg.getTxtY().setText(Integer.toString(hexagonAdapter.getHexagon().getY()));
				dlg.getTxtR().setText(Integer.toString(hexagonAdapter.getHexagon().getR()));
				dlg.getBtnColor().setBackground(((HexagonAdapter) selectedShape).getBorderColor());
				dlg.getBtnFill().setBackground(((HexagonAdapter) selectedShape).getFillColor());
				
				
				
				dlg.setModal(true);
				dlg.setVisible(true);
				if (dlg.isCancelClicked()== true) {
					
					dlg.setVisible(false);
				}
				else {
				
					
					int xNew = Integer.parseInt(dlg.getTxtX().getText());
					int yNew = Integer.parseInt(dlg.getTxtY().getText());
					int rNew = Integer.parseInt(dlg.getTxtR().getText());
				
					Color c1New = dlg.getBtnColor().getBackground();
					Color c2New = dlg.getBtnFill().getBackground();	
					
				//	donut.getCenter().moveTo(xNew,yNew);
					
					Hexagon hexNew = new Hexagon(xNew, yNew,rNew);
					HexagonAdapter newHexagonAdapter = new HexagonAdapter(hexNew, true,c1New,c2New);
					UpdateHexagonCmd updateHexagon = new UpdateHexagonCmd(hexagonAdapter,newHexagonAdapter);
					updateHexagon.execute();
					
					updatedShape = newHexagonAdapter;
					commandList.add(updateHexagon);
					setCommandListSize(commandList.size());
				
				
				}
			}
			
			
			if (selectedShape instanceof HexagonAdapter) {
				
				
				frame.getArea().append("Modify Hexagon " + selectedShape + "\n");
				undoCommandText.add("Modify Hexagon " + selectedShape + "\n");
			} else {
				frame.getArea().append("Modify " + selectedShape.getClass().getSimpleName() + " " + selectedShape + "\n" );
				undoCommandText.add("Modify " + selectedShape.getClass().getSimpleName() + " " + selectedShape + "\n");
			}
			
			
			savedCommands.clear();
			
		
			setSavedCommandsSize(savedCommands.size());
		}
		else {
			JOptionPane.showMessageDialog(null,"Nothing is selected", "Error message", JOptionPane.ERROR_MESSAGE);
		}
		
				frame.repaint();
	}
	
	public void undo() {
		
		
			
			
		
		command = commandList.get(commandList.size() - 1);
		
		if (command instanceof SelectCmd) {
			
		
			
			
			
			
			UndoCmd undoCommand = new UndoCmd(command,commandList,savedCommands);
			undoCommand.execute();
			
			
			if (listOfSelectedShapes.size() == 1) {
				for (int i = 0 ; i < model.getShapes().size(); i++) {
					if (listOfSelectedShapes.get(0).equals(model.getShapes().get(i))) {
						setCurrentIndex(i);
						setNumberOfShapes(model.getShapes().size());
						
					}
				}
			} else {
				setCurrentIndex(-1);
			}
			setSelectListSize(listOfSelectedShapes.size());
			
			
			frame.repaint();
			
		
			
			
			
			
		}
		if (command instanceof AddShapeCmd) {
			
			
			
			UndoCmd undoCommand = new UndoCmd(command,commandList,savedCommands);
			undoCommand.execute();
			
			
			setNumberOfShapes(model.getShapes().size());
			if (listOfSelectedShapes.size() == 1) {
				for (int i = 0 ; i < model.getShapes().size(); i++) {
					if (listOfSelectedShapes.get(0).equals(model.getShapes().get(i))) {
						setCurrentIndex(i);
						
						
					}
				}
			} else {
				setCurrentIndex(-1);
			}
			
			
			frame.repaint();
			
			
		}
		if (command instanceof RemoveShapeCmd) {
			
			
			
			UndoCmd undoCommand = new UndoCmd(command,commandList,savedCommands);
			undoCommand.execute();
			
			
			setNumberOfShapes(model.getShapes().size());
			//setSavedCommandsSize(savedCommands.size());
			
			if (listOfSelectedShapes.size() == 1) {
				for (int i = 0 ; i < model.getShapes().size(); i++) {
					if (listOfSelectedShapes.get(0).equals(model.getShapes().get(i))) {
						setCurrentIndex(i);
						setNumberOfShapes(model.getShapes().size());
						
					}
				}
			} else {
				setCurrentIndex(-1);
			}
			
			setSelectListSize(listOfSelectedShapes.size());
			

			
			frame.repaint();
			
			
		}
		if (command instanceof UpdatePointCmd) {
			
			
			
			UndoCmd undoCommand = new UndoCmd(command,commandList,savedCommands);
			undoCommand.execute();
			
			frame.repaint();
			
			
			
		}
		
		if (command instanceof UpdateLineCmd) {
			
			
			
			UndoCmd undoCommand = new UndoCmd(command,commandList,savedCommands);
			undoCommand.execute();
			
			frame.repaint();
			
			
		}
		
		if (command instanceof UpdateRectangleCmd) {
			
			
			
			UndoCmd undoCommand = new UndoCmd(command,commandList,savedCommands);
			undoCommand.execute();
			
			frame.repaint();
			
			
		}
		
		if (command instanceof UpdateCircleCmd) {
			
			
			
			UndoCmd undoCommand = new UndoCmd(command,commandList,savedCommands);
			undoCommand.execute();
			
			frame.repaint();
			
			
			
		}
		if (command instanceof UpdateDonutCmd) {
			
			
			
			UndoCmd undoCommand = new UndoCmd(command,commandList,savedCommands);
			undoCommand.execute();
			
			frame.repaint();
			
			
		}
		
		if (command instanceof UpdateHexagonCmd) {
			
		
			
			UndoCmd undoCommand = new UndoCmd(command,commandList,savedCommands);
			undoCommand.execute();
			
			
			frame.repaint();
			
			
		}
		if (command instanceof ToFrontCmd) {
			ToFrontCmd cmd = (ToFrontCmd) command;
			
			cmd.setCurrentIndexCmd(currentIndex);
			
			UndoCmd undoCommand = new UndoCmd(cmd,commandList,savedCommands);
			undoCommand.execute();
			
			setCurrentIndex(currentIndex - 1);
			
			
			frame.repaint();
			
			
			
		}
		if (command instanceof ToBackCmd) {
			ToBackCmd cmd = (ToBackCmd) command;
			
			cmd.setCurrentIndexCmd(currentIndex);
			
			
			UndoCmd undoCommand = new UndoCmd(cmd,commandList,savedCommands);
			undoCommand.execute();
			
			setCurrentIndex(currentIndex + 1);
			
			
			frame.repaint();
			
			
		}
		
		if (command instanceof BringToFrontCmd) {
			BringToFrontCmd cmd = (BringToFrontCmd) command;
			
			cmd.setCurrentIndexCmd(currentIndex);
		
			
			UndoCmd undoCommand = new UndoCmd(cmd,commandList,savedCommands);
			undoCommand.execute();
			
			
			setCurrentIndex(cmd.getOldIndex());
			
			
			frame.repaint();
			
			
			
		}
		if (command instanceof BringToBackCmd) {
			BringToBackCmd cmd = (BringToBackCmd) command;
			
			cmd.setCurrentIndexCmd(currentIndex);
			
			
			UndoCmd undoCommand = new UndoCmd(cmd,commandList,savedCommands);
			undoCommand.execute();
			
			setCurrentIndex(cmd.getOldIndex());
			
			frame.repaint();
			
			
			
		}
		
		
		
		setSelectListSize(listOfSelectedShapes.size());
		setCommandListSize(commandList.size());
		setSavedCommandsSize(savedCommands.size());
		
		if (listOfSelectedShapes.size() == 1) {
			setSelectedShape(listOfSelectedShapes.get(0));
		}
		
		appendText = undoCommandText.pop();
		
		frame.getArea().append("Undo " + appendText);
		
		redoCommandText.add(appendText);
		
		
		
	}
	
public void redo() {
		
	
	
		
		
		command = savedCommands.peek();
		
		if (command instanceof SelectCmd) {
			
	
			
			RedoCmd redoCommand = new RedoCmd(command,commandList,savedCommands);
			redoCommand.execute();
			
			
			if (listOfSelectedShapes.size() == 1) {
				for (int i = 0 ; i < model.getShapes().size(); i++) {
					if (listOfSelectedShapes.get(0).equals(model.getShapes().get(i))) {
						setCurrentIndex(i);
						setNumberOfShapes(model.getShapes().size());
						
					}
				}
			}
			setSelectListSize(listOfSelectedShapes.size());
			
			
			frame.repaint();
			
			
		}
		if (command instanceof AddShapeCmd) {
			
			
			
			RedoCmd redoCommand = new RedoCmd(command,commandList,savedCommands);
			redoCommand.execute();
			
			setNumberOfShapes(model.getShapes().size());
			frame.repaint();
			
			
		}
		if (command instanceof RemoveShapeCmd) {
			
			
			
			RedoCmd redoCommand = new RedoCmd(command,commandList,savedCommands);
			redoCommand.execute();
			
			setNumberOfShapes(model.getShapes().size());
			setSelectListSize(listOfSelectedShapes.size());
			frame.repaint();
			
			
		}
		if (command instanceof UpdatePointCmd) {
			
			
			
			RedoCmd redoCommand = new RedoCmd(command,commandList,savedCommands);
			redoCommand.execute();
			
			frame.repaint();
			
		
			
		}
		
		if (command instanceof UpdateLineCmd) {
			
			
			
			RedoCmd redoCommand = new RedoCmd(command,commandList,savedCommands);
			redoCommand.execute();
			
			frame.repaint();
			
			
			
		}
		
		if (command instanceof UpdateRectangleCmd) {
			
			
			
			RedoCmd redoCommand = new RedoCmd(command,commandList,savedCommands);
			redoCommand.execute();
			
			frame.repaint();
			
			
			
		}
		
		if (command instanceof UpdateCircleCmd) {
			
			
			
			RedoCmd redoCommand = new RedoCmd(command,commandList,savedCommands);
			redoCommand.execute();
			
			frame.repaint();
			
			
			
		}
		if (command instanceof UpdateDonutCmd) {
			
			
			
			RedoCmd redoCommand = new RedoCmd(command,commandList,savedCommands);
			redoCommand.execute();
			
			frame.repaint();
			
			
			
		}
		if (command instanceof UpdateHexagonCmd) {
			
			
			
		
			
			RedoCmd redoCommand = new RedoCmd(command,commandList,savedCommands);
			redoCommand.execute();
			
			frame.repaint();
			
			
			
		}
		
		if (command instanceof ToFrontCmd) {
			ToFrontCmd cmd = (ToFrontCmd) command;
			
			
			cmd.setCurrentIndexCmd(currentIndex);
			
			
			RedoCmd redoCommand = new RedoCmd(cmd,commandList,savedCommands);
			redoCommand.execute();
			
			setCurrentIndex(currentIndex + 1);
			
			frame.repaint();
			
			
			
		}
		if (command instanceof ToBackCmd) {
			ToBackCmd cmd = (ToBackCmd) command;
			
			cmd.setCurrentIndexCmd(currentIndex);
			
			
			RedoCmd redoCommand  = new RedoCmd(cmd,commandList,savedCommands);
			redoCommand.execute();
			
			setCurrentIndex(currentIndex - 1);
			
			frame.repaint();
			
			
			
		}
		
		if (command instanceof BringToFrontCmd) {
			BringToFrontCmd cmd = (BringToFrontCmd) command;
			
			cmd.setCurrentIndexCmd(currentIndex);
			
			
			RedoCmd redoCommand  = new RedoCmd(cmd,commandList,savedCommands);
			redoCommand.execute();
			
			setCurrentIndex(model.getShapes().size() - 1);
			
			frame.repaint();
			
			
			
		}
		if (command instanceof BringToBackCmd) {
			BringToBackCmd cmd = (BringToBackCmd) command;
			
			
		   
			
		    cmd.setCurrentIndexCmd(currentIndex);
		    
			RedoCmd redoCommand  = new RedoCmd(cmd,commandList,savedCommands);
			
			redoCommand.execute();
			
			
			
			setCurrentIndex(0);
			
			frame.repaint();
			
			
			
		}
		
		setSelectListSize(listOfSelectedShapes.size());
		setCommandListSize(commandList.size());
		setSavedCommandsSize(savedCommands.size());
		
		
		appendTextRedo = redoCommandText.pop();
		frame.getArea().append("Redo " +  appendTextRedo);
		undoCommandText.add(appendTextRedo);
		
	}


public ArrayList<Shape> getListOfSelectedShapes() {
	return listOfSelectedShapes;
}

public void toFront() {
	
			
	oldIndex = currentIndex;
			
	ToFrontCmd frontCmd= new ToFrontCmd(model,listOfSelectedShapes,currentIndex);
	
		frontCmd.execute();
		
		commandList.add(frontCmd);
		
		setCurrentIndex(currentIndex + 1);
		
		
		setCommandListSize(commandList.size());
		if (selectedShape instanceof HexagonAdapter) {
			frame.getArea().append("toFront " +  " Hexagon " + selectedShape  + "\n");
			this.undoCommandText.add("toFront " +  " Hexagon " + selectedShape  + "\n");
			
		} else {
			frame.getArea().append("toFront " + selectedShape.getClass().getSimpleName() + " " + selectedShape  + "\n");
			this.undoCommandText.add("toFront " + selectedShape.getClass().getSimpleName() + " " + selectedShape  + "\n");
		}
		
		
		
	
	
	savedCommands.clear();
	
	
	setSavedCommandsSize(savedCommands.size());
	
	
	
	frame.repaint();
	
	
}

public void toBack() {
	oldIndex = currentIndex;
	ToBackCmd backCmd= new ToBackCmd(model,listOfSelectedShapes,currentIndex);
	
	
		backCmd.execute();
		
		commandList.add(backCmd);
		
		setCurrentIndex(currentIndex - 1);
		
		
		setCommandListSize(commandList.size());
		
		
		
		if (selectedShape instanceof HexagonAdapter) {
			frame.getArea().append("toBack " +  " Hexagon " + selectedShape  + "\n");
			this.undoCommandText.add("toBack " +  " Hexagon " + selectedShape  + "\n");
		} else {
			frame.getArea().append("toBack " + selectedShape.getClass().getSimpleName() + " " + selectedShape  + "\n");
			this.undoCommandText.add("toBack " +  selectedShape.getClass().getSimpleName() + " " + selectedShape  + "\n");
		}
	
	
	savedCommands.clear();
	
	
	setSavedCommandsSize(savedCommands.size());
	
	
	
	frame.repaint();
	
}

public void bringToFront() {
	oldIndex = currentIndex;
	BringToFrontCmd bringToFrontCmd= new BringToFrontCmd(model,listOfSelectedShapes,oldIndex,currentIndex);
	
	
		bringToFrontCmd.execute();
		
		
		commandList.add(bringToFrontCmd);
		
		setCurrentIndex(model.getShapes().size()-1);
		
		
		setCommandListSize(commandList.size());
		
		
		if (selectedShape instanceof HexagonAdapter) {
			frame.getArea().append("bringToFront " +  " Hexagon " + selectedShape  + "\n");
			this.undoCommandText.add("bringToFront " +  " Hexagon " + selectedShape  + "\n");
		} else {
			frame.getArea().append("bringToFront " + selectedShape.getClass().getSimpleName() + " " + selectedShape  + "\n");
			this.undoCommandText.add("bringToFront " + selectedShape.getClass().getSimpleName() + " " + selectedShape  + "\n");
		}
	
	
	
	savedCommands.clear();
	
	
	setSavedCommandsSize(savedCommands.size());


	
	
	
	
	
	
	
	frame.repaint();
}

public void bringtoBack() {
	
	oldIndex = currentIndex;
	BringToBackCmd bringToBackCmd= new BringToBackCmd(model,listOfSelectedShapes,oldIndex,currentIndex);
	
	
	bringToBackCmd.execute();
		
	commandList.add(bringToBackCmd);
		
	setCurrentIndex(0);
		
	setCommandListSize(commandList.size());
		
	if (selectedShape instanceof HexagonAdapter) {
			frame.getArea().append("bringToBack " +  " Hexagon " + selectedShape  + "\n");
			this.undoCommandText.add("bringToBack " +  " Hexagon " + selectedShape  + "\n");
	} else {
			frame.getArea().append("bringToBack " + selectedShape.getClass().getSimpleName() + " " + selectedShape  + "\n");
			this.undoCommandText.add("bringToBack " + selectedShape.getClass().getSimpleName()  + " " + selectedShape  + "\n");
	}
	
	
	
	
		savedCommands.clear();
		
		
		setSavedCommandsSize(savedCommands.size());

	
	
	

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
		
		
		listOfSelectedShapes.clear();
		model.getShapes().clear();
		commandList.clear();
		savedCommands.clear();
		this.setCurrentIndex(-1);
		this.setNumberOfShapes(model.getShapes().size());
		this.setSelectedShape(null);
		this.setCommandListSize(commandList.size());
		this.setSavedCommandsSize(savedCommands.size());
		this.setSelectListSize(listOfSelectedShapes.size());
		
		
		
		
		
			
			
		frame.getBtnNextCommand().setVisible(true);
		
		frame.repaint();
		
			
			
			
		
		
	} else {
		SaveAndLoad fileLoad = new ObjectFileSaveLoad(model);
		fileLoad.loadFile(filePath);
		frame.repaint();
	}
	
	
}

public void loadCommand() {
	
	if (counter == stringArrayList.size()) {
		counter = 0;
		frame.getBtnNextCommand().setVisible(false);
		JOptionPane.showMessageDialog(null, "No more commands are loaded!");
		return;
	}
	
	
		numbers = new int[10];
	
	
		String lineForLog = stringArrayList.get(counter);
		
		int option =JOptionPane.showConfirmDialog(null, "Do you want to execute the following command: " + lineForLog,"Confirmation dialogue",JOptionPane.YES_NO_OPTION);
		
		
		if (option == JOptionPane.YES_OPTION) {
			String[] line = stringArrayList.get(counter).split(" ");
			Scanner scanner = new Scanner(stringArrayList.get(counter));
			for (int j = 0; j <= 9; j++) {

				if (j == 0) {
					numbers[j] = scanner.useDelimiter("[^\\d]+").nextInt();
				} else {
					if (scanner.hasNextInt() == true) {
						numbers[j] = scanner.useDelimiter("[^\\d]+").nextInt();
					}
				}

			}
			scanner.close();
			if (line[0].equals("Added")) {

				if (line[1].contentEquals("Point")) {
					int x = numbers[0];
					int y = numbers[1];
					Color borderColor = new Color(numbers[2], numbers[3], numbers[4]);

					Point point = new Point(x, y, false, borderColor);

					oldShape = point;

					AddShapeCmd addNewShape = new AddShapeCmd(point, model);
					addNewShape.execute();
					commandList.add(addNewShape);

					frame.repaint();

				}

				if (line[1].contentEquals("Line")) {

					int x1 = numbers[0];
					int y1 = numbers[1];

					int x2 = numbers[2];
					int y2 = numbers[3];
					Color borderColor = new Color(numbers[4], numbers[5], numbers[6]);

					Point startPoint = new Point(x1, y1, false);
					Point endPoint = new Point(x2, y2, false);

					Line lineShape = new Line(startPoint, endPoint, false, borderColor);

					oldShape = lineShape;

					AddShapeCmd addNewShape = new AddShapeCmd(lineShape, model);
					addNewShape.execute();
					commandList.add(addNewShape);

					frame.repaint();

				}

				if (line[1].contentEquals("Rectangle")) {

					int x = numbers[0];
					int y = numbers[1];
					int width = numbers[2];
					int height = numbers[3];
					Color borderColor = new Color(numbers[4], numbers[5], numbers[6]);
					Color fillColor = new Color(numbers[7], numbers[8], numbers[9]);

					Point upperLeftPoint = new Point(x, y);
					Rectangle rectangle = new Rectangle(upperLeftPoint, width, height, false, borderColor, fillColor);

					oldShape = rectangle;

					AddShapeCmd addNewShape = new AddShapeCmd(rectangle, model);
					addNewShape.execute();
					commandList.add(addNewShape);

					frame.repaint();

					

				}

				if (line[1].contentEquals("Circle")) {

					int x = numbers[0];
					int y = numbers[1];
					int radius = numbers[2];
					Color borderColor = new Color(numbers[3], numbers[4], numbers[5]);
					Color fillColor = new Color(numbers[6], numbers[7], numbers[8]);
					Point center = new Point(x, y);

					Circle circle = new Circle(center, radius, false, borderColor, fillColor);

					oldShape = circle;

					AddShapeCmd addNewShape = new AddShapeCmd(circle, model);
					addNewShape.execute();
					commandList.add(addNewShape);

					frame.repaint();

				}

				if (line[1].contentEquals("Donut")) {

					int x = numbers[0];
					int y = numbers[1];
					int outerRadius = numbers[2];
					int innerRadius = numbers[3];

					Color borderColor = new Color(numbers[4], numbers[5], numbers[6]);
					Color fillColor = new Color(numbers[7], numbers[8], numbers[9]);
					Point point = new Point(x, y);

					Donut donut = new Donut(point, outerRadius, innerRadius, false, borderColor, fillColor);

					oldShape = donut;

					AddShapeCmd addNewShape = new AddShapeCmd(donut, model);
					addNewShape.execute();
					commandList.add(addNewShape);

					frame.repaint();

				}

				if (line[1].contentEquals("Hexagon")) {

					int x = numbers[0];
					int y = numbers[1];
					int radius = numbers[2];

					Color borderColor = new Color(numbers[3], numbers[4], numbers[5]);
					Color fillColor = new Color(numbers[6], numbers[7], numbers[8]);

					Hexagon hexagon = new Hexagon(x, y, radius);

					HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon, false, borderColor, fillColor);

					oldShape = hexagonAdapter;

					AddShapeCmd addNewShape = new AddShapeCmd(hexagonAdapter, model);
					addNewShape.execute();
					commandList.add(addNewShape);

					frame.repaint();

				}

				frame.getArea().append(lineForLog + "\n");
				undoCommandText.add(lineForLog + "\n");

				setCommandListSize(commandList.size());
				setNumberOfShapes(model.getShapes().size());

			} else if (line[0].contentEquals("Modify")) {

				if (line[1].contentEquals("Point")) {

					int x = numbers[0];
					int y = numbers[1];
					Color borderColor = new Color(numbers[2], numbers[3], numbers[4]);

					Point point = new Point(x, y, false, borderColor);

					

					UpdatePointCmd updatePoint = new UpdatePointCmd((Point) selectedShape, point);

					updatePoint.execute();
					commandList.add(updatePoint);

					frame.repaint();

				}
				if (line[1].contentEquals("Line")) {

					int x1 = numbers[0];
					int y1 = numbers[1];

					int x2 = numbers[2];
					int y2 = numbers[3];
					Color borderColor = new Color(numbers[4], numbers[5], numbers[6]);

					Point startPoint = new Point(x1, y1, false);
					Point endPoint = new Point(x2, y2, false);

					Line lineShape = new Line(startPoint, endPoint, false, borderColor);

					UpdateLineCmd updateLine = new UpdateLineCmd((Line) selectedShape, lineShape);

					updateLine.execute();
					commandList.add(updateLine);

					frame.repaint();

				}
				if (line[1].contentEquals("Rectangle")) {

					int x = numbers[0];
					int y = numbers[1];
					int width = numbers[2];
					int height = numbers[3];
					Color borderColor = new Color(numbers[4], numbers[5], numbers[6]);
					Color fillColor = new Color(numbers[7], numbers[8], numbers[9]);

					Point upperLeftPoint = new Point(x, y);
					Rectangle rectangle = new Rectangle(upperLeftPoint, width, height, false, borderColor, fillColor);

					UpdateRectangleCmd updateRectangle = new UpdateRectangleCmd((Rectangle) selectedShape, rectangle);

					updateRectangle.execute();
					commandList.add(updateRectangle);

					frame.repaint();

				}
				if (line[1].contentEquals("Circle")) {

					int x = numbers[0];
					int y = numbers[1];
					int radius = numbers[2];
					Color borderColor = new Color(numbers[3], numbers[4], numbers[5]);
					Color fillColor = new Color(numbers[6], numbers[7], numbers[8]);
					Point center = new Point(x, y);

					Circle circle = new Circle(center, radius, false, borderColor, fillColor);

					UpdateCircleCmd updateCircle = new UpdateCircleCmd((Circle) selectedShape, circle);

					updateCircle.execute();
					commandList.add(updateCircle);

					frame.repaint();

				}
				if (line[1].contentEquals("Donut")) {

					int x = numbers[0];
					int y = numbers[1];
					int outerRadius = numbers[2];
					int innerRadius = numbers[3];

					Color borderColor = new Color(numbers[4], numbers[5], numbers[6]);
					Color fillColor = new Color(numbers[7], numbers[8], numbers[9]);
					Point point = new Point(x, y);

					Donut donut = new Donut(point, outerRadius, innerRadius, false, borderColor, fillColor);

					UpdateDonutCmd updateDonut = new UpdateDonutCmd((Donut) selectedShape, donut);

					updateDonut.execute();
					commandList.add(updateDonut);

					frame.repaint();

				}
				if (line[1].contentEquals("Hexagon")) {

					int x = numbers[0];
					int y = numbers[1];
					int radius = numbers[2];

					Color borderColor = new Color(numbers[3], numbers[4], numbers[5]);
					Color fillColor = new Color(numbers[6], numbers[7], numbers[8]);

					Hexagon hexagon = new Hexagon(x, y, radius);

					HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon, false, borderColor, fillColor);

					UpdateHexagonCmd updateHexagon = new UpdateHexagonCmd((HexagonAdapter) selectedShape,
							hexagonAdapter);

					updateHexagon.execute();
					commandList.add(updateHexagon);

					frame.repaint();

				}

				setCommandListSize(commandList.size());

				frame.getArea().append(lineForLog + "\n");
				undoCommandText.add(lineForLog + "\n");

			} else if (line[0].contentEquals("Deleted")) {

				if (line[1].contentEquals("Point")) {
					if (listOfSelectedShapes.size() > 0) {

						int x = numbers[0];
						int y = numbers[1];
						Color borderColor = new Color(numbers[2], numbers[3], numbers[4]);

						Point point = new Point(x, y, false, borderColor);

						for (int i = 0; i < listOfSelectedShapes.size(); i++) {

							if (listOfSelectedShapes.get(i) instanceof Point) {
								Point pointToCompare = (Point) listOfSelectedShapes.get(i);
								int index;

								if (pointToCompare.getX() == x && pointToCompare.getY() == y
										&& borderColor.equals(pointToCompare.getBorderColor())) {

									index = model.getShapes().indexOf(point);
									RemoveShapeCmd removeShape = new RemoveShapeCmd(pointToCompare, model,
											listOfSelectedShapes, index);
									removeShape.execute();
									commandList.add(removeShape);

								}
							}

						}

						setNumberOfShapes(model.getShapes().size());

						

					}

					frame.repaint();

				}
				if (line[1].contentEquals("Line")) {

					if (listOfSelectedShapes.size() > 0) {

						int x1 = numbers[0];
						int y1 = numbers[1];

						int x2 = numbers[2];
						int y2 = numbers[3];
						Color borderColor = new Color(numbers[4], numbers[5], numbers[6]);

						Point startPoint = new Point(x1, y1, false);
						Point endPoint = new Point(x2, y2, false);

						Line lineShape = new Line(startPoint, endPoint, false, borderColor);

						for (int i = 0; i < listOfSelectedShapes.size(); i++) {

							if (listOfSelectedShapes.get(i) instanceof Line) {

								Line lineToCompare = (Line) listOfSelectedShapes.get(i);
								int index;

								if (lineToCompare.getStartPoint().getX() == x1
										&& lineToCompare.getStartPoint().getY() == y1
										&& lineToCompare.getEndPoint().getX() == x2
										&& lineToCompare.getEndPoint().getY() == y2
										&& borderColor.equals(lineToCompare.getBorderColor())) {

									index = model.getShapes().indexOf(lineShape);
									RemoveShapeCmd removeShape = new RemoveShapeCmd(lineToCompare, model,
											listOfSelectedShapes, index);
									removeShape.execute();
									commandList.add(removeShape);

								}

							}

						}

						setNumberOfShapes(model.getShapes().size());
					}

					frame.repaint();

				}
				if (line[1].contentEquals("Rectangle")) {

					if (listOfSelectedShapes.size() > 0) {

						int x = numbers[0];
						int y = numbers[1];
						int width = numbers[2];
						int height = numbers[3];
						Color borderColor = new Color(numbers[4], numbers[5], numbers[6]);
						Color fillColor = new Color(numbers[7], numbers[8], numbers[9]);

						Point upperLeftPoint = new Point(x, y);
						Rectangle rectangle = new Rectangle(upperLeftPoint, width, height, false, borderColor,
								fillColor);

						for (int i = 0; i < listOfSelectedShapes.size(); i++) {

							if (listOfSelectedShapes.get(i) instanceof Rectangle) {
								Rectangle rectangleToCompare = (Rectangle) listOfSelectedShapes.get(i);
								int index;

								if (rectangleToCompare.getUpperLeftPoint().getX() == x
										&& rectangleToCompare.getUpperLeftPoint().getY() == y
										&& rectangleToCompare.getWidth() == width
										&& rectangleToCompare.getHeight() == height
										&& borderColor.equals(rectangleToCompare.getBorderColor())
										&& fillColor.equals(rectangleToCompare.getFillColor())) {

									index = model.getShapes().indexOf(rectangle);
									RemoveShapeCmd removeShape = new RemoveShapeCmd(rectangleToCompare, model,
											listOfSelectedShapes, index);
									removeShape.execute();
									commandList.add(removeShape);

								}
							}

						}

						setNumberOfShapes(model.getShapes().size());
					}

					frame.repaint();

				}
				if (line[1].contentEquals("Circle")) {

					if (listOfSelectedShapes.size() > 0) {

						int x = numbers[0];
						int y = numbers[1];
						int radius = numbers[2];
						Color borderColor = new Color(numbers[3], numbers[4], numbers[5]);
						Color fillColor = new Color(numbers[6], numbers[7], numbers[8]);
						

						

						for (int i = 0; i < listOfSelectedShapes.size(); i++) {

							if (listOfSelectedShapes.get(i) instanceof Circle) {

								Circle circleToCompare = (Circle) listOfSelectedShapes.get(i);
								int index;

								if (circleToCompare.getCenter().getX() == x && circleToCompare.getCenter().getY() == y
										&& circleToCompare.getRadius() == radius
										&& borderColor.equals(circleToCompare.getBorderColor())
										&& fillColor.equals(circleToCompare.getFillColor())) {

									index = model.getShapes().indexOf(circleToCompare);
									RemoveShapeCmd removeShape = new RemoveShapeCmd(circleToCompare, model,
											listOfSelectedShapes, index);
									removeShape.execute();
									commandList.add(removeShape);

								}

							}

						}

						setNumberOfShapes(model.getShapes().size());

					}

					frame.repaint();

				}
				if (line[1].contentEquals("Donut")) {

					if (listOfSelectedShapes.size() > 0) {

						int x = numbers[0];
						int y = numbers[1];
						int outerRadius = numbers[2];
						int innerRadius = numbers[3];

						Color borderColor = new Color(numbers[4], numbers[5], numbers[6]);
						Color fillColor = new Color(numbers[7], numbers[8], numbers[9]);
						

						

						for (int i = 0; i < listOfSelectedShapes.size(); i++) {

							if (listOfSelectedShapes.get(i) instanceof Donut) {
								Donut donutToCompare = (Donut) listOfSelectedShapes.get(i);
								int index;

								if (donutToCompare.getCenter().getX() == x && donutToCompare.getCenter().getY() == y
										&& donutToCompare.getRadius() == outerRadius
										&& donutToCompare.getInnerRadius() == innerRadius
										&& borderColor.equals(donutToCompare.getBorderColor())
										&& fillColor.equals(donutToCompare.getFillColor())) {

									index = model.getShapes().indexOf(donutToCompare);
									RemoveShapeCmd removeShape = new RemoveShapeCmd(donutToCompare, model,
											listOfSelectedShapes, index);
									removeShape.execute();
									commandList.add(removeShape);

								}
							}

						}

						setNumberOfShapes(model.getShapes().size());

					}

					frame.repaint();

				}
				if (line[1].contentEquals("Hexagon")) {

					if (listOfSelectedShapes.size() > 0) {

						int x = numbers[0];
						int y = numbers[1];
						int radius = numbers[2];

						Color borderColor = new Color(numbers[3], numbers[4], numbers[5]);
						Color fillColor = new Color(numbers[6], numbers[7], numbers[8]);

						

						

						for (int i = 0; i < listOfSelectedShapes.size(); i++) {

							if (listOfSelectedShapes.get(i) instanceof HexagonAdapter) {

								HexagonAdapter hexagonToCompare = (HexagonAdapter) listOfSelectedShapes.get(i);
								int index;

								if (hexagonToCompare.getHexagon().getX() == x
										&& hexagonToCompare.getHexagon().getY() == y
										&& hexagonToCompare.getHexagon().getR() == radius
										&& hexagonToCompare.getBorderColor().equals(borderColor)
										&& hexagonToCompare.getFillColor().equals(fillColor)) {

									index = model.getShapes().indexOf(hexagonToCompare);
									RemoveShapeCmd removeShape = new RemoveShapeCmd(hexagonToCompare, model,
											listOfSelectedShapes, index);
									removeShape.execute();
									commandList.add(removeShape);

								}

							}

						}

						setNumberOfShapes(model.getShapes().size());

					}

					frame.repaint();

				}

				frame.getArea().append(lineForLog + "\n");
				undoCommandText.add(lineForLog + "\n");

				setSelectListSize(listOfSelectedShapes.size());

				if (listOfSelectedShapes.size() == 1) {

					setCurrentIndex(model.getShapes().indexOf(listOfSelectedShapes.get(0)));
				}

				setCommandListSize(commandList.size());

			} else if (line[0].contentEquals("toFront")) {

				oldIndex = currentIndex;

				ToFrontCmd frontCmd = new ToFrontCmd(model, listOfSelectedShapes, currentIndex);

				frontCmd.execute();

				commandList.add(frontCmd);

				setCurrentIndex(currentIndex + 1);

				setCommandListSize(commandList.size());
				if (selectedShape instanceof HexagonAdapter) {
					frame.getArea().append("toFront " + "Hexagon " + selectedShape + "\n");
					this.undoCommandText.add("toFront " + "Hexagon " + selectedShape + "\n");

				} else {
					frame.getArea()
							.append("toFront " + selectedShape.getClass().getSimpleName() + " " + selectedShape + "\n");
					this.undoCommandText
							.add("toFront " + selectedShape.getClass().getSimpleName() + " " + selectedShape + "\n");
				}

				frame.repaint();

			} else if (line[0].contentEquals("bringToFront")) {

				oldIndex = currentIndex;
				BringToFrontCmd bringToFrontCmd = new BringToFrontCmd(model, listOfSelectedShapes, oldIndex,
						currentIndex);

				bringToFrontCmd.execute();

				commandList.add(bringToFrontCmd);

				setCurrentIndex(model.getShapes().size() - 1);

				setCommandListSize(commandList.size());

				if (selectedShape instanceof HexagonAdapter) {
					frame.getArea().append("bringToFront " + " Hexagon " + selectedShape + "\n");
					undoCommandText.add("bringToFront " + " Hexagon " + selectedShape + "\n");
				} else {
					frame.getArea().append(
							"bringToFront " + selectedShape.getClass().getSimpleName() + " " + selectedShape + "\n");
					undoCommandText.add(
							"bringToFront " + selectedShape.getClass().getSimpleName() + " " + selectedShape + "\n");
				}

				frame.repaint();

			} else if (line[0].contentEquals("toBack")) {

				oldIndex = currentIndex;
				ToBackCmd backCmd = new ToBackCmd(model, listOfSelectedShapes, currentIndex);

				backCmd.execute();

				commandList.add(backCmd);

				setCurrentIndex(currentIndex - 1);

				setCommandListSize(commandList.size());

				if (selectedShape instanceof HexagonAdapter) {
					frame.getArea().append("toBack " + " Hexagon " + selectedShape + "\n");
					undoCommandText.add("toBack " + " Hexagon " + selectedShape + "\n");
				} else {
					frame.getArea()
							.append("toBack " + selectedShape.getClass().getSimpleName() + " " + selectedShape + "\n");
					undoCommandText
							.add("toBack " + selectedShape.getClass().getSimpleName() + " " + selectedShape + "\n");
				}

				frame.repaint();

			} else if (line[0].contentEquals("bringToBack")) {

				oldIndex = currentIndex;
				BringToBackCmd bringToBackCmd = new BringToBackCmd(model, listOfSelectedShapes, oldIndex, currentIndex);

				bringToBackCmd.execute();

				commandList.add(bringToBackCmd);

				setCurrentIndex(0);

				setCommandListSize(commandList.size());

				if (selectedShape instanceof HexagonAdapter) {
					frame.getArea().append("bringToBack " + " Hexagon " + selectedShape + "\n");
					this.undoCommandText.add("bringToBack " + " Hexagon " + selectedShape + "\n");
				} else {
					frame.getArea().append(
							"bringToBack " + selectedShape.getClass().getSimpleName() + " " + selectedShape + "\n");
					this.undoCommandText.add("bringToBack " + " Hexagon " + selectedShape + "\n");
				}

				frame.repaint();

			}

			else if (line[0].contentEquals("Undo")) {

				undo();

			} else if (line[0].contentEquals("Redo")) {

				redo();

			} else if (line[0].contentEquals("Selected") || line[0].contentEquals("Deselected")) {

				if (line[1].contentEquals("Point")) {

					int x = numbers[0];
					int y = numbers[1];
					Color borderColor = new Color(numbers[2], numbers[3], numbers[4]);

					Point point = new Point(x, y, false, borderColor);

					if (model.getShapes().contains(point) == true) {
						int indexOfShape = model.getShapes().indexOf(point);

						Point pointFromModel = (Point) model.getShapes().get(indexOfShape);
						SelectCmd select = new SelectCmd(pointFromModel, listOfSelectedShapes, this, selectFlag);
						select.execute();
						commandList.add(select);
						setSelectedShape(pointFromModel);

						frame.repaint();
					}

				}
				if (line[1].contentEquals("Line")) {

					int x1 = numbers[0];
					int y1 = numbers[1];

					int x2 = numbers[2];
					int y2 = numbers[3];
					Color borderColor = new Color(numbers[4], numbers[5], numbers[6]);

					Point startPoint = new Point(x1, y1, false);
					Point endPoint = new Point(x2, y2, false);

					Line lineShape = new Line(startPoint, endPoint, false, borderColor);

					if (model.getShapes().contains(lineShape) == true) {
						int indexOfShape = model.getShapes().indexOf(lineShape);

						Line lineFromModel = (Line) model.getShapes().get(indexOfShape);
						SelectCmd select = new SelectCmd(lineFromModel, listOfSelectedShapes, this, selectFlag);
						select.execute();
						commandList.add(select);
						setSelectedShape(lineFromModel);
						frame.repaint();
					}

				}
				if (line[1].contentEquals("Rectangle")) {

					int x = numbers[0];
					int y = numbers[1];
					int width = numbers[2];
					int height = numbers[3];
					Color borderColor = new Color(numbers[4], numbers[5], numbers[6]);
					Color fillColor = new Color(numbers[7], numbers[8], numbers[9]);

					Point upperLeftPoint = new Point(x, y);
					Rectangle rectangle = new Rectangle(upperLeftPoint, width, height, false, borderColor, fillColor);

					if (model.getShapes().contains(rectangle) == true) {
						int indexOfShape = model.getShapes().indexOf(rectangle);

						Rectangle rectangleFromModel = (Rectangle) model.getShapes().get(indexOfShape);
						SelectCmd select = new SelectCmd(rectangleFromModel, listOfSelectedShapes, this, selectFlag);
						select.execute();
						commandList.add(select);
						setSelectedShape(rectangleFromModel);
						frame.repaint();
					}

				}
				if (line[1].contentEquals("Circle")) {

					int x = numbers[0];
					int y = numbers[1];
					int radius = numbers[2];
					Color borderColor = new Color(numbers[3], numbers[4], numbers[5]);
					Color fillColor = new Color(numbers[6], numbers[7], numbers[8]);
					Point center = new Point(x, y);

					Circle circle = new Circle(center, radius, false, borderColor, fillColor);

					if (model.getShapes().contains(circle) == true) {
						int indexOfShape = model.getShapes().indexOf(circle);

						Circle circleFromModel = (Circle) model.getShapes().get(indexOfShape);
						SelectCmd select = new SelectCmd(circleFromModel, listOfSelectedShapes, this, selectFlag);
						select.execute();
						commandList.add(select);
						setSelectedShape(circleFromModel);
						frame.repaint();
					}

				}
				if (line[1].contentEquals("Donut")) {

					int x = numbers[0];
					int y = numbers[1];
					int outerRadius = numbers[2];
					int innerRadius = numbers[3];

					Color borderColor = new Color(numbers[4], numbers[5], numbers[6]);
					Color fillColor = new Color(numbers[7], numbers[8], numbers[9]);

					int xCompare;
					int yCompare;
					int outerRadiusCompare;
					int innerRadiusCompare;
					Color borderColorCompare;
					Color fillColorCompare;
					Donut donutFromModel;

					for (int i = 0; i < model.getShapes().size(); i++) {

						if (model.getShapes().get(i) instanceof Donut) {
							donutFromModel = (Donut) model.getShapes().get(i);
							xCompare = donutFromModel.getCenter().getX();
							yCompare = donutFromModel.getCenter().getY();
							outerRadiusCompare = donutFromModel.getRadius();
							innerRadiusCompare = donutFromModel.getInnerRadius();

							borderColorCompare = new Color(donutFromModel.getBorderColor().getRed(),
									donutFromModel.getBorderColor().getGreen(),
									donutFromModel.getBorderColor().getBlue());
							fillColorCompare = new Color(donutFromModel.getFillColor().getRed(),
									donutFromModel.getFillColor().getGreen(), donutFromModel.getFillColor().getBlue());

							if (x == xCompare && y == yCompare && outerRadius == outerRadiusCompare
									&& innerRadius == innerRadiusCompare && borderColor.equals(borderColorCompare)
									&& fillColor.equals(fillColorCompare)) {

								SelectCmd select = new SelectCmd(donutFromModel, listOfSelectedShapes, this,
										selectFlag);
								select.execute();
								commandList.add(select);
								setSelectedShape(donutFromModel);
								frame.repaint();

							}

						}

					}

					

				}
				if (line[1].contentEquals("Hexagon")) {

					int x = numbers[0];
					int y = numbers[1];
					int radius = numbers[2];

					Color borderColor = new Color(numbers[3], numbers[4], numbers[5]);
					Color fillColor = new Color(numbers[6], numbers[7], numbers[8]);

					int xCompare;
					int yCompare;

					int radiusCompare;

					Color borderColorCompare;
					Color fillColorCompare;

					HexagonAdapter hexagonFromModel;

					for (int i = 0; i < model.getShapes().size(); i++) {

						if (model.getShapes().get(i) instanceof HexagonAdapter) {
							hexagonFromModel = (HexagonAdapter) model.getShapes().get(i);
							xCompare = hexagonFromModel.getHexagon().getX();
							yCompare = hexagonFromModel.getHexagon().getY();
							radiusCompare = hexagonFromModel.getHexagon().getR();

							borderColorCompare = new Color(hexagonFromModel.getBorderColor().getRed(),
									hexagonFromModel.getBorderColor().getGreen(),
									hexagonFromModel.getBorderColor().getBlue());
							fillColorCompare = new Color(hexagonFromModel.getFillColor().getRed(),
									hexagonFromModel.getFillColor().getGreen(),
									hexagonFromModel.getFillColor().getBlue());

							if (x == xCompare && y == yCompare && radius == radiusCompare
									&& borderColor.equals(borderColorCompare) && fillColor.equals(fillColorCompare)) {

								SelectCmd select = new SelectCmd(hexagonFromModel, listOfSelectedShapes, this,
										selectFlag);
								select.execute();
								commandList.add(select);
								setSelectedShape(hexagonFromModel);
								frame.repaint();

							}

						}

					}

				}

				setSelectListSize(this.listOfSelectedShapes.size());

				if (listOfSelectedShapes.size() == 1) {
					this.setSelectedShape(listOfSelectedShapes.get(0));
					this.setCurrentIndex(model.getShapes().indexOf(selectedShape));
					frame.getArea().append(lineForLog + "\n");
					undoCommandText.add(lineForLog + "\n");
				} else {
					this.setCurrentIndex(-1);
					frame.getArea().append(lineForLog + "\n");
					undoCommandText.add(lineForLog + "\n");
				}

			}
			counter++;
		}
		
		
	
	
}


public int getSelectListSize() {
	return selectListSize;
}

public void setSelectListSize(int selectListSize) {
	propertyChangeSupport.firePropertyChange("selectListSize",this.selectListSize,selectListSize);
	this.selectListSize = selectListSize;
}

public void setNumberOfShapes(int numberOfShapes) {
	propertyChangeSupport.firePropertyChange("numberOfShapes", this.numberOfShapes, numberOfShapes);
	this.numberOfShapes = numberOfShapes;
}

public void setSavedCommandsSize(int savedCommandsSize) {
	propertyChangeSupport.firePropertyChange("savedCommandsSize",this.savedCommandsSize,savedCommandsSize);
	this.savedCommandsSize = savedCommandsSize;
}

public void setCommandListSize(int commandListSize) {
	propertyChangeSupport.firePropertyChange("commandListSize",this.commandListSize,commandListSize);
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









	
	
	
	
	
	

}
