package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.JobAttributes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import code.Player;
import datebase.CheckConnection;
import datebase.DataBaseConnection;

import javax.swing.event.ChangeEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JProgressBar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.SwingConstants;

public class AddPlayers {

	private JFrame frameWpmsManage;
	private JTextArea textArea;
	private JLabel lblFirstName;
	private JTextField txtFieldFirstName;
	private JLabel lblLastName;
	private JTextField txtFieldLastName;
	private JLabel lblAge;
	private JTextField txtFieldAge;
	private JSlider slider;
	private JLabel lblHeight;
	private JLabel height;
	private JLabel lblWeight;
	private JSpinner spinner;
	private JLabel lblKg;
	private JLabel lblTypeOfPlayer;
	private JEditorPane editorPane;
	private JLabel lblComment;
	private JButton btnAddPlayer;
	private JLabel lblPlayerId;
	private JTextField textFieldPlayerID;

	private JRadioButton rdbtnGoalKeeper;
	private JRadioButton rdbtnRegular;
	private ButtonGroup type;

	private DataBaseConnection dbs;
	private CheckConnection cc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPlayers window = new AddPlayers();
					window.frameWpmsManage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the application.
	 */
	public AddPlayers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameWpmsManage = new JFrame();
		frameWpmsManage.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AddPlayers.class.getResource("/icons/water-polo (1).png")));
		frameWpmsManage.setResizable(false);
		frameWpmsManage.setTitle("WPMS - Add Players");
		frameWpmsManage.setBounds(100, 100, 701, 519);
		frameWpmsManage.getContentPane().setLayout(null);
		frameWpmsManage.getContentPane().add(getTextArea());
		frameWpmsManage.getContentPane().add(getLblFirstName());
		frameWpmsManage.getContentPane().add(getTxtFieldFirstName());
		frameWpmsManage.getContentPane().add(getLblLastName());
		frameWpmsManage.getContentPane().add(getTxtFieldLastName());
		frameWpmsManage.getContentPane().add(getLblAge());
		frameWpmsManage.getContentPane().add(getTxtFieldAge());
		frameWpmsManage.getContentPane().add(getSlider());
		frameWpmsManage.getContentPane().add(getLblHeight());
		frameWpmsManage.getContentPane().add(getHeight());
		frameWpmsManage.getContentPane().add(getLblWeight());
		frameWpmsManage.getContentPane().add(getSpinner());
		frameWpmsManage.getContentPane().add(getLblKg());
		frameWpmsManage.getContentPane().add(getLblTypeOfPlayer());
		frameWpmsManage.getContentPane().add(getEditorPane());
		frameWpmsManage.getContentPane().add(getLblComment());
		frameWpmsManage.getContentPane().add(getBtnAddPlayer());
		frameWpmsManage.getContentPane().add(getLblPlayerId());
		frameWpmsManage.getContentPane().add(getTextFieldPlayerID());
		frameWpmsManage.getContentPane().add(getRdbtnGoalKeeper());
		frameWpmsManage.getContentPane().add(getRdbtnRegular());

		type = new ButtonGroup();
		type.add(rdbtnRegular);
		type.add(rdbtnGoalKeeper);
		frameWpmsManage.getContentPane()
				.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { getTxtFieldFirstName(),
						getTxtFieldLastName(), getTxtFieldAge(), getSlider(), getSpinner(), getRdbtnGoalKeeper(),
						getRdbtnRegular(), getTextArea(), getBtnAddPlayer() }));
		frameWpmsManage.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { getTextFieldPlayerID(),
				getTxtFieldFirstName(), getTxtFieldLastName(), getTxtFieldAge(), getSlider(), getSpinner(),
				getRdbtnGoalKeeper(), getRdbtnRegular(), getBtnAddPlayer() }));

		cc = new CheckConnection();
		dbs = cc.getJDBC();

	}

	public void open() {

		initialize();
		frameWpmsManage.setVisible(true);

	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEnabled(false);
			textArea.setToolTipText("Developed by Vasilije");
			textArea.setText("           Water Polo Management System");
			textArea.setForeground(Color.WHITE);
			textArea.setFont(new Font("Ebrima", Font.PLAIN, 35));
			textArea.setEditable(false);
			textArea.setBackground(Color.BLACK);
			textArea.setBounds(0, 0, 700, 70);
		}
		return textArea;
	}

	private JLabel getLblFirstName() {
		if (lblFirstName == null) {
			lblFirstName = new JLabel("First Name");
			lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblFirstName.setBounds(40, 129, 103, 20);
		}
		return lblFirstName;
	}

	private JTextField getTxtFieldFirstName() {
		if (txtFieldFirstName == null) {
			txtFieldFirstName = new JTextField();
			txtFieldFirstName.setBounds(178, 129, 150, 25);
			txtFieldFirstName.setColumns(10);
		}
		return txtFieldFirstName;
	}

	private JLabel getLblLastName() {
		if (lblLastName == null) {
			lblLastName = new JLabel("Last Name");
			lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblLastName.setBounds(40, 171, 103, 20);
		}
		return lblLastName;
	}

	private JTextField getTxtFieldLastName() {
		if (txtFieldLastName == null) {
			txtFieldLastName = new JTextField();
			txtFieldLastName.setColumns(10);
			txtFieldLastName.setBounds(178, 171, 150, 25);
		}
		return txtFieldLastName;
	}

	private JLabel getLblAge() {
		if (lblAge == null) {
			lblAge = new JLabel("Age");
			lblAge.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblAge.setBounds(40, 213, 103, 20);
		}
		return lblAge;
	}

	private JTextField getTxtFieldAge() {
		if (txtFieldAge == null) {
			txtFieldAge = new JTextField();
			txtFieldAge.setColumns(10);
			txtFieldAge.setBounds(178, 213, 45, 25);
		}
		return txtFieldAge;
	}

	private JSlider getSlider() {
		if (slider == null) {
			slider = new JSlider(JSlider.HORIZONTAL, 160, 220, 160);
			slider.setMajorTickSpacing(5);
			slider.setPaintTicks(true);

			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					int value = slider.getValue();
					height.setText(value + " cm");
				}
			});

			slider.setBounds(178, 255, 200, 26);
		}
		return slider;
	}

	private JLabel getLblHeight() {
		if (lblHeight == null) {
			lblHeight = new JLabel("Height");
			lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblHeight.setBounds(40, 255, 103, 20);
		}
		return lblHeight;
	}

	private JLabel getHeight() {
		if (height == null) {
			height = new JLabel("160 cm");
			height.setFont(new Font("Tahoma", Font.PLAIN, 18));
			height.setBounds(237, 289, 75, 20);
		}
		return height;
	}

	private JLabel getLblWeight() {
		if (lblWeight == null) {
			lblWeight = new JLabel("Weight");
			lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblWeight.setBounds(40, 325, 103, 20);
		}
		return lblWeight;
	}

	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setFont(new Font("Tahoma", Font.PLAIN, 18));
			spinner.setModel(new SpinnerNumberModel(50, 40, 200, 1));

			spinner.setBounds(158, 325, 63, 38);
		}
		return spinner;
	}

	private JLabel getLblKg() {
		if (lblKg == null) {
			lblKg = new JLabel("kg");
			lblKg.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblKg.setBounds(231, 334, 25, 20);
		}
		return lblKg;
	}

	private JLabel getLblTypeOfPlayer() {
		if (lblTypeOfPlayer == null) {
			lblTypeOfPlayer = new JLabel("Type of Player");
			lblTypeOfPlayer.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblTypeOfPlayer.setBounds(40, 400, 129, 20);
		}
		return lblTypeOfPlayer;
	}

	private JEditorPane getEditorPane() {
		if (editorPane == null) {
			editorPane = new JEditorPane();
			editorPane.setBounds(469, 129, 211, 180);
		}
		return editorPane;
	}

	private JLabel getLblComment() {
		if (lblComment == null) {
			lblComment = new JLabel("Comment");
			lblComment.setVerticalAlignment(SwingConstants.TOP);
			lblComment.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblComment.setBounds(523, 86, 103, 20);
		}
		return lblComment;
	}

	private JButton getBtnAddPlayer() {
		if (btnAddPlayer == null) {
			btnAddPlayer = new JButton("Add Player");
			btnAddPlayer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String checkData = validateData();

					if (checkData != null) {

						JOptionPane.showMessageDialog(frameWpmsManage, checkData, "Check Input",
								JOptionPane.ERROR_MESSAGE);
						return;

					} else {

						String playerID = textFieldPlayerID.getText();
						String firstName = txtFieldFirstName.getText();
						String lastName = txtFieldLastName.getText();
						int age = Integer.parseInt(getTxtFieldAge().getText());
						int height = slider.getValue();
						int weight = (int) spinner.getValue();

						String typeOfPlayer = "Regular";

						if (rdbtnGoalKeeper.isSelected()) {
							typeOfPlayer = "Goalkeeper";
						}

						String comment = editorPane.getText();

						Player p = new Player(playerID, firstName, lastName, age, height, weight, typeOfPlayer,
								comment);

						
						int checkQuery = dbs.insertPlayer(p);

						if (checkQuery == 1) {
							JOptionPane.showMessageDialog(frameWpmsManage,
									"Player with ID " + playerID + " has been successfully added!", "Successful",
									JOptionPane.INFORMATION_MESSAGE);
							textFieldPlayerID.setText("");
							txtFieldFirstName.setText("");
							txtFieldLastName.setText("");
							txtFieldAge.setText("");
							slider.setValue(180);
							spinner.setValue(80);
							editorPane.setText("");

						} else {
							JOptionPane.showMessageDialog(frameWpmsManage, "That player already seems to exist!",
									"Try Again!", JOptionPane.ERROR_MESSAGE);
							textFieldPlayerID.setText("");
						}

					}

				}
			});
			btnAddPlayer.setForeground(Color.BLUE);
			btnAddPlayer.setBounds(497, 372, 129, 57);
		}
		return btnAddPlayer;
	}


	public String validateData() {

		if (textFieldPlayerID.getText().length() < 3 || textFieldPlayerID.getText().contains(" ")) {
			return "Player ID should be at least 3 characters and have no spaces!";
		}

		if (txtFieldFirstName.getText().length() < 2 || txtFieldFirstName.getText().contains(" ")) {
			return "First name should be at least 2 characters!";
		}

		if (txtFieldLastName.getText().length() < 2 || txtFieldLastName.getText().contains(" ")) {
			return "Last name should be at least 2 characters!";
		}

		String age = getTxtFieldAge().getText();

		try {
			int ageInt = Integer.parseInt(age);
			if (ageInt < 0 || ageInt > 150) {
				return "Age should be between 0 and 150!";
			}

		} catch (NumberFormatException e) {
			return "There should be a number in age text field!";
		}

		if ((int) spinner.getValue() < 0 || (int) spinner.getValue() > 400) {
			return "Weight should be normal!";
		}

		return null;

	}

	public JLabel getLblPlayerId() {
		if (lblPlayerId == null) {
			lblPlayerId = new JLabel("Player ID");
			lblPlayerId.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblPlayerId.setBounds(40, 86, 103, 20);
		}
		return lblPlayerId;
	}

	public JTextField getTextFieldPlayerID() {
		if (textFieldPlayerID == null) {
			textFieldPlayerID = new JTextField();
			textFieldPlayerID.setColumns(10);
			textFieldPlayerID.setBounds(178, 86, 150, 25);
		}
		return textFieldPlayerID;
	}

	public JRadioButton getRdbtnGoalKeeper() {
		if (rdbtnGoalKeeper == null) {
			rdbtnGoalKeeper = new JRadioButton("Goalkeeper");
			rdbtnGoalKeeper.setFont(new Font("Tahoma", Font.PLAIN, 18));
			rdbtnGoalKeeper.setBounds(173, 397, 132, 29);
		}
		return rdbtnGoalKeeper;
	}

	public JRadioButton getRdbtnRegular() {
		if (rdbtnRegular == null) {
			rdbtnRegular = new JRadioButton("Regular");
			rdbtnRegular.setSelected(true);
			rdbtnRegular.setFont(new Font("Tahoma", Font.PLAIN, 18));
			rdbtnRegular.setBounds(312, 397, 155, 29);
		}
		return rdbtnRegular;
	}
}
