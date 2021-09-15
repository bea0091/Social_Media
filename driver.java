package Social_Media;
/* Team name : A-Yo BJ
 * Team Members : Eunbee Na (20383329) & Jaeeun Jung (20198902)
 * CIS 22C Data Structure
 * Dec 11 2020
 */


import java.util.Scanner;

public class driver<T> {
	
	// Ask the users whether they want to join the service or not
	public static void main(String[] args) {
		manager managing = new manager();
		
		System.out.println("\nWelcome to AYO's social media!");
		System.out.print("Do you want to join the network? [y/n] : ");
		
		Scanner sc = new Scanner(System.in);
		char answer = sc.next().charAt(0);
		
		if (answer == 'y') {
			managing.openUpMainroom();
		}
		
		else {
			System.out.println("Leave the network now. Bye!");
		}
		
		
	}
}
