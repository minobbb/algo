package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 42240kb, 544ms
public class BJ_14889_스타트와링크_S2 {

	static int N;
	static boolean[] visited;
	static int[][] team;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		team = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				team[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(0, 0);
		System.out.println(min);
		
	}

	private static void solve(int start, int cnt) {
		
		if (cnt == N / 2) { // 종료조건
			int val = calcAbility();
			min = val < min ? val : min;
			return;
		}
		
		for (int i = start; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				solve(i + 1, cnt + 1);
				visited[i] = false;
			}
		}		
	}

	private static int calcAbility() {
		ArrayList<Integer> startList = new ArrayList<>();
		ArrayList<Integer> linkList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			if (visited[i])
				startList.add(i);
			else
				linkList.add(i);
		}
		
		int startAbility = 0;
		int linkAbility = 0;
		
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < N / 2; j++) {
				if (i == j)
					continue;
				startAbility += team[startList.get(i)][startList.get(j)];
				linkAbility += team[linkList.get(i)][linkList.get(j)];
			}
		}
		
		return Math.abs(startAbility - linkAbility);
	}
}
