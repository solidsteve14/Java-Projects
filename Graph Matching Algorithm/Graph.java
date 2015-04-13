
public class Graph {
	private String name;
	private int nodecount;
	private int edgecount;
	private boolean[][] edges; // Matrix for edges. 
	private int[] nodes;
	
	// Constructor
	public Graph(String name, int nodecount, int edgecount) {
		this.name = name;
		this.nodecount = nodecount;
		this.edgecount = edgecount;
		this.edges = new boolean[nodecount + 1][nodecount + 1];
		this.nodes = new int[nodecount + 1];
	}
	
	// Adds an edge between the given nodes to the graph.
	public void addEdge(int v1, int v2){
		edges[v1][v2] = true;
		edges[v2][v1] = true;
	}
	
	
	// Returns the number of nodes in the graph.
	public int getNodeCount() {
		return this.nodecount;
	}
	
	public boolean[][] getEdges() {
		return edges;
	}
	
	// Returns true of an edge between a and b is in the graph.
	public boolean hasEdge(int a, int b) {
		return edges[a][b];
	}
	// Prints out all of the edges in the graph.
	// FORMAT:
	// graph name
	//     1 to 3
	//     4 to 17
	//     20 to 4
	public void printGraph() {
		System.out.println(name);
		for (int i = 0; i <= nodecount; i++)
			for (int j = 0; j <= nodecount; j++)
				if (edges[i][j])
					System.out.println("\t" + i + " --> " + j);	
	}
}
	
