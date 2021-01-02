package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.Color;

import javax.print.attribute.standard.Finishings;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import code.Login;
import datebase.CheckConnection;
import datebase.DataBaseConnection;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

public class LoginFrame {

	private JFrame frmWpmsLogin;
	private JLabel lblUserName;
	private JTextField textFieldUserName;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JTextArea txtrWaterPoloManagement;
	private JButton btnLogin;
	private JButton btnExit;
	private JButton btnCreateAccount;
	private JLabel lblS;

	private DataBaseConnection dbs;

	// checks connection with database
	private CheckConnection cc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frmWpmsLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// First window of the app

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	private void initialize() {


		frmWpmsLogin = new JFrame("WPMS");
		frmWpmsLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmWpmsLogin.setResizable(false);
		frmWpmsLogin.setIconImage(
				Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/icons/water-polo (1).png")));
		frmWpmsLogin.setTitle("WPMS - Login");
		frmWpmsLogin.getContentPane().setBackground(new Color(240, 240, 240));
		frmWpmsLogin.getContentPane().setForeground(new Color(255, 255, 255));
		frmWpmsLogin.getContentPane().setLayout(null);
		frmWpmsLogin.getContentPane().add(getLblUserName());
		frmWpmsLogin.getContentPane().add(getTextFieldUserName());
		frmWpmsLogin.getContentPane().add(getLblPassword());
		frmWpmsLogin.getContentPane().add(getPasswordField());
		frmWpmsLogin.getContentPane().add(getTxtrWaterPoloManagement());
		frmWpmsLogin.getContentPane().add(getBtnLogin());
		frmWpmsLogin.getContentPane().add(getBtnExit());
		frmWpmsLogin.getContentPane().add(getBtnCreateAccount());
		frmWpmsLogin.getContentPane().add(getLblS());
		frmWpmsLogin.setBounds(100, 100, 700, 500);
		frmWpmsLogin.setLocationRelativeTo(null);
		frmWpmsLogin.setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { getTextFieldUserName(), getPasswordField() }));
		cc = new CheckConnection(frmWpmsLogin);
		dbs = cc.check();

	}

	private JLabel getLblUserName() {
		if (lblUserName == null) {
			lblUserName = new JLabel("Username");
			lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblUserName.setBounds(196, 137, 116, 20);
		}
		return lblUserName;
	}

	private JTextField getTextFieldUserName() {
		if (textFieldUserName == null) {
			textFieldUserName = new JTextField();
			textFieldUserName.setToolTipText("");
			textFieldUserName.setColumns(10);
			textFieldUserName.setBounds(196, 162, 315, 25);
		}
		return textFieldUserName;
	}

	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password");
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblPassword.setBounds(196, 214, 116, 20);
		}
		return lblPassword;
	}

	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {


					if (e.getKeyCode() == KeyEvent.VK_ENTER) {


						cc.error();

						String username = textFieldUserName.getText();
						String password = passwordField.getText();

						if (passwordField.getText().equals("") || passwordField.getText().equals(" ")) {
							JOptionPane.showMessageDialog(frmWpmsLogin, "Please enter password!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						
						String output = dbs.checkLogin(username, password);

						if (output.equals("1")) {
							JOptionPane.showMessageDialog(frmWpmsLogin, "Successful Login!");
							MenuFrame mf = new MenuFrame();
							mf.open();

							textFieldUserName.setText("");
							passwordField.setText("");

						} else {
							JOptionPane.showMessageDialog(frmWpmsLogin, output, "Error", JOptionPane.ERROR_MESSAGE);
							passwordField.setText("");
						}

					}

				}
			});
			passwordField.setBounds(196, 239, 315, 26);
		}
		return passwordField;
	}

	private JTextArea getTxtrWaterPoloManagement() {
		if (txtrWaterPoloManagement == null) {
			txtrWaterPoloManagement = new JTextArea();
			txtrWaterPoloManagement.setEnabled(false);
			txtrWaterPoloManagement.setToolTipText("Developed by Vasilije");
			txtrWaterPoloManagement.setFont(new Font("Ebrima", Font.PLAIN, 35));
			txtrWaterPoloManagement.setEditable(false);
			txtrWaterPoloManagement.setBackground(new Color(0, 0, 0));
			txtrWaterPoloManagement.setForeground(Color.WHITE);
			txtrWaterPoloManagement.setText("           Water Polo Management System");
			txtrWaterPoloManagement.setBounds(0, 0, 694, 70);
		}
		return txtrWaterPoloManagement;
	}

	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("Login");

			btnLogin.setIcon(new ImageIcon(LoginFrame.class.getResource("/icons/unlock.png")));

			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					cc.error();

					String username = textFieldUserName.getText();
					String password = passwordField.getText();

					if (passwordField.getText().equals("") || passwordField.getText().equals(" ")) {
						JOptionPane.showMessageDialog(frmWpmsLogin, "Please enter password!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					String output = dbs.checkLogin(username, password);

					if (output.equals("1")) {
						JOptionPane.showMessageDialog(frmWpmsLogin, "Successful Login!");
						MenuFrame mf = new MenuFrame();
						mf.open();

						textFieldUserName.setText("");
						passwordField.setText("");

					} else {
						JOptionPane.showMessageDialog(frmWpmsLogin, output, "Error", JOptionPane.ERROR_MESSAGE);
						passwordField.setText("");
					}

				}
			});
			btnLogin.setHorizontalAlignment(SwingConstants.LEFT);
			btnLogin.setForeground(new Color(0, 0, 0));
			btnLogin.setBackground(new Color(240, 240, 240));
			btnLogin.setBounds(196, 296, 140, 70);
		}
		return btnLogin;
	}

	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("Exit");
			btnExit.setIcon(new ImageIcon(LoginFrame.class.getResource("/icons/exit.png")));
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frmWpmsLogin.setVisible(false);
					frmWpmsLogin.dispose();
				}
			});
			btnExit.setHorizontalAlignment(SwingConstants.LEFT);
			btnExit.setForeground(Color.BLACK);
			btnExit.setBackground(new Color(240, 240, 240));
			btnExit.setBounds(366, 295, 140, 70);
		}
		return btnExit;
	}

	private JButton getBtnCreateAccount() {
		if (btnCreateAccount == null) {
			btnCreateAccount = new JButton("Create Account");
			btnCreateAccount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					ManageAccountsFrame maf = new ManageAccountsFrame();
					maf.open();

				}
			});
			btnCreateAccount.setBackground(new Color(240, 240, 240));
			btnCreateAccount.setHorizontalAlignment(SwingConstants.LEFT);
			btnCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnCreateAccount.setForeground(new Color(0, 0, 255));
			btnCreateAccount.setBounds(495, 400, 155, 30);
			btnCreateAccount.setBorderPainted(false);
		}
		return btnCreateAccount;
	}

	private JLabel getLblS() {
		if (lblS == null) {
			lblS = new JLabel("");
			lblS.setIcon(new ImageIcon(LoginFrame.class.getResource("/icons/ball.png")));
			lblS.setBounds(33, 139, 133, 159);
		}
		return lblS;
	}
}
