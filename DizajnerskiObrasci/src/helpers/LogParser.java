package helpers;

import java.awt.Color;
import geometry.*;
import hexagon.Hexagon;

public class LogParser {
    
	public int[] parseNumbers(String log) {
	    int[] n = new int[12];
	    java.util.regex.Matcher m = java.util.regex.Pattern.compile("-?\\d+").matcher(log);
	    
	    int i = 0;
	    while (m.find() && i < n.length) {
	        n[i++] = Integer.parseInt(m.group());
	    }
	    return n;
	}

    public Shape createShape(String type, int[] n) {
        switch (type) {
            case "Point": 
                return new Point(n[0], n[1], false, new Color(n[2], n[3], n[4]));
            case "Line": 
                return new Line(new Point(n[0], n[1]), new Point(n[2], n[3]), false, new Color(n[4], n[5], n[6]));
            case "Rectangle": 
                return new Rectangle(new Point(n[0], n[1]), n[2], n[3], false, new Color(n[4], n[5], n[6]), new Color(n[7], n[8], n[9]));
            case "Circle": 
                return new Circle(new Point(n[0], n[1]), n[2], false, new Color(n[3], n[4], n[5]), new Color(n[6], n[7], n[8]));
            case "Donut": 
                return new Donut(new Point(n[0], n[1]), n[2], n[3], false, new Color(n[4], n[5], n[6]), new Color(n[7], n[8], n[9]));
            case "Hexagon": 
                return new HexagonAdapter(new Hexagon(n[0], n[1], n[2]), false, new Color(n[3], n[4], n[5]), new Color(n[6], n[7], n[8]));
            default: 
                return null;
        }
    }
}