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
	private Color lineColor = Color.BLACK;

	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgLine() {
		setTitle("Dialogue for Line");
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
		addColorButton();
		addControlButtons();
	}

	private void addInputFields() {
		addLabel("First X coordinate:", 0);
		txtX1 = new JTextField();
		addComponent(txtX1, 2, 0, 5);

		addLabel("First Y coordinate:", 1);
		txtY1 = new JTextField();
		addComponent(txtY1, 2, 1, 5);

		addLabel("Second X coordinate:", 3);
		txtX2 = new JTextField();
		addComponent(txtX2, 2, 3, 5);

		addLabel("Second Y coordinate:", 4);
		txtY2 = new JTextField();
		addComponent(txtY2, 2, 4, 5);
	}

	private void addColorButton() {
		btnColor = new JButton("Color");
		btnColor.setBackground(lineColor);
		btnColor.addActionListener(e -> {
			Color chosen = JColorChooser.showDialog(null, "Select a color", lineColor);
			if (chosen != null) {
				lineColor = chosen;
				btnColor.setBackground(lineColor);
			}
		});
		addComponent(btnColor, 1, 6, 1);
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
			processLineData();
		}
	}

	private void processLineData() {
		int x1 = Integer.parseInt(txtX1.getText());
		int y1 = Integer.parseInt(txtY1.getText());
		int x2 = Integer.parseInt(txtX2.getText());
		int y2 = Integer.parseInt(txtY2.getText());

		if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0) {
			showError("All coordinates must be positive!");
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
		return txtX1.getText().isEmpty() || txtY1.getText().isEmpty() || txtX2.getText().isEmpty()
				|| txtY2.getText().isEmpty();
	}

	private boolean areFieldsNumeric() {
		return isNumeric(txtX1.getText()) && isNumeric(txtY1.getText()) && isNumeric(txtX2.getText())
				&& isNumeric(txtY2.getText());
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

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color color) {
		this.lineColor = color;
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

	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}
}