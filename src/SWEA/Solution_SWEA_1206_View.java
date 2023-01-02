package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_1206_View {

	static int T = 10;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());
			int[] building = new int[N];
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++)
				building[i] = Integer.parseInt(st.nextToken());

			int result = 0;
			for (int i = 2; i < building.length - 2; i++) { // 양쪽 2칸씩은 빌딩이 없음
				int max = 0;
				for (int j = -2; j <= 2; j++) { // i-2, i-1, i+1, i+2 체크 (좌우 2칸씩)
					if (j == 0) continue;
					if (building[i] <= building[i + j]) { // i번째 건물에 조망권이 확보되지 않을 때
						max = -1;
						break;
					}
					if (max < building[i + j])
						max = building[i + j];
				}
				if (max == -1) continue;
				result = result + building[i] - max;
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
