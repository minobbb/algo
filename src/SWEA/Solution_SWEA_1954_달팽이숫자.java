package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SWEA_1954_달팽이숫자 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(in.readLine());
			int[][] snail = new int[N][N];
			int num = 2;
			int nx = 0;
			int ny = 0;
			int size = 0;
			
			snail[nx][ny] = 1;
			while (true) {
				if (N <= size)
					break;
				
				// 우
				while (++nx >= size && nx < N)
					snail[ny][nx] = num++;
				nx--;
				ny++;
				
				// 하
				while (ny >= size && ny < N)
					snail[ny++][nx] = num++;
				nx--;
				ny--;
				
				// 좌
				while (nx >= size && nx < N)
					snail[ny][nx--] = num++;
				nx++;
				ny--;
			
				// 상
				while (ny > size && ny < N)
					snail[ny--][nx] = num++;
				ny++;
				
				N--;
				size++;
				
			}
			sb.append("#" + t + " \n");
			for (int i = 0; i < snail.length; i++) {
				for (int j = 0; j < snail.length; j++) {
					sb.append(snail[i][j] + " ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}
