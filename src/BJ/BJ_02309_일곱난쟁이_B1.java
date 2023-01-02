package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_02309_일곱난쟁이_B1 {

	static int T = 9;
	static int[] heights = new int[T];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int sum = 0;
		int a = 0;
		int b = 0; // 일곱난쟁이가 아닌 인덱스
		for (int i = 0; i < T; i++) {
			heights[i] = Integer.parseInt(in.readLine());
			sum += heights[i];
		}
		Arrays.sort(heights);
		loop:
		for (int i = 0; i < T; i++) {
			for (int j = i + 1; j < T; j++) {
				int temp = sum;
				temp = temp - heights[i] - heights[j];
				if (temp == 100) {
					a = i;
					b = j;
					break loop;
				}
			}
		}
		
		for (int i = 0; i < T; i++) {
			if (i == a || i == b)
				continue;
			sb.append(heights[i]).append("\n");
		}
		System.out.println(sb);
	}
}

	
//	public static void comb(int idx, int cnt, int sum, boolean[] check, boolean b) {
//
//		if (b)
//			check[idx - 1] = true;
//		System.out.println(Arrays.toString(check));
//		if (cnt == 7) {
//			System.out.println(sum);
//			if (sum == 100) {
//				System.out.println("get!");
//			}
//			return;
//		}
//		
//		if (idx > 8)
//			return;
//		
//		comb(idx + 1, cnt + 1, sum + heights[idx], check, true);
//		comb(idx + 1, cnt, sum, check, false);
//	}
