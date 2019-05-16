package homework;

import java.util.Calendar;
import java.util.Date;


public class Record implements java.io.Serializable {
	
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
	
	Record (String record){
		String [] recordElements = record.split("\t");
		String newPlayerName = recordElements[0];
		String newDifficulty = recordElements[1];
		String newTimeToWin = recordElements[2];
		String newDate = recordElements[3];
		
		this.playerName = newPlayerName;
		this.timeToWin = Integer.parseInt(newTimeToWin);
		this.difficulty = Integer.parseInt(newDifficulty);
		
		String [] dateElements = newDate.split(" ");
		
		String dayOfWeek = dateElements[0];
		String Month = dateElements[1];
		String dayOfMonth = dateElements[2];
		String timeOfDay = dateElements[3];
		String timeZone = dateElements[4];
		String year = dateElements[5];
		
		String [] timeElements = timeOfDay.split(":");
		
		int hours = Integer.parseInt(timeElements[0]);
		int minutes = Integer.parseInt(timeElements[1]);
		int seconds = Integer.parseInt(timeElements[2]);
		
		int mon;
		switch (Month) {
			case "January":
				mon = 1;
				break;
			case "February":
				mon = 2;
				break;
			case "March":
				mon = 3;
				break;
			case "April":
				mon = 4;
				break;
			case "May":
				mon = 5;
				break;
			case "June":
				mon = 6;
				break;
			case "July":
				mon = 7;
				break;
			case "August":
				mon = 8;
				break;
			case "September":
				mon = 9;
				break;
			case "October":
				mon = 10;
				break;
			case "November":
				mon = 11;
				break;
			case "December":
				mon = 12;
				break;
			default:
				mon = 1;
		}
		
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mon);
		cal.set(Calendar.YEAR,Integer.parseInt(year));
		cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dayOfMonth));
		cal.set(Calendar.HOUR_OF_DAY,hours);
		cal.set(Calendar.MINUTE,minutes);
		cal.set(Calendar.SECOND,seconds);

		this.date = cal.getTime();
		
	}
	
	Record (String newPlayerName, String newDate, String newTimeToWin, String newDifficulty) {			
		this.playerName = newPlayerName;
		this.timeToWin = Integer.parseInt(newTimeToWin);
		this.difficulty = Integer.parseInt(newDifficulty);
		
		String [] dateElements = newDate.split(" ");
		
		String dayOfWeek = dateElements[0];
		String Month = dateElements[1];
		String dayOfMonth = dateElements[2];
		String timeOfDay = dateElements[3];
		String timeZone = dateElements[4];
		String year = dateElements[5];
		
		String [] timeElements = timeOfDay.split(":");
		
		int hours = Integer.parseInt(timeElements[0]);
		int minutes = Integer.parseInt(timeElements[1]);
		int seconds = Integer.parseInt(timeElements[2]);
		
		int mon;
		switch (Month) {
			case "January":
				mon = 1;
				break;
			case "February":
				mon = 2;
				break;
			case "March":
				mon = 3;
				break;
			case "April":
				mon = 4;
				break;
			case "May":
				mon = 5;
				break;
			case "June":
				mon = 6;
				break;
			case "July":
				mon = 7;
				break;
			case "August":
				mon = 8;
				break;
			case "September":
				mon = 9;
				break;
			case "October":
				mon = 10;
				break;
			case "November":
				mon = 11;
				break;
			case "December":
				mon = 12;
				break;
			default:
				mon = 1;
		}
		
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mon);
		cal.set(Calendar.YEAR,Integer.parseInt(year));
		cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dayOfMonth));
		cal.set(Calendar.HOUR_OF_DAY,hours);
		cal.set(Calendar.MINUTE,minutes);
		cal.set(Calendar.SECOND,seconds);

		this.date = cal.getTime();
		
		}
	
	
	Record () {
		timeToWin = 0;
		playerName = "";
		this.date = new Date(0);
	}
	
	Record(Record record){
		this.playerName = record.getPlayerName();
		this.date = record.getDate();
		this.timeToWin = record.getTimeToWin();
		this.difficulty = record.getDifficulty();
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
