package deenicholas.delnovo.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain 
{
	
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private ServerThread_1 st  = null;
	public ServerMain()
	{
		try 
		{
			serverSocket = new ServerSocket(23000);
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true)
		{	System.out.println("Server Running...");
			try 
			{
				clientSocket = serverSocket.accept();
				System.out.println("Client at connected");
				st = new ServerThread_1(clientSocket);
				st.start();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		
		}

	}
	
	public static void main(String [] args)
	{
		new ServerMain();
	}

	
}
