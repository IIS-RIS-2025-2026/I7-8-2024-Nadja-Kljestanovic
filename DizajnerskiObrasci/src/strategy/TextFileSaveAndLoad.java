package strategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import mvc.DrawingController;
import mvc.DrawingFrame;

public class TextFileSaveAndLoad implements SaveAndLoad{
	
	private String [] lines;
	
	
	private DrawingController controller;
	
	private DrawingFrame frame;
	
	private ArrayList<String> stringArrayList;
	public TextFileSaveAndLoad(ArrayList<String> stringArrayList) {
		this.stringArrayList = stringArrayList;
		
	}
	
	
	public TextFileSaveAndLoad(DrawingFrame frame) {
		this.frame = frame;
		
		
	}

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

	@Override
	public void loadFile(String filePath) {
		
		try{
			FileReader fileReader = new FileReader(filePath);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line = null;
			
		
			while ((line = bufferedReader.readLine()) != null) {
				 //System.out.println(line);
				 stringArrayList.add(line);
				 
			}
			
			bufferedReader.close();
			
		} catch(IOException ex) {
            ex.printStackTrace();
       }
		
	}

}
