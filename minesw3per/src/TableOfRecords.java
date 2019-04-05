import java.util.Date;

public class TableOfRecords {
	
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
	
	TableOfRecords (Record[] bestRecords, Record[] latestRecords) {
		tableOfBestRecords = bestRecords;
		tableOfLatestRecords = latestRecords;
		
		numberOfBestRecords = tableOfBestRecords.length;
		numberOfLatestRecords = tableOfLatestRecords.length;
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
	
}
