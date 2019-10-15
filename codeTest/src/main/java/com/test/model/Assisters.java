package com.test.model;

import java.io.Serializable;

/**
* Program to represent model for details of assist makers
* till the current match week.
*
* @author  Gururaj Maddodi
* @version 1.0
* @since   2019-10-14
*/
public class Assisters implements Serializable{

	private static final long serialVersionUID = 3L;

	private String playerName;	//Name of the player who had assists
	private int numAssists;		//Number of assists the player has
	
	public Assisters(String playerName, int numAssiss) {
		super();
		this.playerName = playerName;
		this.numAssists = numAssiss;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getNumAssists() {
		return numAssists;
	}
	public void setNumAssists(int numAssists) {
		this.numAssists = numAssists;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numAssists;
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
		Assisters other = (Assisters) obj;
		if (numAssists != other.numAssists)
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
		return "Assisters [playerName=" + playerName + ", numAssists=" + numAssists + "]";
	}
}