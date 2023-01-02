package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 67836kb, 688ms
public class BJ_01504_특정한최단경로_G4 {

	static class Node implements Comparable<Node>{
		int idx, weight;

		public Node(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	static int N, E, v1, v2;
	static List<Node>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수

		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b, c));
			list[b].add(new Node(a, c));
		}
		
		st = new StringTokenizer(in.readLine());
		v1 = Integer.parseInt(st.nextToken()); // 꼭 지나야 하는 정점 2개
		v2 = Integer.parseInt(st.nextToken());
		
		// 1 -> v1 -> v2 -> N
		long result1 = 0;
		result1 += dijkstra(1, v1);
		result1 += dijkstra(v1, v2);
		result1 += dijkstra(v2, N);
		
		// 1 -> v2 -> v1 -> N
		long result2 = 0;
		result2 += dijkstra(1, v2);
		result2 += dijkstra(v2, v1);
		result2 += dijkstra(v1, N);
		
		System.out.println((result1 >= Integer.MAX_VALUE && result2 >= Integer.MAX_VALUE) ? -1 : Math.min(result1, result2));
	}

	private static int dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
	
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int cur = node.idx;
			
			if (visited[cur])
				continue;
			visited[cur] = true;
			
			for (Node n: list[cur]) {
				if (!visited[n.idx] && dist[n.idx] > dist[cur] + n.weight) {
					dist[n.idx] = dist[cur] + n.weight;
					pq.add(new Node(n.idx, dist[n.idx]));
				}
			}
		}
		
	return dist[end];
	}
}
