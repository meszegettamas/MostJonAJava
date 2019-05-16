package homework;

import java.io.*; 
import java.net.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner; 

public class Client {
	
	private TableOfRecords results;
	private InetAddress ip;
	private int portNumber;
	
	public Client(InetAddress ip, int portNumber) {
		this.ip = ip;
		this.portNumber = portNumber;
	}

	public TableOfRecords getUpdatedResults(Record newRecord){
		
		try
        { 
 //           Scanner scn = new Scanner(System.in); 
   //           
            InetAddress ip = InetAddress.getByName("localhost"); 
      
            // establish the connection with server port 5056 
            Socket s = new Socket(this.ip, this.portNumber); 

            // obtaining input and out streams 
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream()); 
           
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
            
            // the following loop performs the exchange of 
            // information between client and client handler 
              
            	
                System.out.println(dis.readUTF()); 
                
                oos.writeObject(newRecord);
                this.results = (TableOfRecords) ois.readObject();
                
                
//                System.out.println("enter E to exit, enter anything else to continuoe"); 
//                String tosend = scn.nextLine();
                  
                                
                    System.out.println("Closing this connection : " + s); 
                    s.close(); 
                    System.out.println("Connection closed"); 
                
                  

             
              
            // closing resources 
//            scn.close(); 
            dis.close(); 
            dos.close();
            ois.close();
            oos.close();
            
   //         ois.close();
   //         oos.close();
            
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 	
		
		return this.results;
	}
	
	public TableOfRecords getResults(){
		
		try
        { 
			Record newRec = new Record();
//            Scanner scn = new Scanner(System.in); 
              
 //           InetAddress ip = InetAddress.getByName("localhost"); 
      
            // establish the connection with server port 5056 
            Socket s = new Socket(this.ip, this.portNumber); 

            // obtaining input and out streams 
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream()); 
           
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
            
            // the following loop performs the exchange of 
            // information between client and client handler 
              
            	
                System.out.println(dis.readUTF()); 
                
                oos.writeObject(newRec);
                this.results = (TableOfRecords) ois.readObject();
                
                
//                System.out.println("enter E to exit, enter anything else to continuoe"); 
//                String tosend = scn.nextLine();
                  
                                
                    System.out.println("Closing this connection : " + s); 
                    s.close(); 
                    System.out.println("Connection closed"); 
                
                  

             
              
            // closing resources 
//            scn.close(); 
            dis.close(); 
            dos.close();
            ois.close();
            oos.close();
            
   //         ois.close();
   //         oos.close();
            
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 	
		
		return this.results;
	}
	
}
