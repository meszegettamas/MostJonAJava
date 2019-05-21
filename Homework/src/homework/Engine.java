package homework;

import java.util.ArrayList;
import java.util.*;

public class Engine {
	
	/*kezdetben 1->10x10, majd lehet 2->16x16, 3->22x22*/
	int level=1;
	int sizeOfBoardx=10;
	int sizeOfBoardy=10;
	int numberOfMines=10;
	int[][] board = new int[sizeOfBoardx][sizeOfBoardy];
	int[][] state = new int[sizeOfBoardx][sizeOfBoardy];
	final int MINE = 9;
	int clickx;
	int clicky;
	int mousebutton; /* 0 bal klikk, 1 jobb*/
	boolean reset = false;
	boolean lost = false;
	boolean won = false;
	boolean settings;
	boolean okbutton;
	boolean newgame;
	boolean leaderboard;
	boolean fieldbutton;
	boolean cancelbutton;
	int minesRemaining = numberOfMines;
	private GUI gui;
	private Record newRecord;
	int timeToWin;
	TableOfRecords results;
	Timer timer = new Timer();
	TimerTask timerTask = new TimerTask() {
	    @Override
	    public void run() {
	    	if(lost == false && won == false)
	    	{
		    	timeToWin++;
		    	gui.gameTime.setText(Integer.toString(timeToWin));
	    	}
	    }
	};	

	public Engine(){
		board = new int[sizeOfBoardx][sizeOfBoardy];
		state = new int[sizeOfBoardx][sizeOfBoardy];
		createMines();
		countNeighbours();
		reset();
		timer.schedule(timerTask, 1000, 1000);	
	}
	
	public void setGui(GUI gui) {
		this.gui = gui;
	}
	
	public void setFieldbutton(boolean fieldbutton) {
		this.fieldbutton = fieldbutton;
	}

	public void setLeaderboard(boolean leaderboard) {
		this.leaderboard = leaderboard;
	}

	public void setOkbutton(boolean okbutton) {
		this.okbutton = okbutton;
	}

	public void setNewgame(boolean newgame) {
		this.newgame = newgame;
	}
	
	public void setCancelbutton(boolean cancelbutton) {
		this.cancelbutton = cancelbutton;
	}

	public int[][] getBoard() {
		return board;
	}
	
	public int[][] getState() {
		return state;
	}
	
	public void setSettings(boolean settings) {
		this.settings = settings;
	}
	
	public boolean isLost() {
		return lost;
	}

	public boolean isWon() {
		return won;
	}

	public int getMinesRemaining() {
		return minesRemaining;
	}

	/*nehézségi szint alapján a tábla adatai*/
	public void level(){
		if (level == 1){
			sizeOfBoardx=10;
			sizeOfBoardy=10;
			numberOfMines=10;
			gui.setField_size(sizeOfBoardx);
		}
		else if (level == 2){
			sizeOfBoardx=16;
			sizeOfBoardy=16;
			numberOfMines=40;
			gui.setField_size(sizeOfBoardx);
		}
		else {
			sizeOfBoardx=22;
			sizeOfBoardy=22;
			numberOfMines=100;
			gui.setField_size(sizeOfBoardx);
		}
	}

	/* aknák létrehozása a tömbben*/
	public void createMines(){
		ArrayList<Integer> listOfMines = new ArrayList<Integer>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				listOfMines.add(i*100+j);
			}
		} 
		board = new int [sizeOfBoardx][sizeOfBoardy];
		for (int i = 0; i < numberOfMines; i++) {
			int mine = (int)(Math.random()*listOfMines.size());
			board[listOfMines.get(mine) / 100][listOfMines.get(mine) % 100] = MINE;
			listOfMines.remove(mine);
		}
	}
	
	/*szomszédos aknák számának meghatározása*/
	public void countNeighbours(){
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if(board[i][j]!=MINE){
					int neighbours = 0;
					/*bal felsõ cella akna*/
					if(i>0 && j>0 && board[i-1][j-1] == MINE) {
						neighbours++;
					}
					/*felsõ cella akna*/
					if (j>0 && board[i][j-1] == MINE) {
						neighbours++;
					}
					/*jobb felsõ cella akna*/
					if (i<board.length-1 && j>0 && board[i+1][j-1] == MINE) {
						neighbours++;
					}
					/*bal oldali cella akna*/
					if (i>0 && board[i-1][j] == MINE) {
						neighbours++;
					}
					/*jobb oldali cella akna*/
					if (i<board.length-1 && board[i+1][j] == MINE) {
						neighbours++;
					}
					/*bal alsó cella akna*/
					if (i>0 && j<board[0].length-1 && board[i-1][j+1] == MINE) {
						neighbours++;
					}
					/*alsó cella akna*/
					if (j<board[0].length-1 && board[i][j+1] == MINE) {
						neighbours++;
					}
					/*jobb alsó cella akna*/
					if (i<board.length-1 && j<board[0].length-1 && board[i+1][j+1] == MINE) {
						neighbours++;
					}
					board[i][j]=neighbours;
				}
			}
		}
	}
	
	

	/*mi volt a kattintás*/
	public void readClickMeaning(){
		if(settings == true)
		{
			gui.setActualboard(2);
			gui.paintBoards();
			settings = false;
		}
		
		if(okbutton == true)
		{
			if(gui.getActualboard() == 2)
			{
				newgame = true;
				level = gui.getDifficulity();
				level();
				reset();
			}
			if(gui.getActualboard() == 3)
			{
				if(gui.isValid_data() == true)
				{
					win(gui.getUserName(), gui.getUserIP());
					// Adatok elküldése a szervernek.
					// gui.getUserIp()-val lehet elkérni az IP címet.
					newgame = true;
					level = gui.getDifficulity();
					level();
					reset();
				}
				else
				{
					newgame = true;
					level = gui.getDifficulity();
					level();
					reset();
				}
			}
			gui.setActualboard(1);
			gui.paintBoards();
			okbutton = false;
		}
		
		if(cancelbutton == true)
		{
			gui.setActualboard(1);
			gui.paintBoards();
			cancelbutton = false;
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
			getResults(gui.getUserIP());
			gui.setLeaderboard(results.returnTableAsStringArray());
			gui.setActualboard(4);
			gui.paintBoards();
			leaderboard = false;
		}
		
		if(fieldbutton == true)
		{

			mousebutton = gui.getMouseclick();
			clickx = gui.getCoordinate_x();
			clicky = gui.getCoordinate_y();
			if (mousebutton == 0 && state[clickx][clicky] == 0) {	
			reveal();
			}
			else if (mousebutton == 1 && state[clickx][clicky] == 0) {
				state[clickx][clicky] = 2;
				minesRemaining--;
			}
			else if (mousebutton == 1 && state[clickx][clicky] == 2) {
				state[clickx][clicky] = 0;
				minesRemaining++;
			}
			gui.updateButtonAppearance();
			wonGame();
			fieldbutton = false;
		}
		
	}
	
	/*cella felfedése, 0->még nincs felfedve, 1->fel van fedve, 2->zászlós*/
	public void reveal(){
		state[clickx][clicky] = 1;
		if (board[clickx][clicky] == 0) {
			ArrayList<Integer> zeros = new ArrayList<Integer>();
			zeros.add(clickx*100 + clicky);
			revealZeros(zeros);
		}
		lostGame();	
	}
	
	/*szomszédos nullás cellák felfedése*/
	public void revealZeros(ArrayList<Integer> zeros) {
		if (zeros.size() == 0) {
			return;
		}
		else {
			for (int k = 0; k <board.length*board[0].length*8; k++) {
				
				int i = zeros.get(0) / 100;
				int j = zeros.get(0) % 100;
				
				/*bal felsõ*/
				if (i>0 && j>0) {
					if (state[i-1][j-1]!=2) {
					state[i-1][j-1]=1;
					}
					if (board[i-1][j-1] == 0 && !zeros.contains((i-1)*100+(j-1))) {
						zeros.add((i-1)*100+(j-1));
					}
				}
				/*felsõ*/
				if (j>0) {
					if (state[i][j-1]!=2) {
					state[i][j-1]=1;
					}
					if (board[i][j-1] == 0 && !zeros.contains((i)*100+(j-1))) {
						zeros.add((i)*100+(j-1));
					}
				}
				/*jobb felsõ*/
				if (i<state.length-1 && j>0) {
					if (state[i+1][j-1]!=2) {
					state[i+1][j-1]=1;
					}
					if (board[i+1][j-1] == 0 && !zeros.contains((i+1)*100+(j-1))) {
						zeros.add((i+1)*100+(j-1));
					}
				}
				/*bal oldali*/
				if (i>0) {
					if (state[i-1][j]!=2) {
					state[i-1][j]=1;
					}
					if (board[i-1][j] == 0 && !zeros.contains((i-1)*100+(j))) {
						zeros.add((i-1)*100+(j));
					}
				}
				/*jobb oldali*/
				if (i<state.length-1) {
					if (state[i+1][j]!=2) {
					state[i+1][j]=1;
					}
					if (board[i+1][j] == 0 && !zeros.contains((i+1)*100+(j))) {
						zeros.add((i+1)*100+(j));
					}
				}
				/*bal alsó*/
				if (i>0 && j<state[0].length-1) {
					if (state[i-1][j+1]!=2) {
					state[i-1][j+1]=1;
					}
					if (board[i-1][j+1] == 0 && !zeros.contains((i-1)*100+(j+1))) {
						zeros.add((i-1)*100+(j+1));
					}
				}
				/*alsó*/
				if (j<state[0].length-1) {
					if (state[i][j+1]!=2) {
					state[i][j+1]=1;
					}
					if (board[i][j+1] == 0 && !zeros.contains((i)*100+(j+1))) {
						zeros.add((i)*100+(j+1));
					}
				}
				/*jobb alsó*/
				if (i<state.length-1 && j<state[0].length-1) {
					if (state[i+1][j+1]!=2) {
					state[i+1][j+1]=1;
					}
					if (board[i+1][j+1] == 0 && !zeros.contains((i+1)*100+(j+1))) {
						zeros.add((i+1)*100+(j+1));
					}
				}
				zeros.remove(0);
				if (zeros.isEmpty() == true) {
					break;
				}
		}	
		}
	}
	
	/*aknára kattintás->játék vége, összes cella felfedése*/
	public void lostGame(){
		if(board[clickx][clicky] == MINE){
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					state[i][j]=1;
				}
			}
			lost=true;
			reset=true;
		}
	}
	
	/*ha minden cella felfedett vagy akna, akkor gyõzelem*/
	public void wonGame() {
		int win = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if ((state[i][j] == 1 || board[i][j] == MINE) && lost == false) {	
					win++;
				}
				}
			}
		if (win == board.length*board[0].length){	
			won=true;
		}
		}
	
	//ezeket hivjuk, mikor a user meg akarja nezni az eredmenyeket
	public void getResults (String ip) {
		Client client = new Client(ip);
		this.results = client.getResults();	
	}
	
	public void getUpdatedResults (String ip) {
		Client client = new Client(ip);
		this.results  = client.getUpdatedResults(this.newRecord);
//		String Adamnak [][] = this.results.returnTableAsStringArray();		
	}
	
	//fgv, ha a user nyert es megadja az adatait
	public void win (String name, String IP) {
		Date date = new Date();
		this.newRecord = new Record(name, date, this.timeToWin, this.level);
		Client client = new Client(IP);
		this.results  = client.getUpdatedResults(this.newRecord);
		
	}
	
	public void reset() {
		if (reset == true || newgame == true) {
			board = new int[sizeOfBoardx][sizeOfBoardy];
			state = new int[sizeOfBoardx][sizeOfBoardy];
			createMines();
			countNeighbours();
			minesRemaining = numberOfMines;
			lost = false;
			won = false;
			reset = false;
			newgame = false;
			timeToWin = 0;
			gui.gameTime.setText(Integer.toString(timeToWin));
			timer.cancel();
			timer = new Timer();
			timerTask = new TimerTask() {
			    @Override
			    public void run() {
			    	if(lost == false && won == false)
			    	{
				    	timeToWin++;
				    	gui.gameTime.setText(Integer.toString(timeToWin));
			    	}
			    }
			};
			timer.schedule(timerTask, 1000, 1000);
		}
		
	}
	
}
