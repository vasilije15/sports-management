package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import code.Player;
import code.Team;
import datebase.CheckConnection;
import datebase.DataBaseConnection;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class AddPlayersToTeams {

	private JFrame frameAddPlayersToTeams;
	private JTextArea textArea;
	private JLabel lblAllPlayers;
	private JComboBox comboBox;
	private JLabel lblChooseATeam;
	private JButton btnAdd;
	private JLabel lblInfo;

	private JList list;
	private JScrollPane scrollPane;

	private DataBaseConnection dbs;
	private CheckConnection cc;

	private List<String> playerNames;
	private List<String> teamNames;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPlayersToTeams window = new AddPlayersToTeams();
					window.frameAddPlayersToTeams.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// add players to teams

	/**
	 * Create the application.
	 */
	public AddPlayersToTeams() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		cc = new CheckConnection();
		dbs = cc.getJDBC();

		playerNames = dbs.getAllPlayersNames();
		teamNames = dbs.getAllTeamsNames();

		frameAddPlayersToTeams = new JFrame();
		frameAddPlayersToTeams.setResizable(false);
		frameAddPlayersToTeams.setTitle("WPMS - Add Players to Teams");
		frameAddPlayersToTeams.setIconImage(
				Toolkit.getDefaultToolkit().getImage(AddPlayersToTeams.class.getResource("/icons/water-polo (1).png")));
		frameAddPlayersToTeams.setBounds(100, 100, 690, 365);
		frameAddPlayersToTeams.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameAddPlayersToTeams.getContentPane().setLayout(null);
		frameAddPlayersToTeams.getContentPane().add(getTextArea());
		frameAddPlayersToTeams.getContentPane().add(getLblAllPlayers());
		frameAddPlayersToTeams.getContentPane().add(getComboBox());
		frameAddPlayersToTeams.getContentPane().add(getLblChooseATeam());
		frameAddPlayersToTeams.getContentPane().add(getBtnAdd());
		frameAddPlayersToTeams.getContentPane().add(getLblInfo());
		frameAddPlayersToTeams.getContentPane().add(getScrollPane_1());

	}

	public void open() {

		initialize();
		frameAddPlayersToTeams.setVisible(true);

	}

	public JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setToolTipText("Developed by Vasilije");
			textArea.setText("           Water Polo Management System");
			textArea.setForeground(Color.WHITE);
			textArea.setFont(new Font("Ebrima", Font.PLAIN, 35));
			textArea.setEnabled(false);
			textArea.setEditable(false);
			textArea.setBackground(Color.BLACK);
			textArea.setBounds(-32, 0, 716, 70);
		}
		return textArea;
	}

	public JLabel getLblAllPlayers() {
		if (lblAllPlayers == null) {
			lblAllPlayers = new JLabel("All players");
			lblAllPlayers.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblAllPlayers.setBounds(99, 86, 83, 20);
		}
		return lblAllPlayers;
	}

	public JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();

			comboBox.addItem("choose a team");

			for (int i = 0; i < teamNames.size(); i++) {
				String team = teamNames.get(i);
				comboBox.addItem(team);
			}

			comboBox.setBounds(359, 113, 145, 26);
		}
		return comboBox;
	}

	public JLabel getLblChooseATeam() {
		if (lblChooseATeam == null) {
			lblChooseATeam = new JLabel("Choose a team");
			lblChooseATeam.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblChooseATeam.setBounds(360, 86, 154, 20);
		}
		return lblChooseATeam;
	}

	public JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("ADD");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String valide = validate();
					if (valide != null) {

						JOptionPane.showMessageDialog(frameAddPlayersToTeams, valide);

					} else {


						Object[] selectedPlayerNames = list.getSelectedValues();
						List<String> playerNames = new ArrayList<>();

						for (Object obj : selectedPlayerNames) {
							playerNames.add((String) obj);
						}


						String selectedTeamName = comboBox.getSelectedItem().toString();


						int teamID = dbs.findTeam(selectedTeamName);

						for (int i = 0; i < playerNames.size(); i++) {

							String firstName = playerNames.get(i).split(" ")[0];
							String lastName = playerNames.get(i).split(" ")[1];


							String playerID = dbs.findPlayer(firstName, lastName);

							int check = dbs.insertPlayerToTeam(teamID, playerID);

							if (check == 1) {
								JOptionPane.showMessageDialog(frameAddPlayersToTeams, "You have successfully added "
										+ firstName + " " + lastName + " to team " + selectedTeamName + "!");
							} else {
								JOptionPane.showMessageDialog(frameAddPlayersToTeams,
										"Player " + firstName + " " + lastName + " already exists in team "
												+ selectedTeamName,
										"Try different player!", JOptionPane.WARNING_MESSAGE);
							}
						}


						dbs.updateStats(selectedTeamName);

					}

				}
			});
			btnAdd.setBounds(387, 211, 83, 29);
		}
		return btnAdd;
	}

	public String validate() {

		if (list.isSelectionEmpty()) {
			return "Please select a player to add!";
		}

		if (comboBox.getSelectedItem().toString().equals("choose a team")) {
			return "You must specify a team!";
		}

		return null;
	}

	public JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("Adds players from list to the specified team");
			lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));

			lblInfo.setBounds(314, 244, 267, 20);
		}
		return lblInfo;
	}

	public JList getList() {
		if (list == null) {

			DefaultListModel dlm = new DefaultListModel();

			for (int i = 0; i < playerNames.size(); i++) {

				String player = playerNames.get(i);
				dlm.addElement(player);

			}

			list = new JList(dlm);
		}
		return list;
	}

	public JScrollPane getScrollPane_1() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(74, 113, 137, 162);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
}
