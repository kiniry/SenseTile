package ie.ucd.sensetile.eia;

import java.util.Scanner;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class SimpleSink {
	public static final String SOURCE_URL = "mina:tcp://localhost:7777?sync=false";
	public static final String SOURCE_URL2 = "mina:tcp://localhost:7778?sync=false";

	private DefaultCamelContext ctx;

	public SimpleSink() {
		ctx = new DefaultCamelContext();
		setupRoutes(ctx);

	}

	protected void setupRoutes(DefaultCamelContext ctx) {
		try {

			ctx.addRoutes(new RouteBuilder() {
				public void configure() {
					from(SOURCE_URL2).bean(new TestBean("SinkTestBean"));
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			ctx.start();

			Scanner in = new Scanner(System.in);
			in.nextLine();

			ctx.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Logger.getRootLogger().setLevel(Level.FATAL);
		new SimpleSink().run();
	}

}
