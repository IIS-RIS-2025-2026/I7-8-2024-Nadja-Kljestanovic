package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgHexagon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFieldX;
	private JTextField txtFieldY;
	

	private JTextField txtFieldR;
	
	private JButton btnColor;
	private JButton btnFill;

	Color c1;
	Color c2;
	private boolean cancelClicked;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgHexagon dialog = new DlgHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgHexagon() {
		setTitle("Dialogue for Hexagon");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblXCoordinate = new JLabel("X coordinate:");
			GridBagConstraints gbc_lblXCoordinate = new GridBagConstraints();
			gbc_lblXCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblXCoordinate.gridx = 1;
			gbc_lblXCoordinate.gridy = 1;
			contentPanel.add(lblXCoordinate, gbc_lblXCoordinate);
		}
		{
			txtFieldX = new JTextField();
			GridBagConstraints gbc_txtFieldX = new GridBagConstraints();
			gbc_txtFieldX.insets = new Insets(0, 0, 5, 0);
			gbc_txtFieldX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtFieldX.gridx = 4;
			gbc_txtFieldX.gridy = 1;
			contentPanel.add(txtFieldX, gbc_txtFieldX);
			txtFieldX.setColumns(10);
		}
		{
			JLabel lblYCoordinate = new JLabel("Y coordinate");
			GridBagConstraints gbc_lblYCoordinate = new GridBagConstraints();
			gbc_lblYCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblYCoordinate.gridx = 1;
			gbc_lblYCoordinate.gridy = 2;
			contentPanel.add(lblYCoordinate, gbc_lblYCoordinate);
		}
		{
			txtFieldY = new JTextField();
			GridBagConstraints gbc_txtFieldY = new GridBagConstraints();
			gbc_txtFieldY.insets = new Insets(0, 0, 5, 0);
			gbc_txtFieldY.anchor = GridBagConstraints.NORTH;
			gbc_txtFieldY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtFieldY.gridx = 4;
			gbc_txtFieldY.gridy = 2;
			contentPanel.add(txtFieldY, gbc_txtFieldY);
			txtFieldY.setColumns(10);
		}
		{
			JLabel lblR = new JLabel("R:");
			GridBagConstraints gbc_lblR = new GridBagConstraints();
			gbc_lblR.insets = new Insets(0, 0, 5, 5);
			gbc_lblR.gridx = 1;
			gbc_lblR.gridy = 3;
			contentPanel.add(lblR, gbc_lblR);
		}
		{
			txtFieldR = new JTextField();
			GridBagConstraints gbc_txtFieldR = new GridBagConstraints();
			gbc_txtFieldR.insets = new Insets(0, 0, 5, 0);
			gbc_txtFieldR.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtFieldR.gridx = 4;
			gbc_txtFieldR.gridy = 3;
			contentPanel.add(txtFieldR, gbc_txtFieldR);
			txtFieldR.setColumns(10);
		}
		{
			btnColor = new JButton("Color");
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color initialColor = Color.BLACK;
					c1 = JColorChooser.showDialog(null, "Select a color", initialColor);
					
					btnColor.setBackground(c1);
				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnColor.gridx = 1;
			gbc_btnColor.gridy = 5;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		{
			 btnFill = new JButton("Fill");
			btnFill.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color initialColor = Color.BLACK;
					c2 = JColorChooser.showDialog(null, "Select a color", initialColor);
					
					btnFill.setBackground(c2);
				}
			});
			GridBagConstraints gbc_btnFill = new GridBagConstraints();
			gbc_btnFill.insets = new Insets(0, 0, 0, 5);
			gbc_btnFill.gridx = 1;
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
					public void actionPerformed(ActionEvent e) {
						
						if (txtFieldX.getText().isEmpty() || txtFieldY.getText().isEmpty() 
								|| txtFieldR.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Please fill the required fields!", "Error message",JOptionPane.ERROR_MESSAGE);
							}
							else if (!CheckIfNumber(txtFieldX.getText()) || !CheckIfNumber(txtFieldY.getText())
									|| !CheckIfNumber(txtFieldR.getText())) {
								JOptionPane.showMessageDialog(null, "Please input numbers only!", "Error message",JOptionPane.ERROR_MESSAGE);
							}
							else if (CheckIfNumber(txtFieldX.getText()) && CheckIfNumber(txtFieldY.getText())
									&& CheckIfNumber(txtFieldR.getText()) ) {
								
								int x = Integer.parseInt(txtFieldX.getText());
								
								int y = Integer.parseInt(txtFieldY.getText());
								
								int R = Integer.parseInt(txtFieldR.getText());
								
								
								
								if (x<0 || y<0) {
									JOptionPane.showMessageDialog(null, "The value of the coordinates must be positive!", "Error message", JOptionPane.ERROR_MESSAGE);
								}
								else if (R <=0) {
									JOptionPane.showMessageDialog(null, "The value of the width must be positive!", "Error message", JOptionPane.ERROR_MESSAGE);
								}
			
								else {
									
								
								    
								   
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
					public void actionPerformed(ActionEvent e) {
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
	
	public JTextField getTxtFieldX() {
		return txtFieldX;
	}

	public JTextField getTxtFieldY() {
		return txtFieldY;
	}

	public JTextField getTxtFieldR() {
		return txtFieldR;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public JButton getBtnFill() {
		return btnFill;
	}

}
