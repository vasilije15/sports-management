package code;

import java.util.ArrayList;
import java.util.List;

public class Stats {

	private int stat_id;
	private int team_id;
	private int min_age;
	private double avg_age;
	private int max_age;

	private int min_height;
	private double avg_height;
	private int max_height;

	private int min_weight;
	private double avg_weight;
	private int max_weight;

	public Stats(int stat_id, int team_id, int min_age, double avg_age, int max_age, int min_height, double avg_height,
			int max_height, int min_weight, double avg_weight, int max_weight) {
		super();
		this.stat_id = stat_id;
		this.team_id = team_id;
		this.min_age = min_age;
		this.avg_age = avg_age;
		this.max_age = max_age;
		this.min_height = min_height;
		this.avg_height = avg_height;
		this.max_height = max_height;
		this.min_weight = min_weight;
		this.avg_weight = avg_weight;
		this.max_weight = max_weight;
	}

	public int getStat_id() {
		return stat_id;
	}

	public void setStat_id(int stat_id) {
		this.stat_id = stat_id;
	}

	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public int getMin_age() {
		return min_age;
	}

	public void setMin_age(int min_age) {
		this.min_age = min_age;
	}

	public double getAvg_age() {
		return avg_age;
	}

	public void setAvg_age(double avg_age) {
		this.avg_age = avg_age;
	}

	public int getMax_age() {
		return max_age;
	}

	public void setMax_age(int max_age) {
		this.max_age = max_age;
	}

	public int getMin_height() {
		return min_height;
	}

	public void setMin_height(int min_height) {
		this.min_height = min_height;
	}

	public double getAvg_height() {
		return avg_height;
	}

	public void setAvg_height(double avg_height) {
		this.avg_height = avg_height;
	}

	public int getMax_height() {
		return max_height;
	}

	public void setMax_height(int max_height) {
		this.max_height = max_height;
	}

	public int getMin_weight() {
		return min_weight;
	}

	public void setMin_weight(int min_weight) {
		this.min_weight = min_weight;
	}

	public double getAvg_weight() {
		return avg_weight;
	}

	public void setAvg_weight(double avg_weight) {
		this.avg_weight = avg_weight;
	}

	public int getMax_weight() {
		return max_weight;
	}

	public void setMax_weight(int max_weight) {
		this.max_weight = max_weight;
	}

	@Override
	public String toString() {
		return "Stats [stat_id=" + stat_id + ", team_id=" + team_id + ", min_age=" + min_age + ", avg_age=" + avg_age
				+ ", max_age=" + max_age + ", min_height=" + min_height + ", avg_height=" + avg_height + ", max_height="
				+ max_height + ", min_weight=" + min_weight + ", avg_weight=" + avg_weight + ", max_weight="
				+ max_weight + "]";
	}

	public List<Double> allStatsList() {

		List<Double> stats = new ArrayList<Double>();

		stats.add((double) min_age);
		stats.add(avg_age);
		stats.add((double) max_age);

		stats.add((double) min_height);
		stats.add(avg_height);
		stats.add((double) max_height);

		stats.add((double) min_weight);
		stats.add(avg_weight);
		stats.add((double) max_weight);

		return stats;

	}

}
