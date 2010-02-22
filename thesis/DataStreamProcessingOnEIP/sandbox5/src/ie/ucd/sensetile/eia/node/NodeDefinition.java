package ie.ucd.sensetile.eia.node;

import java.io.Serializable;

public class NodeDefinition implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	
	private String homeURI  = null;
	private String registryURI = null;
	
	public String getRegistryURI() {
		return registryURI;
	}

	public void setRegistryURI(String registryURI) {
		this.registryURI = registryURI;
	}

	public void setHomeURI(String uri) {
		this.homeURI = uri;
	}
	
	public String getHomeURI() {
		return homeURI; 
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
