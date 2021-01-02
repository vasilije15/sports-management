package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import code.Login;
import datebase.CheckConnection;
import datebase.DataBaseConnection;

import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.SwingConstants;

public class ManageAccountsFrame {

	private JFrame frameManageAccount;
	private JTextArea textArea;
	private JLabel lblPassword;
	private JLabel lblUserName;
	private JTextField txtFieldUserName;
	private JLabel label;
	private JLabel lblAgain;
	private JSeparator separator;
	private JButton btnAddUser;
	private JPasswordField passwordField;
	private JPasswordField passwordField_again;
	private JLabel lblAddsLoginUser;

	private DataBaseConnection dbs;
	private CheckConnection cc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageAccountsFrame window = new ManageAccountsFrame();
					window.frameManageAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// add user accounts 

	/**
	 * Create the application.
	 */
	public ManageAccountsFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frameManageAccount = new JFrame();
		frameManageAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameManageAccount.setTitle("WPMS - Manage Accounts");
		frameManageAccount.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ManageAccountsFrame.class.getResource("/icons/water-polo (1).png")));
		frameManageAccount.setResizable(false);
		frameManageAccount.setBounds(100, 100, 700, 300);
		frameManageAccount.getContentPane().setLayout(null);
		frameManageAccount.getContentPane().add(getTextArea());
		frameManageAccount.getContentPane().add(getLblPassword());
		frameManageAccount.getContentPane().add(getLblUserName());
		frameManageAccount.getContentPane().add(getTxtFieldUserName());
		frameManageAccount.getContentPane().add(getLabel());
		frameManageAccount.getContentPane().add(getLblAgain());
		frameManageAccount.getContentPane().add(getSeparator());
		frameManageAccount.getContentPane().add(getBtnAddUser());
		frameManageAccount.getContentPane().add(getPasswordField());
		frameManageAccount.getContentPane().add(getPasswordField_again());
		frameManageAccount.getContentPane().add(getLblAddsLoginUser());

		cc = new CheckConnection();
		dbs = cc.getJDBC();

	}

	public void open() {

		initialize();
		frameManageAccount.setVisible(true);

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

	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password");
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblPassword.setBounds(66, 142, 103, 20);
		}
		return lblPassword;
	}

	private JLabel getLblUserName() {
		if (lblUserName == null) {
			lblUserName = new JLabel("Username");
			lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblUserName.setBounds(66, 100, 103, 20);
		}
		return lblUserName;
	}

	private JTextField getTxtFieldUserName() {
		if (txtFieldUserName == null) {
			txtFieldUserName = new JTextField();
			txtFieldUserName.setColumns(10);
			txtFieldUserName.setBounds(204, 100, 146, 26);
		}
		return txtFieldUserName;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Password");
			label.setFont(new Font("Tahoma", Font.PLAIN, 18));
			label.setBounds(66, 187, 103, 20);
		}
		return label;
	}

	private JLabel getLblAgain() {
		if (lblAgain == null) {
			lblAgain = new JLabel("again");
			lblAgain.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblAgain.setBounds(66, 207, 103, 20);
		}
		return lblAgain;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setBounds(392, 100, 13, 127);
		}
		return separator;
	}

	private JButton getBtnAddUser() {
		if (btnAddUser == null) {
			btnAddUser = new JButton("ADD USER");
			btnAddUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String username = txtFieldUserName.getText();
					String password = passwordField.getText();

					int exists = dbs.exists(username);

					if (exists == 1) {
						JOptionPane.showMessageDialog(frameManageAccount,
								"You are trying to add username which already exists!", "Login Error",
								JOptionPane.ERROR_MESSAGE);
						passwordField.setText("");
						passwordField_again.setText("");
						return;
					}

					if (!password.equals(passwordField_again.getText())) {
						JOptionPane.showMessageDialog(frameManageAccount, "Passwords do not match!", "Login Error",
								JOptionPane.ERROR_MESSAGE);
						passwordField.setText("");
						passwordField_again.setText("");
						return;
					}

					
					// HASH PASSWORD HERE
					Login l = new Login(username, password);
					int insert = dbs.insertLogin(l);

					if (insert == 1) {

						JOptionPane.showMessageDialog(frameManageAccount, "Account created!", "Successful Login",
								JOptionPane.INFORMATION_MESSAGE);

						txtFieldUserName.setText("");
						passwordField.setText("");
						passwordField_again.setText("");
						return;
					} else {
						JOptionPane.showMessageDialog(frameManageAccount, "SQL Error! Try again!", "Login Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

				}

			});
			btnAddUser.setBounds(428, 130, 200, 70);
		}
		return btnAddUser;
	}

	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(204, 140, 146, 26);
		}
		return passwordField;
	}

	private JPasswordField getPasswordField_again() {
		if (passwordField_again == null) {
			passwordField_again = new JPasswordField();
			passwordField_again.setBounds(204, 185, 146, 26);
		}
		return passwordField_again;
	}

	private JLabel getLblAddsLoginUser() {
		if (lblAddsLoginUser == null) {
			lblAddsLoginUser = new JLabel("Adds Login user ");
			lblAddsLoginUser.setBounds(470, 200, 120, 20);
		}
		return lblAddsLoginUser;
	}
}
