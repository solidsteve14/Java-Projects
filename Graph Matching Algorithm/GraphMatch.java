import java.util.*;
import java.io.*;

/* Steven Bradley
 * 0932259
 * CSE 373 HOMEWORK 5
 * 
 *
 */
public class GraphMatch {

	public static void main(String[] args) throws FileNotFoundException{
		// read the graph files and store info into Graph object
		Graph graph1 = readGraphFiles(args[0]);
		Graph graph2 = readGraphFiles(args[1]);
		//graph1.printGraph();
		//graph2.printGraph();
		findMatches(graph1, graph2);

	}
	
	// Reads the given .graph file and returns a Graph object with the information from the file.
	public static Graph readGraphFiles(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(filename));
		String name = reader.nextLine();
		int nodecount = reader.nextInt();
		int edgecount = reader.nextInt();
		Graph g = new Graph(name, nodecount, edgecount);
		while (reader.hasNextInt()) {
			int v1 = reader.nextInt();
			int v2 = reader.nextInt();
			g.addEdge(v1,v2);
		}
		return g;
	}
	
	public static void findMatches(Graph graph1, Graph graph2) {
		int[] vm = new int[graph1.getNodeCount()];
		int[] vd = new int[graph2.getNodeCount()];
		for (int i = 0; i < graph1.getNodeCount(); i++)
			vm[i] = i + 1;
		for (int i = 0; i < graph2.getNodeCount(); i++)
			vd[i] = i + 1;
		int[] h  = new int[vm.length + 1];
		findMatchesHelper(graph1, graph2, vm, vd, h);

	}	
	
	private static void findMatchesHelper(Graph graph1, Graph graph2, int[] vm, int[] vd, int[] h) {
		int v = vm[0];

		for (int w : vd) {

			int[] hprime = Arrays.copyOf(h, h.length);
			hprime[v] = w;
			boolean ok = true;

			System.out.println("Counter is :  " + (v));
			System.out.println("VD is : " + Arrays.toString(vd));
			System.out.println("hprime is : " + Arrays.toString(hprime));
			System.out.println("");
			
			for (int i = 1; i <= graph1.getNodeCount(); i++) {
				if (!ok) { break; }
				for (int j = 1; j <= graph1.getNodeCount(); j++) {
					if (graph1.hasEdge(i, j)) {
						
						if ((i == v && hprime[j] != 0) || (j == v && hprime[i] != 0)) {
							if (!graph2.hasEdge(hprime[i], hprime[j])) {
								ok = false;
								break;
							}
						}	
					}
				}
			}	

			if(ok) {
				int[] vmprime = removeValue(vm, v);
				int[] vdprime = removeValue(vd, w);

				if (vmprime.length == 0) {
					System.out.println("Solution Found!" + Arrays.toString(hprime));
					System.out.println("");
				} else {
					findMatchesHelper(graph1, graph2, vmprime, vdprime, hprime);
				}
			}
		}
	}
	
	// Returns an array with the first instance given value removed from the given array.
	public static int[] removeValue(int[] list, int value) {
		int[] result = new int[list.length - 1];
		int j = 0;
		for (int k = 0; k < list.length; k++) {
			if (list[k] != value) {
				result[j] = list[k];
				j++;
			}
		}
		return result;
	}
}

