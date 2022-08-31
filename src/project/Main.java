package project;
import java.util.*;

public class Main {
	
	GetInfo getInfo = new GetInfo();
	
	void frontPage(int choice) {
		switch(choice) {
			case 1 :				
				getInfo.getBusInfo();				
				break;
			case 2:
				getInfo.reserveSeat();
				break;
			case 3:
				getInfo.showReservations();
				break;
			case 4:
				getInfo.busDetails();
				break;
			default:
				System.out.println("\nInsert Value from 1 to 4");
		}
	}


	
//	MAIN METHOD
	public static void main(String[] args) {
		
		Scanner inp = new Scanner(System.in);	
		
		boolean tryAgain = false;		
		do {			
			System.out.println("\n\n\t\t***HANIF BUS RESERVATION SYSTEM***\n\n");
			System.out.println("1. Add Bus Info");
			System.out.println("2. Reserve Seat");
			System.out.println("3. Show Reservations");
			System.out.println("4. Buses Available");
			System.out.print("Enter your choice : ");
			try {				
				
				int uInp = inp.nextInt();
				System.out.println("");
				
				Main ob = new Main();
				ob.frontPage(uInp);
				
			}catch(Exception e){
				System.out.println("\nERROR : You have to enter digit from 1 - 4\n" + e);
				
				//delay loop
				for(double i=1; i<100000; i = i+0.0001);
				
				tryAgain = true;
			}			
			

			System.out.println("");
			
			//try again or not
			System.out.print("\nWant to try again? y/n : ");
			char yOn = inp.next().charAt(0);
			
			if(yOn == 'y' || yOn == 'Y') {
				tryAgain = true;
			}else if(yOn == 'n' || yOn == 'N') {
				tryAgain = false;
			}	

			
		}while(tryAgain == true);
		
		
		
	}	
	
}
