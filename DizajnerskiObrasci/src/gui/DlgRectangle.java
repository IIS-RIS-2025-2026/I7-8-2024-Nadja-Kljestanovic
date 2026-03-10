package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtX;
	protected JTextField txtY;
	protected JTextField txtWidth;
	protected JTextField txtHeight;
	private Rectangle rectangle;
	protected JButton btnColor;
	

	protected JButton btnFill;
	

	Color c1;
	Color c2;
	private boolean cancelClicked;


	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setTitle("Dialogue for Rectangle");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblXCoordinate = new JLabel("X coordinate:");
			GridBagConstraints gbc_lblXCoordinate = new GridBagConstraints();
			gbc_lblXCoordinate.anchor = GridBagConstraints.EAST;
			gbc_lblXCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblXCoordinate.gridx = 2;
			gbc_lblXCoordinate.gridy = 0;
			contentPanel.add(lblXCoordinate, gbc_lblXCoordinate);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.gridwidth = 4;
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 3;
			gbc_txtX.gridy = 0;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblYCoordinate = new JLabel("Y coordinate:");
			GridBagConstraints gbc_lblYCoordinate = new GridBagConstraints();
			gbc_lblYCoordinate.anchor = GridBagConstraints.EAST;
			gbc_lblYCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblYCoordinate.gridx = 2;
			gbc_lblYCoordinate.gridy = 1;
			contentPanel.add(lblYCoordinate, gbc_lblYCoordinate);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.gridwidth = 4;
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 3;
			gbc_txtY.gridy = 1;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.anchor = GridBagConstraints.EAST;
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 2;
			gbc_lblWidth.gridy = 2;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.gridwidth = 4;
			gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 3;
			gbc_txtWidth.gridy = 2;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Height:");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.anchor = GridBagConstraints.EAST;
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.gridx = 2;
			gbc_lblHeight.gridy = 3;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.insets = new Insets(0, 0, 5, 0);
			gbc_txtHeight.gridwidth = 4;
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 3;
			gbc_txtHeight.gridy = 3;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		{
		    btnColor = new JButton("Color");
		    btnColor.setBackground(Color.BLACK);
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Color initialColor = Color.BLACK;
					c1 = JColorChooser.showDialog(null, "Select a color", initialColor);
					
					btnColor.setBackground(c1);
					
				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnColor.gridx = 2;
			gbc_btnColor.gridy = 5;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		{
			btnFill = new JButton("Fill");
			btnFill.setBackground(Color.WHITE);
			btnFill.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Color initialColor = Color.BLACK;
					c2 = JColorChooser.showDialog(null, "Select a color", initialColor);
					
					btnFill.setBackground(c2);
				}
			});
			GridBagConstraints gbc_btnFill = new GridBagConstraints();
			gbc_btnFill.insets = new Insets(0, 0, 0, 5);
			gbc_btnFill.gridx = 2;
			gbc_btnFill.gridy = 6;
			contentPanel.add(btnFill, gbc_btnFill);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtX.getText().isEmpty() || txtY.getText().isEmpty() 
							|| txtWidth.getText().isEmpty() || txtHeight.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please fill the required fields!", "Error message",JOptionPane.ERROR_MESSAGE);
						}
						else if (!CheckIfNumber(txtX.getText()) || !CheckIfNumber(txtY.getText())
								|| !CheckIfNumber(txtWidth.getText()) || !CheckIfNumber(txtHeight.getText())) {
							JOptionPane.showMessageDialog(null, "Please input numbers only!", "Error message",JOptionPane.ERROR_MESSAGE);
						}
						else if (CheckIfNumber(txtX.getText()) && CheckIfNumber(txtY.getText())
								&& CheckIfNumber(txtWidth.getText()) && CheckIfNumber(txtHeight.getText())) {
							
							int x = Integer.parseInt(txtX.getText());
							
							int y = Integer.parseInt(txtY.getText());
							
							int width = Integer.parseInt(txtWidth.getText());
							
							int height = Integer.parseInt(txtHeight.getText());
							
							if (x<0 || y<0) {
								JOptionPane.showMessageDialog(null, "The value of the coordinates must be positive!", "Error message", JOptionPane.ERROR_MESSAGE);
							}
							else if (width <=0) {
								JOptionPane.showMessageDialog(null, "The value of the width must be positive!", "Error message", JOptionPane.ERROR_MESSAGE);
							}
							else if (height <=0) {
								JOptionPane.showMessageDialog(null, "The value of the height must be positive!", "Error message", JOptionPane.ERROR_MESSAGE);
							}
							else {
								Point upperLeftPoint = new Point(x,y);
								
							
							    
							   
								dispose();
							}
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setCancelClicked(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	 public boolean CheckIfNumber (String t) {
		 int number;
		 if (t == null || t.equals("")) {
		 		
			 return false;
			 }
		 try {
		 number = Integer.parseInt(t);
		 return true;
		}
		 catch (NumberFormatException e) {
			 return false;
		 }
	 }
	 
	 public boolean isCancelClicked() {
			return cancelClicked;
		}

		public void setCancelClicked(boolean cancelClicked) {
			this.cancelClicked = cancelClicked;
		}

		public Color getC2() {
			return c2;
		}

		public void setC2(Color c2) {
			this.c2 = c2;
		}

		public Color getC1() {
			return c1;
		}

		public void setC1(Color c1) {
			this.c1 = c1;
		}
		
		
		public Rectangle getRectangle() {
			return rectangle;
		}

		public void setRectangle(Rectangle rectangle) {
			this.rectangle = rectangle;
		}
		
		public JTextField getTxtX() {
			return txtX;
		}

		public JTextField getTxtY() {
			return txtY;
		}

		public JTextField getTxtWidth() {
			return txtWidth;
		}

		public JTextField getTxtHeight() {
			return txtHeight;
		}
		
		public JButton getBtnColor() {
			return btnColor;
		}

		public JButton getBtnFill() {
			return btnFill;
		}
}

	