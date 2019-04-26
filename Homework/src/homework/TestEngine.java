package homework;

public class TestEngine{

	private int sizeofgameboard;
	private int numberofmines;
	private int time;
	GUI gui;
	
	TestEngine()
	{
		
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
	
}
