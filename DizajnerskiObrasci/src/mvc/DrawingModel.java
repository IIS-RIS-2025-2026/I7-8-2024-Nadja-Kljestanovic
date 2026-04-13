package mvc;

import java.util.ArrayList;
import java.util.List;
import geometry.Shape;

public class DrawingModel {
	private List<Shape> shapes = new ArrayList<>();

	public void add(Shape shape) {
		shapes.add(shape);
	}

	public void addAtIndex(int index, Shape shape) {
		shapes.add(index, shape);
	}

	public void remove(Shape shape) {
		shapes.remove(shape);
	}

	public Shape get(int index) {
		return shapes.get(index);
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public int size() {
		return shapes.size();
	}
	
	public boolean isEmpty() {
		return shapes.isEmpty();
	}
}