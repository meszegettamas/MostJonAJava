package homework;

import java.nio.*;
import java.util.Calendar;
import java.util.Date;
import java.io.*;


public class FileHandler {
	
	private TableOfRecords table;
	private String filename;
	private TableOfRecords readedTable;
	
	public FileHandler(String filename) {
		this.filename = filename;
		
		String directory = System.getProperty("user.dir");  
		String absolutePath = directory + File.separator + this.filename;
		File tmpDir = new File(absolutePath);
		boolean exists = tmpDir.exists();
		if (exists) {
			System.out.print("file exists \n");
		}
		else{
			System.out.print("File does not exist, created dummy\n");
			
			
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
			
			int time1 = 100;
			int time2 = 200;
			int time3 = 400;
			int time4 = 1500;
			
			
			Record rec1 = new Record(name1, date, time1, difficulty);
			Record rec2 = new Record(name1, date, time2, difficulty);
			Record rec3 = new Record(name2, date, time3, difficulty);
			
			Record newRec = new Record(name2, date2, time4, difficulty);
			
			Record[] records1 = new Record[3];
			Record[] records2 = new Record[3];
			
			records1[0]=rec1;
			records1[1]=rec2;
			records1[2]=rec3;
			
			records2[0]=rec1;
			records2[1]=rec2;
			records2[2]=rec3;
			
			//end data in
			
			TableOfRecords table = new TableOfRecords(records1,records2);
			
//			String directory = System.getProperty("user.dir");  
//			String absolutePath = directory + File.separator + this.filename;
			
			Record[] records = table.getBestRecords();
			int len = records.length;
			
			try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(absolutePath))) {  
			    
				for (int i=0; i<len; i++) {
					String fileContent = (records[i].getPlayerName() + "\t" +
					records[i].getDifficulty() + "\t" + records[i].getTimeToWin()+ "\t" + records[i].getDate() + "\n" ) ;
				    bufferedOutputStream.write(fileContent.getBytes());
				}
			} catch (IOException e) {
			    // exception handling
				System.out.print("file not found, creating one");
//				PrintWriter writer = new PrintWriter(this.filename);
//				writer.println("The first line");
//				writer.println("The second line");
//				writer.close();
			}
		}
	}
	
	public void writeTableToFile(TableOfRecords table) {
		String directory = System.getProperty("user.dir");  
		String absolutePath = directory + File.separator + this.filename;
		
		Record[] records = table.getBestRecords();
		int len = records.length;
		
		try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(absolutePath))) {  
		    
			for (int i=0; i<len; i++) {
				String fileContent = (records[i].getPlayerName() + "\t" +
				records[i].getDifficulty() + "\t" + records[i].getTimeToWin()+ "\t" + records[i].getDate() + "\n" ) ;
			    bufferedOutputStream.write(fileContent.getBytes());
			}
		} catch (IOException e) {
		    // exception handling
			System.out.print("file not found, creating one");
//			PrintWriter writer = new PrintWriter(this.filename);
//			writer.println("The first line");
//			writer.println("The second line");
//			writer.close();
		}
		
		
	}

	public String [] readTableFromFile() {
		String directory = System.getProperty("user.dir");  
		String absolutePath = directory + File.separator + this.filename;
		
		String data = new String();
		
		try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(absolutePath))) {  
		    int ch = bufferedInputStream.read();
		    while(ch != -1) {
		        //System.out.print((char)ch);
		    	data = data + Character.toString((char)ch);
		        ch = bufferedInputStream.read();
		    }
		} catch (FileNotFoundException e) {
		    // exception handling
		} catch (IOException e) {
		    // exception handling
		}
	//	System.out.print(data);
		
		String[] dataLines = data.split("\n");
		this.readedTable = new TableOfRecords(dataLines, dataLines);
	//	readedTable.printBestRecords();
		
		return dataLines; 
		
	}
}
