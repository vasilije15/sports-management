package code;

// junction table
public class TeamPlayer {

	
	private int teamID;
	private String teamName;
	private String coachName;
	private String playerID;
	private String firstName;
	private String lastName;
	
	
	public TeamPlayer(int teamID, String teamName, String coachName, String playerID, String firstName,
			String lastName) {
		super();
		this.teamID = teamID;
		this.teamName = teamName;
		this.coachName = coachName;
		this.playerID = playerID;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getPlayerID() {
		return playerID;
	}
	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "TeamPlayer [teamID=" + teamID + ", teamName=" + teamName + ", coachName=" + coachName + ", playerID="
				+ playerID + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
