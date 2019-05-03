import java.util.Date;

public class TableOfRecords implements java.io.Serializable {
	
	//data fields
	private int numberOfBestRecords;
	private int numberOfLatestRecords;
	
	private Record[] tableOfBestRecords;
	private Record[] tableOfLatestRecords;
	
	//constructors
	TableOfRecords (int numberOfBestRecords, int numberOfLatestRecords) {
		this.numberOfBestRecords = numberOfBestRecords;
		this.numberOfLatestRecords = numberOfLatestRecords;
		
		tableOfBestRecords = new Record[this.numberOfBestRecords];
		tableOfLatestRecords = new Record[this.numberOfLatestRecords];
	}
	
	TableOfRecords (Record[] tableOfBestRecords, Record[] tableOfLatestRecords) {
		
		this.tableOfBestRecords = new Record[tableOfBestRecords.length];
		this.tableOfLatestRecords = new Record[tableOfLatestRecords.length];
		
		for (int i = 0; i < tableOfBestRecords.length; i++) {
			this.tableOfBestRecords[i] = new Record(tableOfBestRecords[i]);
		}
		
		for (int i = 0; i < tableOfLatestRecords.length; i++) {
			this.tableOfLatestRecords[i] = new Record(tableOfLatestRecords[i]);
		}
		
		numberOfBestRecords = this.tableOfBestRecords.length;
		numberOfLatestRecords = this.tableOfLatestRecords.length;
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
	
	
	
	
}
