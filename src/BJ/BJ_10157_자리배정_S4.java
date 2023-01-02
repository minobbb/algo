package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 15858kb, 104ms
public class BJ_10157_자리배정_S4 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(in.readLine());

		if (K > C * R) { // 좌석 배정이 불가능한 경우
			System.out.println(0);
			System.exit(0);
		}
		if (K == 1) { // K == 1인 경우 무조건 1,1 배정
			System.out.println(1 + " " + 1);
			System.exit(0);
		}

		int[][] map = new int[R][C];
		int[] dx = { 0, 1, 0, -1 }; // 하 우 상 좌
		int[] dy = { 1, 0, -1, 0 };
		int x = 0, y = 0;
		int start = 1;
		map[y][x] = start; // 초기값 넣기
		breakPoint:
		while (true) {
			for (int i = 0; i < 4; i++) { // 4방 탐색
				int nx = x + dx[i];
				int ny = y + dy[i];

				while (nx >= 0 && nx < C && ny >= 0 && ny < R && map[ny][nx] == 0) { // 달팽이모양으로 숫자 채우기
					map[ny][nx] = ++start;
					if (start == K) { // K번째 좌석을 찾으면 x, y에 좌표 넣어주고 2중반복문 빠져나가기
						x = nx;
						y = ny;
						break breakPoint;
					}
					nx += dx[i];
					ny += dy[i];
				}
				// while문의 조건에 맞지 않아서 빠져나온 것이므로 한 칸 뒤로 되돌리기
				x = nx - dx[i];
				y = ny - dy[i];
			}
		}
		// output
		StringBuilder sb = new StringBuilder();
		sb.append(x + 1).append(" ").append(y + 1);
		System.out.println(sb);
	}
}
