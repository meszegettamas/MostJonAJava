package homework;

public class TestEngine{

	private int sizeofgameboard;
	private int numberofmines;
	private int time;
	private boolean settings;
	private boolean okbutton;
	private boolean newgame;
	private boolean leaderboard;
	private GUI gui;
	
	TestEngine()
	{
		
	}
	
	public void setLeaderboard(boolean leaderboard) {
		this.leaderboard = leaderboard;
	}

	public void setNewgame(boolean newgame) {
		this.newgame = newgame;
	}

	public void setOkbutton(boolean okbutton) {
		this.okbutton = okbutton;
	}

	public void setSettings(boolean settings) {
		this.settings = settings;
	}
	
	public void setTime(int time) {
		this.time = time;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public int getTime() {
		gui.paintGameBoard();
		return time;
	}

	public int getSizeofgameboard() {
		return sizeofgameboard;
	}

	public int getNumberofmines() {
		return numberofmines;
	}
	
	public void readClickMeaning()
	{
		if(settings == true)
		{
			gui.setActualboard(2);
			gui.paintBoards();
			settings = false;
		}
		
		if(okbutton == true)
		{
			gui.setActualboard(1);
			gui.paintBoards();
			okbutton = false;
		}
		
		if(newgame == true)
		{
			gui.setActualboard(1);
			gui.paintBoards();
			newgame = false;
		}
		
		if(leaderboard == true)
		{
			gui.setActualboard(4);
			gui.paintBoards();
			leaderboard = false;
		}
	}
}
