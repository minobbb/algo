package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 크루스칼 알고리즘 기본 문제
// 48548kb, 592ms
public class Solution_BJ_01197_최소스패닝트리_Kruskal {

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

	static int V, E;
	static int[] parents;
	static Edge[] edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수

		edgeList = new Edge[E];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList); // 간선 비용 오름차순 정렬
		makeSet();

		int result = 0, cnt = 0;
		
		for(Edge edge: edgeList) {
			if (union(edge.from, edge.to)) {
				result += edge.weight;
				if (++cnt == V - 1) break;
			}
		}
		
		System.out.println(result);
	}

	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) // 대표자가 같다면 (사이클이 생긴다면)
			return false;
		
		parents[bRoot] = aRoot; // 대표자 변경
		return true;
	}

	private static int findSet(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = findSet(parents[a]); // path compression
	}

	private static void makeSet() {
		parents = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}

}
