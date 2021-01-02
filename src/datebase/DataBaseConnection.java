package datebase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import code.Login;
import code.Player;
import code.Stats;
import code.Team;
import code.TeamPlayer;

// database queries go here
public class DataBaseConnection {

	private String url;
	private String user;
	private String pass;

	public DataBaseConnection(String url, String user, String pass) {
		super();
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	public Connection open() {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			System.out.println("Greska prilikom konektovanja!");
			// e.printStackTrace();
			return null;
		}
	}

	public boolean close(Connection c) {
		try {
			c.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Greska u zatvaranju konekcije");
			return false;
		}
	}


	public int findUsernameID(String username) {
		Connection conn = open();

		String sql = "SELECT id FROM login WHERE username = '" + username + "'";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int id = -1;
			while (rs.next()) {
				id = rs.getInt("id");
			}
			close(conn);
			return id;
		} catch (SQLException e) {
			System.out.println("GRESKAAAAAAAAAAA");
			e.printStackTrace();
		}
		return -1;
	}


	public int exists(String username) {

		int id = findUsernameID(username);

		if (id != -1) {
			return 1;
		} else {
			return 0;
		}
	}

	// passwords need hashing
	public int insertLogin(Login login) {

		if (exists(login.getUsername()) == 1) {
			System.out.println("Vec postoji");
			return -1;
		}

		Connection conn = open();

		String sql = "INSERT INTO login(username,password)" + "VALUES ('" + login.getUsername() + "','"
				+ login.getPassword() + "')";

		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			close(conn);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	public String checkLogin(String username, String password) {

		if (exists(username) == 0) {
			return "That username was not found!";
		}

		// int userNameID = findUsernameID(username);

		Connection conn = open();

		String sql = "SELECT password FROM login WHERE username = '" + username + "'";
		Statement st;
		String actualPassword = "";
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				actualPassword = rs.getString("password");
			}
			close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (password.equals(actualPassword)) {
			return "1";
		} else {
			return "Username or password is incorrect!";
		}

	}


	public String findPlayer(String firstName, String lastName) {
		Connection conn = open();

		String sql = "SELECT id FROM player WHERE first_name = '" + firstName + "'" + "AND last_name = '" + lastName
				+ "'";

		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			String id = "-1";
			while (rs.next()) {
				id = rs.getString("id");
			}
			close(conn);
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}

	public String findPlayerID(String firstName, String lastName) {
		Connection conn = open();

		String sql = "SELECT id FROM player WHERE first_name = '" + firstName + "'" + "AND last_name = '" + lastName
				+ "'";

		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			String id = "-1";
			while (rs.next()) {
				id = rs.getString("id");
			}
			close(conn);
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}


	public int insertPlayer(Player p) {

		String findPlayerID = findPlayerID(p.getFirstName(), p.getLastName());

		if (!findPlayerID.equals("-1")) {
			System.out.println("Vec postoji trazeni igrac!");
			return -1;
		}

		Connection conn = open();

		String sql = "INSERT INTO player (id,first_name,last_name,age,height,weight,type,comment)" + "VALUES('"
				+ p.getPlayerID() + "','" + p.getFirstName() + "','" + p.getLastName() + "'," + p.getAge() + ","
				+ p.getHeight() + "," + p.getWeight() + ",'" + p.getTypeOfPlayer() + "','" + p.getComment() + "')";

		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			close(conn);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	public int findTeam(String teamName) {
		Connection conn = open();

		String sql = "SELECT id FROM team WHERE name = '" + teamName + "'";

		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int id = -1;
			while (rs.next()) {
				id = rs.getInt("id");
			}
			close(conn);
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}


	public int insertTeam(Team t) {

		if (findTeam(t.getTeamName()) != -1) {
			System.out.println("Vec postoji trazeni tim!");
			return -1;
		}

		Connection conn = open();

		String sql = "INSERT INTO team (name, country, coach_name)" + "VALUES('" + t.getTeamName() + "','"
				+ t.getCountry() + "','" + t.getCoachName() + "')";

		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			close(conn);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	public int checkIfPlayerBelongsToTeam(int teamID, String playerID) {

		Connection conn = open();

		String sql = "SELECT id FROM team_player WHERE player_id ='" + playerID + "' AND team_id = " + teamID + "";

		// String sq2l = "SELECT id FROM team WHERE name = '" + teamName + "'";

		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int id = -1;
			while (rs.next()) {
				id = rs.getInt("id");
			}
			close(conn);
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;

	}


	public int insertPlayerToTeam(int teamID, String playerID) {

		if (checkIfPlayerBelongsToTeam(teamID, playerID) != -1) {
			System.out.println("Vec postoji trazeni igrac u timu!");
			return -1;
		}

		Connection conn = open();

		String sql = "INSERT INTO team_player (team_id, player_id)" + "VALUES(" + teamID + ",'" + playerID + "')";

		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			close(conn);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}


	public List<String> getAllPlayersNames() {
		Connection conn = open();

		String sql = "SELECT CONCAT(first_name, ' ', last_name) AS name FROM player;";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			List<String> playerNames = new ArrayList<>();
			while (rs.next()) {
				String name = rs.getString("name");
				playerNames.add(name);
			}
			close(conn);
			return playerNames;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public List<Player> getAllPlayers() {
		Connection conn = open();

		String sql = "SELECT * FROM player;";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			List<Player> players = new ArrayList<>();
			while (rs.next()) {

				String playerID = rs.getString("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int age = rs.getInt("age");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				String typeOfPlayer = rs.getString("type");
				String comment = rs.getString("comment");
				Player p = new Player(playerID, firstName, lastName, age, height, weight, typeOfPlayer, comment);
				players.add(p);
			}
			close(conn);
			return players;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}


	public List<Team> getAllTeams() {
		Connection conn = open();

		String sql = "SELECT * FROM team;";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			List<Team> teams = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String teamName = rs.getString("name");
				String country = rs.getString("country");
				String coachName = rs.getString("coach_name");
				String teamID = id + "";
				Team t = new Team(teamID, teamName, country, coachName);

				teams.add(t);

			}
			close(conn);
			return teams;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}


	public List<String> getAllTeamsNames() {
		Connection conn = open();

		String sql = "SELECT name FROM team";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			List<String> teamNames = new ArrayList<>();
			while (rs.next()) {
				String name = rs.getString("name");
				teamNames.add(name);
			}
			close(conn);
			return teamNames;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public List<TeamPlayer> getPlayersFromTeams() {
		Connection conn = open();

		String sql = "SELECT tp.team_id, t.name,t.coach_name, p.id, p.first_name, p.last_name "
				+ "FROM team_player tp, team t, player p " + "WHERE tp.team_id = t.id AND tp.player_id = p.id"
				+ " ORDER by t.id";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			List<TeamPlayer> teamPlayerList = new ArrayList<>();
			while (rs.next()) {
				int teamID = rs.getInt("team_id");
				String tName = rs.getString("name");
				String coachName = rs.getString("coach_name");
				String playerID = rs.getString("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				TeamPlayer tp = new TeamPlayer(teamID, tName, coachName, playerID, firstName, lastName);
				teamPlayerList.add(tp);
			}
			close(conn);
			return teamPlayerList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<TeamPlayer> getPlayersFromTeam(String teamName) {
		Connection conn = open();

		String sql = "SELECT tp.team_id, t.name,t.coach_name, p.id, p.first_name, p.last_name "
				+ "FROM team_player tp, team t, player p "
				+ "WHERE tp.team_id = t.id AND tp.player_id = p.id AND t.name = '" + teamName + "'" + " ORDER by t.id";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			List<TeamPlayer> teamPlayerList = new ArrayList<>();
			while (rs.next()) {
				int teamID = rs.getInt("team_id");
				String tName = rs.getString("name");
				String coachName = rs.getString("coach_name");
				String playerID = rs.getString("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				TeamPlayer tp = new TeamPlayer(teamID, tName, coachName, playerID, firstName, lastName);
				teamPlayerList.add(tp);
			}
			close(conn);
			return teamPlayerList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}


	public int insertStats(String teamName) {

		Connection conn = open();

		String sql = "INSERT INTO stats (team_id, min_age, avg_age, max_age, min_height, avg_height, max_height, min_weight, avg_weight, max_weight)"
				+ " SELECT t.id AS team_id, MIN(p.age) AS min_age, AVG(p.age) AS avg_age, MAX(p.age) AS max_age,"
				+ " MIN(p.height) AS min_height, AVG(p.height) AS avg_height, MAX(p.height) AS max_height,"
				+ " MIN(p.weight) AS min_height, AVG(p.weight) AS avg_weight, MAX(p.weight) AS max_weight"
				+ " FROM team_player tp, team t, player p" + " WHERE tp.team_id = t.id AND tp.player_id = p.id"
				+ " AND t.name ='" + teamName + "'" + " GROUP BY tp.team_id;";

		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			close(conn);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public int findTeamInStats(String teamName) {
		Connection conn = open();

		String sql = "SELECT s.team_id FROM stats s, team t WHERE s.team_id = t.id" + " AND t.name = '" + teamName
				+ "'";

		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int id = -1;
			while (rs.next()) {
				id = rs.getInt("team_id");
			}
			close(conn);
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}


	public int updateStats(String teamName) {

		int teamID = findTeamInStats(teamName);

		if (teamID == -1) {
			insertStats(teamName);
			return 11;
		}

		Connection conn = open();

		String min_age = "UPDATE stats SET min_age = ( SELECT MIN(p.age)" + " FROM team_player tp, team t, player p"
				+ " WHERE tp.team_id = t.id AND tp.player_id = p.id" + " AND tp.team_id = " + teamID + ""
				+ " GROUP BY tp.team_id )" + " WHERE team_id=" + teamID + "";

		String avg_age = "UPDATE stats SET avg_age = ( SELECT AVG(p.age)" + " FROM team_player tp, team t, player p"
				+ " WHERE tp.team_id = t.id AND tp.player_id = p.id" + " AND tp.team_id = " + teamID + ""
				+ " GROUP BY tp.team_id )" + " WHERE team_id=" + teamID + "";

		String max_age = "UPDATE stats SET max_age = ( SELECT MAX(p.age)" + " FROM team_player tp, team t, player p"
				+ " WHERE tp.team_id = t.id AND tp.player_id = p.id" + " AND tp.team_id = " + teamID + ""
				+ " GROUP BY tp.team_id )" + " WHERE team_id=" + teamID + "";

		String min_height = "UPDATE stats SET min_height = ( SELECT MIN(p.height)"
				+ " FROM team_player tp, team t, player p" + " WHERE tp.team_id = t.id AND tp.player_id = p.id"
				+ " AND tp.team_id = " + teamID + "" + " GROUP BY tp.team_id )" + " WHERE team_id=" + teamID + "";

		String avg_height = "UPDATE stats SET avg_height = ( SELECT AVG(p.height)"
				+ " FROM team_player tp, team t, player p" + " WHERE tp.team_id = t.id AND tp.player_id = p.id"
				+ " AND tp.team_id = " + teamID + "" + " GROUP BY tp.team_id )" + " WHERE team_id=" + teamID + "";

		String max_height = "UPDATE stats SET max_height = ( SELECT MAX(p.height)"
				+ " FROM team_player tp, team t, player p" + " WHERE tp.team_id = t.id AND tp.player_id = p.id"
				+ " AND tp.team_id = " + teamID + "" + " GROUP BY tp.team_id )" + " WHERE team_id=" + teamID + "";

		String min_weight = "UPDATE stats SET min_weight = ( SELECT MIN(p.weight)"
				+ " FROM team_player tp, team t, player p" + " WHERE tp.team_id = t.id AND tp.player_id = p.id"
				+ " AND tp.team_id = " + teamID + "" + " GROUP BY tp.team_id )" + " WHERE team_id=" + teamID + "";

		String avg_weight = "UPDATE stats SET avg_weight = ( SELECT AVG(p.weight)"
				+ " FROM team_player tp, team t, player p" + " WHERE tp.team_id = t.id AND tp.player_id = p.id"
				+ " AND tp.team_id = " + teamID + "" + " GROUP BY tp.team_id )" + " WHERE team_id=" + teamID + "";

		String max_weight = "UPDATE stats SET max_weight = ( SELECT MAX(p.weight)"
				+ " FROM team_player tp, team t, player p" + " WHERE tp.team_id = t.id AND tp.player_id = p.id"
				+ " AND tp.team_id = " + teamID + "" + " GROUP BY tp.team_id )" + " WHERE team_id=" + teamID + "";

		try {
			Statement st = conn.createStatement();

			st.executeUpdate(min_age);
			st.executeUpdate(avg_age);
			st.executeUpdate(max_age);

			st.executeUpdate(min_height);
			st.executeUpdate(avg_height);
			st.executeUpdate(max_height);

			st.executeUpdate(min_weight);
			st.executeUpdate(avg_weight);
			st.executeUpdate(max_weight);

			close(conn);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}


	public Stats getStats(String teamName) {

		int teamID = findTeamInStats(teamName);

		if (teamID == -1) {
			return null;
		}

		Connection conn = open();

		String sql = "SELECT * FROM stats WHERE team_id = " + teamID;
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			Stats s = null;

			while (rs.next()) {

				int stat_id = rs.getInt("stat_id");
				int team_id = rs.getInt("team_id");
				int min_age = rs.getInt("min_age");
				double avg_age = rs.getDouble("avg_age");
				int max_age = rs.getInt("max_age");

				int min_height = rs.getInt("min_height");
				double avg_height = rs.getDouble("avg_height");
				int max_height = rs.getInt("max_height");

				int min_weight = rs.getInt("min_weight");
				double avg_weight = rs.getDouble("avg_weight");
				int max_weight = rs.getInt("max_weight");

				s = new Stats(stat_id, team_id, min_age, avg_age, max_age, min_height, avg_height, max_height,
						min_weight, avg_weight, max_weight);

			}
			close(conn);
			return s;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Player> getAllPlayers(String teamName) {
		Connection conn = open();

		String sql = "SELECT p.* FROM player p, team t, team_player tp WHERE tp.team_id = t.id AND tp.player_id = p.id "
				+ "AND t.name = '" + teamName + "'";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			List<Player> players = new ArrayList<>();
			while (rs.next()) {

				String playerID = rs.getString("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int age = rs.getInt("age");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				String typeOfPlayer = rs.getString("type");
				String comment = rs.getString("comment");
				Player p = new Player(playerID, firstName, lastName, age, height, weight, typeOfPlayer, comment);
				players.add(p);
			}
			close(conn);
			return players;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<String> getTeamNamesFromTeamPlayerTable() {

		Connection conn = open();

		String sql = "SELECT t.name FROM team t, team_player tp WHERE tp.team_id = t.id";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			List<String> teamNames = new ArrayList<>();
			while (rs.next()) {
				
				String teamName = rs.getString("name");
				teamNames.add(teamName);
			}
			close(conn);
			return teamNames;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
