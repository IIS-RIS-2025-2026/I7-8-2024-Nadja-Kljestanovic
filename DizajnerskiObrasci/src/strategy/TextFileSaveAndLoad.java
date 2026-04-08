package strategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import mvc.DrawingFrame;

public class TextFileSaveAndLoad implements SaveAndLoad {

	private DrawingFrame frame;
	private List<String> stringArrayList;

	public TextFileSaveAndLoad(ArrayList<String> stringArrayList) {
		this.stringArrayList = stringArrayList;
	}

	public TextFileSaveAndLoad(DrawingFrame frame) {
		this.frame = frame;
	}

	// Stara saveFile metoda
	/*
	@Override
	public void saveFile(String filePath) {
		 try {
	            FileWriter writer = new FileWriter(filePath + ".txt");
	            String string = frame.getArea().getText();
	            System.out.println(string);
	            writer.write(string);
	            writer.close();
	       } catch(IOException ex) {
	             ex.printStackTrace();
	       }
	}
	*/

	// Nova saveFile metoda
	@Override
	public void saveFile(String filePath) {
		String fullPath = filePath.endsWith(".txt") ? filePath : filePath + ".txt";

		try (PrintWriter writer = new PrintWriter(new FileWriter(fullPath))) {
			String logText = frame.getArea().getText();
			writer.print(logText);
		} catch (IOException ex) {
			handleError("Greška pri čuvanju tekstualnog loga: ", ex);
		}
	}

	// Stara loadFile metoda
	/*
	@Override
	public void loadFile(String filePath) {
		try{
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				 stringArrayList.add(line);
			}
			bufferedReader.close();
		} catch(IOException ex) {
            ex.printStackTrace();
       }
	}
	*/

	// Nova loadFile metoda
	@Override
	public void loadFile(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				stringArrayList.add(line);
			}
		} catch (IOException ex) {
			handleError("Greška pri učitavanju tekstualnog loga: ", ex);
		}
	}

	private void handleError(String message, Exception ex) {
		System.err.println(message + ex.getMessage());
		ex.printStackTrace();
	}
}