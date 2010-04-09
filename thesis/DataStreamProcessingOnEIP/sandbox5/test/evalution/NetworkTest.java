package evalution;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkTest {
	ObjectOutputStream out = null;

	public NetworkTest() {
		try {
			startClient();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void startClient() {
		Socket echoSocket = null;
		try {
			echoSocket = new Socket("localhost", 7);
			out = new ObjectOutputStream(echoSocket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void writeObject(Object o) {
		try {
			out.writeObject(o);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
