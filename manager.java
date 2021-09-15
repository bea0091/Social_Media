package Social_Media;
/* Team name : A-Yo BJ
 * Team Members : Eunbee Na (20383329) & Jaeeun Jung (20198902)
 * CIS 22C Data Structure
 * Dec 11 2020
 */


import java.util.Scanner;


public class manager<T> {
	private ListInterface<profiles> users = new AList<profiles>();
	private static UndirectedGraph<String> profileGraph = new UndirectedGraph<>();
	
	static profiles p1 = new profiles("Bea", "Online");
	static profiles p2 = new profiles("Jae", "Online");
	static profiles p3 = new profiles("Chris", "Busy");
	static profiles p4 = new profiles("Joy", "Offline");
	static profiles p5 = new profiles("Dane", "Sleepy");
	static profiles p6 = new profiles("Sam", "Working");
	static profiles p7 = new profiles("Alex", "Working");
	static profiles p8 = new profiles("Evelyn", "Offline");
	
	static String username;

	
	Scanner sc = new Scanner(System.in);

	public void openUpMainroom() {
	
		manager<profiles> database = new manager<profiles>();
		
		users.add(p1);
		users.add(p2);
		users.add(p3);
		users.add(p4);
		users.add(p5);
		users.add(p6);
		users.add(p7);
		users.add(p8);
		
		setGraph();
	
		boolean makeYourOwnProfile = false;

		while (true) {
			System.out.println("\n=================================");
			System.out.println("[ MAIN ROOM ]");
			System.out.println("1. Create a profile");
			System.out.println("2. Modify the profile");
			System.out.println("3. Search for other profile");
			System.out.println("4. Add friends");
			System.out.println("5. Remove friends");
			System.out.println("6. Display my friends");
			System.out.println("7. Display all users' profiles");
			System.out.println("8. Exit");
			System.out.println("=================================");
			System.out.print(">> ");

			int menu = sc.nextInt();
			
			if (menu == 8) {
				profileGraph.clear();
				System.out.println("Close the main room. Bye!");
				break;
			}

			else if (menu == 1) {
				database.create(users);
				makeYourOwnProfile = true;
			}

			else if (menu == 2) {
				if (makeYourOwnProfile)
					database.modify(users);
				else 
					System.out.println("Sorry! You should create you profile first to use this service.");
			}
			
			else if (menu == 3) {
				if (makeYourOwnProfile)
					database.search(users);
				else 
					System.out.println("Sorry! You should create you profile first to use this service.");
			}
			
			else if (menu == 4) {
				if (makeYourOwnProfile)
					database.add(users);
				else 
					System.out.println("Sorry! You should create you profile first to use this service.");
			}
			
			else if (menu == 5) {
				if (makeYourOwnProfile)
					database.remove(users);
				else 
					System.out.println("Sorry! You should create you profile first to use this service.");
			}
			
			else if (menu == 6) {
				if (makeYourOwnProfile)
					database.displayfriends(users);
				else 
					System.out.println("Sorry! You should create you profile first to use this service.");
			}
			
			else if (menu == 7) {
				database.displayall(users);
			}
			
			else
				System.out.println("Invalid value. Choose the service from 1 to 7");
		}
		
	}

	
	// Set a graph with all the profiles as vertex and relations as edges.
	public static void setGraph()
	   {
	      setVertices();
	      
	      setEdges(p1.getName(), p2.getName());
	      setEdges(p1.getName(), p4.getName());
	      setEdges(p1.getName(), p5.getName());
	      
	      setEdges(p2.getName(), p5.getName());
	      
	      setEdges(p3.getName(), p2.getName());
	      
	      setEdges(p4.getName(), p7.getName());
	      
	      setEdges(p5.getName(), p6.getName());
	      setEdges(p5.getName(), p8.getName());
	      
	      setEdges(p6.getName(), p3.getName());
	      setEdges(p6.getName(), p8.getName());
	      
	      setEdges(p7.getName(), p8.getName());
	      
			
	   } 
	
	
	public static void setVertices()
	{
		profileGraph.clear();

		profileGraph.addVertex(p1.getName());
		profileGraph.addVertex(p2.getName());
		profileGraph.addVertex(p3.getName());
		profileGraph.addVertex(p4.getName());
		profileGraph.addVertex(p5.getName());
		profileGraph.addVertex(p6.getName());
		profileGraph.addVertex(p7.getName());
		profileGraph.addVertex(p8.getName());
	} 
	
	
	public static void setEdges(String from, String to)
	{
		profileGraph.addEdge(from, to, 1);
	}
	

	public static void printQueue(QueueInterface<String> q)
	{
		
		if (q.isEmpty()) 
			System.out.println("No friends yet");
		else {
			while (!q.isEmpty())
				System.out.print(q.dequeue() + " ");
		}	
	} // end printQueue
	
	
	// Find the friends of friends
	public static void showFriends(String friend)
	{
		QueueInterface<String> bfs = profileGraph.getBreadthFirstTraversal(friend);
		
		while (!bfs.isEmpty()) {
			String friendOfFriend = bfs.dequeue();
			if (profileGraph.hasEdge(friend, friendOfFriend)) {
				System.out.print(friendOfFriend + "  ");
			}
		}
		
		System.out.println();
	}
	
	
	// Show the relationship between the users
	private static void showRelation(String user1, String user2)
	{
		System.out.println(user1 + " - " + user2 + " : friends");
	} // end showPath
	
	

	// Create a new class profile and add it to the users list
	private void create(ListInterface<profiles> list) {
		System.out.println("\n[ Creating a profile... ]");

		System.out.print("Enter your name : ");
		String name = sc.nextLine();
		System.out.print("Enter your status : ");
		String status = sc.nextLine();
		
		profiles newuser = new profiles(name, status);
		list.add(newuser);
		username = name;
		profileGraph.addVertex(name);
		
		
		System.out.println("Your profile is created!");
		
	}

	
	// Get a new value and replace the profile.
	private void modify(ListInterface<profiles> list) {
		System.out.println("\n[ Modifying the profile... ]");

		System.out.print("Updated name : ");
		String newname = sc.nextLine();
		System.out.print("Updated status : ");
		String newstatus = sc.nextLine();
				
		profiles modifieduser= new profiles(newname, newstatus);
		modifieduser.setFriends(list.getEntry(list.getLength()).getFriends());

		list.replace(list.getLength(), modifieduser);
		
		System.out.println("Your profile is modified!");
	}

	
	// Check whether the input equals to one of the users' names
	// and get the information of the user.
	private void search(ListInterface<profiles> list) {
		System.out.println("\n[ Searching for other profiles... ]");
		profiles targetuser;
		boolean found = false;

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the profile name you want to search for : ");
		String input = sc.nextLine();


		for (int i = 1; i <= list.getLength(); i++) {
			if(list.getEntry(i).getName().equals(input)) {
				targetuser = list.getEntry(i);
				System.out.println("Name : " + targetuser.getName());
				System.out.println("Current status : " + targetuser.getStatus());
				System.out.println("- Friends list -");
				showFriends(targetuser.getName());
				found = true;
			}
		}

		if(found == false) {
			System.out.println("The account doesn't exist!");
		}
	}


	// Check whether the input equals to one of the users names
	// and add the profile to the friend list.
	private void add(ListInterface<profiles> list) {
		System.out.println("\n[ Adding friends... ]");
		System.out.print("Enter the profile name you want to add : ");
		String friendname = sc.nextLine();
		profiles target;
		profiles user = list.getEntry(list.getLength());
		boolean found = false;
		
		for (int i=1; i<=list.getLength(); i++) {
			if (list.getEntry(i).getName().equals(friendname)) {
				target = list.getEntry(i);
				found = true;
				user.addFriend(target);
				profileGraph.addEdge(user.getName(), target.getName(), 1);
				showRelation(username, target.getName());
			}
		}
		
		if (!found)
			System.out.println("The account doesn't exist.");
	}

	
	// Get the input from the user and pass it to the removeFriend function.
	private void remove(ListInterface<profiles> list) {
		System.out.println("\n[ Deleting friends... ]");
		System.out.print("Enter the profile name you want to delete : ");
		String friendname = sc.nextLine();
		profiles user = list.getEntry(list.getLength());
		
		if (profileGraph.hasEdge(user.getName(), friendname)) {
			
		}
	
		user.removeFriend(friendname);
	}
	
	
	// Call the displayFriend function of the user.
	public void displayfriends(ListInterface<profiles> list) {
		list.getEntry(list.getLength()).displayFriend();
	}

	
	// Display all the profiles the database has.
	public void displayall(ListInterface<profiles> list) {
		String info = "";
		for (int i = 1; i <= list.getLength(); i++) {
			System.out.println("\nNAME: " + list.getEntry(i).getName());
			System.out.println("Current status: " + list.getEntry(i).getStatus());
			System.out.println("- Friends list -");
			showFriends(list.getEntry(i).getName());
			System.out.println();
		}

		System.out.println(info);
	}

}
