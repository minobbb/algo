package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11528kb, 72ms
public class BJ_02669_직사각형면적_B1 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[101][101]; // 1~100
		
		StringTokenizer st;
		for (int i = 0; i < 4; i++) { // 4개의 직사각형
			st = new StringTokenizer(in.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for (int r = y1; r < y2; r++) {
				for (int c = x1; c < x2; c++) {
					map[r][c] = 1;
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if (map[i][j] == 1) cnt++;
			}
		}
		System.out.println(cnt);
	}
}
