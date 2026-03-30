package mvc;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
		System.out.println("Dobrodosli na vezbe iz predmeta Dizajnerski obrasci.");
		
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		DrawingController controller = new DrawingController(model,frame);
				
		frame.getView().setModel(model);
		frame.setController(controller);
		
		controller.setSelectListSize(0);

		setupMainFrame(frame);
	}
	
	//Nova pomoćna metoda za postavke prozora
	private static void setupMainFrame(DrawingFrame frame) {
		frame.setSize(1500,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
