import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Main {
	private static final String dirPath = "films";
	
	public static void main(String[] args) {		
		Graph graph = new Graph();
		File dir = new File(dirPath);
		JSONParser parser = new JSONParser();
		File[] directoryList = dir.listFiles();
		
		// Parses the JSON files in the specified directory and draws the graph.
		if (directoryList != null) {
			for (File file : directoryList) {
				try {
					Object obj = parser.parse(new FileReader(file.toString()));
					JSONObject jsonObject = (JSONObject) obj;
					JSONObject filmObject = (JSONObject) jsonObject.get("film");
					JSONArray castList = (JSONArray) jsonObject.get("cast");
					String filmName = (String) filmObject.get("name");
					for (Object castObj : castList) {
						JSONObject cast = (JSONObject) castObj;
						String castName = (String) cast.get("name");
						graph.drawEdge(filmName, castName);
					}
				} catch (Exception e) {
					// File read error. The file causing the error
					// will be ignored and the data will not be added
					// to the graph.
				}
			}
		}
		
		// The menu keeps looping until the user chooses 3 to exit.
		while (true) {
			System.out.println("Menu:");
			System.out.println("1 - Find the path to Bacon!");
			System.out.println("2 - Instructions.");
			System.out.println("3 - Exit.");
			System.out.println("\nChoose wisely:");
			Scanner s = new Scanner(System.in);
			if (s.hasNextInt()) {	
				int menuChoice = s.nextInt();
				if (menuChoice == 1) {		
					System.out.println("\nEnter an actor or an actress: ");
					Scanner scan = new Scanner(System.in);
					String actor = scan.nextLine();	
					if (actor.equals("Kevin Bacon")) {
						System.out.println("\nPath:\nKevin Bacon\n");
					} else if (!graph.getCastToMovieMap().containsKey(actor)) {
						System.err.println("\nError: Actor does not exist.\n");
					} else {
						BFS bfs = new BFS(graph);
						Node kevinBacon = bfs.findPath(actor, "Kevin Bacon");
						System.out.println("\nPath:\n" + bfs.outputPath(kevinBacon) + "\n");
					}
				} else if (menuChoice == 2) {
					System.out.println("\nInstructions - Find the path to Bacon!\n" +
							"Enter the name of an actor or actress, and\n" +
							"the steps to get to Kevin Bacon from the\n" +
							"specified actor or actress will be outputted.\n");
				} else if (menuChoice == 3) {
					System.out.println("\nBye bye!");
					break;
				} else {
					System.out.println("\nPlease enter a valid choice.\n");
				}
			} else {
				System.out.println("\nPlease enter a valid choice.\n");
			}
		}
	}
}
