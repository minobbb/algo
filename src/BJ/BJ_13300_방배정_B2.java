package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11964kb, 92ms
public class BJ_13300_방배정_B2 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int[][] student = new int[7][2]; // 1~6학년 / 남,여 학생
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int S = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			student[Y][S]++;
		}
		
		int result = 0;
		for (int i = 1; i < student.length; i++) { // 1학년부터
			for (int j = 0; j < student[0].length; j++) {
				if (student[i][j] % K == 0)
					result += student[i][j] / K;
				else
					result += student[i][j] / K + 1;
			}
		}
		System.out.println(result);
	}
}
