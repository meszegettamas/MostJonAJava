package homework;

public class TestEngine{

	private int sizeofgameboard;
	private int numberofmines;
	private int time;
	private boolean settings;
	private boolean okbutton;
	private boolean newgame;
	private boolean leaderboard;
	private boolean fieldbutton;
	private int c_x;
	private int c_y;
	private GUI gui;
	/*
	 * State jelentése:
	 * 		0: nincs felfedve.
	 * 		1: fel van fedve.
	 * 		2: zászlós.
	 */
	private int[][] state = new int[22][22];
	
	/*
	 * Board jelentése:
	 * 		0-8: szomszédok száma.
	 * 		9: akna.
	 */
	private int[][] board = new int[22][22];
	
	TestEngine()
	{
		c_x = 0;
		c_y = 0;
		reset();
	}

	public void setFieldbutton(boolean fieldbutton) {
		this.fieldbutton = fieldbutton;
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
		return time;
	}

	public int getSizeofgameboard() {
		return sizeofgameboard;
	}

	public int getNumberofmines() {
		return numberofmines;
	}
	
	public int getState(int x, int y)
	{
		return state[x][y];
	}
	
	public int getBoard(int x, int y)
	{
		return board[x][y];
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
			reset();
			gui.paintBoards();
			newgame = false;
		}
		
		if(leaderboard == true)
		{
			gui.setActualboard(4);
			gui.paintBoards();
			leaderboard = false;
		}
		
		if(fieldbutton == true)
		{
			c_x = gui.getCoordinate_x();
			c_y = gui.getCoordinate_y();
			state[c_x][c_y] = 1;
			gui.updateButtonAppearance(c_x, c_y);
			fieldbutton = false;
		}
	}
	
	public void reset()
	{
		for(int y = 0;y < 22;y++)
		{
			for(int x = 0;x < 22;x++)
			{
				state[x][y] = 0;
				if(y < 9)
				{
					board[x][y] = y;
				}
				else
				{
					board[x][y] = 9;
				}
			}
		}
	}
}
