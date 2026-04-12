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
	protected JButton btnColor;
	protected JButton btnFill;

	private Rectangle rectangle;
	private Color borderColor = Color.BLACK;
	private Color fillColor = Color.WHITE;
	private boolean cancelClicked;

	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgRectangle() {
		setTitle("Dialogue for Rectangle");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		initGui();
	}

	private void initGui() {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		contentPanel.setLayout(gbl_contentPanel);

		addInputFields();
		addColorButtons();
		addControlButtons();
	}

	private void addInputFields() {
		addLabel("X coordinate:", 0);
		txtX = new JTextField();
		addComponent(txtX, 3, 0, 4);

		addLabel("Y coordinate:", 1);
		txtY = new JTextField();
		addComponent(txtY, 3, 1, 4);

		addLabel("Width:", 2);
		txtWidth = new JTextField();
		addComponent(txtWidth, 3, 2, 4);

		addLabel("Height:", 3);
		txtHeight = new JTextField();
		addComponent(txtHeight, 3, 3, 4);
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
		addComponent(btnColor, 2, 5, 1);

		btnFill = new JButton("Fill");
		btnFill.setBackground(fillColor);
		btnFill.addActionListener(e -> {
			Color chosen = JColorChooser.showDialog(null, "Select fill color", fillColor);
			if (chosen != null) {
				fillColor = chosen;
				btnFill.setBackground(fillColor);
			}
		});
		addComponent(btnFill, 2, 6, 1);
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
			processRectangleData();
		}
	}

	private void processRectangleData() {
		int x = Integer.parseInt(txtX.getText());
		int y = Integer.parseInt(txtY.getText());
		int width = Integer.parseInt(txtWidth.getText());
		int height = Integer.parseInt(txtHeight.getText());

		if (x < 0 || y < 0) {
			showError("The value of the coordinates must be positive!");
		} else if (width <= 0) {
			showError("The value of the width must be positive!");
		} else if (height <= 0) {
			showError("The value of the height must be positive!");
		} else {
			this.rectangle = new Rectangle(new Point(x, y), width, height);
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
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 2;
		gbc.gridy = y;
		contentPanel.add(label, gbc);
	}

	private boolean isAnyFieldEmpty() {
		return txtX.getText().isEmpty() || txtY.getText().isEmpty() || txtWidth.getText().isEmpty()
				|| txtHeight.getText().isEmpty();
	}

	private boolean areFieldsNumeric() {
		return isNumeric(txtX.getText()) && isNumeric(txtY.getText()) && isNumeric(txtWidth.getText())
				&& isNumeric(txtHeight.getText());
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

	public void setCancelClicked(boolean cancelClicked) {
		this.cancelClicked = cancelClicked;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
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
}