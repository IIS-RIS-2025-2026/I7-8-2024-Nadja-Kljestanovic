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

public class DlgPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField textX;
	public JTextField textY;
	private Point point;
	private boolean commited;
	private Color color;
	protected JButton btnColor;
	
	public JButton getBtnColor() {
		return btnColor;
	}
	private boolean cancelClicked;
	
	
	
	public boolean isCancelClicked() {
		return cancelClicked;
	}

	public void setCancelClicked(boolean cancelClicked) {
		this.cancelClicked = cancelClicked;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isCommited() {
		return commited;
	}

	public void setCommited(boolean commited) {
		this.commited = commited;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint() {
		setTitle("Dialogue for Point");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblXCoordinate = new JLabel("X coordinate:");
			GridBagConstraints gbc_lblXCoordinate = new GridBagConstraints();
			gbc_lblXCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblXCoordinate.gridx = 1;
			gbc_lblXCoordinate.gridy = 0;
			contentPanel.add(lblXCoordinate, gbc_lblXCoordinate);
		}
		{
			textX = new JTextField();
			GridBagConstraints gbc_textX = new GridBagConstraints();
			gbc_textX.insets = new Insets(0, 0, 5, 0);
			gbc_textX.gridwidth = 4;
			gbc_textX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textX.gridx = 2;
			gbc_textX.gridy = 0;
			contentPanel.add(textX, gbc_textX);
			textX.setColumns(10);
		}
		{
			JLabel lblYCoordinate = new JLabel("Y coordinate:");
			GridBagConstraints gbc_lblYCoordinate = new GridBagConstraints();
			gbc_lblYCoordinate.anchor = GridBagConstraints.EAST;
			gbc_lblYCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblYCoordinate.gridx = 1;
			gbc_lblYCoordinate.gridy = 1;
			contentPanel.add(lblYCoordinate, gbc_lblYCoordinate);
		}
		{
			textY = new JTextField();
			GridBagConstraints gbc_textY = new GridBagConstraints();
			gbc_textY.insets = new Insets(0, 0, 5, 0);
			gbc_textY.gridwidth = 4;
			gbc_textY.fill = GridBagConstraints.HORIZONTAL;
			gbc_textY.gridx = 2;
			gbc_textY.gridy = 1;
			contentPanel.add(textY, gbc_textY);
			textY.setColumns(10);
		}
		{
			btnColor = new JButton("Color");
			btnColor.setBackground(Color.BLACK);
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color initialColor = Color.BLACK;
					color = JColorChooser.showDialog(null, "Select a color", initialColor);
					btnColor.setBackground(color);
				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnColor.gridx = 1;
			gbc_btnColor.gridy = 5;
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
						if (textX.getText().isEmpty() || textY.getText().isEmpty() ) {
							JOptionPane.showMessageDialog(null, "Please fill the required fields!", "Error message",JOptionPane.ERROR_MESSAGE);
						}
						else if (!CheckIfNumber(textX.getText()) || !CheckIfNumber(textY.getText())) {
							JOptionPane.showMessageDialog(null, "Please input numbers only!", "Error message",JOptionPane.ERROR_MESSAGE);
						}
						else if (getBtnColor().getBackground() == null) {
							JOptionPane.showMessageDialog(null, "Please select a color!", "Error message",JOptionPane.ERROR_MESSAGE);
						}
						else if (CheckIfNumber(textX.getText()) && CheckIfNumber(textX.getText())) {
							int x = Integer.parseInt(textX.getText());
							int y = Integer.parseInt(textY.getText());		
							if (x<0 || y<0) {
								JOptionPane.showMessageDialog(null, "The value of the coordinates must be positive!", "Error message", JOptionPane.ERROR_MESSAGE);
							} 
							else {
								
							 /*point = new Point(x,y);
							 point.setColor(color);*/
								System.out.println(getBtnColor().getBackground());
							 setCommited(true);
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
}
