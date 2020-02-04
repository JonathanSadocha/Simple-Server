/**
 * Jonathan Sadocha
 *  A very simple server:
 *  It will receive an integer and return the square of the parameter
 *
 */
import java.io.*;
import java.net.*;

class Server {
    public static void main(String a[]) throws IOException {
        int maxPendingConn = 10;
        int port = 4444;
        Socket sock;
        ServerSocket servsock = new ServerSocket(port, maxPendingConn);

    while (true) {
        // wait for the next client connection
	sock=servsock.accept();

        // Get I/O streams from the socket
	PrintStream out = new PrintStream( sock.getOutputStream() );
	InputStreamReader isr  = new InputStreamReader( sock.getInputStream() );
	BufferedReader in  = new BufferedReader( isr );

        // Accept the request
	String request = in.readLine();
	
	
	String requestTrim = request.replaceAll(" ", "");
	System.out.println(requestTrim);
	int cut1 = (int)Math.floor(requestTrim.length()/2);
	int cut2 = 0;
	
	if (requestTrim.length() % 2 == 0) {
		cut2 = (int)Math.floor(requestTrim.length()/2);
	}
	else {
		cut2 = (int)Math.floor(requestTrim.length()/2)+1;
	}
	
	String firstHalf = requestTrim.substring(0,cut1);
	String lastHalf = requestTrim.substring(cut2 , requestTrim.length());
	String reversedHalf = "";
	
	for(int i = lastHalf.length() - 1; i >= 0; i--){
		reversedHalf = reversedHalf + lastHalf.charAt(i);
    }
	
	String result = "";
	
	if(firstHalf.equals(reversedHalf)) {
		result = request + " is a palindrome number ";
	}
	else{
		result =  request +" is not a palindrome number ";
	}
	
    out.print(result + "\r\n" );
    out.flush();

	// Close this connection, (not the overall server socket)
	sock.close();
     }
  }
}
