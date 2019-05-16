package homework;

import java.util.Date;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;    
import java.util.Calendar; 

public class TableOfRecords implements java.io.Serializable {
	
	//data fields
	private int numberOfBestRecords;
	private int numberOfLatestRecords;
	
	private Record[] tableOfBestRecords;
	private Record[] tableOfLatestRecords;
	
	//constructors
//	TableOfRecords (int numberOfBestRecords, int numberOfLatestRecords) {
//		this.numberOfBestRecords = numberOfBestRecords;
//		this.numberOfLatestRecords = numberOfLatestRecords;
//		
//		tableOfBestRecords = new Record[this.numberOfBestRecords];
//		tableOfLatestRecords = new Record[this.numberOfLatestRecords];
//	}
	TableOfRecords(){
		Date date = new Date();
		String name1 = "NocConnection!";
		String name2 = "MintaBela";
		int diff = 1;
		int time = 100;
		Record record1 = new Record(name1,date,time,diff);
		Record record2 = new Record(name2,date,time,diff);
		Record[] records = new Record[8];
		records[0]=record1
				;
		for(int i=1;i<records.length;i++)
		{
		records[i]=record2;
		}
		
		this.tableOfBestRecords = new Record[10];
		this.tableOfLatestRecords = new Record[10];
		
		for (int i = 0; i < 10; i++) {
			if (i < records.length) {
			this.tableOfBestRecords[i] = new Record(records[i]);
			}
			else {
				this.tableOfBestRecords[i] = new Record();
			}
		}
		
		for (int i = 0; i < 10; i++) {
			if (i < records.length) {
				this.tableOfLatestRecords[i] = new Record(records[i]);
				}
				else {
					this.tableOfLatestRecords[i] = new Record();
				}
			
		}
		
		
		numberOfBestRecords = 10;
		numberOfLatestRecords = 10;
		
		
	}
	
	TableOfRecords (Record[] tableOfBestRecords, Record[] tableOfLatestRecords) {  //define with 10 elements always
		
		
		this.tableOfBestRecords = new Record[10];
		this.tableOfLatestRecords = new Record[10];
		
		for (int i = 0; i < 10; i++) {
			if (i < tableOfBestRecords.length) {
			this.tableOfBestRecords[i] = new Record(tableOfBestRecords[i]);
			}
			else {
				this.tableOfBestRecords[i] = new Record();
			}
		}
		
		for (int i = 0; i < 10; i++) {
			if (i < tableOfBestRecords.length) {
				this.tableOfLatestRecords[i] = new Record(tableOfLatestRecords[i]);
				}
				else {
					this.tableOfLatestRecords[i] = new Record();
				}
			
		}
		
		
		numberOfBestRecords = 10;
		numberOfLatestRecords = 10;
	}
	
	TableOfRecords (String[] tableOfBestRecords, String[] tableOfLatestRecords) {
		
		this.tableOfBestRecords = new Record[10];
		this.tableOfLatestRecords = new Record[10];
		
		for (int i = 0; i < 10; i++) {
			
			if (i < tableOfBestRecords.length) {
				this.tableOfBestRecords[i] = new Record(tableOfBestRecords[i]);
				}
			else {
					this.tableOfBestRecords[i] = new Record();
				}
			
		}
		
		for (int i = 0; i < 10; i++) {
			if (i < tableOfBestRecords.length) {
				this.tableOfLatestRecords[i] = new Record(tableOfLatestRecords[i]);
				}
			else {
					this.tableOfLatestRecords[i] = new Record();
				}
		}
		
		numberOfBestRecords = 10;
		numberOfLatestRecords = 10;
	}
	
	//insert record
	private void insertNewBestRecord(Record newRecord) {
		
		boolean inserted = false;
		
		
		if (newRecord.getTimeToWin() > 0) {
			
		
			for (int i = 0; i < numberOfBestRecords ; i++)
				{
				if (tableOfBestRecords[i].getTimeToWin() == 0 && inserted == false) {
					tableOfBestRecords[i] = newRecord;
					inserted = true;
				}
				else if (tableOfBestRecords[i].getTimeToWin() > newRecord.getTimeToWin()  && inserted == false) {
					
					// shift results below and then change the current record to the new record
					for (int j=numberOfBestRecords-1; j > i; j--) {
						tableOfBestRecords[j] = tableOfBestRecords[j-1];
					}
					
					tableOfBestRecords[i] = newRecord;
					
					inserted = true;
				}
				}
			
		}
		
	}

	private void insertNewLatestRecord(Record newRecord) {
		// compare dates, execute similar logic as at the best records
		//what id these two methods are private and one public method (insert newRecord) calls them?
		
		
		boolean inserted = false;
		
		
		if (newRecord.getTimeToWin() > 0) {
			
		
			for (int i = 0; i < numberOfLatestRecords ; i++) {
				
					if (tableOfLatestRecords[i].getTimeToWin() == 0 && inserted == false) {
						tableOfLatestRecords[i] = newRecord;
						inserted = true;
					}
					else if (tableOfLatestRecords[i].getDate().before(newRecord.getDate()) && inserted == false) {
						
						// shift results below and then change the current record to the new record
						for (int j=numberOfLatestRecords-1; j > i; j--) {
							tableOfLatestRecords[j] = tableOfLatestRecords[j-1];
						}
						
						tableOfLatestRecords[i] = newRecord;
						
						inserted = true;
					}
				}
			
		}
	}
	
	public void insertNewRecord(Record newRecord) {
		this.insertNewBestRecord(newRecord);
		this.insertNewLatestRecord(newRecord);
	}
	
	//get functions
	public Record[] getBestRecords() {
		return this.tableOfBestRecords;
	}
	
	public Record[] getLatestRecords() {
		return this.tableOfLatestRecords;
	}
	
	//print functions
	public void printBestRecords() {
		
		for (int i = 0; i < this.tableOfBestRecords.length; i++) {
			System.out.print(this.tableOfBestRecords[i].getPlayerName() + "\t" + this.tableOfBestRecords[i].getDifficulty() + "\t" + this.tableOfBestRecords[i].getTimeToWin() + "\t" + this.tableOfBestRecords[i].getDate() + "\n");
		}
		
		System.out.print("\n");		
		
	}
	
	public void printLatestRecords() {
		
		for (int i = 0; i < this.tableOfLatestRecords.length; i++) {
			System.out.print(this.tableOfLatestRecords[i].getPlayerName() + "\t" + this.tableOfLatestRecords[i].getDifficulty() + "\t" + this.tableOfLatestRecords[i].getTimeToWin() + "\t" + this.tableOfLatestRecords[i].getDate() + "\n");
		}
		
		System.out.print("\n");		
		
	}
	
	public void printTable() {
		
		this.printBestRecords();
		this.printLatestRecords();
		
	}
	
	public String[][] returnTableAsStringArray(){
		String[][] stringArray = new String[10][4];
		
		for (int record = 0; record<10; record++) {
			for(int column = 0; column<4; column++) {
				switch (column) {
				case 0:
					stringArray[record][column] = this.tableOfBestRecords[record].getPlayerName();
					break;
				case 1:
					stringArray[record][column] = Integer.toString(this.tableOfBestRecords[record].getTimeToWin());
					break;
				case 2:
					int difficulty = this.tableOfBestRecords[record].getDifficulty();
					switch (difficulty) {
						case 1:
							stringArray[record][column] = "easy";
							break;
						case 2:
							stringArray[record][column] = "medium";
							break;
						case 3:
							stringArray[record][column] = "hard";
							break;
						}
					break;
				case 3:
					 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		             String strDate = dateFormat.format(this.tableOfBestRecords[record].getDate());
					stringArray[record][column] = this.tableOfBestRecords[record].getDate().toString();
					break;
				default:
					stringArray[record][column] = "-";
				}
			}
		}
		
		return stringArray;
	}
	
	
}
