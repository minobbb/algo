package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10158_개미_S4 {

	// 11756kb, 76ms
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
	
		st = new StringTokenizer(in.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		int t = Integer.parseInt(in.readLine());
		
		// 가로와 세로를 따로 보면 가로와 세로는 각각 왕복을 함.
		// 즉, 연산 횟수 줄이기 가능
		
		int xT = t % (w * 2); // x와 y의 값이 같아지는 t 구하기
		int yT = t % (h * 2);
		int dx = 1;
		int dy = 1;
		
		for (int i = 0; i < xT; i++) {
			int nx = x + dx;
			if (nx < 0 || nx > w) { // x좌표 벗어나면 반대로 가게 하기
				dx *= -1;
				nx = nx + dx * 2;
			}
			x = nx;
		}
		
		for (int i = 0; i < yT; i++) {
			int ny = y + dy;
			if (ny < 0 || ny > h) { // y좌표 벗어나면 반대로 가게 하기
				dy *= -1;
				ny = ny + dy * 2;
			}
			y = ny;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(x).append(" ").append(y);
		System.out.println(sb);
		
		
	}
}
