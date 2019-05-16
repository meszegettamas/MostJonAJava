package homework;

import java.net.InetAddress;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;

public class MainClient {

	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		
		
		System.out.println("enter name"); 
		String name = scn.nextLine();
		
		
		System.out.println("enter timeToWin"); 
		int timeToWin = scn.nextInt();
		
		Date date = new Date();
		
		int difficulty = 1;
		
		Record record = new Record(name, date, timeToWin, difficulty);
		
		
		
		System.out.println("enter port, enter 0 for default"); 
		int port = scn.nextInt();
		if (port == 0) {
			port = 5015;
		}
		
		// need this line because a string follows an int in scan
		String toDiscard = scn.nextLine();
		
		System.out.println("enter ip, enter 0 for localhost"); 
		String ipAd = scn.nextLine();

//		System.out.println("enter ip, enter 0 for localhost"); 
//		String ipAd = scn.nextLine();
		
		if (ipAd.equals("152.66.152.97") ) {
			try {
			InetAddress ip = InetAddress.getByName("localhost");
			Client client = new Client(ip, port);
			TableOfRecords table = client.getUpdatedResults(record);
			table.printBestRecords();
			
			
			System.out.print("SecondClient");
			Client client2 = new Client(ip,port);
			TableOfRecords table2 = client2.getResults();
			table2.printBestRecords();
			
			
			}catch(Exception e) {
				System.out.print("unknown host");
			}}
			else  {
				try {
					InetAddress ip = InetAddress.getByName(ipAd);
					
					// ez a harom sor kell
					Client client = new Client(ipAd);
					TableOfRecords table = client.getUpdatedResults(record);
					table.printBestRecords();
					
					
					}catch(Exception e) {
						System.out.print("unknown host");
					}
			}
		//}
		
		
	}

}
