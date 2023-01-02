package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11936kb, 88ms
public class BJ_14719_빗물_G5 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int H = Integer.parseInt(st.nextToken()); // 높이
		int W = Integer.parseInt(st.nextToken()); // 길이
		int[] map = new int[W];
		int result = 0;
		int maxH = 0;
		int maxW = 0;
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < W; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			if (maxH < map[i]) {
				maxH = map[i];
				maxW = i;
			}
		}
		
		int height = map[0];
		for (int i = 1; i <= maxW; i++) { // 가장 높은 블록 기준 왼쪽
			if (height < map[i]) {
				height = map[i];
			}
			else {
				result += height - map[i];
			}
		}
		
		height = map[W - 1];
		for (int i = W - 2; i > maxW; i--) { // 가장 높은 블록 기준 오른쪽
			if (height < map[i]) {
				height = map[i];
			}
			else {
				result += height - map[i];
			}
		}
		System.out.println(result);
	}
}
