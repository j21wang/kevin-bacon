import java.util.ArrayList;
import java.util.HashMap;


public class Graph {
	public HashMap <String, ArrayList<Node>> movieToCastMap;
	public HashMap <String, ArrayList<Node>> castToMovieMap;
	
	public Graph() {
		movieToCastMap = new HashMap <String, ArrayList<Node>>();
		castToMovieMap = new HashMap <String, ArrayList<Node>>();
	}
	
	public Node createCastNode (String actor) {
		boolean visited = false;
		boolean isActor = true;
		Node node = new Node (actor, visited, isActor);
		return node;
	}
	
	public Node createMovieNode (String movie) {
		boolean visited = false;
		boolean isActor = false;
		Node node = new Node(movie, visited, isActor);
		return node;
	}
	
	/*
	 * New node containing actor information added to the cast
	 * ArrayList for that movie.
	 */
	private void putInMovieToCastMap (String movie, String actor) {	
		Node castNode = createCastNode(actor);
		ArrayList<Node> castList = movieToCastMap.get(movie);
		if (castList == null) {
			castList = new ArrayList<Node>();
		}
		castList.add(castNode);
		movieToCastMap.put(movie, castList);
		return;
	}
	
	/*
	 * New node containing movie information added to the ArrayList
	 * of movies for that actor.
	 */
	private void putInCastToMovieMap (String movie, String cast) {
		Node movieNode = createMovieNode(movie);
		ArrayList<Node> movieList = castToMovieMap.get(cast);
		if (movieList == null) {
			movieList = new ArrayList<Node>();
		}
		movieList.add(movieNode);
		castToMovieMap.put(cast, movieList);
		return;
	}
	
	/*
	 * Drawing an edge between movie and the actor means that the
	 * movie and actor were both added in both HashMaps.
	 */
	public void drawEdge (String movie, String actor) {
		putInMovieToCastMap(movie, actor);
		putInCastToMovieMap(movie, actor);
	}
	
	public HashMap<String, ArrayList<Node>> getCastToMovieMap () {
		return castToMovieMap;
	}
	
	public HashMap<String, ArrayList<Node>> getMovieToCastMap () {
		return movieToCastMap;
	}
}
