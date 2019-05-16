package homework;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Engine engine = new Engine();
		GUI gui = new GUI();
		engine.setGui(gui);
		gui.setEngine(engine);
	}

}
