package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 330084kb, 2296ms
public class BJ_01647_도시분할계획_G4 {

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge e) {
			return this.weight - e.weight;
		}
	}

	static int N, M;
	static int[] parents;
	static Edge[] edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken()); // 집의 개수
		M = Integer.parseInt(st.nextToken()); // 길의 개수

		edgeList = new Edge[M];
	
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList); // 유지비 오름차순 정렬
		makeSet();
		
		int result = 0, cnt = 0;
		
		for(Edge edge: edgeList) {
			if (union(edge.from, edge.to)) {
				if (++cnt == N - 1) break; // 가장 유지비가 큰 길 없애서 2개의 마을 만들기
				result += edge.weight;
			}
		}
		
		System.out.println(result);
	}

	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) // 대표자가 같다면 (사이클이 생긴다면)
			return false;
		
		parents[bRoot] = aRoot;
		return true;
	}

	private static int findSet(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = findSet(parents[a]);
	}

	private static void makeSet() {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
}
