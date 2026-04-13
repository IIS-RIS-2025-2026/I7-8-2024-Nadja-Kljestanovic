package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class DrawingFrame extends JFrame {
	private DrawingView view = new DrawingView();
	private DrawingController controller;

	private ButtonGroup grupa = new ButtonGroup();

	private JToggleButton tglbtnPoint, tglbtnLinija, tglbtnRectangle, tglbtnCircle, tglbtnDonut, tglbtnHexagon,
			tglbtnSelection;

	private JButton btnModify, btnDelete, btnUndo, btnRedo;
	private JButton btnToFront, btnToBack, btnBringToFront, btnBringToBack;
	private JButton btnBorderColor, btnFillColor, btnSave, btnLoad, btnNextCommand;

	private Color borderColor = Color.BLACK;
	private Color fillColor = Color.WHITE;

	private JTextArea area;
	private JScrollPane scrollPanel;
	private String selectedButton;

	public DrawingFrame() {
		setTitle("Djakovic Nikola IT 24/2021");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 800);
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().add(view, BorderLayout.CENTER);

		area = new JTextArea(5, 20);
		scrollPanel = new JScrollPane(area);
		scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPanel, BorderLayout.EAST);

		JToolBar toolBar = new JToolBar();
		configureToolBar(toolBar);

		JToolBar toolBar1 = new JToolBar();
		configureToolBar(toolBar1);

		JPanel panelNorth = new JPanel();
		panelNorth.setBackground(Color.BLUE);
		panelNorth.add(toolBar);
		getContentPane().add(panelNorth, BorderLayout.NORTH);

		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(Color.BLUE);
		panelSouth.add(toolBar1);
		getContentPane().add(panelSouth, BorderLayout.SOUTH);

		setupShapeButtons(toolBar);
		setupActionButtons(toolBar);
		setupColorButtons(toolBar1);
		setupOrderButtons(toolBar1);
		setupFileButtons(toolBar1);
	}

	private void configureToolBar(JToolBar tb) {
		tb.setForeground(Color.ORANGE);
		tb.setBackground(Color.ORANGE);
		tb.setFloatable(false);
		tb.setBorderPainted(false);
		tb.addSeparator(new Dimension(20, 0));
		tb.setPreferredSize(new Dimension(1000, 50));
	}

	private void setupShapeButtons(JToolBar toolBar) {
		tglbtnPoint = createToggleButton("Point", toolBar);
		tglbtnLinija = createToggleButton("Line", toolBar);
		tglbtnRectangle = createToggleButton("Rectangle", toolBar);
		tglbtnCircle = createToggleButton("Circle", toolBar);
		tglbtnDonut = createToggleButton("Donut", toolBar);
		tglbtnHexagon = createToggleButton("Hexagon", toolBar);
		tglbtnSelection = createToggleButton("Selection", toolBar);
		toolBar.addSeparator(new Dimension(30, 0));
	}

	private void setupActionButtons(JToolBar toolBar) {
		btnModify = createButton("Modify", toolBar, e -> controller.modify(), false);
		btnDelete = createButton("Delete", toolBar, e -> controller.delete(), false);
		toolBar.addSeparator(new Dimension(30, 0));

		btnUndo = createButton("Undo", toolBar, e -> controller.undo(), false);
		btnRedo = createButton("Redo", toolBar, e -> controller.redo(), false);
	}

	private void setupColorButtons(JToolBar toolBar1) {
		btnBorderColor = createButton("Border color", toolBar1, e -> {
			borderColor = JColorChooser.showDialog(null, "Select a color", borderColor);
			btnBorderColor.setBackground(borderColor);
		}, true);
		btnBorderColor.setBackground(Color.BLACK);

		btnFillColor = createButton("Fill color", toolBar1, e -> {
			fillColor = JColorChooser.showDialog(null, "Select a color", fillColor);
			btnFillColor.setBackground(fillColor);
		}, true);
		btnFillColor.setBackground(Color.WHITE);

		toolBar1.addSeparator(new Dimension(100, 0));
	}

	private void setupOrderButtons(JToolBar toolBar1) {
		btnToFront = createButton("To Front", toolBar1, e -> controller.toFront(), false);
		btnToBack = createButton("To Back", toolBar1, e -> controller.toBack(), false);
		btnBringToFront = createButton("Bring to front", toolBar1, e -> controller.bringToFront(), false);
		btnBringToBack = createButton("Bring to back", toolBar1, e -> controller.bringToBack(), false);

		toolBar1.addSeparator(new Dimension(100, 0));
	}

	private void setupFileButtons(JToolBar toolBar1) {
		btnSave = createButton("Save", toolBar1, e -> {
			JFileChooser file = new JFileChooser();
			if (file.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
				controller.saveFile(file.getSelectedFile().getPath());
		}, true);

		btnLoad = createButton("Load", toolBar1, e -> {
			JFileChooser file = new JFileChooser();
			if (file.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
				controller.loadFile(file.getSelectedFile().getPath());
		}, true);

		btnNextCommand = createButton("Next Command", toolBar1, e -> controller.loadCommand(), true);
		btnNextCommand.setVisible(false);
	}

	private JToggleButton createToggleButton(String name, JToolBar bar) {
		JToggleButton btn = new JToggleButton(name);
		btn.setPreferredSize(new Dimension(100, 50));
		btn.addActionListener(e -> selectedButton = name);
		grupa.add(btn);
		bar.add(btn);
		return btn;
	}

	private JButton createButton(String name, JToolBar bar, java.awt.event.ActionListener al, boolean enabled) {
		JButton btn = new JButton(name);
		btn.setPreferredSize(new Dimension(100, 50));
		btn.addActionListener(al);
		btn.setEnabled(enabled);
		bar.add(btn);
		return btn;
	}

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public String getSelectedButton() {
		return selectedButton;
	}

	public JTextArea getArea() {
		return area;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public JButton getBtnNextCommand() {
		return btnNextCommand;
	}
}