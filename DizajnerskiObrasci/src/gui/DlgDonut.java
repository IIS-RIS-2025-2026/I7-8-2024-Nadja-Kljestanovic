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

import geometry.Donut;
import geometry.Point;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtX;
	protected JTextField txtY;
	protected JTextField txtOuterRadius;
	protected JTextField txtInnerRadius;
	protected JButton btnColor;
	protected JButton btnFill;

	private Donut donut;
	private Color borderColor = Color.BLACK;
	private Color fillColor = Color.WHITE;
	private boolean cancelClicked;

	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgDonut() {
		setTitle("Dialogue for Donut");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 320);

		initGui();
	}

	private void initGui() {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 1, 0 };
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

		addLabel("Outer radius:", 2);
		txtOuterRadius = new JTextField();
		addComponent(txtOuterRadius, 2, 2, 4);

		addLabel("Inner radius:", 3);
		txtInnerRadius = new JTextField();
		addComponent(txtInnerRadius, 2, 3, 4);
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
			processDonutData();
		}
	}

	private void processDonutData() {
		int x = Integer.parseInt(txtX.getText());
		int y = Integer.parseInt(txtY.getText());
		int outerRadius = Integer.parseInt(txtOuterRadius.getText());
		int innerRadius = Integer.parseInt(txtInnerRadius.getText());

		if (x < 0 || y < 0) {
			showError("The value of the coordinates must be positive!");
		} else if (outerRadius <= 0 || innerRadius <= 0) {
			showError("The value of a radius must be positive!");
		} else if (innerRadius >= outerRadius) {
			showError("The value of the inner radius must be lower than the outer radius!");
		} else {
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
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.gridy = y;
		contentPanel.add(label, gbc);
	}

	private boolean isAnyFieldEmpty() {
		return txtX.getText().isEmpty() || txtY.getText().isEmpty() || txtOuterRadius.getText().isEmpty()
				|| txtInnerRadius.getText().isEmpty();
	}

	private boolean areFieldsNumeric() {
		return isNumeric(txtX.getText()) && isNumeric(txtY.getText()) && isNumeric(txtOuterRadius.getText())
				&& isNumeric(txtInnerRadius.getText());
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

	public Donut getDonut() {
		return donut;
	}

	public void setDonut(Donut donut) {
		this.donut = donut;
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public JTextField getTxtOuterRadius() {
		return txtOuterRadius;
	}

	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}
}