
import java.awt.Point;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;



public class Client extends Network{
	
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	
	private Record newRecord;
	private TableOfRecords results;
	

	Client(Control c) {
		super(c);
	}

	private class ReceiverThread implements Runnable {

		public void run() {
			System.out.println("Waiting for results...");
			try {
				while (true) {
					results = (TableOfRecords) in.readObject();
					notify();											//!!!!
//					ctrl.clickReceived(received);
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				System.err.println("Server disconnected!");
			} finally {
				disconnect();
			}
		}
	}

	@Override
	void connect(String ip) {
		disconnect();
		try {
			socket = new Socket(ip, 10007);

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			out.flush();

			Thread rec = new Thread(new ReceiverThread());
			rec.start();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection. ");
			JOptionPane.showMessageDialog(null, "Cannot connect to server!");
		}
	}

	@Override
	void send(Point p) {
		if (out == null)
			return;
		System.out.println("Sending point: " + p + " to Server");
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException ex) {
			System.err.println("Send error.");
		}
	}

	@Override
	void disconnect() {
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (socket != null)
				socket.close();
		} catch (IOException ex) {
			System.err.println("Error while closing conn.");
		}
	}
	
	
	public void exchangeDataWithServer() {
		if (out == null)
			return;
		System.out.println("Sending record to Server");
		
		try {
			out.writeObject(this.newRecord);
			out.flush();
		} catch (IOException ex) {
			System.err.println("Send error.");
		}
		try {
		wait(10);  											//!!!!
		} catch (InterruptedException ie) {
			System.err.println("Send error.");
		}
		
	}
	

	
}












//public class Client {
//	
//	
//	private String serverAdress;
//	private int portNumber;
//	private Socket clientSocket;
//	
//	private InputStream inputStream;
//	private ObjectInputStream objInputStream;
//	
//	private OutputStream outputStream;
//	private ObjectOutputStream objOutputStream;
//	
//	private TableOfRecords table;
//	
//	
//	
//	public String getServerName() {
//			return serverAdress;
//			};
//			
//	public void setServerAdress(String Adress) {
//		this.serverAdress = Adress;
//		};
//		
//	public int getPortNumber() {
//		return this.portNumber;
//		};
//			
//	public void setPortNumber(int pNumber) {
//		this.portNumber = pNumber;
//		};
//	
//	public void setTableOfRecords (Record[] bestRecords, Record[] latestRecords) {
//		this.table = new TableOfRecords(bestRecords, latestRecords);
//	}
//	
//	public 	void sendTableToServer() {
//		
//		try {
//		this.objOutputStream.writeObject(this.table);
//		this.objOutputStream.writeObject(new String("another object from the client"));
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
//		
//	
//	Client (String Adress, int port) {
//		
//		this.serverAdress = Adress;
//		this.portNumber = port;
//		
//		try {
//		clientSocket = new Socket(this.serverAdress, this.portNumber);
//		
//		this.inputStream = clientSocket.getInputStream();
//		this.objInputStream = new ObjectInputStream(inputStream);
//		
//		this.outputStream = clientSocket.getOutputStream();
//		this.objOutputStream = new ObjectOutputStream(outputStream);
//		}
//		catch (IOException e) {
//	         e.printStackTrace();
//	    }	
//	}
//	
//	
//	public void close() {
//		try {
//		objOutputStream.close();
//		outputStream.close();
//		
//		objInputStream.close();
//		inputStream.close();
//		
//		clientSocket.close();
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	 

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
	
//}
