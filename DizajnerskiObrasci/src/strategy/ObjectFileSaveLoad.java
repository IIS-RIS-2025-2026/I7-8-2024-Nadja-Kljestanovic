package strategy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import geometry.Shape;
import mvc.DrawingModel;

public class ObjectFileSaveLoad implements SaveAndLoad, Serializable {

	private static final long serialVersionUID = 1L; // Dobra praksa za Serializable klase
	private final DrawingModel model;

	public ObjectFileSaveLoad(DrawingModel model) {
		this.model = model;
	}
	
	// Stara saveFile metoda
	/*
	@Override
	public void saveFile(String filePath) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(model.getShapes());
			objectOutputStream.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	*/

	// Nova saveFile metoda
	@Override
	public void saveFile(String filePath) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
			out.writeObject(model.getShapes());
		} catch (IOException e) {
			handleFileError("Greška prilikom čuvanja datoteke: ", e);
		}
	}

	// Stara loadFile metoda
	/*
	@Override
	public void loadFile(String filePath) {
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			@SuppressWarnings("unchecked")
			ArrayList<Shape> modelObjects = (ArrayList<Shape>) objectInputStream.readObject();
			for (Shape shape : modelObjects) {
				model.add(shape);
			}
			objectInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	*/

	// Nova loadFile metoda
	@Override
	public void loadFile(String filePath) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
			
			@SuppressWarnings("unchecked")
			List<Shape> loadedShapes = (List<Shape>) in.readObject();
			addShapesToModel(loadedShapes);
			
		} catch (IOException | ClassNotFoundException e) {
			handleFileError("Greška prilikom učitavanja datoteke: ", e);
		}
	}

	// Pomoćna metoda za dodavanje oblika u model
	private void addShapesToModel(List<Shape> shapes) {
		for (Shape shape : shapes) {
			model.add(shape);
		}
	}

	// Centralizovano rukovanje greškama
	private void handleFileError(String message, Exception e) {
		System.err.println(message + e.getMessage());
		e.printStackTrace();
	}
}