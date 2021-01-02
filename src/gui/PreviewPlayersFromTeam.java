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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Highlighter;

import org.apache.poi.sl.draw.geom.Path;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Row;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import code.Player;
import code.Team;
import code.TeamPlayer;
import datebase.CheckConnection;
import datebase.DataBaseConnection;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;

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
import javax.swing.JButton;

public class PreviewPlayersFromTeam extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable tableAll;
	private JTextArea txtrWaterPoloManagement;
	private JScrollPane scrollPane_1;
	private JLabel lblPlayerInfo;
	private JLabel lblInfoTxtField;

	private List<Player> playersList;
	private List<Team> teamsList;
	private List<String> teamNames;

	private JComboBox comboBoxTeamNames;

	private String choosenTeam = "";
	private JLabel lblCoach;

	private DataBaseConnection dbs;
	private CheckConnection cc;

	private JFrame frame;
	private JTextField textFieldFilter;
	private JLabel lblFilter;
	private JButton btnPdf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreviewPlayersFromTeam frame = new PreviewPlayersFromTeam();
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


	public PreviewPlayersFromTeam() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PreviewPlayersFromTeam.class.getResource("/icons/water-polo (1).png")));
		setTitle("WPMS - Team - Players");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 573);

		cc = new CheckConnection();
		dbs = cc.getJDBC();

		teamsList = dbs.getAllTeams();

		teamNames = dbs.getTeamNamesFromTeamPlayerTable();

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
		contentPane.add(getTextFieldFilter());
		contentPane.add(getLblFilter());
		contentPane.add(getBtnPdf());

		btnPdf.setEnabled(false); 
		frame = this;

	}

	private JTable getTableAll() {
		if (tableAll == null) {

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
			lblInfoTxtField = new JLabel("Shows players which play for choosen team");
			lblInfoTxtField.setFont(new Font("Tahoma", Font.ITALIC, 14));
			lblInfoTxtField.setBounds(20, 165, 305, 20);
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

					playersList = dbs.getAllPlayers(choosenTeam);

					choosenTeam = comboBoxTeamNames.getSelectedItem().toString();
					
					if (choosenTeam.equals("choose a team")) {
						btnPdf.setEnabled(false);
					}else {
						btnPdf.setEnabled(true);
					}
					
					

					String[] col = { "Team", "Player ID", "First Name", "Last Name" };

					tableModel = new DefaultTableModel(col, 0);

					for (int i = 0; i < playersList.size(); i++) {

						String teamName = comboBoxTeamNames.getSelectedItem().toString();
						String playerID = playersList.get(i).getPlayerID();
						String firstName = playersList.get(i).getFirstName();
						String lastName = playersList.get(i).getLastName();

						Object[] obj = { teamName, playerID, firstName, lastName };
						tableModel.addRow(obj);

					} // end of for

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

					scrollPane_1.setViewportView(tableAll);

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

			comboBoxTeamNames.setBounds(20, 136, 150, 25);
		}
		return comboBoxTeamNames;
	}

	private JLabel getLblCoach() {
		if (lblCoach == null) {
			lblCoach = new JLabel("");
			lblCoach.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
			lblCoach.setBounds(171, 138, 368, 23);
		}
		return lblCoach;
	}

	private JTextField getTextFieldFilter() {
		if (textFieldFilter == null) {
			textFieldFilter = new JTextField();
			textFieldFilter.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {

					DefaultTableModel table = (DefaultTableModel) tableAll.getModel();
					TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
					tableAll.setRowSorter(tr);
					tr.setRowFilter(RowFilter.regexFilter("(?i)" + textFieldFilter.getText()));

				}
			});
			textFieldFilter.setBounds(565, 138, 146, 26);
			textFieldFilter.setColumns(10);
		}
		return textFieldFilter;
	}

	private JLabel getLblFilter() {
		if (lblFilter == null) {
			lblFilter = new JLabel("Filter results");
			lblFilter.setFont(new Font("Tahoma", Font.ITALIC, 14));
			lblFilter.setBounds(565, 165, 146, 20);

		}
		return lblFilter;
	}

	private JButton getBtnPdf() {
		if (btnPdf == null) {
			btnPdf = new JButton("PDF");
			btnPdf.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
			btnPdf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					generatePDF();
				}
			});
			btnPdf.setBounds(615, 488, 115, 29);
		}
		return btnPdf;
	}

	public void generatePDF() {
		try {

			String fileName = "table -" + choosenTeam + ".pdf";

			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(fileName));
			doc.open();
			PdfPTable pdfTable = new PdfPTable(tableAll.getColumnCount());
			// adding table header

			for (int i = 0; i < tableAll.getColumnCount(); i++) {
				pdfTable.addCell(tableAll.getColumnName(i));
			}
			// extracting data from the JTable and inserting it to PdfPTable
			for (int rows = 0; rows < tableAll.getRowCount(); rows++) {
				for (int cols = 0; cols < tableAll.getColumnCount(); cols++) {
					pdfTable.addCell(tableAll.getModel().getValueAt(rows, cols).toString());

				}
			}
			doc.add(pdfTable);
			doc.close();
			JOptionPane.showMessageDialog(frame, "Your PDF has been generated!");
		} catch (Exception e) {

			JOptionPane.showMessageDialog(frame, "There was an error in creating your PDF! Try again!", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

}
