package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import code.MyThread;
import code.Player;
import code.Stats;
import code.Team;
import datebase.CheckConnection;
import datebase.DataBaseConnection;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.RowFilter;

import java.awt.Toolkit;
import java.io.File;
import java.util.List;

import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PreviewStats extends JFrame {

	private JPanel contentPane;
	private JLabel labelInfo;
	private JTextArea txtrWaterPoloManagement;

	
	private List<Team> teamsList;
	private JPanel panel;
	private JComboBox comboBoxTeam;
	private JComboBox comboBoxSpeed;
	private JButton buttonShowStats;

	private Stats stats;
	private String choosenTeam;
	private List<Double> statsList;

	private DataBaseConnection dbs;
	private CheckConnection cc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreviewStats frame = new PreviewStats();
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
	public PreviewStats() {
		setTitle("WMPS - Stats");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PreviewStats.class.getResource("/icons/water-polo (1).png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 700);

		cc = new CheckConnection();
		dbs = cc.getJDBC();
		teamsList = dbs.getAllTeams();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLabelInfo());
		contentPane.add(getTxtrWaterPoloManagement());
		contentPane.add(getPanel());
		contentPane.add(getComboBoxTeam());
		contentPane.add(getComboBoxSpeed());
		contentPane.add(getButtonShowStats());

	}

	public JLabel getLabelInfo() {
		if (labelInfo == null) {
			labelInfo = new JLabel("Teams Averages");
			labelInfo.setVerticalAlignment(SwingConstants.TOP);
			labelInfo.setHorizontalAlignment(SwingConstants.CENTER);
			labelInfo.setForeground(Color.BLACK);
			labelInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
			labelInfo.setBackground(Color.RED);
			labelInfo.setBounds(0, 80, 994, 31);
		}
		return labelInfo;
	}

	public JTextArea getTxtrWaterPoloManagement() {
		if (txtrWaterPoloManagement == null) {
			txtrWaterPoloManagement = new JTextArea();
			txtrWaterPoloManagement.setEnabled(false);
			txtrWaterPoloManagement.setEditable(false);
			txtrWaterPoloManagement.setToolTipText("Developed by Vasilije");
			txtrWaterPoloManagement.setText("                             Water Polo Management System");
			txtrWaterPoloManagement.setForeground(Color.WHITE);
			txtrWaterPoloManagement.setFont(new Font("Ebrima", Font.PLAIN, 35));
			txtrWaterPoloManagement.setBackground(Color.BLACK);
			txtrWaterPoloManagement.setBounds(0, 0, 994, 70);

		}
		return txtrWaterPoloManagement;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(15, 200, 964, 444);
			panel.setLayout(null);
		}
		return panel;
	}

	private JComboBox getComboBoxTeam() {
		if (comboBoxTeam == null) {
			comboBoxTeam = new JComboBox();
			comboBoxTeam.addItem("choose a team");

			for (Team team : teamsList) {
				comboBoxTeam.addItem(team.getTeamName());
			}

			comboBoxTeam.setBounds(396, 127, 209, 26);
		}
		return comboBoxTeam;
	}

	private JComboBox getComboBoxSpeed() {
		if (comboBoxSpeed == null) {
			comboBoxSpeed = new JComboBox();
			comboBoxSpeed.addItem("choose speed");
			comboBoxSpeed.addItem("fast");
			comboBoxSpeed.addItem("medium");
			comboBoxSpeed.addItem("slow");
			comboBoxSpeed.setBounds(15, 127, 133, 26);
		}
		return comboBoxSpeed;
	}

	private JButton getButtonShowStats() {
		if (buttonShowStats == null) {
			buttonShowStats = new JButton("Show Stats");
			buttonShowStats.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
			buttonShowStats.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (comboBoxSpeed.getSelectedIndex() == 0) {
						panel.removeAll();
						panel.repaint();
						JOptionPane.showMessageDialog(panel, "Please choose speed to show the graph!");
						return;
					}

					if (comboBoxTeam.getSelectedIndex() == 0) {
						panel.removeAll();
						panel.repaint();
						JOptionPane.showMessageDialog(panel, "Please choose a team to show the graph!");
						return;
					}

					choosenTeam = comboBoxTeam.getSelectedItem().toString();

					if ((stats = dbs.getStats(choosenTeam)) != null) {
						statsList = stats.allStatsList();
					} else {
						panel.removeAll();
						panel.repaint();
						JOptionPane.showMessageDialog(panel, "Please insert at least one player to team " + choosenTeam
								+ " in order to show the graph!");
						return;
					}
					

					int speed = 100;
					if (comboBoxSpeed.getSelectedIndex() == 2) {
						speed = 500;
					} else if (comboBoxSpeed.getSelectedIndex() == 3) {
						speed = 1000;
					}

					MyThread mt = new MyThread(panel, statsList, speed, buttonShowStats);
					mt.start();

				}
			});
			buttonShowStats.setBounds(809, 127, 133, 29);
		}
		return buttonShowStats;
	}
}
