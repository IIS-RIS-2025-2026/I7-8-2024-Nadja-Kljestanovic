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

import geometry.Line;
import geometry.Point;


public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtX1;
	

	protected JTextField txtY1;
	protected JTextField txtX2;
	protected JTextField txtY2;
	protected JButton btnColor;
    

	private Line line;
    private boolean cancelClicked;
   
    Color c1;
    
   
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setTitle("Dialogue for Line");
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
			JLabel lblFirstXCoordinate = new JLabel("First X coordinate:");
			GridBagConstraints gbc_lblFirstXCoordinate = new GridBagConstraints();
			gbc_lblFirstXCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblFirstXCoordinate.gridx = 1;
			gbc_lblFirstXCoordinate.gridy = 0;
			contentPanel.add(lblFirstXCoordinate, gbc_lblFirstXCoordinate);
		}
		{
			txtX1 = new JTextField();
			GridBagConstraints gbc_txtX1 = new GridBagConstraints();
			gbc_txtX1.gridwidth = 5;
			gbc_txtX1.insets = new Insets(0, 0, 5, 0);
			gbc_txtX1.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX1.gridx = 2;
			gbc_txtX1.gridy = 0;
			contentPanel.add(txtX1, gbc_txtX1);
			txtX1.setColumns(10);
		}
		{
			JLabel lblFirstYCoordinate = new JLabel("First Y coordinate:");
			GridBagConstraints gbc_lblFirstYCoordinate = new GridBagConstraints();
			gbc_lblFirstYCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblFirstYCoordinate.gridx = 1;
			gbc_lblFirstYCoordinate.gridy = 1;
			contentPanel.add(lblFirstYCoordinate, gbc_lblFirstYCoordinate);
		}
		{
			txtY1 = new JTextField();
			GridBagConstraints gbc_txtY1 = new GridBagConstraints();
			gbc_txtY1.gridwidth = 5;
			gbc_txtY1.insets = new Insets(0, 0, 5, 0);
			gbc_txtY1.anchor = GridBagConstraints.NORTH;
			gbc_txtY1.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY1.gridx = 2;
			gbc_txtY1.gridy = 1;
			contentPanel.add(txtY1, gbc_txtY1);
			txtY1.setColumns(10);
		}
		{
			JLabel lblSecondXCoordinate = new JLabel("Second X coordinate:");
			GridBagConstraints gbc_lblSecondXCoordinate = new GridBagConstraints();
			gbc_lblSecondXCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblSecondXCoordinate.gridx = 1;
			gbc_lblSecondXCoordinate.gridy = 3;
			contentPanel.add(lblSecondXCoordinate, gbc_lblSecondXCoordinate);
		}
		{
			txtX2 = new JTextField();
			GridBagConstraints gbc_txtX2 = new GridBagConstraints();
			gbc_txtX2.gridwidth = 5;
			gbc_txtX2.insets = new Insets(0, 0, 5, 0);
			gbc_txtX2.anchor = GridBagConstraints.NORTH;
			gbc_txtX2.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX2.gridx = 2;
			gbc_txtX2.gridy = 3;
			contentPanel.add(txtX2, gbc_txtX2);
			txtX2.setColumns(10);
		}
		{
			JLabel lblSecondYCoordinate = new JLabel("Second Y coordinate:");
			GridBagConstraints gbc_lblSecondYCoordinate = new GridBagConstraints();
			gbc_lblSecondYCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblSecondYCoordinate.gridx = 1;
			gbc_lblSecondYCoordinate.gridy = 4;
			contentPanel.add(lblSecondYCoordinate, gbc_lblSecondYCoordinate);
		}
		{
			txtY2 = new JTextField();
			GridBagConstraints gbc_txtY2 = new GridBagConstraints();
			gbc_txtY2.insets = new Insets(0, 0, 5, 0);
			gbc_txtY2.gridwidth = 5;
			gbc_txtY2.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY2.gridx = 2;
			gbc_txtY2.gridy = 4;
			contentPanel.add(txtY2, gbc_txtY2);
			txtY2.setColumns(10);
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
			gbc_btnColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnColor.gridx = 1;
			gbc_btnColor.gridy = 6;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtX1.getText().isEmpty() || txtY1.getText().isEmpty() 
							|| txtX1.getText().isEmpty() || txtY1.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please fill the required fields!", "Error message", JOptionPane.ERROR_MESSAGE);
						}
						else if (!CheckIfNumber(txtX1.getText()) || !CheckIfNumber(txtY1.getText())
								|| !CheckIfNumber(txtX2.getText()) || !CheckIfNumber(txtY2.getText()) ) {
							JOptionPane.showMessageDialog(null, "Please input numbers only!", "Error message", JOptionPane.ERROR_MESSAGE);
						}
						else if (CheckIfNumber(txtX1.getText()) && CheckIfNumber(txtY1.getText())
								&& CheckIfNumber(txtX2.getText()) && CheckIfNumber(txtY2.getText())) {
							int x1 = Integer.parseInt(txtX1.getText());
							int y1 = Integer.parseInt(txtY1.getText());
							int x2 = Integer.parseInt(txtX2.getText());
							int y2 = Integer.parseInt(txtY2.getText());
							
							if (x1<0 || y1<0 || x2<0 || y2<0) {
								JOptionPane.showMessageDialog(null,"All coordinates must be positive!","Error message", JOptionPane.ERROR_MESSAGE);
								
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
    public Color getC1() {
		return c1;
	}

	public void setC1(Color c1) {
		this.c1 = c1;
	}
    
	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}
	
	public JTextField getTxtX1() {
		return txtX1;
	}

	public JTextField getTxtY1() {
		return txtY1;
	}

	public JTextField getTxtX2() {
		return txtX2;
	}

	public JTextField getTxtY2() {
		return txtY2;
	}
	public JButton getBtnColor() {
		return btnColor;
	}



}
