package ie.ucd.sensetile.eia.node.command;

import org.apache.camel.Processor;

public interface CommandHandler extends Processor {
	public void handleCommand(Command command);
}
