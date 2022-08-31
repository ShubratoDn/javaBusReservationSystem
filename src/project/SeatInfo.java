package project;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SeatInfo {

	ArrayList allSeats = new ArrayList<String>();
	
	SeatInfo(){
		try {
			
			File seatFile = new File("Data/seatInfo.txt");
			//checking if the file is exist or not
			if(!seatFile.exists()) {				
				if(seatFile.createNewFile()) {
					System.out.println("Seat Information File created");
				}				
			}
			
		}catch(Exception e) {
			System.out.println("SeatInfo file can't create. Beacuse : " + e);
		}
	}
	
	boolean setSeatSize(int BusNum) {
		int SeatCount = 40;
		String[] arrSeats = new String[SeatCount+1]; 
		String seats = BusNum+"#";
		
		//generating seat text
		for(int x=1; x < arrSeats.length; x++) {
				arrSeats[x] = x+ ". Available";									
		}
		
		for(int x=1; x < arrSeats.length; x++) {			
			if(x==arrSeats.length-1) {
				seats = seats+arrSeats[x];
			}else {
				seats = seats+arrSeats[x]+"#";
			}	
		}
		
//		System.out.println(seats);
//		ArrayList myArr = new ArrayList<>();
//		
//		String[] asa = seats.split("#");
//		
//		for(String x : asa) {
//			myArr.add(x);
//		}
//		
//		System.out.println(myArr);
		
		try {
			FileWriter sifw = new FileWriter("Data/seatInfo.txt",true);				
			sifw.write(seats+"\n");			
			sifw.close();
			return true;
			
		}catch(Exception e) {
			System.out.println("Failed to write in seatInfo. Error : " + e);
			return false;
		}
		
	}//set seat size method ends
	
	
	void getSeatInfo() {
		try{
			File gsi = new File("Data/seatInfo.txt");			
			Scanner scan = new Scanner(gsi);
			
			while(scan.hasNext()) {
				String line = scan.nextLine();
				allSeats.add(line);
			}			
			scan.close();
			
		}catch(Exception e) {
			System.out.println("Failed to get seat info method");
		}
	}
	
	
	void seatAvailibity(int BusNum) {
//		ArrayList seats = new ArrayList<String>();
		String busid;
		boolean err = true;
		
		SeatInfo si = new SeatInfo();		
		si.getSeatInfo();		
//		System.out.println("Total bus is : " + si.allSeats.size());
		
		for(int x=0; x<si.allSeats.size(); x++) {
//			System.out.println("line " +x +" is : "+si.allSeats.get(x));
				String line = (String) si.allSeats.get(x);
				String[] seats = line.split("#");	
				
				busid = seats[0];		
				
				if(Integer.parseInt(busid) == BusNum) {
//					System.out.println("Loop Is called"+ busid);					
					int sc = 1; //seat count
					for(int row = 1; row<=12;  row++ ) {
						for(int col = 1; col<5; col++) {						
							if(sc != 41) {
								System.out.print(seats[sc]+"\t");
								sc++;
							}else {
								break;
							}
						}
						System.out.println("");
					}					
					err = false;
					break;
				}
		}//for loop ends		
		
//		System.out.println("Seat Available = " +  availableSeat);
		
		if(err) {
			System.out.println("---*** No Bus Found ***---");
		}		
		
	}//Seat availibility method ends
	
	
	
	
	int checkSeat(int BusNum) {
		String busid;
		boolean err = true;
		int availableSeat = 0;
		
		SeatInfo si = new SeatInfo();		
		si.getSeatInfo();
		
		for(int x=0; x<si.allSeats.size(); x++) {
				String line = (String) si.allSeats.get(x);
				String[] seats = line.split("#");
				
				busid = seats[0];		
				
				if(Integer.parseInt(busid) == BusNum) {
					
					for(int i=1; i<seats.length; i++) {						
						if(seats[i].contains("Available")) {
							availableSeat++;
						}	
					}					
					err = false;
					break;
				}
		}//for loop ends
		
		if(err) {
			System.out.println("---*** No Bus Found ***---");
		}
		return availableSeat;		
	}
	
	
	
	boolean avaiableCheck(int BusId, int SeatNum) {
		boolean available = false;
		String busid;
		boolean err = true;
		
		getSeatInfo();		
		
		for(int x=0; x<allSeats.size(); x++) {
			String line = (String)allSeats.get(x);
			String[] seats = line.split("#");	
			
			//matching file's bus id with BusId parameter
			busid = seats[0];			
			if(Integer.parseInt(busid) == BusId) {
				
				for(int i=1; i<seats.length; i++) {						
					if(seats[SeatNum].contains("Available")) {
						available = true;
					}	
				}				
				err = false;
				break;
			}
		}//for loop ends
	
		if(err) {
			System.out.println("---*** No Bus Found ***---");
		}
		
		return available;
	}
	
	
	
}
