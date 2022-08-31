package project;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class BusInfo {

	ArrayList<Integer> BusId = new ArrayList<Integer>();					
	ArrayList<String> DriverName  = new ArrayList<String>();
//	ArrayList<Integer> SeatCount = new ArrayList<Integer>() ;
	ArrayList<String> Departure = new ArrayList<String>();
	ArrayList<String> From = new ArrayList<String>();
	ArrayList<String> To = new ArrayList<String>();	

	
	 BusInfo() {
		 try {					
				File busInfoFile = new File("Data/busInfo.txt");				
				//if file exist
				if(busInfoFile.exists()) {
					
					Scanner scan = new Scanner(busInfoFile);					
					
					while(scan.hasNext()) {
						//return each row of BUS INFORMATION
						String busInfoRow = scan.nextLine();
						
						
						//spliting each row of data by # symbol
						String[] busData = busInfoRow.split("#");	
						
						 this.BusId.add(Integer.parseInt(busData[0]));					
						 this.DriverName.add(busData[1]);
//						 this.SeatCount.add(Integer.parseInt(busData[2]));
						 this.Departure.add(busData[3]);
						 this.From.add(busData[4]) ;
						 this.To.add(busData[5]);						 
					}
					
					scan.close();
					
				}else {
					System.out.println("Missing Bus Info File");
				}
				
			}catch(Exception e) {
				System.out.println("Error on BusAvailable function  : " + e);
			}	
	 }
	 
	 
	 
	 void shortBusList() {
		 BusInfo bi = new BusInfo();
		 SeatInfo si =  new SeatInfo();
		 
			System.out.println("\t\t**Bus List**\n");
			System.out.println("____________________________________________________________\n");		
			for(int i=0; i<bi.BusId.size(); i++) {
				System.out.println("Bus Number: "+bi.BusId.get(i));
				System.out.println("From : \""+bi.From.get(i)+"\" \tTo : \""+bi.To.get(i)+"\"");
				System.out.println(si.checkSeat(bi.BusId.get(i))+" Seats available for this bus");
				System.out.println("____________________________________________________________\n");		
			}	
	 }
	 
	
	
}
