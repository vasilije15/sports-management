package code;


public class Login{

	private String username;
	private String password;

	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login: "  + username + " " + password;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Login) {
			Login l = (Login) obj;
			if (l.getUsername().equals(username) && l.getUsername().equals(password)) {
				return true;
			}

		}
		return false;
	}
}
