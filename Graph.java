import java.lang.Math;
import java.util.Arrays;

class Graph {

	static final int INF = Integer.MAX_VALUE;
	private int[][] weights;
	private int[][] predecessor;

	public Graph (int[][] weights) {
		this.weights = weights;
	}

	public int[][] getPredecessor () {
		return this.predecessor;
	}

	public int[][] floydWarshall() {
		int n = weights.length;

		int[][] prev_D = weights;
		int[][] next_D = new int[n][n];

		int[][] prev_PI = new int [n][n];
		int[][] next_PI = new int [n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i == j || weights[i][j] == INF)
					prev_PI[i][j] = -1;
				else
					prev_PI[i][j] = i + 1;


		for (int k = 0; k < n; k++) {

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					int x = prev_D[i][k];
					int y = prev_D[k][j];

					if (x == INF || y == INF) {
						next_D[i][j] = prev_D[i][j];
						next_PI[i][j] = prev_PI[i][j];
					}
					else if (x < INF && y < INF) {
						if (prev_D[i][j] <= x + y) {
							next_D[i][j] = prev_D[i][j];
							next_PI[i][j] = prev_PI[i][j];
						} else {
							next_D[i][j] = x + y;
							next_PI[i][j] = prev_PI[k][j];
						}
					}
				}

			prev_D = next_D;
			prev_PI = next_PI;
		}

		this.predecessor = next_PI;
		return next_D;
	}
}
