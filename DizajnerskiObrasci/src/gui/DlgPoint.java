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
	protected JTextField txtX;
	protected JTextField txtY;
	protected JButton btnColor;

	private Point point;
	private Color pointColor = Color.BLACK;
	private boolean commited;
	private boolean cancelClicked;

	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgPoint() {
		setTitle("Dialogue for Point");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		initGui();
	}

	private void initGui() {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		contentPanel.setLayout(gbl_contentPanel);

		addInputFields();
		addColorButton();
		addControlButtons();
	}

	private void addInputFields() {
		addLabel("X coordinate:", 0);
		txtX = new JTextField();
		addComponent(txtX, 2, 0, 4);

		addLabel("Y coordinate:", 1);
		txtY = new JTextField();
		addComponent(txtY, 2, 1, 4);
	}

	private void addColorButton() {
		btnColor = new JButton("Color");
		btnColor.setBackground(pointColor);
		btnColor.addActionListener(e -> {
			Color chosen = JColorChooser.showDialog(null, "Select a color", pointColor);
			if (chosen != null) {
				pointColor = chosen;
				btnColor.setBackground(pointColor);
			}
		});
		addComponent(btnColor, 1, 5, 1);
	}

	private void addControlButtons() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(e -> handleOkAction());
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			cancelClicked = true;
			dispose();
		});
		buttonPane.add(cancelButton);
	}

	private void handleOkAction() {
		if (isAnyFieldEmpty()) {
			showError("Please fill the required fields!");
		} else if (!areFieldsNumeric()) {
			showError("Please input numbers only!");
		} else if (pointColor == null) {
			showError("Please select a color!");
		} else {
			processPointData();
		}
	}

	private void processPointData() {
		int x = Integer.parseInt(txtX.getText());
		int y = Integer.parseInt(txtY.getText());

		if (x < 0 || y < 0) {
			showError("The value of the coordinates must be positive!");
		} else {
			this.point = new Point(x, y);
			this.point.setBorderColor(pointColor);
			setCommited(true);
			dispose();
		}
	}

	private void addComponent(javax.swing.JComponent comp, int x, int y, int width) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		contentPanel.add(comp, gbc);
	}

	private void addLabel(String text, int y) {
		JLabel label = new JLabel(text);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 1;
		gbc.gridy = y;
		contentPanel.add(label, gbc);
	}

	private boolean isAnyFieldEmpty() {
		return txtX.getText().isEmpty() || txtY.getText().isEmpty();
	}

	private boolean areFieldsNumeric() {
		return isNumeric(txtX.getText()) && isNumeric(txtY.getText());
	}

	public boolean isNumeric(String t) {
		if (t == null || t.isEmpty())
			return false;
		try {
			Integer.parseInt(t);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error message", JOptionPane.ERROR_MESSAGE);
	}

	public boolean isCancelClicked() {
		return cancelClicked;
	}

	public void setCancelClicked(boolean cancelClicked) {
		this.cancelClicked = cancelClicked;
	}

	public Color getPointColor() {
		return pointColor;
	}

	public void setPointColor(Color color) {
		this.pointColor = color;
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

	public JTextField getTxtX() {
		return txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JButton getBtnColor() {
		return btnColor;
	}
}