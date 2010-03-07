package ie.ucd.sensetile.eia.node;

import org.apache.camel.CamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class NodeRunner {

	public NodeRunner(String xmlPath) {
	    try {
			ApplicationContext context = new FileSystemXmlApplicationContext(xmlPath);
		    CamelContext camel = (CamelContext) context.getBean("camel");
		    SensetileNode node = (SensetileNode)camel.getRegistry().lookup("node");
		    node.setCamelContext(camel);
		    node.setupRoutes();
	    	
		    camel.start();
	    	node.registerNode();
	    	node.start();

	    	while (true) {
	    		try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public static void main (String [] args) {
		if (args.length != 1) {
			System.out.println("One argument needed, the xml config file!");
			return;
		}
		
		new NodeRunner(args[0]);
	}
}
