package homework;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread guiThread = new Thread(new GUI());
		guiThread.start();
	}

}
