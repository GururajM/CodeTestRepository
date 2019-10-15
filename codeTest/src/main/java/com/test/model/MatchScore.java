package com.test.model;

import java.io.Serializable;
import java.util.List;

/**
* Program to represent model for details of match details
* from the specified league and match day.
*
* @author  Gururaj Maddodi
* @version 1.0
* @since   2019-10-14
*/
public class MatchScore implements Serializable{

	private static final long serialVersionUID = 3L;

	private String homeTeam;				//Holds home team name
	private String awayTeam;				//Holds away team name
	private int homeGoals;					//Holds home team number of goals scored
	private int awayGoals;					//Holds home away number of goals scored
	private List<String> homeGoalScores;	//Holds names of the home team goals scorers
	private List<String> awayGoalScores;	//Holds names of the away team goals scorers
	
	public MatchScore(String homeTeam, String awayTeam, int homeGoals, int awayGoals, List<String> homeGoalScores,
			List<String> awayGoalScores) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
		this.homeGoalScores = homeGoalScores;
		this.awayGoalScores = awayGoalScores;
	}
	
	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(int homeGoals) {
		this.homeGoals = homeGoals;
	}

	public int getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(int awayGoals) {
		this.awayGoals = awayGoals;
	}

	public List<String> getHomeGoalScores() {
		return homeGoalScores;
	}

	public void setHomeGoalScores(List<String> homeGoalScores) {
		this.homeGoalScores = homeGoalScores;
	}

	public List<String> getAwayGoalScores() {
		return awayGoalScores;
	}

	public void setAwayGoalScores(List<String> awayGoalScores) {
		this.awayGoalScores = awayGoalScores;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayGoalScores == null) ? 0 : awayGoalScores.hashCode());
		result = prime * result + awayGoals;
		result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((homeGoalScores == null) ? 0 : homeGoalScores.hashCode());
		result = prime * result + homeGoals;
		result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchScore other = (MatchScore) obj;
		if (awayGoalScores == null) {
			if (other.awayGoalScores != null)
				return false;
		} else if (!awayGoalScores.equals(other.awayGoalScores))
			return false;
		if (awayGoals != other.awayGoals)
			return false;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;
		if (homeGoalScores == null) {
			if (other.homeGoalScores != null)
				return false;
		} else if (!homeGoalScores.equals(other.homeGoalScores))
			return false;
		if (homeGoals != other.homeGoals)
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MatchScore [homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", homeGoals=" + homeGoals
				+ ", awayGoals=" + awayGoals + ", homeGoalScores=" + homeGoalScores + ", awayGoalScores="
				+ awayGoalScores + "]";
	}
}
