import java.io.*;

class Main {

	public static void main (String[] args) {
		String fileName = "graph.txt";
		int[][] W = readInput(fileName);

		Graph graph = new Graph(W);
		int[][] paths_weights = graph.floydWarshall();
		int[][] predecessors = graph.getPredecessor();

		print("Floyd-Marshall all-pairs shortest-path's weights: ", paths_weights, Graph.INF);
		print("\nFloyd-Marshall all-pairs shortest-path predecessors: ", predecessors, -1);
	}

	static int[][] readInput (String fileName) {
		int[][] W = null;

		try(BufferedReader br = new BufferedReader(new FileReader("graph.txt"))) {
		    StringBuilder sb = new StringBuilder();
		    int n = Integer.parseInt(br.readLine());

			W = new int[n][n];

			for (int i = 0; i < n; i++) {
				String[] row = br.readLine().split("\\s+");
				for (int j = 0; j < n; j++) {
					String x = row[j];
					if (x.equals("INF")) {
						W[i][j] = Graph.INF;
					} else {
						W[i][j] = Integer.parseInt(x);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		return W;
	}

	static void print(String title, int[][] matrix, int comparator) {
		int n = matrix.length;

		System.out.print(title + "\n\t");
		for (int i = 1; i <= n; i++) System.out.print("[," + i + "]" + "\t");
		System.out.println("");

		for (int i = 0; i < n; i++) {
			System.out.print("[" + (i + 1) + ",]\t");
			for (int j = 0; j < n; j++) {
				int x = matrix[i][j];
				System.out.print(
					(x == comparator ?
							(comparator == -1 ? "nil" : "INF")
						:
							x) + "\t"
				);
			}
			System.out.println("");
		}
	}
}
