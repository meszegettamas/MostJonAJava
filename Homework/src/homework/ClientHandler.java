package homework;

import java.io.*; 
import java.text.*; 
//import java.util.*; 
import java.net.*; 

// ClientHandler class 
class ClientHandler extends Thread  
{ 
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final ObjectInputStream ois; 
    final ObjectOutputStream oos;
    final Socket s; 
    private TableOfRecords table;
    private Record record;
      
  
    // Constructor 
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, ObjectInputStream ois, ObjectOutputStream oos, TableOfRecords table)  
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos;
        this.ois = ois;
        this.oos = oos;

        this.table = table;
    } 
  
    @Override
    public void run()  
    { 
//        String received; 
//        String toreturn; 
        boolean succesfullySentOrExit = true;
        while (succesfullySentOrExit)  
        { 
            try { 
  
                // Ask user what he wants 
                dos.writeUTF("Receiving new record..."); 
                  
                // receive the answer from client
                try {
                this.record = (Record) ois.readObject();
                this.table.insertNewRecord(this.record);
                this.table.printBestRecords();
                oos.writeObject(this.table);
                dos.writeUTF("Results sent");
                succesfullySentOrExit = false;
                } catch(Exception e) {
                	System.out.print("received an Object of the wrong class");
                	e.getMessage();
                	this.s.close(); 
                    System.out.println("Connection closed"); 
                }
                  
//                if(received.equals("Exit")) 
//                {  
//                    System.out.println("Client " + this.s + " sends exit..."); 
//                    System.out.println("Closing this connection."); 
//                    this.s.close(); 
//                    System.out.println("Connection closed"); 
//                    break; 
//                } 
//                  
//                // creating Date object 
//                Date date = new Date(); 
//                  
//                // write on output stream based on the 
//                // answer from the client 
//                switch (received) { 
//                  
//                    case "Date" : 
//                        toreturn = fordate.format(date); 
//                        dos.writeUTF(toreturn); 
//                        break; 
//                          
//                    case "Time" : 
//                        toreturn = fortime.format(date); 
//                        dos.writeUTF(toreturn); 
//                        break; 
//                          
//                    default: 
//                        dos.writeUTF("Invalid input"); 
//                        break; 
//                } 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
          
        try
        { 
            // closing resources 
            this.dis.close(); 
            this.dos.close();
            this.ois.close();
            this.oos.close();
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    } 
} 