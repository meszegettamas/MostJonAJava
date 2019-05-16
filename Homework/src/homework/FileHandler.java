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
		    	data = data + Character.toString(ch);
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
