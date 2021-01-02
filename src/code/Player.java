package code;

public class Player{

	private String playerID;
	private String firstName;
	private String lastName;
	private int age;
	private int height;
	private int weight;
	private String typeOfPlayer;
	private String comment;

	public Player(String playerID, String firstName, String lastName, int age, int height, int weight,
			String typeOfPlayer, String comment) {
		super();
		this.playerID = playerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.typeOfPlayer = typeOfPlayer;
		this.comment = comment;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getTypeOfPlayer() {
		return typeOfPlayer;
	}

	public void setTypeOfPlayer(String typeOfPlayer) {
		this.typeOfPlayer = typeOfPlayer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Player) {
			Player p = (Player) obj;

			if (p.getPlayerID().equalsIgnoreCase(playerID)) {
				return true;
			}

		}
		return false;
	}

	
	public String info() {
		return "Player [playerID=" + playerID + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", height=" + height + ", weight=" + weight + ", typeOfPlayer=" + typeOfPlayer + ", comment="
				+ comment + "]";
	}
	@Override
	public String toString() {

		return firstName + " " + lastName + "\n";

	}

}
