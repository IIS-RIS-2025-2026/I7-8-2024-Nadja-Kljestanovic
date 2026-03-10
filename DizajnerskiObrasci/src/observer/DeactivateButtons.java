package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingController;
import mvc.DrawingFrame;

public class DeactivateButtons implements PropertyChangeListener{
	
	
	private DrawingFrame frame;
	private int selectListSize;
	private int numberOfShapes;
	private int currentIndex;
	
	public DeactivateButtons(DrawingFrame frame) {
		this.frame = frame;
	}
	
	

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName() == "selectListSize") {
			
			this.selectListSize = (int)event.getNewValue();
			
			System.out.println("Current Index in select list " + currentIndex );
			System.out.println("Current number of Shapes in select list " + numberOfShapes );
			System.out.println("Current select list size  in select list " + selectListSize );
			
			if (selectListSize == 1 ) {
				frame.getBtnModify().setEnabled(true);
				frame.getBtnDelete().setEnabled(true);
				if (numberOfShapes > 1) {
					frame.getBtnToBack().setEnabled(true);
					frame.getBtnToFront().setEnabled(true);
					frame.getBtnBringToBack().setEnabled(true);
					frame.getBtnBringToFront().setEnabled(true);
					if (currentIndex == 0) {
						frame.getBtnBringToBack().setEnabled(false);
						frame.getBtnToBack().setEnabled(false);
					}
					else if (currentIndex == numberOfShapes - 1) {
						frame.getBtnBringToFront().setEnabled(false);
						frame.getBtnToFront().setEnabled(false);
						frame.getBtnBringToBack().setEnabled(true);
						frame.getBtnToBack().setEnabled(true);
						
					} else if (currentIndex != -1){
						frame.getBtnToBack().setEnabled(true);
						frame.getBtnToFront().setEnabled(true);
						frame.getBtnBringToBack().setEnabled(true);
						frame.getBtnBringToFront().setEnabled(true);
					} else if (currentIndex == -1) {
						frame.getBtnToBack().setEnabled(false);
						frame.getBtnToFront().setEnabled(false);
						frame.getBtnBringToBack().setEnabled(false);
						frame.getBtnBringToFront().setEnabled(false);
					}
				} else {
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getBtnBringToBack().setEnabled(false);
					frame.getBtnBringToFront().setEnabled(false);
				
				}
			} else {
				frame.getBtnModify().setEnabled(false);
				if (selectListSize == 0) {
					frame.getBtnDelete().setEnabled(false);
					
					
				}
				
				frame.getBtnToBack().setEnabled(false);
				frame.getBtnToFront().setEnabled(false);
				frame.getBtnBringToBack().setEnabled(false);
				frame.getBtnBringToFront().setEnabled(false);
			}
			
			
		} else if (event.getPropertyName() == "numberOfShapes") {
			this.numberOfShapes = (int)event.getNewValue();
			
			System.out.println("Current Index in number of shapes " + currentIndex );
			System.out.println("Current number of Shapes in number of shapes " + numberOfShapes );
			System.out.println("Current select list size  in number of shapes  " + selectListSize );
			if (currentIndex != 0 && currentIndex != numberOfShapes - 1 && currentIndex != -1) {
				frame.getBtnToBack().setEnabled(true);
				frame.getBtnToFront().setEnabled(true);
				
				frame.getBtnBringToBack().setEnabled(true);
				frame.getBtnBringToFront().setEnabled(true);
			} else if (currentIndex == 0) {
				
				if (selectListSize == 0 || selectListSize > 1) {
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnBringToBack().setEnabled(false);
					
					frame.getBtnToFront().setEnabled(false);
					frame.getBtnBringToFront().setEnabled(false);
				} else {
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnBringToBack().setEnabled(false);
					
					frame.getBtnToFront().setEnabled(true);
					frame.getBtnBringToFront().setEnabled(true);
				}
				
			}	else if (currentIndex == numberOfShapes - 1) {
				
				if (selectListSize == 0 || selectListSize > 1) {
					
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					
					frame.getBtnBringToBack().setEnabled(false);
					frame.getBtnBringToFront().setEnabled(false);
				} else {
					frame.getBtnToBack().setEnabled(true);
					frame.getBtnBringToBack().setEnabled(true);
					frame.getBtnToFront().setEnabled(false);
					frame.getBtnBringToFront().setEnabled(false);
				}
				
			} else if (currentIndex == -1) {
				frame.getBtnToBack().setEnabled(false);
				frame.getBtnToFront().setEnabled(false);
				
				frame.getBtnBringToBack().setEnabled(false);
				frame.getBtnBringToFront().setEnabled(false);
			}
				
			
		} else if (event.getPropertyName() == "currentIndex") {
			this.currentIndex = (int) event.getNewValue();
			
			
			System.out.println("Current Index in currentIndexProperty " + currentIndex );
			System.out.println("Number of shapes  in currentIndexProperty " + numberOfShapes );
			System.out.println("Select list size in currentIndexProperty " + selectListSize );
			if (currentIndex == 0) {
				
				if (numberOfShapes == 1) {
					frame.getBtnBringToBack().setEnabled(false);
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getBtnBringToFront().setEnabled(false);
				} else {
					frame.getBtnBringToBack().setEnabled(false);
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(true);
					frame.getBtnBringToFront().setEnabled(true);
				}
				
				
			} else if (currentIndex == numberOfShapes - 1) {
				
				if (numberOfShapes == 1) {
					frame.getBtnBringToFront().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getBtnBringToBack().setEnabled(false);
					frame.getBtnToBack().setEnabled(false);
				} else {
					frame.getBtnBringToFront().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getBtnBringToBack().setEnabled(true);
					frame.getBtnToBack().setEnabled(true);
				}
				
				
				
			} else {
				if (numberOfShapes == 1) {

					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getBtnBringToBack().setEnabled(false);
					frame.getBtnBringToFront().setEnabled(false);
				} else {
					
					if (currentIndex == -1) {
						frame.getBtnToBack().setEnabled(false);
						frame.getBtnToFront().setEnabled(false);
						frame.getBtnBringToBack().setEnabled(false);
						frame.getBtnBringToFront().setEnabled(false);
					} else {
						frame.getBtnToBack().setEnabled(true);
						frame.getBtnToFront().setEnabled(true);
						frame.getBtnBringToBack().setEnabled(true);
						frame.getBtnBringToFront().setEnabled(true);
					}
					
					
				}
				
				
			}
		}
			
		
	}

}
