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
	
	public Client(String ip) {
		InetAddress ipAddress;
		try {
		ipAddress = InetAddress.getByName(ip);
		}catch(Exception e) {
			ipAddress = null;
		}
		this.ip = ipAddress;
		this.portNumber = 5015;
	}

	public TableOfRecords getUpdatedResults(Record newRecord){
		
		try
        { 
 //           Scanner scn = new Scanner(System.in); 
   //           
            InetAddress ip = InetAddress.getByName("localhost"); 
      
            // establish the connection with server port 5056 
  //          Socket s = new Socket(this.ip, this.portNumber); 
            int connection_time_out = 5000;
            Socket s=new Socket();   
            s.connect(new InetSocketAddress(this.ip,this.portNumber),connection_time_out); 

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
            System.out.print("unable to connect");
            this.results = new TableOfRecords();
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
 //           Socket s = new Socket(this.ip, this.portNumber);
            
            int connection_time_out = 5000;
            Socket s=new Socket();   
            s.connect(new InetSocketAddress(this.ip,this.portNumber),connection_time_out); 

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
            System.out.print("unable to connect");
            this.results = new TableOfRecords();
        } 	
		
		return this.results;
	}
	
}
