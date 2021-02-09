package wikibinator105.plugins.colorededgespanel;

/** is allowed if (ax (fpr type.fn from.fn to.fn)) is λColor.proof, and may optionally exist if so. */
public class Edge{
	
	/** (ax (fpr type.fn from.fn to.fn)) is λColor.proof */
	public final Node type, from, to;
	
	public Edge(Node type, Node from, Node to){
		this.type = type;
		this.from = from;
		this.to = to;
	}

}
