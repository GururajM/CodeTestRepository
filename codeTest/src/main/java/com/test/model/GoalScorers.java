package com.test.model;

import java.io.Serializable;

/**
* Program to represent model for details of goal scorers
* till the current match week.
*
* @author  Gururaj Maddodi
* @version 1.0
* @since   2019-10-14
*/
public class GoalScorers implements Serializable{
	private static final long serialVersionUID = 3L;

	private String playerName;		//Name of the player who had scored goal/s
	private int numGoals;			//Number of goals the player has scored
	
	public GoalScorers(String playerName, int numGoals) {
		super();
		this.playerName = playerName;
		this.numGoals = numGoals;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public int getNumGoals() {
		return numGoals;
	}
	
	public void setNumGoals(int numGoals) {
		this.numGoals = numGoals;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numGoals;
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
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
		GoalScorers other = (GoalScorers) obj;
		if (numGoals != other.numGoals)
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "GoalScorers [playerName=" + playerName + ", numGoals=" + numGoals + "]";
	}
}
