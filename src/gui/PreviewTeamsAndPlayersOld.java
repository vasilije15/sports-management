package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.CellRendererPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Highlighter;

import code.Player;
import code.Team;
import code.TeamPlayer;
import datebase.CheckConnection;
import datebase.DataBaseConnection;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PreviewTeamsAndPlayersOld extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable tableAll;
	private JTextArea txtrWaterPoloManagement;
	private JScrollPane scrollPane_1;
	private JLabel lblPlayerInfo;
	private JLabel lblInfoTxtField;


	private List<Player> playersList;
	private List<Team> teamsList;
	private List<TeamPlayer> teamPlayersList;
	private JComboBox comboBoxTeamNames;

	private String choosenTeam = "";
	private JLabel lblCoach;
	
	private DataBaseConnection dbs;
	private CheckConnection cc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreviewTeamsAndPlayersOld frame = new PreviewTeamsAndPlayersOld();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	// prikazuje koji igraci igraju u kojem timu
	
	
	public PreviewTeamsAndPlayersOld() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PreviewTeamsAndPlayersOld.class.getResource("/icons/water-polo (1).png")));
		setTitle("WPMS - Team - Players");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 751, 525);

		cc = new CheckConnection();
		dbs = cc.getJDBC();
		playersList = dbs.getAllPlayers();
		teamsList = dbs.getAllTeams();
		teamPlayersList = dbs.getPlayersFromTeams();

		contentPane = new JPanel();
		contentPane.add(getLblCoach());
		contentPane.add(getComboBoxTeamNames());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTxtrWaterPoloManagement());
		contentPane.add(getScrollPane_1());
		contentPane.add(getLblPlayerInfo());
		contentPane.add(getLblInfoTxtField());

	}

	private JTable getTableAll() {
		if (tableAll == null) {

			String[] col = { "Team", "Player ID", "First Name", "Last Name" };

			tableModel = new DefaultTableModel(col, 0);

			for (int i = 0; i < teamPlayersList.size(); i++) {

				String teamName = teamPlayersList.get(i).getTeamName();
				String playerID = teamPlayersList.get(i).getPlayerID();
				String firstName = teamPlayersList.get(i).getFirstName();
				String lastName = teamPlayersList.get(i).getLastName();
				
				Object[] obj = { teamName, playerID, firstName, lastName };
				tableModel.addRow(obj);

			}

			tableModel.setColumnIdentifiers(col);

			tableAll = new JTable(tableModel);

			for (int i = 0; i < tableAll.getColumnCount(); i++) {
				TableColumn column = tableAll.getTableHeader().getColumnModel().getColumn(i);

				if (i == 0) {
					column.setMinWidth(150);
					column.setMaxWidth(300);

				} else {
					column.setMinWidth(180);
					column.setMaxWidth(300);
				}

			}

			tableAll.setRowHeight(50);

			tableAll.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tableAll.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 20));
			tableAll.getTableHeader().setBackground(Color.BLACK);
			tableAll.getTableHeader().setForeground(Color.WHITE);

			tableAll.setFont(new Font("Tahoma", Font.PLAIN, 22));
		}
		return tableAll;
	}

	public JTextArea getTxtrWaterPoloManagement() {
		if (txtrWaterPoloManagement == null) {
			txtrWaterPoloManagement = new JTextArea();
			txtrWaterPoloManagement.setToolTipText("Developed by Vasilije");
			txtrWaterPoloManagement.setText("            Water Polo Management System");
			txtrWaterPoloManagement.setForeground(Color.WHITE);
			txtrWaterPoloManagement.setFont(new Font("Ebrima", Font.PLAIN, 35));
			txtrWaterPoloManagement.setEnabled(false);
			txtrWaterPoloManagement.setEditable(false);
			txtrWaterPoloManagement.setBackground(Color.BLACK);
			txtrWaterPoloManagement.setBounds(0, 0, 745, 70);
		}
		return txtrWaterPoloManagement;
	}

	public JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane_1.setBounds(20, 201, 710, 269);
			scrollPane_1.setViewportView(getTableAll());
		}
		return scrollPane_1;
	}

	public JLabel getLblPlayerInfo() {
		if (lblPlayerInfo == null) {
			lblPlayerInfo = new JLabel("Team - Players Information");
			lblPlayerInfo.setForeground(Color.BLACK);
			lblPlayerInfo.setBackground(Color.RED);
			lblPlayerInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
			lblPlayerInfo.setVerticalAlignment(SwingConstants.TOP);
			lblPlayerInfo.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayerInfo.setBounds(0, 74, 745, 31);
		}
		return lblPlayerInfo;
	}

	public JLabel getLblInfoTxtField() {
		if (lblInfoTxtField == null) {
			lblInfoTxtField = new JLabel("Shows players which play for that team");
			lblInfoTxtField.setFont(new Font("Tahoma", Font.ITALIC, 14));
			lblInfoTxtField.setBounds(20, 165, 249, 20);
		}
		return lblInfoTxtField;
	}

	private JComboBox getComboBoxTeamNames() {
		if (comboBoxTeamNames == null) {
			comboBoxTeamNames = new JComboBox();
			comboBoxTeamNames.addItem("choose a team");
			for (Team t : teamsList) {
				String tName = t.getTeamName();
				comboBoxTeamNames.addItem(tName);
			}
			

			
			comboBoxTeamNames.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {

					choosenTeam = comboBoxTeamNames.getSelectedItem().toString();
					
					DefaultTableModel table = (DefaultTableModel) tableAll.getModel();
					TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
					tableAll.setRowSorter(tr);
					tr.setRowFilter(RowFilter.regexFilter(choosenTeam,0)); 

					String coach = ""; 
					for (int i = 0; i < teamsList.size(); i++) {
						if (teamsList.get(i).getTeamName().equals(choosenTeam)) {
							coach = teamsList.get(i).getCoachName();
							break;
						}
					}
					lblCoach.setText("Coach: " + coach);

				}
			});

			comboBoxTeamNames.setBounds(25, 137, 153, 25);
		}
		return comboBoxTeamNames;
	}

	private JLabel getLblCoach() {
		if (lblCoach == null) {
			lblCoach = new JLabel("");
			lblCoach.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
			lblCoach.setBounds(200, 139, 530, 23);
		}
		return lblCoach;
	}
}
