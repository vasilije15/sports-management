package code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Team{

	private String teamID;
	private String teamName;
	private String country;
	private String coachName;
	private List<Player> players;

	public Team(String teamID, String teamName, String country, String coachName) {
		super();
		this.teamID = teamID;
		this.teamName = teamName;
		this.country = country;
		this.coachName = coachName;
		this.players = new ArrayList<>();
	}
	
	

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean addPlayer(Player player) {

		if (!players.contains(player)) {
			players.add(player);
			return true;
		}

		return false;
	}
	
	public String getFullPlayerName(int position) {
		
		for (Player player : players) {
			return players.get(position).getFirstName() + " " + players.get(position).getLastName();
		}
		
		return null;
		
	}
	
	

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Team) {

			Team t = (Team) obj;

			if (t.getTeamID().equalsIgnoreCase(teamID)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {

		String s = teamID + " " + teamName + " " + country + " " + coachName + ", Players: \n";

		int i = 0;
		for (Player player : players) {
			s += players.get(i) + "\n";
			i++;
		}
		return s;
	}
	
	
	public String serialize(String fileName) {

		ObjectOutputStream oo;
		try {
			oo = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
			oo.writeObject(this);
			oo.flush();
			oo.close();

			return null;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "File not found!";
		} catch (IOException e) {

			e.printStackTrace();
			return "IO error!";
		}

	}

	public static Team deserialize(String fileName) {

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)));
			Team team = (Team) ois.readObject();
			ois.close();
			return team;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	
}
