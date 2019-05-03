
import java.net.*;
import java.io.*;


public class Client {
	
	
	private String serverAdress;
	private int portNumber;
	private Socket clientSocket;
	
	private InputStream inputStream;
	private ObjectInputStream objInputStream;
	
	private OutputStream outputStream;
	private ObjectOutputStream objOutputStream;
	
	private TableOfRecords table;
	
	
	
	public String getServerName() {
			return serverAdress;
			};
			
	public void setServerAdress(String Adress) {
		this.serverAdress = Adress;
		};
		
	public int getPortNumber() {
		return this.portNumber;
		};
			
	public void setPortNumber(int pNumber) {
		this.portNumber = pNumber;
		};
	
	public void setTableOfRecords (Record[] bestRecords, Record[] latestRecords) {
		this.table = new TableOfRecords(bestRecords, latestRecords);
	}
	
	public 	void sendTableToServer() {
		
		try {
		this.objOutputStream.writeObject(this.table);
		this.objOutputStream.writeObject(new String("another object from the client"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
		
	
	Client (String Adress, int port) {
		
		this.serverAdress = Adress;
		this.portNumber = port;
		
		try {
		clientSocket = new Socket(this.serverAdress, this.portNumber);
		
//		this.inputStream = clientSocket.getInputStream();
//		this.objInputStream = new ObjectInputStream(inputStream);
//		
//		this.outputStream = clientSocket.getOutputStream();
//		this.objOutputStream = new ObjectOutputStream(outputStream);
		}
		catch (IOException e) {
	         e.printStackTrace();
	    }	
	}
	
	
	public void close() {
		try {
		objOutputStream.close();
		outputStream.close();
		
		objInputStream.close();
		inputStream.close();
		
		clientSocket.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	 

//public static void main(String[] args) {
//		
//		String serverName = args[0];
//		
//	    int port = Integer.parseInt(args[1]);
//	      
//      try {
//         System.out.println("Connecting to " + serverName + " on port " + port);
//         Socket client = new Socket(serverName, port);
//         
//         System.out.println("Just connected to " + client.getRemoteSocketAddress());
//         OutputStream outToServer = client.getOutputStream();
//         DataOutputStream out = new DataOutputStream(outToServer);
//         
//         out.writeUTF("Hello from " + client.getLocalSocketAddress());
//         InputStream inFromServer = client.getInputStream();
//         DataInputStream in = new DataInputStream(inFromServer);
//         
//         System.out.println("Server says " + in.readUTF());
//         client.close();
//	      } catch (IOException e) {
//	         e.printStackTrace();
//	      }	
//		
//	}
	
}
