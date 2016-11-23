package qpsReceiver;

import java.net.*;
import java.util.Date;
import java.io.*;

public class ReceivingServer {

	 public static void main(String[] args) throws IOException {
         
		 if (args.length != 1)
		 {
	            System.err.println("Please specify port.");
	            System.exit(1);
	     }
	 
		int portNumber = Integer.parseInt(args[0]);
		Date date = new java.util.Date();
		 
while(true)
{
	        try ( 
	            ServerSocket serverSocket = new ServerSocket(portNumber);
	            Socket clientSocket = serverSocket.accept();
	            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
	            BufferedReader incoming = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        		)
	        {
	          	String input = incoming.readLine();
	          	ReceivingProtocol receivingProto = new ReceivingProtocol();
	          	
	          	if(clientSocket.isConnected())
	          	{
	        		System.out.println("<" + date + "> *********************New Connection from " + clientSocket.getRemoteSocketAddress().toString() + "*********************");
		          	out.print(1);	
		    		System.out.println("<" + date + "> New Message Received from " + clientSocket.getRemoteSocketAddress().toString());
		          	out.print(1);
		          	receivingProto.run(input);
	          	}
	          		          	
		        while ((input = incoming.readLine()) != null)
		        {	
		    		System.out.println("<" + date + "> New Message Received from " + clientSocket.getRemoteSocketAddress().toString());
		          	out.print(1);
		        	receivingProto.run(input);
		        }
	        }
	        
	        catch (IOException e) {
	            System.out.println("Exception caught when trying to listen on port "
	                + portNumber + " or listening for a connection");
	            System.out.println(e.getMessage());
	        }
	    }
	 }
}
