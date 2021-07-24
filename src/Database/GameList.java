package Database;

/**
 * Class to represent a single linked-list of Database.Game objects.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class GameList {

    public Game head;
    //Constructor with given head
	public GameList(Game head) {
		if(head != null) {
			this.head = head; 
			while(head.getNext() != null) {
				head = head.getNext();
				}					
			}
		else {
			head = null;
			}
    }

    public String toString() {
    	String result = "";
    	Game temp = head;
    	if(head == null) {
    		return "Empty game list";
    	}
    	while(temp != null) {
    		result += temp.toString();
    		if(temp.getNext()!=null) {
    			result += "\n";
    		}
    		temp = temp.getNext();	
    	}
		return result;
    }

	/**
	 * Add the provided game to the end of the game list
	 * @param g2 - provided game
	 */
	public void addGame(Game g2) {
		// TODO Auto-generated method stub
		//Handle null provided Game object
		if (g2 == null) {
			throw new IllegalArgumentException();
		}
		else {
		//check if the list is empty and if it is, set provided game as list head
		if(head == null) {
			head = g2;	
		}
		else {
		//search for available slot
		Game temp = head;
		while(temp.getNext()!=null) {
			temp = temp.getNext();	
		}	
		//set the previous game's next variable
		temp.setNext(g2);
		g2.setNext(null);
		}	
		}						
	}

	/**
	 * Traverse the linked list to find a game with the matching name
	 * @param string - given game name
	 * @return the found game if it exist in the linked list
	 */
	public Game getGame(String string) {
		// handle null argument
		if (string == null) {
			throw new IllegalArgumentException();
		}
		else {
			
			Game h = head;
			//Loop through linked list to find the game with matching name
			while(h.getNext()!= null && !h.getName().equals(string)) {
				h = h.getNext();
			}
			if (h.getName().equals(string)) {
				return h;
			}
			//if the game cannot be found
		return null;
		}
			
	}

	/**
	 * Search the linked list for the target game and remove it from the list
	 * @param str - given game name
	 */
	public void removeGame(String str) {
		// handle null argument 
		if (str == null) {
			throw new IllegalArgumentException();
		}
		//if the head is the game to remove then set head's next game to be list's head
		if(head != null && head.getName().equals(str)) {
			head = head.getNext();
		}
		//else, start searching from the linked list head and the next game
		Game temp = head;
		Game tn = temp.getNext();
		
	    while (tn.getNext() != null && !tn.getName().equals(str)) {
	    	//if the reference game is not the game to remove, move to the next game
	    	tn = tn.getNext();
	    	temp = temp.getNext();  
	    }
	    //remove game in the middle of the list
	    if(tn.getNext()!=null && tn.getName().equals(str)) {
	        temp.setNext(tn.getNext());
	    }
	    //remove game at the end of the list
	    if (tn.getNext()==null && tn.getName().equals(str)) {
	        temp.setNext(null);
	    }
	}

	/**
	 * Search the linked list for the target game and remove it from the list
	 * @param str - given game 
	 */
	public void removeGame(Game g) {
		// handle null argument 
		if (g == null) {
			throw new IllegalArgumentException();
		}
		//if the head is the game to remove then set head's next game to be list's head
		if(head != null && head.getName().equals(g.getName())) {
			head = head.getNext();
		}
		//else, start searching from the linked list head and the next game
		Game temp = head;
		Game tn = temp.getNext();
	    while (tn.getNext() != null && !tn.getName().equals(g.getName())) {
	    	//if the reference game is not the game to remove, move to the next game
	        tn = tn.getNext();
	        temp = temp.getNext();
	    }
	    //remove game in the middle of the list
	    if(tn.getNext()!=null && tn.getName().equals(g.getName())) {
	        temp.setNext(tn.getNext());
	    }
	  //remove game at the end of the list
	    if (tn.getNext()==null && tn.getName().equals(g.getName())) {
	        temp.setNext(null);
	    }
		
	}

}
