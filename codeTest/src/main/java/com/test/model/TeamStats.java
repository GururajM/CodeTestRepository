package com.test.model;

import java.io.Serializable;
import java.util.List;

/**
* Program to represent model for details of team 
* from the specified league.
*
* @author  Gururaj Maddodi
* @version 1.0
* @since   2019-10-14
*/
public class TeamStats implements Serializable{

	private static final long serialVersionUID = 3L;

	private String teamName;			//Holds team name
	private String competition;			//Holds home competition name
	private int points;					//Holds number of points
	private int gamesWon;				//Holds number of games won
	private int gamesDrawn;				//Holds number of games drawn
	private int gamesLost;				//Holds number of games lost
	private int goalScored;				//Holds number of goals scored
	private int goalConceeded;			//Holds number of goals conceeded

	public TeamStats(String teamName, String competition, int points, int gamesWon, int gamesDrawn, int gamesLost,
			int goalScored, int goalConceeded) {
		super();
		this.teamName = teamName;
		this.competition = competition;
		this.points = points;
		this.gamesWon = gamesWon;
		this.gamesDrawn = gamesDrawn;
		this.gamesLost = gamesLost;
		this.goalScored = goalScored;
		this.goalConceeded = goalConceeded;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public String getCompetition() {
		return competition;
	}
	
	public void setCompetition(String competition) {
		this.competition = competition;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getGamesWon() {
		return gamesWon;
	}
	
	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}
	
	public int getGamesDrawn() {
		return gamesDrawn;
	}
	
	public void setGamesDrawn(int gamesDrawn) {
		this.gamesDrawn = gamesDrawn;
	}
	
	public int getGamesLost() {
		return gamesLost;
	}
	
	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
	}
	
	public int getGoalScored() {
		return goalScored;
	}
	
	public void setGoalScored(int goalScored) {
		this.goalScored = goalScored;
	}
	
	public int getGoalConceeded() {
		return goalConceeded;
	}
	
	public void setGoalConceeded(int goalConceeded) {
		this.goalConceeded = goalConceeded;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((competition == null) ? 0 : competition.hashCode());
		result = prime * result + gamesDrawn;
		result = prime * result + gamesLost;
		result = prime * result + gamesWon;
		result = prime * result + goalConceeded;
		result = prime * result + goalScored;
		result = prime * result + points;
		result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
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
		TeamStats other = (TeamStats) obj;
		if (competition == null) {
			if (other.competition != null)
				return false;
		} else if (!competition.equals(other.competition))
			return false;
		if (gamesDrawn != other.gamesDrawn)
			return false;
		if (gamesLost != other.gamesLost)
			return false;
		if (gamesWon != other.gamesWon)
			return false;
		if (goalConceeded != other.goalConceeded)
			return false;
		if (goalScored != other.goalScored)
			return false;
		if (points != other.points)
			return false;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "TeamStats [teamName=" + teamName + ", competition=" + competition + ", points=" + points + ", gamesWon="
				+ gamesWon + ", gamesDrawn=" + gamesDrawn + ", gamesLost=" + gamesLost + ", goalScored=" + goalScored
				+ ", goalConceeded=" + goalConceeded + "]";
	}
}
