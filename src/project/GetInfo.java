package project;
import java.util.*;

public class GetInfo {
	Scanner inpStr = new Scanner(System.in);
	Scanner inpInt = new Scanner(System.in);
	BusInfo bi = new BusInfo();
	SeatInfo si = new SeatInfo();
	
	SetInfo set = new SetInfo();
	
	void getBusInfo() {		
		boolean err = false;		
		System.out.println("\n\n\t***INSERT BUS INFORMATION***");
		
		System.out.print("Bus number : ");
		int busNum = inpInt.nextInt();	
		
		System.out.print("Driver name : ");
		String drivName = inpStr.nextLine();
		
//		System.out.print("Bus seat size : ");
//		int seatCount;
//		try {
//			seatCount = inpInt.nextInt();
//			
//			if(seatCount < 20 || seatCount > 48) {
//				System.out.println("**Seat size must be within 20 to 48**\n");
//				System.out.print("Bus seat size : ");
//				seatCount = inpInt.nextInt();
//			}
//			
//		}catch(Exception e) {
//			System.out.println("Please enter digit\n");
//			System.out.print("Bus seat size : ");
//			seatCount = inpInt.nextInt();
//			
//			if(seatCount < 20 || seatCount > 44) {
//				err = true;
//			}
//		}
		
		String departure = "";
		System.out.print("Departure Time ( 1 - 12) : ");
		double time = inpInt.nextInt();
		System.out.print("Am or Pm?\n1. Am \t\t2. Pm : \t");
		int timeState = inpInt.nextInt();
		
		switch(timeState){
			case 1:
				departure = time+"  Am";
				break;
			case 2:
				departure = time + " Pm";
				break;				
			default:
				System.out.println("Error input");
				err = true;
		}
		
		System.out.print("From : ");
		String from = inpStr.nextLine();
		
		System.out.print("To : ");
		String to = inpStr.nextLine();
		
		
		if(!err) {
//			System.out.println("Bus num  is : "+busNum);
//			System.out.println("Driver Name is : "+drivName);
//			System.out.println("Seat size is : " + seatCount);
//			System.out.println("Departure time : "+departure);
//			System.out.println("From  : " + from );
//			System.out.println("To : " + to);
			
			boolean setSuccess = set.setBusInfo(busNum, drivName, departure, from, to);
			//return a signal showing the confirmtion of Add Success
			if(setSuccess) {
				System.out.println("\n** Bus information Added successfully **\n");
				
				//Set Seat Information
				SeatInfo seatInfo = new SeatInfo();
//				if(seatInfo.setSeatSize(busNum,seatCount)) {
				if(!seatInfo.setSeatSize(busNum)) {
					System.out.println("Something wrong into add seat information");
				}			
				
			}else {
				System.out.println("\n** Failed to add bus info **\n");
			}
			
		}else {
			System.out.println("Some error happens");
		}				
		
	}
	
	
	
	
	
	
	
	
//	Getting Reserve Seat Information
	void reserveSeat() {
		//getting the short bus info
		bi.shortBusList();
		boolean err = false;
		int[] seatNumbers = null;
		String errSeatAvl = "";
		boolean wrongSeat = false;
		String passName;
		String seatReserve = "";
		
		System.out.print("Please, enter the bus number of your desire route : " );
		int busId = inpInt.nextInt();
		
		System.out.print("How many seats you wants to reserve? : ");
		int reserveCount = inpInt.nextInt();
		
		int seatAvailable = si.checkSeat(busId);		
		if(reserveCount > seatAvailable || reserveCount > 40) {
			err = true;
		}else {
			//setting the seat numbers array size
			seatNumbers = new int[reserveCount];
//			errSeatAvl = new int[reserveCount];
		}
		
		if (err) {
			System.out.println("\n*** ERROR : We don't have " + reserveCount +" seats available for this bus ***");
		}else {
			System.out.println("\n\n\t\t\tBus Seats Map");
			System.out.println("\t______________________________________________\n");
			
			si.seatAvailibity(busId);			
			
			//getting seat numbers if user enter wrong value
			do {
				wrongSeat = false;
				for(int n=0; n<seatNumbers.length; n++){
					System.out.print("Seat "+n+"th : ");
					seatNumbers[n] = inpInt.nextInt();
					
					//jodi seat available na hoy tahhole....
					if(!si.avaiableCheck(busId, seatNumbers[n])) {
						//if the seat is not available
						errSeatAvl = errSeatAvl + seatNumbers[n]+" \t";
						wrongSeat = true;
						err = true;			
					}else {
						seatReserve = seatReserve+"\t"+seatNumbers[n];
					}
				}//for loop ends
				

				if(wrongSeat == false) {
					err = false;	
					break;					
				}else {
					seatReserve = "";
					System.out.println("Seat Number\t " + errSeatAvl + " Not available  ");
					System.out.println("Try again.\n");
					errSeatAvl = "";					
				}				
			}while(wrongSeat != false );			
						
			
			
			if(err) {
				System.out.println("Failed");				
			}else {				
//				System.out.println("All is ok");
				System.out.print("Enter passenger name : ");
				passName = inpStr.nextLine();
				System.out.print("Enter phone number : ");
				String phone = inpStr.nextLine();
				
				System.out.println("Are you sure to reserve Seat number : " + seatReserve + " ?");
				System.out.print("( y / n) : ");
				String confirm = inpStr.nextLine();
				
				if(confirm.equals("y") || confirm.equals("Y")) {
					
					//if confirmed the reserving the seat
					if(set.setReserveInfo(busId,passName, phone, seatNumbers)) {
						SetInfo set = new SetInfo();
						boolean reserveFDB = set.setReserveInfo(busId, passName, phone, seatNumbers);
						
						//if setting reserve info return true...
						if(reserveFDB) {
							System.out.println("Your Selected seat has been reserved");
						}else {
							System.out.println("Operation fail in reserving seat");
						}
						
					}else{
						System.out.println("Something went wrong in reserving seat.");
					}
					
				}else {
					System.out.print("Failed to reserve seat");
				}				
				
			}		

			
		}//first else

	}
//	Reserve Seat method ends
	
	
	
	
	
	
	
	void busDetails (){		

		for(int i=0; i<bi.BusId.size(); i++) {
			//Showing Bus availability
			System.out.println("\n==================================================");
			System.out.println("Information about bus number : " + bi.BusId.get(i));
			System.out.println("Driver Name : " +bi.DriverName.get(i));
//			System.out.println("Total Seat : " + bi.SeatCount.get(i)+"\t\t Departure time : "+bi.Departure.get(i));
			System.out.println("Total Seat : 40" +"\t\t Departure time : "+bi.Departure.get(i));
			System.out.println("From : " + bi.From.get(i)+"\t\t To : "+bi.To.get(i));
			System.out.println("==================================================\n");
			
		}
		
	}
	
	
	//Show bus Reservations
	void showReservations() {
		//Getting the short bus list		
		bi.shortBusList();		
	
		
		System.out.print("\n\nEnter Bus Number : ");
		int busId = inpInt.nextInt();
		System.out.println("");
		
		//getting the array index of bus id
		int index = 0;
		for(int x=0; x<bi.BusId.size(); x++) {
			if(busId == bi.BusId.get(x)) {
				index = x;
				break;
			}
		}
		String from = bi.From.get(index);
		String to = bi.To.get(index);
		
		System.out.println("_______________________________________________________________\n");
		System.out.println("Bus id : " + busId);
		System.out.println("From : "+from+"\t\tTo : "+to);
		System.out.println("_______________________________________________________________\n");
		
		//here will show the seats empty or booked
		si.seatAvailibity(busId);		
		
	}
	
	
	
	
	
	
	
	
}//class er bracket
