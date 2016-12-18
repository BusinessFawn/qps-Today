import java.io.IOException;
import java.net.ServerSocket;

public class ReceivingServer {

	 public static void main(String[] args) throws IOException {
         
		 if (args.length != 1)
		 {
	            System.err.println("Please specify port.");
	            System.exit(1);
	     }
	 
		int portNumber = Integer.parseInt(args[0]);
		 
		while(true)
		{
	        try (ServerSocket serverSocket = new ServerSocket(portNumber)){
	        		
	        		while(true) {
	        			new ReceivingProtocolThread(serverSocket.accept()).start();
	        		}
	        } catch(IOException e) {
	        	System.err.println("This faled...." + portNumber);
	        }
		}
	 }
}
