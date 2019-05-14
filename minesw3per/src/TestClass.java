import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestClass {

	public static void main(String[] args) {
		
		//data in
		String name1 = new String("Imre");
		String name2 = new String("Zoltan");
		
		Date date = new Date();
		
		//after reading from a file the year, month, day, hour, minute, second,
		//I set the elements of a calendar object and load them into a date object
		//this should be the method of the server object, which is going to read the data from the file 
		//into a records array
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 3);
		cal.set(Calendar.YEAR,2019);
		cal.set(Calendar.DAY_OF_MONTH,06);
		cal.set(Calendar.HOUR_OF_DAY,13);
		cal.set(Calendar.MINUTE,38);
		cal.set(Calendar.SECOND,14);

		Date date2 = cal.getTime();
		
		
		//Date date2 = new Date(1000000000);
		
		int difficulty = 2;
		
		int time1 = 10;
		int time2 = 20;
		int time3 = 40;
		int time4 = 15;
		
		
		Record rec1 = new Record(name1, date, time1, difficulty);
		Record rec2 = new Record(name1, date, time2, difficulty);
		Record rec3 = new Record(name2, date, time3, difficulty);
		
		Record newRec = new Record(name2, date2, time4, difficulty);
		
		Record[] records = new Record[3];
		Record[] records2 = new Record[3];
		
		records[0]=rec1;
		records[1]=rec2;
		records[2]=rec3;
		
		records2[0]=rec1;
		records2[1]=rec2;
		records2[2]=rec3;
		
		//end data in
		
		TableOfRecords table = new TableOfRecords(records,records2);
//		
//		Record [] trecords = table.getBestRecords();
//		Record [] srecords = table.getLatestRecords();
//		
//		table.printTable();
		
		

		
//		table.insertNewRecord(newRec);
//		trecords = table.getBestRecords();
//		srecords = table.getLatestRecords();
//		table.printTable();	

		
		
		
		
///		String serverAdress = "localhost";
//		int portNumber = 6010;
//		
			
//		Client client = new Client(serverAdress, portNumber);
		
///		client.setTableOfRecords(records,records2);
//		client.sendTableToServer();
		
		
//		client.close();
		FileHandleTest fhandle = new FileHandleTest();
//		fhandle.writeTableToFile();
		fhandle.readTableFromFile();
//		String directory = System.getProperty("user.home");  
//		System.out.print(directory);
//		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
		
		
	}

}
