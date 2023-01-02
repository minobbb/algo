// 슬라이딩 윈도우를 사용하면 효율적으로 풀이 가능함. 다음에 찾아보기

package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_02559_수열_S3 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		// input
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] temp = new int[N];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			temp[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = Integer.MIN_VALUE;
		
		for (int i = 0; i < N - K + 1; i++) {
			int sum = 0;
			for (int j = i; j < i + K; j++) {
				sum += temp[j];
			}
			result = result < sum ? sum : result;
			
		}
		System.out.println(result);
	}
}
