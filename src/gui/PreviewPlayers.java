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
import datebase.CheckConnection;
import datebase.DataBaseConnection;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
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

public class PreviewPlayers extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable tableAll;
	private JTextArea txtrWaterPoloManagement;
	private JScrollPane scrollPane_1;
	private JLabel lblPlayerInfo;
	private JTextField searchField;
	private JLabel lblInfoTxtField;
	

	private DataBaseConnection dbs;
	private CheckConnection cc;
	
	private List<Player> playersList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreviewPlayers frame = new PreviewPlayers();
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
	public PreviewPlayers() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(PreviewPlayers.class.getResource("/icons/water-polo (1).png")));
		setTitle("WPMS - Player Info");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 700);

		cc = new CheckConnection();
		dbs = cc.getJDBC();
		playersList = dbs.getAllPlayers();
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTxtrWaterPoloManagement());
		contentPane.add(getScrollPane_1());
		contentPane.add(getLblPlayerInfo());
		contentPane.add(getSearchField());
		contentPane.add(getLblInfoTxtField());

	}

	private JTable getTableAll() {
		if (tableAll == null) {

			String[] col = { "Player ID", "First Name", "Last Name", "Age", "Height (cm)", "Weight (kg)", "Type","Comment" };

			tableModel = new DefaultTableModel(col, 0);


			for (int i = 0; i < playersList.size(); i++) {

				String playerID = playersList.get(i).getPlayerID();
				String firstName = playersList.get(i).getFirstName();
				String lastName = playersList.get(i).getLastName();
				int age = playersList.get(i).getAge();
				int height = playersList.get(i).getHeight();
				int weight = playersList.get(i).getWeight();
				String typeOfPlayer = playersList.get(i).getTypeOfPlayer();
				String comment = playersList.get(i).getComment();

				Object[] obj = { playerID, firstName, lastName, age, height, weight, typeOfPlayer, comment };
				tableModel.addRow(obj);

			} 

			tableModel.setColumnIdentifiers(col);

			tableAll = new JTable(tableModel);

			for (int i = 0; i < tableAll.getColumnCount(); i++) {
				TableColumn column = tableAll.getTableHeader().getColumnModel().getColumn(i);

				if (i == 0 || i == 3 || i == 4 || i == 5) {

					column.setMinWidth(120);
					column.setMaxWidth(140);

				}

				if (i == 1 || i == 2 || i == 6) {
					column.setMinWidth(150);
					column.setMaxWidth(290);
				}

				if (i == 7) {
					column.setMinWidth(200);
					column.setMaxWidth(700);
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
			txtrWaterPoloManagement.setText("                                       Water Polo Management System");
			txtrWaterPoloManagement.setForeground(Color.WHITE);
			txtrWaterPoloManagement.setFont(new Font("Ebrima", Font.PLAIN, 35));
			txtrWaterPoloManagement.setEnabled(false);
			txtrWaterPoloManagement.setEditable(false);
			txtrWaterPoloManagement.setBackground(Color.BLACK);
			txtrWaterPoloManagement.setBounds(0, 0, 1194, 70);
		}
		return txtrWaterPoloManagement;
	}

	public JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane_1.setBounds(20, 175, 1135, 453);
			scrollPane_1.setViewportView(getTableAll());
		}
		return scrollPane_1;
	}

	public JLabel getLblPlayerInfo() {
		if (lblPlayerInfo == null) {
			lblPlayerInfo = new JLabel("Players Information");
			lblPlayerInfo.setForeground(Color.BLACK);
			lblPlayerInfo.setBackground(Color.RED);
			lblPlayerInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
			lblPlayerInfo.setVerticalAlignment(SwingConstants.TOP);
			lblPlayerInfo.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayerInfo.setBounds(-11, 86, 1163, 31);
		}
		return lblPlayerInfo;
	}

	public JTextField getSearchField() {
		if (searchField == null) {
			searchField = new JTextField();
			searchField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
				
					DefaultTableModel table = (DefaultTableModel)tableAll.getModel();
			        String search = searchField.getText();
			        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
			        tableAll.setRowSorter(tr);
			        tr.setRowFilter(RowFilter.regexFilter("(?i)" + search)); // (?i) ignore case
				 
			      
				
				}
			});
			
			searchField.setBounds(20, 125, 145, 25);
			searchField.setColumns(10);
		}
		return searchField;
	}

	public JLabel getLblInfoTxtField() {
		if (lblInfoTxtField == null) {
			lblInfoTxtField = new JLabel("Type to search");
			lblInfoTxtField.setFont(new Font("Tahoma", Font.ITALIC, 14));
			lblInfoTxtField.setBounds(20, 150, 145, 20);
		}
		return lblInfoTxtField;
	}
}
