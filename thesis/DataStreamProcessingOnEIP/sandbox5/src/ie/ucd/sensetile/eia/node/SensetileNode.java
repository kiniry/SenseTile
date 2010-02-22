package ie.ucd.sensetile.eia.node;

import ie.ucd.sensetile.eia.node.command.RegistryCommand;
import ie.ucd.sensetile.eia.node.command.RegistryCommand.Action;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;


public class SensetileNode {
	protected NodeDefinition nodeConfig = null;
	protected CamelContext camelContext = null;
	protected ProducerTemplate producer = null;
	
	public void setNodeDefinition(NodeDefinition config) {
		this.nodeConfig = config;
	}
	
	public void setCamelContext(CamelContext context) {
		this.camelContext = context;
		this.producer = camelContext.createProducerTemplate();
	}
	
	public void setupRoutes() throws Exception {
		
	}
	
	public void registerNode() {
		if (nodeConfig.getRegistryURI() != null) {
			RegistryCommand cmd = new RegistryCommand(Action.ADD, nodeConfig);
			producer.asyncRequestBody(nodeConfig.getRegistryURI(), cmd);
		}
	}
	
	public void start() {
	}
}
