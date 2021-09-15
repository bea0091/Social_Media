package Social_Media;
/* Team name : A-Yo BJ
 * Team Members : Eunbee Na (20383329) & Jaeeun Jung (20198902)
 * CIS 22C Data Structure
 * Dec 11 2020
 */


public class profiles {
	private String name;
	private String status;
	private ListInterface<profiles> friends;
	
	// constructor
	public profiles(String name, String status) {
		this.name = name;
		this.status = status;
		friends = new LList<profiles> ();
	}

	// constructor
	public profiles(String name, String status, int numberOfFriends) {
		this.name = name;
		this.status = status;
	}
	
	// getter and setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ListInterface<profiles> getFriends() {
		return friends;
	}

	public void setFriends(ListInterface<profiles> friends) {
		this.friends = friends;
	}
	
	
	// Check whether the friend list contains the given profile
	// and add it to the list.
	public void addFriend(profiles user) {
		
		if (!friends.contains(user)) {
			friends.add(user);
			System.out.println("Now you are following this account!");
		}
		
		else 
			System.out.println("You've already followed this account.");
	}
	

	// Check whether the friend list has the input string 
	// and remove the profile from the list.
	public void removeFriend(String user) {
		
		boolean found = false;
		
		for (int i = 1; i <= friends.getLength(); i++) {
			profiles targetuser = friends.getEntry(i);
			if (targetuser.getName().equals(user)) {
				friends.remove(i);
				found = true;
				System.out.println("Now you are unfollowing this account!");
			}
		}
		
		if (!found)
			System.out.println("You are not following this account.");
	}
	
	
	// Display user's friend list
	public void displayFriend() {
		System.out.println("\nHere is your friend list");
		for (int i=1; i<=friends.getLength(); i++) {
			System.out.print(friends.getEntry(i).getName() + "    ");
		}
		System.out.println();
	}
	
	
	
}

