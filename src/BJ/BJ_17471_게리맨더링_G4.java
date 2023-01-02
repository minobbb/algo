package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 13544kb, 116ms
public class BJ_17471_게리맨더링_G4 {

	static int N, pick, min = Integer.MAX_VALUE;
	static boolean[] selected;
	static int[] population;
	static boolean[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(in.readLine());
		map = new boolean[N + 1][N + 1];
		population = new int[N + 1];
		st = new StringTokenizer(in.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int j = 0; j < size; j++) {
				int a = Integer.parseInt(st.nextToken());
				map[i][a] = true;
			}
		}

		// 6개의 자치구가 있다고 했을 때,
		// 2개, 4개로 나누는 경우와
		// 4개, 2개로 나누는 경우는 같으므로 N / 2번 실행
		for (int i = 1; i <= N / 2; i++) {
			pick = i;
			selected = new boolean[N + 1];
			comb(1, 0);
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void comb(int start, int cnt) {
		if (cnt == pick) { // 종료 조건
			// 1. 두 그룹으로 나누기
			if (!separate())
				return;
			// 2. 최소값 저장
			getMinValue();
			return;
		}

		// 조합
		for (int i = start; i <= N; i++) {
			if (selected[i])
				continue;
			selected[i] = true;
			comb(i, cnt + 1);
			selected[i] = false;
		}

	}

	private static boolean separate() {
		// 두 그룹으로 나누기
		List<Integer> groupA = new ArrayList<>();
		List<Integer> groupB = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			if (selected[i]) { // groupA
				groupA.add(i);
			} else { // groupB
				groupB.add(i);
			}
		}

		// 이어져있는지 확인
		if (!isConnected(groupA)) return false;
		if (!isConnected(groupB)) return false;

		return true;
	}

	private static boolean isConnected(List<Integer> group) {
		boolean[] visited = new boolean[N + 1];

		// 그룹이 이어져있는지 확인
		Queue<Integer> queue = new LinkedList<>();
		queue.add(group.get(0));
		visited[group.get(0)] = true;

		int cnt = 1;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (map[cur][i] && !visited[i] && group.contains(i)) {
					cnt++;
					visited[i] = true;
					queue.add(i);
				}
			}
		}
		return cnt == group.size() ? true : false;
	}

	private static void getMinValue() {
		int groupA = 0;
		int groupB = 0;

		for (int i = 1; i <= N; i++) {
			if (selected[i])
				groupA += population[i];
			else
				groupB += population[i];
		}
		min = Math.min(min, Math.abs(groupA - groupB));
	}

}
