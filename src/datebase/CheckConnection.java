package datebase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// object to check connection with database

public class CheckConnection {

	private DataBaseConnection dbs;
	private BufferedReader br;
	private String url;
	private String user;
	private String pass;
	private JFrame frame;

	public CheckConnection(JFrame frame) {
		super();
		this.frame = frame;
	}

	public CheckConnection() {
		super();
	}

	public DataBaseConnection check() {
		try {
			br = new BufferedReader(new FileReader(new File("dbConnection")));
			try {
				url = br.readLine().split("!")[1];
				user = br.readLine().split("!")[1];
				pass = br.readLine().split("!")[1];

				
				if (pass.equals(" ")) {
					pass = "";
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			dbs = new DataBaseConnection(url, user, pass);
			return dbs;

		} catch (FileNotFoundException e) {
			 JOptionPane.showMessageDialog(frame, "Please provide dbConnection file!",
			 "SQL Error",JOptionPane.ERROR_MESSAGE);
			dbs = new DataBaseConnection("jdbc:mysql://localhost/wpolo", "root", ""); // vrati na default
			return dbs;
			// e.printStackTrace();
		}
	}

	public boolean error() {
		try {
			DriverManager.getConnection(url, user, pass);
			return true;
		} catch (SQLException sql) {
			 JOptionPane.showMessageDialog(frame, "Cannot connect to database!\nI will try to use default settings!", "SQL Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public DataBaseConnection getJDBC() {
		try {
			br = new BufferedReader(new FileReader(new File("dbConnection")));
			try {
				url = br.readLine().split("!")[1];
				user = br.readLine().split("!")[1];
				pass = br.readLine().split("!")[1];

			
				if (pass.equals(" ")) {
					pass = "";
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			dbs = new DataBaseConnection(url, user, pass);
			return dbs;

		} catch (FileNotFoundException e) {
			// database name should be wpolo
			dbs = new DataBaseConnection("jdbc:mysql://localhost/wpolo", "root", "");
			return dbs;
		}
	}

}
