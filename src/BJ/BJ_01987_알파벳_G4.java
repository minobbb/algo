package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_01987_알파벳_G4 {

	static int R, C, result;
	static char[][] board;
	static int[] dx = { 0, 1, 0, -1 }; // 하 우 상 좌
	static int[] dy = { 1, 0, -1, 0 };
	static boolean[] alphabet = new boolean[26]; // 알파벳 26개

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// input
		board = new char[R][C];
		for (int i = 0; i < R; i++)
			board[i] = in.readLine().toCharArray();

		result = 0;
		alphabet[board[0][0] - 'A'] = true; // 알파벳에 해당하는 인덱스 자리
		dfs(0, 0, 1);
		System.out.println(result);
	}

	private static void dfs(int curX, int curY, int cnt) {
		
		if (result < cnt) // result에 최대값 갱신
			result = cnt;
		
		for (int i = 0, size = dx.length ; i < size; i++) {
			int nx = curX + dx[i];
			int ny = curY + dy[i];
			
			if (nx >= 0 && nx < C && ny >= 0 && ny < R) { // board 범위 내인지 검사
				if (alphabet[board[ny][nx] - 'A']) // 이미 지나온 알파벳이면
					continue;
				alphabet[board[ny][nx] - 'A'] = true;
				dfs(nx, ny, cnt + 1);
				alphabet[board[ny][nx] - 'A'] = false;
			}	
		}
	}
}
