package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;

public class MenuFrame {

	private JFrame frameMenu;
	private JTextArea textArea;
	private JButton btnAddPlayer;
	private JLabel lblAddPlayers;
	private JLabel lblAddTeams;
	private JButton btnAddTeams;
	private JButton btnOutput;
	private JLabel lblAddPlyrToTemas;
	private JButton btnSearch;
	private JLabel lblSearch;
	private JButton btnExit;
	private JLabel lblExit;
	private JButton btnShowStats;
	private JLabel lblManageAccounts;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JMenuItem mntmAddPlayers;
	private JMenuItem mntmAddTeams;
	private JMenuItem mntmAddPlyrToTeams;
	private JMenuItem mntmManageAccounts;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	private JMenu mnPreview;
	private JMenuItem mntmAllPlayers;
	private JMenuItem mntmAllTeams;
	private JMenuItem mntmPlayersFromTeam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame window = new MenuFrame();
					window.frameMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the application.
	 */
	public MenuFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frameMenu = new JFrame();
		frameMenu.setIconImage(
				Toolkit.getDefaultToolkit().getImage(MenuFrame.class.getResource("/icons/water-polo (1).png")));
		frameMenu.setTitle("WPMS - Main Menu");
		frameMenu.setResizable(false);
		frameMenu.setBounds(100, 100, 700, 600);
		frameMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameMenu.getContentPane().setLayout(null);
		frameMenu.getContentPane().add(getTextArea());
		frameMenu.getContentPane().add(getBtnAddPlayer());
		frameMenu.getContentPane().add(getLblAddPlayers());
		frameMenu.getContentPane().add(getLblAddTeams());
		frameMenu.getContentPane().add(getBtnAddTeams());
		frameMenu.getContentPane().add(getBtnOutput());
		frameMenu.getContentPane().add(getLblAddPlyrToTemas());
		frameMenu.getContentPane().add(getBtnSearch());
		frameMenu.getContentPane().add(getLblSearch());
		frameMenu.getContentPane().add(getBtnExit());
		frameMenu.getContentPane().add(getLblExit());
		frameMenu.getContentPane().add(getBtnShowStats());
		frameMenu.getContentPane().add(getLblManageAccounts());
		frameMenu.getContentPane().add(getMenuBar());
		frameMenu.setLocationRelativeTo(null);
	}

	public void open() {

		initialize();
		frameMenu.setVisible(true);

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
			textArea.setBounds(0, 31, 694, 70);
		}
		return textArea;
	}

	private JButton getBtnAddPlayer() {
		if (btnAddPlayer == null) {
			btnAddPlayer = new JButton("");
			btnAddPlayer.setIcon(new ImageIcon(MenuFrame.class.getResource("/icons/main-water-polo.png")));
			btnAddPlayer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					AddPlayers mpf = new AddPlayers();
					mpf.open();

				}
			});
			btnAddPlayer.setBounds(80, 137, 140, 140);
			btnAddPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return btnAddPlayer;
	}

	private JLabel getLblAddPlayers() {
		if (lblAddPlayers == null) {
			lblAddPlayers = new JLabel("Add Players");
			lblAddPlayers.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblAddPlayers.setBounds(80, 293, 140, 32);
			lblAddPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblAddPlayers;
	}

	private JLabel getLblAddTeams() {
		if (lblAddTeams == null) {
			lblAddTeams = new JLabel("Add Teams");
			lblAddTeams.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddTeams.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblAddTeams.setBounds(270, 293, 140, 32);
		}
		return lblAddTeams;
	}

	private JButton getBtnAddTeams() {
		if (btnAddTeams == null) {
			btnAddTeams = new JButton("");
			btnAddTeams.setIcon(new ImageIcon(MenuFrame.class.getResource("/icons/team.png")));
			btnAddTeams.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					AddTeams at = new AddTeams();
					at.open();

				}
			});
			btnAddTeams.setHorizontalAlignment(SwingConstants.CENTER);
			btnAddTeams.setBounds(270, 137, 140, 140);
		}
		return btnAddTeams;
	}

	private JButton getBtnOutput() {
		if (btnOutput == null) {
			btnOutput = new JButton("");
			btnOutput.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					AddPlayersToTeams aptt = new AddPlayersToTeams();
					aptt.open();

				}
			});
			btnOutput.setIcon(new ImageIcon(MenuFrame.class.getResource("/icons/addToTeams.png")));
			btnOutput.setHorizontalAlignment(SwingConstants.CENTER);
			btnOutput.setBounds(460, 137, 140, 140);
		}
		return btnOutput;
	}

	private JLabel getLblAddPlyrToTemas() {
		if (lblAddPlyrToTemas == null) {
			lblAddPlyrToTemas = new JLabel("Add Players to Teams");
			lblAddPlyrToTemas.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddPlyrToTemas.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblAddPlyrToTemas.setBounds(439, 293, 183, 32);
		}
		return lblAddPlyrToTemas;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("");
			btnSearch.setIcon(new ImageIcon(MenuFrame.class.getResource("/icons/search.png")));
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					

					JLabel question = new JLabel("Which preview  would you like?");
					question.setFont(new Font("Tahoma", Font.BOLD, 25));

					Object[] options = { "All Players", "All Teams", "Players from Team" };

					int n = JOptionPane.showOptionDialog(menuBar, question, "Choose a preview",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

					if (n == 0) {
						PreviewPlayers pw = new PreviewPlayers();
						pw.setVisible(true);
					}

					if (n == 1) {
						PreviewTeams pt = new PreviewTeams();
						pt.setVisible(true);

					}
					
					if (n == 2) {
//						PreviewTeamsAndPlayers ptap = new PreviewTeamsAndPlayers();
//						ptap.setVisible(true);
						
						PreviewPlayersFromTeam  all = new PreviewPlayersFromTeam();
						all.setVisible(true);

					}

				}
			});
			btnSearch.setHorizontalAlignment(SwingConstants.CENTER);
			btnSearch.setBounds(80, 341, 140, 140);
		}
		return btnSearch;
	}

	private JLabel getLblSearch() {
		if (lblSearch == null) {
			lblSearch = new JLabel("Preview");
			lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
			lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblSearch.setBounds(80, 497, 140, 32);
		}
		return lblSearch;
	}

	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("");
			btnExit.setIcon(new ImageIcon(MenuFrame.class.getResource("/icons/exit 128.png")));
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frameMenu.setVisible(false);
					frameMenu.dispose();
					//System.exit(0);
				}
			});
			btnExit.setHorizontalAlignment(SwingConstants.CENTER);
			btnExit.setBounds(460, 341, 140, 140);
		}
		return btnExit;
	}

	private JLabel getLblExit() {
		if (lblExit == null) {
			lblExit = new JLabel("Exit");
			lblExit.setHorizontalAlignment(SwingConstants.CENTER);
			lblExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblExit.setBounds(460, 497, 140, 32);
		}
		return lblExit;
	}

	private JButton getBtnShowStats() {
		if (btnShowStats == null) {
			btnShowStats = new JButton("");
			btnShowStats.setIcon(new ImageIcon(MenuFrame.class.getResource("/icons/stats.png")));
			btnShowStats.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PreviewStats ps = new PreviewStats();
					ps.setVisible(true);

				}
			});
			btnShowStats.setHorizontalAlignment(SwingConstants.CENTER);
			btnShowStats.setBounds(270, 341, 140, 140);
		}
		return btnShowStats;
	}

	private JLabel getLblManageAccounts() {
		if (lblManageAccounts == null) {
			lblManageAccounts = new JLabel("Team Statistics");
			lblManageAccounts.setHorizontalAlignment(SwingConstants.CENTER);
			lblManageAccounts.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblManageAccounts.setBounds(270, 497, 140, 32);
		}
		return lblManageAccounts;
	}

	private JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 694, 32);
			menuBar.add(getMnFile());
			menuBar.add(getMnPreview());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.add(getMntmAddPlayers());
			mnFile.add(getMntmAddTeams());
			mnFile.add(getMntmAddPlyrToTeams());
			mnFile.add(getMntmManageAccounts());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}

	private JMenuItem getMntmAddPlayers() {
		if (mntmAddPlayers == null) {
			mntmAddPlayers = new JMenuItem("Add Players");
			mntmAddPlayers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					AddPlayers mpf = new AddPlayers();
					mpf.open();

				}
			});
		}
		return mntmAddPlayers;
	}

	private JMenuItem getMntmAddTeams() {
		if (mntmAddTeams == null) {
			mntmAddTeams = new JMenuItem("Add Teams");
			mntmAddTeams.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					AddTeams at = new AddTeams();
					at.open();

				}
			});
		}
		return mntmAddTeams;
	}

	private JMenuItem getMntmAddPlyrToTeams() {
		if (mntmAddPlyrToTeams == null) {
			mntmAddPlyrToTeams = new JMenuItem("Add Players to Teams");
			mntmAddPlyrToTeams.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					AddPlayersToTeams aptt = new AddPlayersToTeams();
					aptt.open();

				}
			});
		}
		return mntmAddPlyrToTeams;
	}

	private JMenuItem getMntmManageAccounts() {
		if (mntmManageAccounts == null) {
			mntmManageAccounts = new JMenuItem("Manage Accounts");
			mntmManageAccounts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					ManageAccountsFrame maf = new ManageAccountsFrame();
					maf.open();

				}
			});
		}
		return mntmManageAccounts;
	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frameMenu.setVisible(false);
					frameMenu.dispose();

				}
			});
		}
		return mntmExit;
	}

	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					JOptionPane.showMessageDialog(frameMenu,
							"Developed by Vasilije\nFakultet za informacione tehnologije 2019", "About",
							JOptionPane.WARNING_MESSAGE);
				}
			});
		}
		return mntmAbout;
	}
	private JMenu getMnPreview() {
		if (mnPreview == null) {
			mnPreview = new JMenu("Preview");
			mnPreview.add(getMntmAllPlayers());
			mnPreview.add(getMntmAllTeams());
			mnPreview.add(getMntmPlayersFromTeam());
		}
		return mnPreview;
	}
	private JMenuItem getMntmAllPlayers() {
		if (mntmAllPlayers == null) {
			mntmAllPlayers = new JMenuItem("All Players");
			mntmAllPlayers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					PreviewPlayers pw = new PreviewPlayers();
					pw.setVisible(true);
					
				}
			});
		}
		return mntmAllPlayers;
	}
	private JMenuItem getMntmAllTeams() {
		if (mntmAllTeams == null) {
			mntmAllTeams = new JMenuItem("All Teams");
			mntmAllTeams.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PreviewTeams pt = new PreviewTeams();
					pt.setVisible(true);
				}
			});
		}
		return mntmAllTeams;
	}
	private JMenuItem getMntmPlayersFromTeam() {
		if (mntmPlayersFromTeam == null) {
			mntmPlayersFromTeam = new JMenuItem("Players from Team");
			mntmPlayersFromTeam.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
//					PreviewTeamsAndPlayersOld ptap = new PreviewTeamsAndPlayersOld();
//					ptap.setVisible(true);
					
					PreviewPlayersFromTeam all = new PreviewPlayersFromTeam();
					all.setVisible(true);
					
				}
			});
		}
		return mntmPlayersFromTeam;
	}
}
