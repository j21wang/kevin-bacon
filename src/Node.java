public class Node {
	String name;
	boolean visited;
	boolean isActor;
	Node previous;
	
	public Node(String name, boolean visited, boolean isActor) {
		this.name = name;
		this.visited = visited;
		this.isActor = isActor;
		this.previous = null;
	}
}
