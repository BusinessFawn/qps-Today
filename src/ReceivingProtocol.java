import java.io.IOException;
import java.util.Date;



public class ReceivingProtocol  {
	Void run(String input)
	{	
		try{
			
			if(input.substring(0,1).equals("{")) {
				
				System.out.println("Received JSON");
				
			}
			else {
				String nameArray[] = {"binary","data","reports","orders","master","epos"};
				String colorArray[] = {"green.py","yellow.py","red.py"};
				String inputArray[] = input.split(", ");
				Date date = new java.util.Date();
				int statusArray[] = new int[inputArray.length];
			
				for(int i = 0; i < inputArray.length; i++)
				{
				    statusArray[i] = Integer.parseInt(inputArray[i]);
					int x = statusArray[i];
					String name = nameArray[i];
					String color = colorArray[x];
					String cmd = "python /home/pi/Project/" + name + color;
					System.out.println("<" + date + "> System Running Script: " + cmd);
					System.out.println("<" + date + "> " + name.substring(0, 1).toUpperCase()  + name.substring(1) + " Status Updated");
					Runtime.getRuntime().exec(cmd);
				}
			}
		}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		
		return null;
	}
}

