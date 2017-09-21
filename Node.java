
public class Node {
	public Node parent;
	public int walls = 255;
	private int rank = 0;
	/************************************************/
	public Node(){
		
	}
	//Set node's parent to itself
	public void makeSet(Node node){
		node.parent = node;
	}
	
	public Node find(Node node){
		if(node.parent!=node)
			node.parent=find(node.parent);
		return node.parent;
	}
	
	public boolean union (Node xNode, Node yNode){
		Node xRoot = find(xNode);
		Node yRoot = find(yNode);
		if(xRoot==yRoot)return false;
		
		if(xRoot.getRank()<yRoot.getRank()) xRoot.parent=yRoot;
		else if(xRoot.getRank()>yRoot.getRank()) yRoot.parent=xRoot;
		else{
			yRoot.parent=xRoot;
			xRoot.rank++;
		}
		return true;
	}
	/* Returns the highest parent (root) in the tree
	 * A node with a parent will adjust it's own
	 * to point at the root
	 */
	public Node getParent(){		
		if(parent!=null){
			Node node = parent.getParent();
			parent = node;
			return node;
		}
		else return this;
	}
	
	public int getWalls(){
		return walls;
	}
	
	public int getRank(){
		return rank;
	}
}
