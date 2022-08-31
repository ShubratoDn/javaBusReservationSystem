package project;

import java.io.File;
import java.util.Scanner;

public class test {

//	int busId = Integer.parseInt(busData[0]);					
//	String DriverName = busData[1];
//	int SeatCount = Integer.parseInt(busData[2]);
//	String Departure = busData[3];
//	String From = busData[4] ;
//	String To = busData[5];	
	
	int[] BusId ;					
	String[] DriverName;
	int[] SeatCount ;
	String[] Departure;
	String[] From ;
	String[] To;	
	
	 test() {
		 try {					
				File busInfoFile = new File("Data/busInfo.txt");				
				//if file exist
				if(busInfoFile.exists()) {
					
					Scanner scan = new Scanner(busInfoFile);
					
					int count = 0;
					
					while(scan.hasNext()) {
						//return each row of BUS INFORMATION
						String busInfoRow = scan.nextLine();					
						
						//spliting each row of data by # symbol
						String[] busData = busInfoRow.split("#");	
						
						 this.BusId[count] = Integer.parseInt(busData[0]);					
						 this.DriverName[count] = busData[1];
						 this.SeatCount[count] = Integer.parseInt(busData[2]);
						 this.Departure[count] = busData[3];
						 this.From [count] = busData[4] ;
						 this.To [count] = busData[5];	
						 
						 count++;
						 System.out.println("Error");
					}
					
					scan.close();
					
				}else {
					System.out.println("Missing Bus Info File");
				}
				
			}catch(Exception e) {
				System.out.println("Error on BusAvailable function  : " + e);
			}	
	 }
	
	
}
