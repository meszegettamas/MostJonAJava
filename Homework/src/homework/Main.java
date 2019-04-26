package homework;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestEngine engine = new TestEngine();
		GUI gui = new GUI();
		engine.setGui(gui);
		gui.setEngine(engine);
	}

}
