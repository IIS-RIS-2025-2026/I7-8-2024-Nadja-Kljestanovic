package mvc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class DrawingFrame extends JFrame  {
	//Ovamo dodaj dve globalne boje
	// Dodaj select - pogledaj kod kontrolera
	 private ButtonGroup grupa = new ButtonGroup();
	 private ButtonGroup grupa1 = new ButtonGroup();
	 private JToggleButton tglbtnPoint;
		private JToggleButton tglbtnLinija;
		private JToggleButton tglbtnRectangle;
		private JToggleButton tglbtnCircle;
		private JToggleButton tglbtnDonut;
		private JToggleButton tglbtnSelection;
		private JButton btnModify;
		public JButton getBtnModify() {
			return btnModify;
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

		private JButton btnDelete;
		public JButton getBtnDelete() {
			return btnDelete;
		}

		private JButton btnBorderColor;
		private JButton btnFillColor;
		private JTextArea area;
		private JButton btnUndo;
		public JButton getBtnUndo() {
			return btnUndo;
		}

		public JButton getBtnRedo() {
			return btnRedo;
		}

		private JButton btnRedo;

		private Color borderColor;
		
		

		private Color fillColor;
		
		
		
	
	private String selectedButton;
	
	
	public DrawingView view = new DrawingView();
	public DrawingController controller;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JToggleButton tglbtnHexagon;
	private JScrollPane scrollPanel;
	private JButton btnSave;
	private JButton btnLoad;
	private JButton btnNextCommand;
	
	
	public DrawingFrame() {
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
				
				
			}
		});
		
		
		//JPanel panel = new JPanel();
		getContentPane().add(view, BorderLayout.CENTER);
		getContentPane().setBackground(Color.WHITE);
		setTitle("Djakovic Nikola IT 24/2021");
		
		
		
		JToolBar toolBar = new JToolBar();
		toolBar.setForeground(Color.ORANGE);
		toolBar.setBackground(Color.ORANGE);
		
		JToolBar toolBar1 = new JToolBar();
		toolBar1.setForeground(Color.ORANGE);
		toolBar1.setBackground(Color.ORANGE);
		
	
		
		
		JPanel panelNorth = new JPanel();
		getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		JPanel panelSouth = new JPanel();
		getContentPane().add(panelSouth,BorderLayout.SOUTH);
		
		
		
		
		panelNorth.add(toolBar);
		panelNorth.setBackground(Color.BLUE);
		
		toolBar.setFloatable(false);
		
		toolBar.setBorderPainted(false);
		
		toolBar.addSeparator(new Dimension(20,0));
		
		
		
		
		panelSouth.add(toolBar1);
		
		panelSouth.setBackground(Color.BLUE);
		
		toolBar1.setFloatable(false);
		toolBar1.setBorderPainted(false);
		
		toolBar1.addSeparator(new Dimension(20,0));
		
		
		
		//getContentPane().add(toolBar,BorderLayout.NORTH);
		//getContentPane().add(toolBar1,BorderLayout.SOUTH);
		
		
		
		
		area = new JTextArea(5,20);
		
		
		
		
		scrollPanel = new JScrollPane(area);
		scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		getContentPane().add(scrollPanel,BorderLayout.EAST);
		
		
		
		tglbtnPoint = new JToggleButton("Point");
		
		tglbtnPoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedButton = "Point";
			}
		});
		toolBar.add(tglbtnPoint);
		
		
		grupa.add(tglbtnPoint);
		
		tglbtnLinija = new JToggleButton("Line");
		tglbtnLinija.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedButton = "Line";
			}
		});
		toolBar.add(tglbtnLinija);
		grupa.add(tglbtnLinija);
		
		tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedButton = "Rectangle";
			}
		});
		toolBar.add(tglbtnRectangle);
		grupa.add(tglbtnRectangle);
	
		
		tglbtnCircle = new JToggleButton("Circle");
		tglbtnCircle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedButton = "Circle";
			}
		});
		toolBar.add(tglbtnCircle);
		grupa.add(tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
		tglbtnDonut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedButton = "Donut";
			}
		});
		toolBar.add(tglbtnDonut);
		grupa.add(tglbtnDonut);
		
		tglbtnSelection = new JToggleButton("Selection");
		tglbtnSelection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedButton = "Selection";
			}
		});
		
		tglbtnHexagon = new JToggleButton("Hexagon");
		tglbtnHexagon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedButton = "Hexagon";
			}
		});
		toolBar.add(tglbtnHexagon);
		grupa.add(tglbtnHexagon);
		
		toolBar.add(tglbtnSelection);
		toolBar.addSeparator(new Dimension(30,0));
		
		grupa.add(tglbtnSelection);
		
		
		btnBorderColor = new JButton("Border color");
		btnBorderColor.setBackground(Color.black);
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Ovo je vazno
				
				borderColor = JColorChooser.showDialog(null, "Select a color", borderColor);
				btnBorderColor.setBackground(borderColor);
			}
		});
		
		toolBar1.add(btnBorderColor);
		
		btnFillColor = new JButton("Fill color");
		btnFillColor.setBackground(Color.white);
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				// Ovo je vazno
				fillColor = JColorChooser.showDialog(null, "Select a color", fillColor);
				btnFillColor.setBackground(fillColor);
			}
		});
		
		toolBar1.add(btnFillColor);
		toolBar1.addSeparator(new Dimension(100,0));
		
		
		
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify();
			}
		});
	
		toolBar.add(btnModify);
		btnModify.setEnabled(false);
		
		btnDelete = new JButton("Delete");
		
		
		
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	selectedButton = "Deletion";
				
				controller.delete();
				
				
			}
		});
		toolBar.add(btnDelete);
		btnDelete.setEnabled(false);
		toolBar.addSeparator(new Dimension(30,0));
		
		
	
		
		btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});
		toolBar.add(btnUndo);
		btnUndo.setEnabled(false);
		
		btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}

		});
		toolBar.add(btnRedo);
		btnRedo.setEnabled(false);
		toolBar.setPreferredSize(new Dimension(1000,50));
		toolBar.addSeparator(new Dimension(20,0));
		
		
		
		
		
		
		
		btnToFront = new JButton("To Front");
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toFront();
			}
		});
		toolBar1.add(btnToFront);
		btnToFront.setEnabled(false);
		
		
		
		btnToBack= new JButton("To Back");
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		toolBar1.add(btnToBack);
		btnToBack.setEnabled(false);
		
			
		btnBringToFront = new JButton("Bring to front");
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		toolBar1.add(btnBringToFront);
		btnBringToFront.setEnabled(false);
		
	
		
		btnBringToBack = new JButton("Bring to back");
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringtoBack();
			}
		});
		toolBar1.add(btnBringToBack);
		btnBringToBack.setEnabled(false);
		toolBar1.addSeparator(new Dimension(100,0));
		
		
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser file = new JFileChooser();
				int yesOrNo = file.showSaveDialog(file);
				if (yesOrNo == JFileChooser.APPROVE_OPTION) {
					controller.saveFile(file.getSelectedFile().getPath());
				}
			}
		});
		toolBar1.add(btnSave);
		
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				int yesOrNo = file.showSaveDialog(file);
				if (yesOrNo == JFileChooser.APPROVE_OPTION) {
					controller.loadFile(file.getSelectedFile().getPath());
				}
				
			}
		});
		toolBar1.add(btnLoad);
		
		btnNextCommand = new JButton("Next Command");
		btnNextCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				controller.loadCommand();
			}
		});
		toolBar1.add(btnNextCommand);
		btnNextCommand.setVisible(false);
		
		toolBar1.setPreferredSize(new Dimension(1000,50));
		toolBar1.addSeparator(new Dimension(20,0));
		
		
		
		tglbtnPoint.setPreferredSize(new Dimension(100, 50));
		tglbtnLinija.setPreferredSize(new Dimension(100, 50));
		tglbtnRectangle.setPreferredSize(new Dimension(100, 50));
		tglbtnCircle.setPreferredSize(new Dimension(100, 50));
		tglbtnDonut.setPreferredSize(new Dimension(100, 50));
		tglbtnHexagon.setPreferredSize(new Dimension(100, 50));
		tglbtnSelection.setPreferredSize(new Dimension(100, 50));

		btnModify.setPreferredSize(new Dimension(100, 50));
		btnDelete.setPreferredSize(new Dimension(100, 50));
		btnUndo.setPreferredSize(new Dimension(100, 50));
		btnRedo.setPreferredSize(new Dimension(100, 50));
		
		btnToFront.setPreferredSize(new Dimension(100, 50));
		btnToBack.setPreferredSize(new Dimension(100, 50));
		btnBringToFront.setPreferredSize(new Dimension(100, 50));
		btnBringToBack.setPreferredSize(new Dimension(100, 50));
		btnBorderColor.setPreferredSize(new Dimension(100, 50));
		btnFillColor.setPreferredSize(new Dimension(100, 50));
		btnSave.setPreferredSize(new Dimension(100, 50));
		btnLoad.setPreferredSize(new Dimension(100, 50));
		btnNextCommand.setPreferredSize(new Dimension(100, 50));
		
		
		
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public JButton getBtnFillColor() {
		return btnFillColor;
	}

	public ButtonGroup getGrupa() {
		return grupa;
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
	
	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	
	public JToggleButton getTglbtnLinija() {
		return tglbtnLinija;
	}
	

	public JButton getBtnNextCommand() {
		return btnNextCommand;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}

	public Color getFillColor() {
		return fillColor;
	}
	
	public JTextArea getArea() {
		return area;
	}
	
	
		
	
	
	

}
