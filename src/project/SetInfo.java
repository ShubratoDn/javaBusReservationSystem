package project;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class SetInfo {

	
	SetInfo(){
		try {
			File data= new File("Data");
			if(data.exists()) {
//				System.out.println("Folder is Already Exist");
			}else {				
				data.mkdir();
				System.out.println("Folder Created Successfully");				
			}
			
		}catch(Exception e){
			System.out.println("Error on SetInfo Constructor : " + e);
		}
		
	}


	boolean setBusInfo (int BusNumber, String DriverName, String Departure, String From, String To ){		
		
		try {
			FileWriter busInfo = new FileWriter("Data/busInfo.txt", true);
			int SeatCount = 40;			
			busInfo.write((int) BusNumber+ "#" + DriverName+"#"+ SeatCount+"#"+ Departure+"#"+From+"#"+To+"\r\n");
			
			busInfo.close();
			
			return true;
			
		}catch(Exception e) {
			System.out.println("Error on Bus Info Uploading : " + e);
			return false;
		}
		
	}
//	Set bus info ends
	
	
	boolean setReserveInfo(int BusId,String Name, String Phone, int[] SeatIndex) {
		SeatInfo si = new SeatInfo();
		
//		System.out.println("=============================");
//		System.out.println("name is : " + Name );
//		System.out.println("Phone is : "+ Phone);
//		System.out.println("Seat number :  "+ SeatIndex.length);
		String fileSeats = "";
		String newSeatLine = BusId+"";
		
		for(int fileSeat: SeatIndex) {
			fileSeats = fileSeats+fileSeat+"~";
		}
		
		
		try {
			
			FileWriter userFile = new FileWriter("Data/userInfo.txt", true);
			FileWriter seatFW = new FileWriter("Data/seatInfo.txt",true);
			FileWriter tempFW = new FileWriter("Data/tempSeatInfo.txt", true);
			
			

			String wholeFile = "";
			
			si.getSeatInfo();			
			//replacing available seats with booked
			for(int x=0; x<si.allSeats.size(); x++) {
				String line = (String)si.allSeats.get(x);
				String[] seats = line.split("#");
				
				//matching file's bus id with BusId parameter
				String busid = seats[0];			
				if(Integer.parseInt(busid) == BusId) {
//					System.out.println("prev line : " + line);
					//generating seat text	
					for(int y=0; y<SeatIndex.length; y++ ) {
						seats[SeatIndex[y]] = SeatIndex[y]+". Booked";
					}					
					for(int z=1; z<seats.length; z++ ) {
						newSeatLine = newSeatLine+"#"+seats[z];
					}
					wholeFile = wholeFile+newSeatLine+"\n";					
				}else {				
					wholeFile = wholeFile+line+"\n";	
				}				
				
			}//we got new seat line			
		
			userFile.write(BusId+"#"+Name+"#"+Phone+"#"+fileSeats+"\r\n");
			
			tempFW.write(wholeFile);
			tempFW.flush();
			tempFW.close();
			seatFW.close();			
			
			userFile.close();

			
			//ager file delete kore new file baniye setate data store kora hoise. then age file ta delete kore new file ta te ager file er nam add kora hoise
			try {
				File seatFile = new File("Data/seatInfo.txt");
				File tempFile = new File("Data/tempSeatInfo.txt");
				
				if(!seatFile.delete()) {
					System.out.println("Failed to delete previous Seat Info File");
				}
				
				if(!tempFile.renameTo(seatFile)) {
					System.out.println("Failed to rename Seat Info File");
				}
				
			}catch(Exception e) {
				System.out.println("Error in rename : " +e );
			}		
			return true;
			
		}catch(Exception e) {
			System.out.println("Error on uploading reserve file. Error : " +e);
			return false;
		}		
		
	}
	
	

	
	
	
	
	
}
