package ie.ucd.sensetile.eia.node.registry;

import ie.ucd.sensetile.eia.node.NodeDefinition;
import ie.ucd.sensetile.eia.node.command.RegistryCommand;
import ie.ucd.sensetile.eia.node.command.RegistryCommand.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RegistryStore implements Processor {
	
	protected Map<String, NodeDefinition> nodes = new HashMap<String, NodeDefinition>();

	@Override
	public void process(Exchange exchange) throws Exception {
		
		System.out.println("Handling command: " + exchange.getIn().getBody());
		
		RegistryCommand cmd = (RegistryCommand) exchange.getIn().getBody();
		Action action = cmd.getAction();
		NodeDefinition nodeDef = cmd.getNodeDefinition();
	
		if (action == Action.ADD ) {
			nodes.put(nodeDef.getHomeURI(), nodeDef);
		} else if (action == Action.REMOVE) {
			nodes.remove(nodeDef.getHomeURI());
		} else if (action == Action.GET_ALL) {
			List<NodeDefinition> result = new ArrayList<NodeDefinition>(nodes.size());
			for (NodeDefinition def : nodes.values()) {
				result.add((NodeDefinition)def.clone());
			}
			exchange.getOut().setBody(result);			
		}			
	}

}
