package com.ssafy.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_7465_창용마을무리의개수 {

	static int[] town;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken()); // 마을 사람 수
			int M = Integer.parseInt(st.nextToken()); // 관계 수
			town = new int[N + 1]; // 1 ~ N 사용
			makeTown();

			// 관계에 따라 무리 생성
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b); // a와 b를 같은 무리로 합침
			}
			int result = 0; // 결과값
			for (int i = 1; i < N + 1; i++) {
				if (town[i] == i)
					result++; // i 인덱스의 값이 i 이면 (자기 자신을 가리키면) 무리의 수 1개 추가
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void union(int a, int b) { // a와 b를 같은 무리로 합침
		int aRoot = findSet(town[a]);
		int bRoot = findSet(town[b]);
		if (aRoot == bRoot)
			return; // a와 b의 대표자가 같다면(같은 무리라면) return.
		town[bRoot] = aRoot;
	}

	private static int findSet(int a) { // a의 대표자 return
		if (town[a] == a)
			return a;
		return town[a] = findSet(town[a]);
	}

	private static void makeTown() { // 마을 사람 번호 부여
		for (int i = 0; i < N + 1; i++)
			town[i] = i;
	}

}
