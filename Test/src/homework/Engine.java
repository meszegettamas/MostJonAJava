package homework;

import java.util.ArrayList;

public class Engine {
	
	/*kezdetben 1->10x10, majd lehet 2->16x16, 3->22x22*/
	int level=1;
	int sizeOfBoardx;
	int sizeOfBoardy;
	int numberOfMines;
	int[][] board = new int[sizeOfBoardx][sizeOfBoardy];
	int[][] state = new int[sizeOfBoardx][sizeOfBoardy];
	final int MINE = 9;
	int clickx=0;
	int clicky=0;
	int clickxj=0;
	int clickyj=2;
	boolean reset = false;
	boolean lost = false;
	boolean won = false;
	int minesRemaining = numberOfMines;
	
	
	public Engine(){
		level();
		board = new int[sizeOfBoardx][sizeOfBoardy];
		state = new int[sizeOfBoardx][sizeOfBoardy];
		createMines();
		countNeighbours();
		reveal();
		lostGame();
		wonGame();
		reset();
		display();
				
	}
	
	/*neh�zs�gi szint alapj�n a t�bla adatai*/
	public void level(){
		if (level == 1){
			sizeOfBoardx=10;
			sizeOfBoardy=10;
			numberOfMines=10;
		}
		else if (level == 2){
			sizeOfBoardx=16;
			sizeOfBoardy=16;
			numberOfMines=40;
		}
		else {
			sizeOfBoardx=22;
			sizeOfBoardy=22;
			numberOfMines=100;
		}
	}

	/* akn�k l�trehoz�sa a t�mbben*/
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
	
	/*szomsz�dos akn�k sz�m�nak meghat�roz�sa*/
	public void countNeighbours(){
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if(board[i][j]!=MINE){
					int neighbours = 0;
					/*bal fels� cella akna*/
					if(i>0 && j>0 && board[i-1][j-1] == MINE) {
						neighbours++;
					}
					/*fels� cella akna*/
					if (j>0 && board[i][j-1] == MINE) {
						neighbours++;
					}
					/*jobb fels� cella akna*/
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
					/*bal als� cella akna*/
					if (i>0 && j<board[0].length-1 && board[i-1][j+1] == MINE) {
						neighbours++;
					}
					/*als� cella akna*/
					if (j<board[0].length-1 && board[i][j+1] == MINE) {
						neighbours++;
					}
					/*jobb als� cella akna*/
					if (i<board.length-1 && j<board[0].length-1 && board[i+1][j+1] == MINE) {
						neighbours++;
					}
					board[i][j]=neighbours;
				}
			}
		}
	}
	
	/*mi volt a kattint�s*/
	public void readClickMeaning(){
		
	}
	
	/*cella felfed�se, 0->m�g nincs felfedve, 1->fel van fedve, 2->z�szl�s*/
	public void reveal(){
		if (state[clickxj][clickyj] == 0) {
			state[clickxj][clickyj] = 2;
			minesRemaining--;
		}
		else if (state[clickxj][clickyj] == 2) {
			state[clickxj][clickyj] = 0;
			minesRemaining++;
		}
		if (board[clickx][clicky] == 0) {
			ArrayList<Integer> zeros = new ArrayList<Integer>();
			zeros.add(clickx*100 + clicky);
			revealZeros(zeros);
		}
		state[clickx][clicky] = 1;
	}
	
	/*szomsz�dos null�s cell�k felfed�se*/
	public void revealZeros(ArrayList<Integer> zeros) {
		if (zeros.size() == 0) {
			return;
		}
		else {
			for (int k = 0; k <board.length*board[0].length*8; k++) {
				
				int i = zeros.get(0) / 100;
				int j = zeros.get(0) % 100;
				
				/*bal fels�*/
				if (i>0 && j>0) {
					if (state[i-1][j-1]!=2) {
					state[i-1][j-1]=1;
					}
					if (board[i-1][j-1] == 0 && !zeros.contains((i-1)*100+(j-1))) {
						zeros.add((i-1)*100+(j-1));
					}
				}
				/*fels�*/
				if (j>0) {
					if (state[i][j-1]!=2) {
					state[i][j-1]=1;
					}
					if (board[i][j-1] == 0 && !zeros.contains((i)*100+(j-1))) {
						zeros.add((i)*100+(j-1));
					}
				}
				/*jobb fels�*/
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
				/*bal als�*/
				if (i>0 && j<state[0].length-1) {
					if (state[i-1][j+1]!=2) {
					state[i-1][j+1]=1;
					}
					if (board[i-1][j+1] == 0 && !zeros.contains((i-1)*100+(j+1))) {
						zeros.add((i-1)*100+(j+1));
					}
				}
				/*als�*/
				if (j<state[0].length-1) {
					if (state[i][j+1]!=2) {
					state[i][j+1]=1;
					}
					if (board[i][j+1] == 0 && !zeros.contains((i)*100+(j+1))) {
						zeros.add((i)*100+(j+1));
					}
				}
				/*jobb als�*/
				if (i<state.length-1 && j<state[0].length-1) {
					if (state[i+1][j+1]!=2) {
					state[i+1][j+1]=1;
					}
					if (board[i+1][j+1] == 0 && !zeros.contains((i+1)*100+(j+1))) {
						zeros.add((i+1)*100+(j+1));
					}
				}
				zeros.remove(0);
		}	
		}
	}
	
	/*akn�ra kattint�s->j�t�k v�ge, �sszes cella felfed�se*/
	public void lostGame(){
		if(board[clickx][clicky] == MINE){
			System.out.println("Loser \n");
			lost=true;
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					state[i][j]=1;
				}
			}
		}
	}
	
	/*ha minden cella felfedett vagy akna, akkor gy�zelem*/
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
			System.out.println("Winner \n");
		}
		}
	
	public void reset() {
		if (reset == true) {
			board = new int[sizeOfBoardx][sizeOfBoardy];
			state = new int[sizeOfBoardx][sizeOfBoardy];
			createMines();
			countNeighbours();
			minesRemaining = numberOfMines;
			lost = false;
			won = false;
			reset = false;
		}
		
	}
	
	/*board �s state megjelen�t�se*/
	public void display(){
	    for(int i=0;i<board.length;i++)
	    {
	        for(int j=0;j<board[0].length;j++) {
	             System.out.print(board[i][j]+" "); 
	        }
	        System.out.println("");
	    }
	    System.out.println("\n");
	    for(int i=0;i<state.length;i++)
	    {
	        for(int j=0;j<state[0].length;j++) {
	             System.out.print(state[i][j]+" "); 
	        }
	        System.out.println("");
	    }
	    System.out.println("\n");
	}
	
	
	public static void main(String[] args) {
		new Engine();
	}  
	
}
