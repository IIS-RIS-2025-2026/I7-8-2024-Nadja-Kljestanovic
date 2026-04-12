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

import geometry.Circle;
import geometry.Point;

public class DlgCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtX;
	protected JTextField txtY;
	protected JTextField txtRadius;
	protected JButton btnColor;
	protected JButton btnFill;

	private Circle circle;
	private Color borderColor = Color.BLACK;
	private Color fillColor = Color.WHITE;
	private boolean cancelClicked;

	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgCircle() {
		setTitle("Dialogue for Circle");
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
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		contentPanel.setLayout(gbl_contentPanel);

		addInputFields();
		addColorButtons();
		addControlButtons();
	}

	private void addInputFields() {
		addLabel("X coordinate:", 0);
		txtX = new JTextField();
		addComponent(txtX, 2, 0, 4);

		addLabel("Y coordinate:", 1);
		txtY = new JTextField();
		addComponent(txtY, 2, 1, 4);

		addLabel("Radius:", 2);
		txtRadius = new JTextField();
		addComponent(txtRadius, 2, 2, 4);
	}

	private void addColorButtons() {
		btnColor = new JButton("Color");
		btnColor.setBackground(borderColor);
		btnColor.addActionListener(e -> {
			Color chosen = JColorChooser.showDialog(null, "Select border color", borderColor);
			if (chosen != null) {
				borderColor = chosen;
				btnColor.setBackground(borderColor);
			}
		});
		addComponent(btnColor, 1, 5, 1);

		btnFill = new JButton("Fill");
		btnFill.setBackground(fillColor);
		btnFill.addActionListener(e -> {
			Color chosen = JColorChooser.showDialog(null, "Select fill color", fillColor);
			if (chosen != null) {
				fillColor = chosen;
				btnFill.setBackground(fillColor);
			}
		});
		addComponent(btnFill, 1, 6, 1);
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
		} else {
			processCircleData();
		}
	}

	private void processCircleData() {
		try {
			int x = Integer.parseInt(txtX.getText());
			int y = Integer.parseInt(txtY.getText());
			int radius = Integer.parseInt(txtRadius.getText());

			if (x < 0 || y < 0) {
				showError("The value of the coordinates must be positive!");
			} else if (radius <= 0) {
				showError("The value of the radius must be positive!");
			} else {
				dispose();
			}
		} catch (NumberFormatException e) {
			showError("Invalid number format!");
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
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.gridy = y;
		contentPanel.add(label, gbc);
	}

	private boolean isAnyFieldEmpty() {
		return txtX.getText().isEmpty() || txtY.getText().isEmpty() || txtRadius.getText().isEmpty();
	}

	private boolean areFieldsNumeric() {
		return isNumeric(txtX.getText()) && isNumeric(txtY.getText()) && isNumeric(txtRadius.getText());
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

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color color) {
		this.borderColor = color;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color color) {
		this.fillColor = color;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}

	public JButton getBtnFill() {
		return btnFill;
	}

	public void setBtnFill(JButton btnFill) {
		this.btnFill = btnFill;
	}
}