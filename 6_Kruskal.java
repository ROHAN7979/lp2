import java.util.Arrays;
import java.util.Scanner;

class Kruskal {
	class Edge implements Comparable<Edge> {
		int source, destination, weight;

		@Override
		public int compareTo(Edge edgeToCompare) {
			return this.weight - edgeToCompare.weight;
		}
	}

	class Subset {
		int parent, value;
	}

	int vertices, edges;
	Edge[] edgeArray;

	Kruskal(int vertices, int edges) {
		this.vertices = vertices;
		this.edges = edges;
		edgeArray = new Edge[this.edges];
		for (int i = 0; i < edges; ++i)
			edgeArray[i] = new Edge();
	}

	void applyKruskal() {
		Edge[] finalResult = new Edge[vertices];
		int newEdge = 0;
		int index;

		for (index = 0; index < vertices; ++index)
			finalResult[index] = new Edge();

		Arrays.sort(edgeArray);

		Subset[] subsetArray = new Subset[vertices];

		for (index = 0; index < vertices; ++index)
			subsetArray[index] = new Subset();

		for (int vertex = 0; vertex < vertices; ++vertex) {
			subsetArray[vertex].parent = vertex;
			subsetArray[vertex].value = 0;
		}

		index = 0;

		while (newEdge < vertices - 1) {
			Edge nextEdge = edgeArray[index++];

			int nextSource = findSetOfElement(subsetArray, nextEdge.source);
			int nextDestination = findSetOfElement(subsetArray, nextEdge.destination);

			if (nextSource != nextDestination) {
				finalResult[newEdge++] = nextEdge;
				performUnion(subsetArray, nextSource, nextDestination);
			}
		}

		System.out.println("Minimum Spanning Tree (Kruskal Algorithm):");
		for (index = 0; index < newEdge; ++index) {
			System.out.println(finalResult[index].source + " - " + finalResult[index].destination + ": "
					+ finalResult[index].weight);
		}
	}

	int findSetOfElement(Subset[] subsetArray, int i) {
		if (subsetArray[i].parent != i)
			subsetArray[i].parent = findSetOfElement(subsetArray, subsetArray[i].parent);
		return subsetArray[i].parent;
	}

	void performUnion(Subset[] subsetArray, int sourceRoot, int destinationRoot) {
		int nextSourceRoot = findSetOfElement(subsetArray, sourceRoot);
		int nextDestinationRoot = findSetOfElement(subsetArray, destinationRoot);

		if (subsetArray[nextSourceRoot].value < subsetArray[nextDestinationRoot].value)
			subsetArray[nextSourceRoot].parent = nextDestinationRoot;
		else if (subsetArray[nextSourceRoot].value > subsetArray[nextDestinationRoot].value)
			subsetArray[nextDestinationRoot].parent = nextSourceRoot;
		else {
			subsetArray[nextDestinationRoot].parent = nextSourceRoot;
			subsetArray[nextSourceRoot].value++;
		}
	}

	public static void main(String[] args) {
		int v, e;

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the number of vertices: ");
		v = sc.nextInt();

		System.out.println("Enter the number of edges: ");
		e = sc.nextInt();

		Kruskal graph = new Kruskal(v, e);

		for (int i = 0; i < e; i++) {
			System.out.println("Enter the source vertex for edge[" + i + "]: ");
			graph.edgeArray[i].source = sc.nextInt();

			System.out.println("Enter the destination vertex for edge[" + i + "]: ");
			graph.edgeArray[i].destination = sc.nextInt();

			System.out.println("Enter the weight for edge[" + i + "]: ");
			graph.edgeArray[i].weight = sc.nextInt();
		}

		graph.applyKruskal();
		sc.close();
	}
}
Input:

// Number of vertices: 5
// Number of edges: 7
// Edge inputs:

// Source: 0, Destination: 1, Weight: 4
// Source: 0, Destination: 2, Weight: 8
// Source: 1, Destination: 2, Weight: 2
// Source: 1, Destination: 3, Weight: 5
// Source: 2, Destination: 3, Weight: 1
// Source: 2, Destination: 4, Weight: 6
// Source: 3, Destination: 4, Weight: 3