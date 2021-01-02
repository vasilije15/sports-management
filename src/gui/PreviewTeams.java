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

import code.Player;
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

public class PreviewTeams extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JLabel labelInfo;
	private JTextArea txtrWaterPoloManagement;
	private JTable table;
	private DefaultTableModel tableModel;

	private List<Team> teamsList;
	private JTextField textFieldSearch;
	private JLabel labelSearch;
	
	private DataBaseConnection dbs;
	private CheckConnection cc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreviewTeams frame = new PreviewTeams();
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
	public PreviewTeams() {
		setTitle("WMPS - Teams Preview");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PreviewTeams.class.getResource("/icons/water-polo (1).png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 650);
		
		cc = new CheckConnection();
		dbs = cc.getJDBC();
		teamsList = dbs.getAllTeams();
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getLabelInfo());
		contentPane.add(getTxtrWaterPoloManagement());
		contentPane.add(getTextFieldSearch());
		contentPane.add(getLabelSearch());

	}

	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane.setBounds(15, 195, 650, 384);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	public JLabel getLabelInfo() {
		if (labelInfo == null) {
			labelInfo = new JLabel("Teams Information");
			labelInfo.setVerticalAlignment(SwingConstants.TOP);
			labelInfo.setHorizontalAlignment(SwingConstants.CENTER);
			labelInfo.setForeground(Color.BLACK);
			labelInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
			labelInfo.setBackground(Color.RED);
			labelInfo.setBounds(0, 80, 661, 31);
		}
		return labelInfo;
	}

	public JTextArea getTxtrWaterPoloManagement() {
		if (txtrWaterPoloManagement == null) {
			txtrWaterPoloManagement = new JTextArea();
			txtrWaterPoloManagement.setEnabled(false);
			txtrWaterPoloManagement.setEditable(false);
			txtrWaterPoloManagement.setToolTipText("Developed by Vasilije");
			txtrWaterPoloManagement.setText("           Water Polo Management System");
			txtrWaterPoloManagement.setForeground(Color.WHITE);
			txtrWaterPoloManagement.setFont(new Font("Ebrima", Font.PLAIN, 35));
			txtrWaterPoloManagement.setBackground(Color.BLACK);
			txtrWaterPoloManagement.setBounds(0, 0, 694, 70);

		}
		return txtrWaterPoloManagement;
	}

	public JTable getTable() {
		if (table == null) {
			table = new JTable();

			String[] col = {"Team Name", "Country", "Coach's Name"};

			tableModel = new DefaultTableModel(col, 0);

			for (int i = 0; i < teamsList.size(); i++) {

				String teamName = teamsList.get(i).getTeamName();
				String country = teamsList.get(i).getCountry();
				String coachName = teamsList.get(i).getCoachName();

				Object[] obj = { teamName, country, coachName};
				tableModel.addRow(obj);

			}

			tableModel.setColumnIdentifiers(col);

			table = new JTable(tableModel);
			
			for (int i = 0; i < table.getColumnCount(); i++) {
				TableColumn column = table.getTableHeader().getColumnModel().getColumn(i);

				if (i == 0) {
					column.setMinWidth(150);
					column.setMaxWidth(300);
				}

				if (i == 1) {
					column.setMinWidth(230);
					column.setMaxWidth(350);
				}

				if (i == 2 || i == 3) {
					column.setMinWidth(250);
					;
					column.setMaxWidth(380);
				}
			}

			table.setRowHeight(80);

			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 20));
			table.getTableHeader().setBackground(Color.BLACK);
			table.getTableHeader().setForeground(Color.WHITE);
			table.setFont(new Font("Tahoma", Font.PLAIN, 18));

		}
		return table;
	}
	private JTextField getTextFieldSearch() {
		if (textFieldSearch == null) {
			textFieldSearch = new JTextField();
			textFieldSearch.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					
					
					DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
			        String search = textFieldSearch.getText();
			        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tblModel);
			        table.setRowSorter(tr);
			        tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
			        
			        
				}
			});
			textFieldSearch.setBounds(15, 127, 146, 26);
			textFieldSearch.setColumns(10);
		}
		return textFieldSearch;
	}
	private JLabel getLabelSearch() {
		if (labelSearch == null) {
			labelSearch = new JLabel("Type to search");
			labelSearch.setFont(new Font("Tahoma", Font.ITALIC, 14));
			labelSearch.setBounds(15, 159, 145, 20);
		}
		return labelSearch;
	}
}
