package mvc;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
		System.out.println("Dobrodosli na vezbe iz predmeta Dizajnerski obrasci.");
		
		
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		frame.getView().setModel(model);
		DrawingController controller = new DrawingController(model,frame);
		
		controller.setSelectListSize(0);
		frame.setController(controller);
		
		
		frame.setSize(2000,1000);
		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		
		
		
		
		
		

	}

}
