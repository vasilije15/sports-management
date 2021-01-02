package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import code.Player;
import code.Team;
import datebase.CheckConnection;
import datebase.DataBaseConnection;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class AddTeams {

	private JFrame frmWpmsAddTeams;
	private JTextArea textArea;
	private JLabel lblTeamName;
	private JTextField textFieldTeamName;
	private JLabel lblCountry;
	private JLabel lblCoachsName;
	private JTextField textFieldCoachName;
	private JButton btnAddTeam;
	private JComboBox comboBoxCountry;

	private DataBaseConnection dbs;
	private CheckConnection cc;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTeams window = new AddTeams();
					window.frmWpmsAddTeams.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Prozor za dodavanje timova
	
	/**
	 * Create the application.
	 */
	public AddTeams() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmWpmsAddTeams = new JFrame();
		frmWpmsAddTeams.setIconImage(Toolkit.getDefaultToolkit().getImage(AddTeams.class.getResource("/icons/water-polo (1).png")));
		frmWpmsAddTeams.setResizable(false);
		frmWpmsAddTeams.setTitle("WPMS - Add Teams");
		frmWpmsAddTeams.setBounds(100, 100, 700, 290);
		frmWpmsAddTeams.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmWpmsAddTeams.getContentPane().setLayout(null);
		frmWpmsAddTeams.getContentPane().add(getTextArea());
		frmWpmsAddTeams.getContentPane().add(getLblTeamName());
		frmWpmsAddTeams.getContentPane().add(getTextFieldTeamName());
		frmWpmsAddTeams.getContentPane().add(getLblCountry());
		frmWpmsAddTeams.getContentPane().add(getLblCoachsName());
		frmWpmsAddTeams.getContentPane().add(getTextFieldCoachName());
		frmWpmsAddTeams.getContentPane().add(getBtnAddTeam());
		frmWpmsAddTeams.getContentPane().add(getComboBoxCountry());
		frmWpmsAddTeams.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getTextFieldTeamName(), getComboBoxCountry(), getTextFieldCoachName(), getBtnAddTeam()}));

		cc = new CheckConnection();
		
		dbs = cc.getJDBC();
		
	}

	public void open() {

		initialize();
		frmWpmsAddTeams.setVisible(true);

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

	private JLabel getLblTeamName() {
		if (lblTeamName == null) {
			lblTeamName = new JLabel("Team Name");
			lblTeamName.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblTeamName.setBounds(25, 99, 103, 20);
		}
		return lblTeamName;
	}

	private JTextField getTextFieldTeamName() {
		if (textFieldTeamName == null) {
			textFieldTeamName = new JTextField();
			textFieldTeamName.setColumns(10);
			textFieldTeamName.setBounds(176, 97, 170, 25);
		}
		return textFieldTeamName;
	}

	private JLabel getLblCountry() {
		if (lblCountry == null) {
			lblCountry = new JLabel("Country");
			lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblCountry.setBounds(25, 138, 103, 20);
		}
		return lblCountry;
	}

	private JLabel getLblCoachsName() {
		if (lblCoachsName == null) {
			lblCoachsName = new JLabel("Coach's Name");
			lblCoachsName.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblCoachsName.setBounds(25, 195, 111, 20);
		}
		return lblCoachsName;
	}

	private JTextField getTextFieldCoachName() {
		if (textFieldCoachName == null) {
			textFieldCoachName = new JTextField();
			textFieldCoachName.setColumns(10);
			textFieldCoachName.setBounds(176, 193, 170, 25);
		}
		return textFieldCoachName;
	}

	private JButton getBtnAddTeam() {
		if (btnAddTeam == null) {
			btnAddTeam = new JButton("Add Team");
			btnAddTeam.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String validate = validateData();

					if (validate != null) {

						JOptionPane.showMessageDialog(frmWpmsAddTeams, validate, "Check Input",
								JOptionPane.WARNING_MESSAGE);
						return;

					} else {

						String teamName = textFieldTeamName.getText();
						String country = comboBoxCountry.getSelectedItem().toString();
						String coachName = textFieldCoachName.getText();

					

						Team team = new Team("", teamName, country, coachName);

						int check = dbs.insertTeam(team);

						if (check == 1) {
							
							JOptionPane.showMessageDialog(frmWpmsAddTeams, "You have successfully added team " + teamName + "!", "Successful", JOptionPane.INFORMATION_MESSAGE);
							
							textFieldTeamName.setText("");
							comboBoxCountry.setSelectedIndex(0);
							textFieldCoachName.setText("");
							
						} else {
							JOptionPane.showMessageDialog(frmWpmsAddTeams, "Team with name " + teamName + " already seems to exist!", "Error", JOptionPane.ERROR_MESSAGE);
							
						}
					}

				}
			});
			btnAddTeam.setForeground(Color.BLUE);
			btnAddTeam.setBounds(435, 121, 170, 57);
		}
		return btnAddTeam;
	}

	public String validateData() {

		if (textFieldTeamName.getText().length() < 2 || textFieldTeamName.getText().equals("")) {
			return "Team's name should be at least 2 characters!";
		}

		if (comboBoxCountry.getSelectedItem().equals("select a country")) {
			return "You need to select a country!";
		}

		if (textFieldCoachName.getText().length() < 2 || textFieldCoachName.getText().equals("")
				|| textFieldCoachName.getText().equals(" ")) {
			return "Please enter coach's name!";
		}

		return null;
	}

	private JComboBox getComboBoxCountry() {
		if (comboBoxCountry == null) {

			comboBoxCountry = new JComboBox();

			comboBoxCountry.addItem("select a country");

			
			String[] locales = Locale.getISOCountries();

			for (String countryCode : locales) {

				Locale obj = new Locale("", countryCode);

				comboBoxCountry.addItem(obj.getDisplayCountry());
			}

			comboBoxCountry.setBounds(176, 137, 170, 25);

		}
		return comboBoxCountry;
	}
}
