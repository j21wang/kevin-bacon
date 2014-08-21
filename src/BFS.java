import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class BFS {
	Graph graph;
	
	public BFS(Graph graph) {
		this.graph = graph;
	}
	
	/*
	 * Using BFS, finds the shortest path from an actor to another actor.
	 * There is a possibility that there are multiple shortest paths. In
	 * this case, the first one that is considered.
	 */
	public Node findPath (String actor, String targetActor) {	
		Queue<Node> queue = new LinkedList<Node>();
		ArrayList<Node> movieList = graph.castToMovieMap.get(actor);
		Node root = graph.createCastNode(actor);
		queue.add(root);
		root.visited = true;
		while (!queue.isEmpty()) {
			Node node = (Node) queue.remove();
			if (node.isActor) {
				movieList = graph.getCastToMovieMap().get(node.name);
				for (Node movie : movieList) {
					if (!movie.visited) {
						movie.previous = node;
						movie.visited = true;
						queue.add(movie);
					}
				}
			} else {
				ArrayList<Node> castList = graph.movieToCastMap.get(node.name);
				for (Node cast : castList) {
					if (!cast.visited) {
						cast.previous = node;
						cast.visited = true;
						if (cast.name.equals(targetActor)) {
							return cast;
						}
						queue.add(cast);
					}
				}
			}
		}
		return null;
	}
	
	/*
	 * Uses a stack to save the path so that when
	 * pop() is called on the stack, the steps in the
	 * path are in the correct order.
	 */
	public String outputPath(Node kevinBaconNode) {
		Stack<Node> output = new Stack<Node>();
		Node current = kevinBaconNode;
		output.push(current);
		while (current.previous != null) {
			current = current.previous;
			output.push(current);
		}
		return stringifyPath(output);
	}
	
	private String stringifyPath(Stack<Node> outputStack) {
		StringBuilder result = new StringBuilder();
		while(!outputStack.isEmpty()) {
			Node outputNode = outputStack.pop();
			if (outputNode.isActor) {
				result.append(outputNode.name);
			} else {
				result.append(" -(" + outputNode.name + ")-> ");
			}
		}
		return result.toString();
	}
}
