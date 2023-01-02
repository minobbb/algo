package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 + 오름차순
// 11536kb, 76ms
public class BJ_15650_N과M2_S3 {

	static int N, M;
	static int[] arr;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		visited = new boolean[N + 1];
		
		solve(1, 0);
		System.out.println(sb);
	}

	private static void solve(int start, int depth) {

		if (depth == M) {
			for(int num: arr) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			
			if (visited[i])
				continue;

			visited[i] = true;
			arr[depth] = i;
			solve(i + 1, depth + 1);
			visited[i] = false;
		}
		
	}
}
