import java.net.*;
import java.util.Date;
import java.io.*;

public class ReceivingProtocolThread extends Thread {
	
	private Socket socket = null;
	Date date;
	 
    public ReceivingProtocolThread(Socket socket) {
        super("ReceivingProtocolThread");
        this.socket = socket;
        date = new java.util.Date();
    }
    
    public void run() {
        try (
        		BufferedReader incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        		)
        {
      	String input = incoming.readLine();
      	ReceivingProtocol receivingProto = new ReceivingProtocol();
      	
      	if(socket.isConnected())
	      	{
	    		System.out.println("<" + date + "> *********************New Connection from " + socket.getRemoteSocketAddress().toString() + "*********************");
	    		System.out.println("<" + date + "> New Message Received from " + socket.getRemoteSocketAddress().toString());
	          	receivingProto.run(input);
	          	out.println("Received: " + input);
	          	out.flush();
	      	}
      	
      	socket.close();
	      		          	
	        /*while ((input = incoming.readLine()) != null)
	        {	
	    		System.out.println("<" + date + "> New Message Received from " + socket.getRemoteSocketAddress().toString());
	        	receivingProto.run(input);
	        	out.println("Received: " + input);
	        	out.flush();
	        }*/
	    }catch (IOException e) {
	    	System.err.println("This failed... " + e);
	    }
    }

}
