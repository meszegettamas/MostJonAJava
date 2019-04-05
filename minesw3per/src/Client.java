
import java.net.*;
import java.io.*;


public class Client {
	
	private String serverName;
	private int portNumber;
	private Socket clientSocket;
	
	public String getServerName() {
			return serverName;
			};
			
	public void setServerName(String sName) {
		serverName = sName;
		};
		
	public int getPortNumber() {
		return portNumber;
		};
			
	public void setPortNumber(int pNumber) {
		portNumber = pNumber;
		};
	
	
	Client (String server, int port) {
		
		serverName = server;
		portNumber = port;
		
		try {
		clientSocket = new Socket(serverName, portNumber);
		}
		catch (IOException e) {
	         e.printStackTrace();
	    }	
	}
	
	 

public static void main(String[] args) {
		
		String serverName = args[0];
		
	    int port = Integer.parseInt(args[1]);
	      
      try {
         System.out.println("Connecting to " + serverName + " on port " + port);
         Socket client = new Socket(serverName, port);
         
         System.out.println("Just connected to " + client.getRemoteSocketAddress());
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         
         out.writeUTF("Hello from " + client.getLocalSocketAddress());
         InputStream inFromServer = client.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);
         
         System.out.println("Server says " + in.readUTF());
         client.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }	
		
	}
	
}
