package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_2001_파리퇴치 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			String[] s = in.readLine().split(" ");
			int N = Integer.parseInt(s[0]); // 배열 크기 N*N
			int M = Integer.parseInt(s[1]); // 파리채 크기 M*M
			int map[][] = new int[N][N];
			int idx;

			// map 생성
			for (int i = 0; i < map.length; i++) {
				idx = 0;
				st = new StringTokenizer(in.readLine(), " ");
				while (st.hasMoreTokens())
					map[i][idx++] = Integer.parseInt(st.nextToken());
			}

			int result = 0;

			// 최대 파리의 수 구하기
			for (int i = 0; i <= N - M; i++) {
				for (int j = 0; j <= N - M; j++) {
					int sum = 0;
					for (int k = i; k < i + M; k++) {
						for (int l = j; l < j + M; l++) {
							sum += map[k][l];
						}
					}
					if (result < sum)
						result = sum;
				}
			}
			sb.append(result + "\n");
		}
		System.out.println(sb);
	}
}
