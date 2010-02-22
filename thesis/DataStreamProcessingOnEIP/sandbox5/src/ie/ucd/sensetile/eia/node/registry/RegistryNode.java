package ie.ucd.sensetile.eia.node.registry;

import ie.ucd.sensetile.eia.node.NodeDefinition;
import ie.ucd.sensetile.eia.node.SensetileNode;

import org.apache.camel.builder.RouteBuilder;

public class RegistryNode extends SensetileNode {
	
	protected RegistryStore store = null;
	
	public void setNodeDefinition(NodeDefinition config) {
		super.setNodeDefinition(config);
	}
	
	public void setStore(RegistryStore store) {
		this.store = store;
	}
	
	public void setupRoutes() throws Exception {
		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from(nodeConfig.getHomeURI()).process(store);
			}
		});
	}
}
