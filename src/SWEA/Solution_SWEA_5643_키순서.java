package com.ssafy.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_5643_키순서 {

	static int N;
	static int[][] adjMatrix;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine()); // 학생 수
			int M = Integer.parseInt(in.readLine()); // 키를 비교하는 횟수

			adjMatrix = new int[N + 1][N + 1];

			for (int i = 1; i <= N; i++) { // 탐색 전
				adjMatrix[i][0] = -1;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = 1;
			}

			// 자신보다 큰 학생 수
			for (int i = 1; i <= N; i++) {
				// 탐색 전인 학생들만 탐색
				if (adjMatrix[i][0] == -1) gtDFS(i);
			}
			
			// 자신보다 작은 학생 수
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adjMatrix[0][i] += adjMatrix[j][i];
				}
			}
			
			int answer = 0; // 자신의 키 순서를 알 수 있는 학생 수
			for (int i = 1; i <= N; i++) {
				if (adjMatrix[0][i] + adjMatrix[i][0] == N - 1) answer++;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");

		}
		System.out.println(sb);
	}

	private static void gtDFS(int cur) {

		for (int i = 0; i <= N; i++) {
			if (adjMatrix[cur][i] != 0) { // 나보다 큰 학생이면

				if (adjMatrix[i][0] == -1)
					gtDFS(i); // 탐색 전이면 탐색하러 가기

				if (adjMatrix[i][0] > 0) { // i보다 큰 학생이 있다면
					// 나보다 큰 학생이 알고있는 다른 학생과의 키 관계를 나와의 직접관계로 매핑
					for (int j = 1; j <= N; j++) {
						if (adjMatrix[i][j] == 1) {
							adjMatrix[cur][j] = 1;
						}
					}
				}
			}
		}
		
		// 나보다 큰 학생의 수 저장
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			cnt += adjMatrix[cur][i];
		}
		adjMatrix[cur][0] = cnt;
		
		
	}

}
