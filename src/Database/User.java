package Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Class to represent a PlayStation user.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class User {
	private String username; 
	private int level; 
	private double key; 
	private ArrayList<Trophy> trophies; 
	private GameList games; 
	private Calendar dob; 
	private User left; 
	private User right; 

	public User() {}
	public User(String username, Calendar dob, int level) {
		this.username = username;
		this.dob = dob;
		this.level = level;
		this.key = this.calculateKey();
    }

    /**
     * DO NOT MODIFY THIS METHOD
     * This method uses the username and level to create a unique key.
     * As we don't want the username's hash to increase the level, it's first converted
     * to a floating point, then added to the level.
     * @return the unique key
     */
    public double calculateKey() {
        int hash = Math.abs(username.hashCode());
        // Calculate number of zeros we need
        int length = (int)(Math.log10(hash) + 1);
        // Make a divisor 10^x
        double divisor = Math.pow(10, length);
        // Return level.hash
        return level + hash / divisor;
    }
    @Override
    public String toString() {
    	String result = "User: " + username +"\n"; 
    	//use SimpleDateFormat class to set pattern for the 'obtained' date
    	SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
    	//list all the trophies in 'trophiesStr' string
    	String trophiesStr = "";
    	for(int i = 0; i < trophies.size(); i++) {
    		//loop through the trophy array list to get the information of each trophy 
    		//include name, rank, rarity and obtained date
    		trophiesStr += '"' + trophies.get(i).getName() +'"'
    				+", rank: " + trophies.get(i).getRank() 
    				+", rarity: " + trophies.get(i).getRarity() 
    				+", obtained on: " + dateFormat.format(trophies.get(i).getObtained().getTime()) + "\n";
    	}
    	//output is a string of information of all the trophies
    	String t = "\n" + "Trophies: \n" + trophiesStr;
    	
    	//list all the games
    	String gameStr = "\nGames: \n" + games.toString() +"\n";
    	String bd = "\nBirth Date: " + dateFormat.format(dob.getTime());
    	
		return result + t + gameStr + bd;}

    //get user name
	public String getUsername() {
		return this.username;
	}
	//set user name
	public void setName(String name) {
		this.username = name;
	}

	//get date of birth
	public Calendar getDob() {
		return this.dob;
	}
	//set date of birth
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	//get level of a user
	public int getLevel() {
		return this.level;
	}
	//set level of a user
	public void setLevel(int level) {
		this.level = level;
	}
	//get key value of a user
	public double getKey() {
		return this.key;
	}

	//set key of a user
	public void setKey(double key) {
		this.key = key;
	}
	
	//get game list of a user
	public GameList getGames() {
		// TODO Auto-generated method stub
		return this.games;
	}
	//set game list of a user
	public void setGames(GameList games) {
		this.games = games;
	}
	
	//get trophy list of a user
	public ArrayList<Trophy> getTrophies() {
		// TODO Auto-generated method stub
		return this.trophies;
	}
	//set trophy list of a user
	public void setTrophies(ArrayList<Trophy> trophies) {
		this.trophies = trophies;
	}
	//get left node
	public User getLeft() {
		// TODO Auto-generated method stub
		return left;
	}
	//set left node
	public void setLeft(User left) {
		// TODO Auto-generated method stub
		this.left = left;
	}
	//get right node
	public User getRight() {
		// TODO Auto-generated method stub
		return right;
	}
	//set right node
	public void setRight(User right) {
		// TODO Auto-generated method stub
		this.right = right;
	}
	

	

	

	
	
	
}
