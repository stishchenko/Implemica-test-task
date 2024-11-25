import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Task2 {
	//this task can be solved by graphs using Dejkstra`s algorithm

	// Class for representing a graph edge
	static class Edge {
		int target, cost;
		Edge(int target, int cost) {
			this.target = target;
			this.cost = cost;
		}
	}

	// Class for representing a node in a priority queue
	static class Node implements Comparable<Node> {
		int city, cost;
		Node(int city, int cost) {
			this.city = city;
			this.cost = cost;
		}
		public int compareTo(Node other) {
			return Integer.compare(this.cost, other.cost);
		}
	}

	public static void task2() {
		// Get data from console
		Scanner scanner = new Scanner(System.in);
		int s = getIntInput(scanner,1, 10); // tests amount
		ArrayList<Integer> results = new ArrayList<>();
		for (int t = 0; t < s; t++) {
			int n = getIntInput(scanner, 1, 10000); // cities amount
			scanner.nextLine(); // skip the line

			Map<String, Integer> cityIndex = new HashMap<>(); // map for city indexes
			List<List<Edge>> graph = new ArrayList<>(n); // graph for adjacency list

			for (int i = 0; i < n; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < n; i++) {
				String cityName = getStringInput(scanner, 1, 10); // city name
				cityIndex.put(cityName, i); // add city name with index
				int p = getIntInput(scanner, 0, Integer.MAX_VALUE); // neighbor amount
				for (int j = 0; j < p; j++) {
					int neighbor = getIntInput(scanner, 1, n) - 1; // neighbor amount for 0-indexation
					int cost = getIntInput(scanner, 1, Integer.MAX_VALUE); // transportation cost
					// Add edge to graph
					graph.get(i).add(new Edge(neighbor, cost));
				}
				if (scanner.hasNextLine()) {
					scanner.nextLine(); // skip line
				}
			}

			int r = getIntInput(scanner, 1, 100); // number of paths to find
			scanner.nextLine(); // skip line
			for (int i = 0; i < r; i++) {
				String source = getStringInput(scanner, 1, 10); // source city
				String destination = getStringInput(scanner, 1, 10); // destination city
				// Find the shortest way
				results.add(dijkstra(graph, cityIndex.get(source), cityIndex.get(destination)));
			}

			if (scanner.hasNextLine()) {
				scanner.nextLine(); // skip line
			}
		}
		scanner.close();

		//Print cost for each way
		for (int result : results) {
			System.out.println(result);
		}
	}

	// Dijkstra's algorithm for finding the shortest path
	public static int dijkstra(List<List<Edge>> graph, int start, int end) {
		int[] dist = new int[graph.size()]; // array to store minimum distances
		Arrays.fill(dist, Integer.MAX_VALUE); // initialize distances with infinity
		dist[start] = 0; // distance to the start city is 0

		PriorityQueue<Node> pq = new PriorityQueue<>(); // priority queue to select the node with the minimum cost
		// Add the start node
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			// Extract the node with the minimum cost
			Node current = pq.poll();
			int currentCity = current.city;
			int currentCost = current.cost;

			// If reached the end city, return the cost
			if (currentCity == end) {
				return currentCost;
			}

			for (Edge edge : graph.get(currentCity)) {
				// Calculate the new cost
				int newCost = currentCost + edge.cost;
				// If the new cost is less than the current cost
				if (newCost < dist[edge.target]) {
					// Update the minimum cost
					dist[edge.target] = newCost;
					// Add the node to the queue
					pq.add(new Node(edge.target, newCost));
				}
			}
		}
		// If the path is not found
		return -1;
	}

	// Method to get integer input with validation
	private static int getIntInput(Scanner scanner, int min, int max) {
		int value;
		while (true) {
			if (scanner.hasNextInt()) {
				value = scanner.nextInt();
				if (value >= min && value <= max) {
					break;
				} else {
					System.out.println("Value must be in the range from " + min + " to " + max + ".");
				}
			} else {
				System.out.println("Please enter integer number.");
				scanner.next();
			}
		}
		return value;
	}

	// Method to get string input with length validation
	private static String getStringInput(Scanner scanner, int minLength, int maxLength) {
		String value;
		while (true) {
			value = scanner.next();
			if (value.length() >= minLength && value.length() <= maxLength) {
				break;
			} else {
				System.out.println("String length must be from " + minLength + " to " + maxLength + " characters.");
			}
		}
		return value;
	}
}