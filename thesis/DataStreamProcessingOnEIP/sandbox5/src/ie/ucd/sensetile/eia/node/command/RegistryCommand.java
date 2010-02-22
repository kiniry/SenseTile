package ie.ucd.sensetile.eia.node.command;

import ie.ucd.sensetile.eia.node.NodeDefinition;

public class RegistryCommand extends Command {

	private static final long serialVersionUID = 1L;

	public static enum Action {ADD, REMOVE, GET_ALL};
	
	private Action action;
	private NodeDefinition nodeDefinition;
	
	public RegistryCommand(Action action, NodeDefinition nodeDef) {
		this.action = action;
		this.nodeDefinition = nodeDef;
	}
	
	public Action getAction() {
		return action;
	}
	
	public NodeDefinition getNodeDefinition() {
		return nodeDefinition;
	}
}
