import java.util.Date;


public class Record {
	
	private String playerName;
	private Date date;
	private int timeToWin;
	private int difficulty;
	
	Record (String newPlayerName, Date newDate, int newTimeToWin, int newDifficulty) {			
		playerName = newPlayerName;
		date = newDate;
		timeToWin = newTimeToWin;
		difficulty = newDifficulty;		
		}
	
	
	Record () {
		timeToWin = 0;
		playerName = "";
	}
	
	
	public String getPlayerName() {
		return playerName;
		};
		
	public void setPlayerName(String newPlayerName) {
		playerName = newPlayerName;
		};
		
		
	public Date getDate() {
		return date;
		};
		
	public void Date(Date newDate) {
		date = newDate;
		};
			
		
	public int getTimeToWin() {
		return timeToWin;
		};
		
	public void setTimeToWIn(int newTimeToWin) {
		timeToWin = newTimeToWin;
		};
		
				
	public int getDifficulty() {
		return difficulty;
		};
		
	public void setDifficulty(int newDifficulty) {
		difficulty = newDifficulty;
		};

}
