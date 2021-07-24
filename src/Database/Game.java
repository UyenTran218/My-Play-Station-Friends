package Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class to represent a PlayStation game.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class Game {
	private String name; 
	private Calendar released; 
	private Game next; 
	private int totalTrophies; 

    public Game() {}

    public Game(String name, Calendar released, int totalTrophies) {
    	this.name = name;
    	this.released = released;
    	this.totalTrophies = totalTrophies;
    }
    @Override
    public String toString() {
    	//use SimpleDateFormat class to set pattern for the 'obtained' date
    	SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
    	String result = '"' + name + '"' + ", released on: " + dateFormat.format(released.getTime());
		return result;
    }

    @Override
    public boolean equals(Object o) {
    	//if o is an instance of Game
    	 if(o instanceof Game) {
    	      Game that = (Game) o;
    	      //compare name, release date and total trophies of 2 games
    	      return (this.name.equals(that.getName()) 
    	    		  && this.released.equals(that.getReleased()) 
    	    		  && this.totalTrophies == that.getTotalTrophies());
    	    } else {
    	      return false;
    	    }
    }
    //get the game released date
	public Calendar getReleased() {
		return released;
	}
	//get number of trophies
	public int getTotalTrophies() {
		return totalTrophies;
	}
	//get game's name
	public String getName() {
		return name;
	}
	//set game's name
	public void setNext(Game g2) {
		this.next = g2;
	}
	//get the next game
	public Game getNext() {
 		return this.next;
	}
}
