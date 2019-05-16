package homework;



public class MainServer {

	public static void main(String[] args) {

		int portNumber = 5015;
		String filename = "Results.txt";
		
		try {
		Server server = new Server(portNumber, filename);
		server.serverStart();
		server.close();
		} catch(Exception e) {
			e.getMessage();
		}

	}

}
