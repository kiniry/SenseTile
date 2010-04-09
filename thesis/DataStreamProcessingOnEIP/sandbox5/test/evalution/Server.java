package evalution;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String [] args) throws Exception{
		new Server().startServer();
	}
	protected void startServer() throws Exception {
		final ServerSocket server = new ServerSocket(7);
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
					Socket client = server.accept();
					client.getInputStream();

					ObjectInputStream ooi = new ObjectInputStream(client.getInputStream());

					long t1 = System.currentTimeMillis();
					Object o = ooi.readObject();
					long t2 = System.currentTimeMillis();
					
					System.out.println(o + " in " + (t2-t1) + " msec");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		t.start();
	}
}
