package homework;

import java.io.*; 
import java.net.*;

public class Server {
	
	private int portNumber;
	private ServerSocket ss;
	private FileHandler filehandle;
	private String filename;
	public TableOfRecords table;
	
	public Server (int portNumber, String filename) throws IOException {
		this.portNumber = portNumber;
		this.ss = new ServerSocket(this.portNumber);
		ss.setSoTimeout(5000);
		
		this.filename = filename;
		this.filehandle = new FileHandler(this.filename);
		String[] records = filehandle.readTableFromFile();
		this.table = new TableOfRecords(records, records);
		this.table.printBestRecords();
	}
	
	public void serverStart() throws IOException{
		
		final Reader rdr = new InputStreamReader(System.in);
	    boolean serverStop = true;
		
	    while (serverStop)  
        { 
            Socket s = null; 
              
            try 
            { 
                
                try {
                	  // socket object to receive incoming client requests 
                	  s=ss.accept();
                	  
                      System.out.println("A new client is connected : " + s); 
                      
                      // obtaining input and out streams 
                      DataInputStream dis = new DataInputStream(s.getInputStream()); 
                      DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                      
                      ObjectInputStream ois = new ObjectInputStream(s.getInputStream()); 
                      ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        
                      System.out.println("Assigning new thread for this client"); 
        
                      // create a new thread object 
                      Thread t = new ClientHandler(s, dis, dos, ois, oos, this.table); 
        
                      // Invoking the start() method 
                      t.start(); 
                	  
                	  }
                	catch ( java.io.InterruptedIOException f ) {
                	  System.out.println( "Timed Out (5 sec)!" );
                	  }
                  

                  
            } 
            catch (Exception e){ 
                s.close(); 
                e.printStackTrace(); 
            } 
            
            if (rdr.ready()) {
                serverStop = false;
                System.out.println("Server stopped");
            }
            
            
        } 
		
	}
	
	public void close() {
		
		filehandle.writeTableToFile(this.table);
		
		try {
			ss.close();
		}catch(Exception e) {
			e.getMessage();
		}
	}
    
    
	
	
}
