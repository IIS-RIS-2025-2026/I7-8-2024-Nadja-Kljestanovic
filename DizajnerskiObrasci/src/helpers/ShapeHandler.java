package helpers;

import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import geometry.*;
import gui.*;
import hexagon.Hexagon;
import mvc.DrawingFrame;

public class ShapeHandler {
    private DrawingFrame frame;

    public ShapeHandler(DrawingFrame frame) {
        this.frame = frame;
    }

    public Shape prepareNewShape(String selection, MouseEvent e, Point startPoint) {
        try {
            switch (selection) {
                case "Point":
                    DlgPoint dp = new DlgPoint();
                    dp.setModal(true);
                    dp.getTxtX().setText(Integer.toString(e.getX()));
                    dp.getTxtY().setText(Integer.toString(e.getY()));
                    dp.getTxtX().setEditable(false);
                    dp.getTxtY().setEditable(false);
                    dp.getBtnColor().setBackground(frame.getBorderColor());
                    dp.setVisible(true);
                    return dp.isCancelClicked() ? null : new Point(e.getX(), e.getY(), false, dp.getBtnColor().getBackground());

                case "Line":
                    if (startPoint == null) return null;
                    DlgLine dl = new DlgLine();
                    dl.setModal(true);
                    dl.getTxtX1().setText(Integer.toString(startPoint.getX()));
                    dl.getTxtY1().setText(Integer.toString(startPoint.getY()));
                    dl.getTxtX2().setText(Integer.toString(e.getX()));
                    dl.getTxtY2().setText(Integer.toString(e.getY()));
                    dl.getTxtX1().setEditable(false);
                    dl.getTxtY1().setEditable(false);
                    dl.getTxtX2().setEditable(false);
                    dl.getTxtY2().setEditable(false);
                    dl.getBtnColor().setBackground(frame.getBorderColor());
                    dl.setVisible(true);
                    return dl.isCancelClicked() ? null : new Line(startPoint, new Point(e.getX(), e.getY()), false, dl.getBtnColor().getBackground());

                case "Rectangle":
                    DlgRectangle dr = new DlgRectangle();
                    dr.setModal(true);
                    dr.getTxtX().setText(Integer.toString(e.getX()));
                    dr.getTxtY().setText(Integer.toString(e.getY()));
                    dr.getTxtX().setEditable(false);
                    dr.getTxtY().setEditable(false);
                    dr.getBtnColor().setBackground(frame.getBorderColor());
                    dr.getBtnFill().setBackground(frame.getFillColor());
                    dr.setVisible(true);
                    if (dr.isCancelClicked()) return null;
                    return new Rectangle(new Point(e.getX(), e.getY()), Integer.parseInt(dr.getTxtWidth().getText()), Integer.parseInt(dr.getTxtHeight().getText()), false, dr.getBtnColor().getBackground(), dr.getBtnFill().getBackground());

                case "Circle":
                    DlgCircle dc = new DlgCircle();
                    dc.setModal(true);
                    dc.getTxtX().setText(Integer.toString(e.getX()));
                    dc.getTxtY().setText(Integer.toString(e.getY()));
                    dc.getTxtX().setEditable(false);
                    dc.getTxtY().setEditable(false);
                    dc.getBtnColor().setBackground(frame.getBorderColor());
                    dc.getBtnFill().setBackground(frame.getFillColor());
                    dc.setVisible(true);
                    if (dc.isCancelClicked()) return null;
                    return new Circle(new Point(e.getX(), e.getY()), Integer.parseInt(dc.getTxtRadius().getText()), false, dc.getBtnColor().getBackground(), dc.getBtnFill().getBackground());

                case "Donut":
                    DlgDonut dd = new DlgDonut();
                    dd.setModal(true);
                    dd.getTxtX().setText(Integer.toString(e.getX()));
                    dd.getTxtY().setText(Integer.toString(e.getY()));
                    dd.getTxtX().setEditable(false);
                    dd.getTxtY().setEditable(false);
                    dd.setBorderColor(frame.getBorderColor());
                    dd.setFillColor(frame.getFillColor());
                    dd.setVisible(true);
                    if (dd.isCancelClicked()) return null;
                    return new Donut(new Point(e.getX(), e.getY()), Integer.parseInt(dd.getTxtOuterRadius().getText()), Integer.parseInt(dd.getTxtInnerRadius().getText()), false, dd.getBorderColor(), dd.getFillColor());

                case "Hexagon":
                    DlgHexagon dh = new DlgHexagon();
                    dh.setModal(true);
                    dh.getTxtX().setText(Integer.toString(e.getX()));
                    dh.getTxtY().setText(Integer.toString(e.getY()));
                    dh.getTxtX().setEditable(false);
                    dh.getTxtY().setEditable(false);
                    dh.getBtnColor().setBackground(frame.getBorderColor());
                    dh.getBtnFill().setBackground(frame.getFillColor());
                    dh.setVisible(true);
                    if (dh.isCancelClicked()) return null;
                    return new HexagonAdapter(new Hexagon(e.getX(), e.getY(), Integer.parseInt(dh.getTxtR().getText())), false, dh.getBtnColor().getBackground(), dh.getBtnFill().getBackground());

                default: return null;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter numbers.");
            return null;
        }
    }

    public Shape prepareUpdateShape(Shape shape) {
        try {
            if (shape instanceof Point) {
                Point p = (Point) shape;
                DlgPoint dp = new DlgPoint();
                dp.getTxtX().setText(String.valueOf(p.getX()));
                dp.getTxtY().setText(String.valueOf(p.getY()));
                dp.getBtnColor().setBackground(p.getBorderColor());
                dp.setModal(true);
                dp.setVisible(true);
                return dp.isCancelClicked() ? null : new Point(Integer.parseInt(dp.getTxtX().getText()), Integer.parseInt(dp.getTxtY().getText()), true, dp.getBtnColor().getBackground());
            } else if (shape instanceof Line) {
                Line l = (Line) shape;
                DlgLine dl = new DlgLine();
                dl.getTxtX1().setText(String.valueOf(l.getStartPoint().getX()));
                dl.getTxtY1().setText(String.valueOf(l.getStartPoint().getY()));
                dl.getTxtX2().setText(String.valueOf(l.getEndPoint().getX()));
                dl.getTxtY2().setText(String.valueOf(l.getEndPoint().getY()));
                dl.getBtnColor().setBackground(l.getBorderColor());
                dl.setModal(true);
                dl.setVisible(true);
                return dl.isCancelClicked() ? null : new Line(new Point(Integer.parseInt(dl.getTxtX1().getText()), Integer.parseInt(dl.getTxtY1().getText())), new Point(Integer.parseInt(dl.getTxtX2().getText()), Integer.parseInt(dl.getTxtY2().getText())), true, dl.getBtnColor().getBackground());
            } else if (shape instanceof Rectangle) {
                Rectangle r = (Rectangle) shape;
                DlgRectangle dr = new DlgRectangle();
                dr.getTxtX().setText(String.valueOf(r.getUpperLeftPoint().getX()));
                dr.getTxtY().setText(String.valueOf(r.getUpperLeftPoint().getY()));
                dr.getTxtWidth().setText(String.valueOf(r.getWidth()));
                dr.getTxtHeight().setText(String.valueOf(r.getHeight()));
                dr.getBtnColor().setBackground(r.getBorderColor());
                dr.getBtnFill().setBackground(r.getFillColor());
                dr.setModal(true);
                dr.setVisible(true);
                return dr.isCancelClicked() ? null : new Rectangle(new Point(Integer.parseInt(dr.getTxtX().getText()), Integer.parseInt(dr.getTxtY().getText())), Integer.parseInt(dr.getTxtWidth().getText()), Integer.parseInt(dr.getTxtHeight().getText()), true, dr.getBtnColor().getBackground(), dr.getBtnFill().getBackground());
            } else if (shape instanceof Circle && !(shape instanceof Donut)) {
                Circle c = (Circle) shape;
                DlgCircle dc = new DlgCircle();
                dc.getTxtX().setText(String.valueOf(c.getCenter().getX()));
                dc.getTxtY().setText(String.valueOf(c.getCenter().getY()));
                dc.getTxtRadius().setText(String.valueOf(c.getRadius()));
                dc.getBtnColor().setBackground(c.getBorderColor());
                dc.getBtnFill().setBackground(c.getFillColor());
                dc.setModal(true);
                dc.setVisible(true);
                return dc.isCancelClicked() ? null : new Circle(new Point(Integer.parseInt(dc.getTxtX().getText()), Integer.parseInt(dc.getTxtY().getText())), Integer.parseInt(dc.getTxtRadius().getText()), true, dc.getBtnColor().getBackground(), dc.getBtnFill().getBackground());
            } else if (shape instanceof Donut) {
                Donut d = (Donut) shape;
                DlgDonut dd = new DlgDonut();
                dd.getTxtX().setText(String.valueOf(d.getCenter().getX()));
                dd.getTxtY().setText(String.valueOf(d.getCenter().getY()));
                dd.getTxtOuterRadius().setText(String.valueOf(d.getRadius()));
                dd.getTxtInnerRadius().setText(String.valueOf(d.getInnerRadius()));
                dd.setBorderColor(d.getBorderColor());
                dd.setFillColor(d.getFillColor());
                dd.setModal(true);
                dd.setVisible(true);
                return dd.isCancelClicked() ? null : new Donut(new Point(Integer.parseInt(dd.getTxtX().getText()), Integer.parseInt(dd.getTxtY().getText())), Integer.parseInt(dd.getTxtOuterRadius().getText()), Integer.parseInt(dd.getTxtInnerRadius().getText()), true, dd.getBorderColor(), dd.getFillColor());
            } else if (shape instanceof HexagonAdapter) {
                HexagonAdapter h = (HexagonAdapter) shape;
                DlgHexagon dh = new DlgHexagon();
                dh.getTxtX().setText(String.valueOf(h.getHexagon().getX()));
                dh.getTxtY().setText(String.valueOf(h.getHexagon().getY()));
                dh.getTxtR().setText(String.valueOf(h.getHexagon().getR()));
                dh.getBtnColor().setBackground(h.getBorderColor());
                dh.getBtnFill().setBackground(h.getFillColor());
                dh.setModal(true);
                dh.setVisible(true);
                return dh.isCancelClicked() ? null : new HexagonAdapter(new Hexagon(Integer.parseInt(dh.getTxtX().getText()), Integer.parseInt(dh.getTxtY().getText()), Integer.parseInt(dh.getTxtR().getText())), true, dh.getBtnColor().getBackground(), dh.getBtnFill().getBackground());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid input!");
        }
        return null;
    }
}