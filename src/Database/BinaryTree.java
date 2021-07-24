package Database;

import java.util.*;


/**
 * Uses a binary search tree to store a database of
 * PlayStation users. Nodes are ordered by user unique key (see the
 * User class for more detail).
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class BinaryTree {

	public User root;

	/**
	 * Making new friends is great. This method should add your new
	 * bestie to your database (tree). Remember that they should be
	 * added according to their key.
	 * @param friend The friend to be added
	 * @return true if  successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean beFriend(User friend) throws IllegalArgumentException {
		if(friend==null) {
			// handle null argument
			throw new IllegalArgumentException();
		}
		else {
			try {
				//Add to empty tree
				if(root==null){
					//if there is no root then set user 'friend' the root of the tree
					root = friend;
					return true;
				}
				
				User current = root;
				User parent = null;
				
				while(true){
					parent = current;
					//Add to the left side if 'friend' key value is smaller than the root user key value
					if(friend.getKey()<current.getKey()){				
						current = current.getLeft();
						if(current==null){
							parent.setLeft(friend);
							return true;
						}
					}else
					//Add to the right side if 'friend' key value is greater than the root user key value
					if(friend.getKey()>current.getKey())
					{
						current = current.getRight();
						if(current==null){
							parent.setRight(friend);
							return true;
						}
					}
					//Duplicate add and other errors  - return false 
					else {
						return false;
					}
				}
		}
		catch (Exception e) {
			return false;
		}
		}	
	}

	/**
	 * Sometimes friendships don't work out. In those cases it's best
	 * to remove that "friend" altogether. This method should remove
	 * all trace of that "friend" in the database (tree).
	 * @param friend the "friend" to remove
	 * @return true if successfully removed, false for all error cases
	 * @throws IllegalArgumentException if "friend" is null
	 */
	public boolean deFriend(User friend) throws IllegalArgumentException {
		if(friend==null) {
		// handle null argument
		throw new IllegalArgumentException();
		}
		try {
		//check if user 'friend' exist in the database
		if(ifExists(root,friend)==false) {
			return false;
		}
		else
		//if user exist in the database then remove 
		root = remove(root,friend);
		return true;
		//return false for all error cases
		}catch (Exception e){
			return false;
		}
	}
	/**
	 * Helper function used to find and remove user in the database
	 * @param root of the tree
	 * @param remove - the user need to be removed
	 * @return 
	 */
	public User remove (User root, User remove) {
		if (root == null) {
		// item is not in the tree.
		return null;
		}
		// Search for item to delete.
		else
		if (remove.getKey()<root.getKey()) {
		// item is smaller than root.getKey()
		root.setLeft(remove(root.getLeft(),remove));
		return root;
		} 
		else
		if (remove.getKey()>root.getKey()) {
			// item is larger than root.getKey()
			root.setRight(remove(root.getRight(),remove));
			return root;
		} 
		else // item is at local root.
		if (root.getLeft() == null) {
		// If there is no left child, return right child
			return root.getRight();
		} 
		else 
		if (root.getRight() == null) {
		// If there is no right child, return left child.
			return root.getLeft();
		} 
		else // Node being deleted has 2 children, replace the data with inorder predecessor.
		if (root.getLeft().getRight() == null) {
		// The left child has no right child.
		// Replace the data with the data in the left child.
		copyData(root.getLeft(),root);
		// Replace the left child with its left child.
		root.setLeft(root.getLeft().getLeft());;
		return root;
		} 
		else
		{// Search for the inorder predecessor (ip) and
		 // replace deleted node's data with ip.
		copyData(findLargestChild(root.getLeft()),root);
		return root;
		}
	}

	
	/** Find the node that is the
	inorder predecessor and replace it
	with its left child (if any).
	post: The inorder predecessor is removed from the tree.
	@param parent The parent of possible inorder
	predecessor (ip)
	@return The data in the ip
	*/
	private User findLargestChild(User parent) {
		// If the right child has no right child, it is
		// the inorder predecessor.
		if (parent.getRight().getRight() == null) {
			User x = parent.getRight();
			parent.setRight(parent.getRight().getLeft());
			return x;
		}else {
			return findLargestChild(parent.getRight());
		}
	}
	/** Copy data from user x to user y
	 * when x and y is not null
	 * @param x - user has the data to be copied
	 * @param y - user has the data copied from user x
	 */
	private void copyData(User x, User y) {
		//if x is null then set y null
		if (x == null) {
			y = null;
		}else {
			//copy all the data of user x to user y
			y.setName(x.getUsername());
			y.setDob(x.getDob());
			y.setLevel(x.getLevel());
			y.setGames(x.getGames());
			y.setTrophies(x.getTrophies());
			y.setKey(x.getKey());	
		}
	}
	
	/**
	 * In your quest to be the very best you need to know how many
	 * of your friends are ranked higher than you. This method should
	 * return the number of higher ranked users that the provided reference
	 * user, or zero if there are none (woot!).
	 * @param reference The starting point in the search
	 * @return Number of higher ranked users or -1 if user not found
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countBetterPlayers(User reference) throws IllegalArgumentException {
		if(reference==null) {
			// handle null argument
			throw new IllegalArgumentException();
		}else
			//check if user 'reference' exist in the database
		if(ifExists(root,reference)==false) {
	    	return -1;
	    }
		else 
			//if user exist in the database, start counting higher ranked users
			return greaterCount(root,reference);
		    
	}

	/**
	 * Recursive searching and counting number of users thats have higher rank
	 * @param root
	 * @param ref - where the searching and counting start
	 * @return number of users have higher rank
	 */
	public int greaterCount(User root, User ref)
	{
		//check if the root is null
	    if (root == null) {
	        return 0;
	    }
	    //search for users with greater key value and count them
	    else if (root.getKey() > ref.getKey() && root.getLevel()!=ref.getLevel()) {
	        return 1 + greaterCount(root.getLeft(), ref) + greaterCount(root.getRight(), ref);
	    }
	    else {
	        return greaterCount(root.getLeft(), ref) + greaterCount(root.getRight(), ref) ;
	    }

	}
	/**
	 * Bragging rights are well earned, but it's good to be sure that you're actually
	 * better than those over whom you're lording your achievements. This method
	 * should find all those friends who have a lower level than you, or zero if
	 * there are none (you suck).
	 * @param reference The starting point in the search
	 * @return Number of lower ranked users
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countWorsePlayers(User reference) throws IllegalArgumentException {
		if(reference==null) {
			// handle null argument
			throw new IllegalArgumentException();
		}else 
			//check if user 'reference' exist in the database
			if(ifExists(root,reference)==false) {
		    	return -1;
		    }
			else 
			//if user exist in the database, start counting lower ranked users
			return smallerCount(root,reference);
			
	}
		
	/**
	 * Recursive searching and counting number of users thats have lower rank
	 * @param root
	 * @param ref - where the searching and counting start
	 * @return number of users have lower rank
	 */
	public int smallerCount(User root, User ref)
	{
		//check if the root is null
	    if (root == null) {
	        return 0;
	    }
	  //search for users with smaller key value and count them
	    else if (root.getKey() < ref.getKey() && root.getLevel()!=ref.getLevel()) {
	        return 1 + smallerCount(root.getLeft(), ref) + smallerCount(root.getRight(), ref);
	    }
	    else {
	        return smallerCount(root.getLeft(), ref) + smallerCount(root.getRight(), ref);
	    }

	}
	
	/**
	 * Recursively traverse the tree to check if a user exist in the database
	 * @param root - root user in the tree
	 * @param user - the user we need to check its existence in the database
	 * @return boolean - return true if the user exist in the database
	 */
	public boolean ifExists(User root, User user)
	{
		//check if the tree is empty
	    if (root == null)
	        return false;
	 
	  //check if user is the root 
	    if (root.getKey() == user.getKey())
	        return true;
	 
	    // check if user exist on left subtree
	    boolean leftRes = ifExists(root.getLeft(), user);
	   
	    // user found, return true
	    if(leftRes) return true;
	 
	    // user is not found in left, search on right subtree 
	    boolean rightRes = ifExists(root.getRight(), user);
	 
	    return rightRes;
	}

	/**
	 * The best player may not necessarily be measured by who has the highest level.
	 * Platinum trophies are the holy grail, so it would be good to know who has the
	 * most. This method should return the user with the highest number of platinum trophies.
	 * @return the user with the most platinum trophies, or null if there are none
	 */
	public User mostPlatinums() {
		//traverse and transfer binary search tree to an array list of users
		List<User> users = transferBSTreeToList(root);
		
		int countP = 0;
		int countP1 = 0;
		int countG = 0;
		int countG1 = 0;
		int maxP = 0;
		int maxG = 0;
		User a = null;
		for(int i = 0; i < users.size();i++) {
			ArrayList<Trophy> trophyList = users.get(i).getTrophies();
			//count platinum trophy of each user
	    	for(int j = 0; j < trophyList.size();j++) {
	    		Trophy t = trophyList.get(j);
	    		if(t.getRank() == Trophy.Rank.PLATINUM) {
	    			countP += 1;
	    		}
	    		if(t.getRank() == Trophy.Rank.GOLD) {
	    			countG += 1;
	    		}
	    	}
	    	if(countP > maxP) {
	    		maxP = countP;
	    		
	    	}
	    	if(countP == maxP && countP > 0){
	    		if(countG > maxG) {
	    			maxG = countG;
	    			
	    		}
	    	}
	    	}
	    	for(int k = 0; k < users.size(); k++) {
	    		ArrayList<Trophy> trophyList = users.get(k).getTrophies();
    			for(int j = 0; j < trophyList.size();j++) {
    	    		Trophy t = trophyList.get(j);
    	    		if(t.getRank() == Trophy.Rank.PLATINUM) {
    	    			countP1 += 1;
    	    		}
    	    		
    	    		}
    			if(countP1 == maxP) {
    				a = users.get(k);
    			}
    			}
	    	
	    	for(int k = 0; k < users.size(); k++) {
	    		ArrayList<Trophy> trophyList = users.get(k).getTrophies();
    			for(int j = 0; j < trophyList.size();j++) {
    	    		Trophy t = trophyList.get(j);
    	    		if(t.getRank() == Trophy.Rank.GOLD) {
    	    			countG1 += 1;
    	    		}
    	    		
    	    		}
    			if(countG1 == maxG) {
    				a = users.get(k);
    			}
    			}
			return a;
	    
	}

	/**
	 * You or one of your friends bought a new game! This method should add it to their
	 * GameList.
	 * @param username The user who has bought the game
	 * @param game The game to be added
	 */
	public void addGame(String username, Game game) throws IllegalArgumentException {
		if(game == null || username == null) {
			// handle null arguments
			throw new IllegalArgumentException();
		}
		else {
			//traverse and transfer binary search tree to an array list of users
			List<User> users = transferBSTreeToList(root);
			//loop through the array list of user to find the matching user
		    for(int i = 0; i < users.size();i++) {
		    	//when the user is found, add the given game to the user's game linked list
		    	if(users.get(i).getUsername()==username) {
		    		users.get(i).getGames().addGame(game);
		    	}		
		    }
		}
	}
	

	/**
	 * You or one of your friends achieved a new trophy! This method should add it to
	 * their trophies.
	 * @param username The user who has earned a new trophy
	 * @param trophy The trophy to be added   
	 */
	public void addTrophy(String username, Trophy trophy) throws IllegalArgumentException {
		if(trophy==null || username==null) {
			// handle null arguments
			throw new IllegalArgumentException();
		}else {
			//traverse and transfer binary search tree to an array list of users
		    List<User> users = transferBSTreeToList(root);
		    //loop through the array list of user to find the matching user
		    for(int i = 0; i < users.size();i++) {
		    	//when the user is found, add the given trophy to the user's trophy array list
		    	if(users.get(i).getUsername()==username) {
		    		users.get(i).getTrophies().add(trophy);
		    	}		
		    }
		}
	}
	

	/**
	 * You or one of your friends has gained one level! This is great news, except that
	 * it may have ruined your tree structure! A node move may be in order.
	 * @param username The user whose level has increased
	 */
	public void levelUp(String username) throws IllegalArgumentException {
		if(username == null) {
			// handle null argument
			throw new IllegalArgumentException();
		}
		//'before' refer to the user before level up which will be removed from the tree
		User before = new User();
		//'after' has the level changed and will be inserted to the tree
		User after = new User();
		//traverse and transfer binary search tree to an array list of users
		List<User> users = transferBSTreeToList(root);
		//find the user that will level up
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getUsername() == username) {
				//set before refer to the user that will level up
				before = users.get(i);
			}
		}
		//check if the username is wrong or non exist
		if(ifExists(root,before) == true) {
			//remove user before level up
			deFriend(before);
			//copy data to user to add after level up
			copyData(before,after);
			//level up - set after's level = before's level + 1
			after.setLevel(after.getLevel() + 1);
			//insert user after level up
			beFriend(after);
		}else {
			// handle username is wrong or non exist
			throw new NoSuchElementException();
		}
			
	}

	/**
	 * As your friends list grows, adding with regular binary tree rules will
	 * result in an unbalanced and inefficient tree. One approach to fix this
	 * is to implement an add method that uses AVL balancing. This method should
	 * work in the same way as beFriend, but maintain a balanced tree according to
	 * AVL rules.
	 * @param friend The friend to be added
	 * @return true if  successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean addAVL(User friend) throws IllegalArgumentException {
		return false;
	}

	/**
	 * A nice, neat print-out of your friends would look great in the secret scrap-book
	 * that you keep hidden underneath your pillow. This method should print out the
	 * details of each user, traversing the tree in order.
	 * @return A string version of the tree
	 */
	public String toString() {
		String result = "";
		//traverse and transfer binary search tree to an array list
		List<User> users = transferBSTreeToList(root);
		//loop through array list of users to get user info
		for(int i = 0; i< users.size()-1;i++) {
			result += users.get(i).toString() + "\n";	
			}
		//the last user does not need to enter a new line when complete printing
		result += users.get(users.size()-1);
		return result;
	}
	/**
	 * Use stack as a tool to traverse binary search tree inorder 
	 * and transfer it to an array list of users 
	 * @param root
	 * @return output array list 
	 */
	public List<User> transferBSTreeToList(User root){
		//stack of users
		Stack<User> stack = new Stack<>();
		//output array list of users
		List<User> output = new ArrayList<User>();
		
		if(root == null) {
			//no tree to traverse
			return null;
			}
		//start with the root of the tree
		User current = root;
		
		while(current!=null || !stack.isEmpty()) {
			//traverse the left side of the tree
			while(current!=null) {
				//push the current user to the stack
				stack.push(current);
		    	current = current.getLeft();
		    }
		    //remove and get the user on the top of the stack 	
		    current = stack.pop();
		    //insert current user to the output array list
		    output.add(current);
		    //traverse the right side of the tree
		    current = current.getRight();
		    }
		return output;
	}
	
}
