package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import mvc.DrawingFrame;

public class DeactivateButtons implements PropertyChangeListener {

	private final DrawingFrame frame;
	private int selectListSize = 0;
	private int numberOfShapes = 0;
	private int currentIndex = -1;

	public DeactivateButtons(DrawingFrame frame) {
		this.frame = frame;
	}

	// Stara propertyChange metoda
	/*
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
			// ... (ostatak koda za numberOfShapes i currentIndex)
		}
	}
	*/

	// Nova propertyChange metoda
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String property = event.getPropertyName();
		int value = (int) event.getNewValue();

		//Lokalno stanje
		if ("selectListSize".equals(property)) {
			this.selectListSize = value;
		} else if ("numberOfShapes".equals(property)) {
			this.numberOfShapes = value;
		} else if ("currentIndex".equals(property)) {
			this.currentIndex = value;
		}

		updateButtonsState();
	}

	private void updateButtonsState() {
		frame.getBtnModify().setEnabled(selectListSize == 1);
		frame.getBtnDelete().setEnabled(selectListSize > 0);

		if (selectListSize == 1 && numberOfShapes > 1 && currentIndex != -1) {
			boolean isAtStart = (currentIndex == 0);
			boolean isAtEnd = (currentIndex == numberOfShapes - 1);

			frame.getBtnToBack().setEnabled(!isAtStart);
			frame.getBtnBringToBack().setEnabled(!isAtStart);
			
			frame.getBtnToFront().setEnabled(!isAtEnd);
			frame.getBtnBringToFront().setEnabled(!isAtEnd);
		} else {
			disableZOrderButtons();
		}
	}

	private void disableZOrderButtons() {
		frame.getBtnToBack().setEnabled(false);
		frame.getBtnToFront().setEnabled(false);
		frame.getBtnBringToBack().setEnabled(false);
		frame.getBtnBringToFront().setEnabled(false);
	}
}