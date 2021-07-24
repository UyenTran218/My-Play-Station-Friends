package Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;

/**
 * Class to represent a PlayStation game trophy. A trophy comes in
 * four ranks: bronze, silver, gold and platinum. The date the trophy was
 * earned and its respective game is also stored.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class Trophy {
	public enum Rank { 
		BRONZE, SILVER, GOLD, PLATINUM 
		} 
		public enum Rarity {
		COMMON, UNCOMMON, RARE, VERY_RARE, ULTRA_RARE
		}
		private String name; 
		private Rank rank; 
		private Rarity rarity; 
		private Calendar obtained; 
		private Game game;

	public Trophy() {}

    public Trophy(String name, Rank rank, Rarity rarity, Calendar obtained, Game game)
    {   
    	this.name = name;
    	this.rank = rank;
    	this.rarity = rarity;
    	this.obtained = obtained;
    	this.game = game;
    }

    @Override
    public String toString() {
    	//use SimpleDateFormat class to set pattern for the 'obtained' date
    	SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
    	String result = "\"" + name + "\", rank: " + rank.toString() 
    		+ ", rarity: " + rarity.toString() + ", obtained on: " + dateFormat.format(obtained.getTime()); 
		return result;
    }

    //get trophy name
	public String getName() {
		return name;
	}

	//get trophy rank
	public Rank getRank() {
		return rank;
	}

	//get trophy rarity
	public Rarity getRarity() {
		return rarity;
	}
	//get trophy obtained date
	public Calendar getObtained() {
		return obtained;
	}
	//get the game from which it was earnt
	public Game getGame() {		
		return game;
	}
}
