package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_02491_수열_S4 {

	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] arr = new int[N];
		int result = 1; // 길이 1일때,
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		//solve
		for (int i = 0; i < arr.length; i++) { // 연속해서 커질시
			if (i < N - 1 && arr[i] <= arr[i + 1]) {
				int upCnt = 1;
				while (i < N - 1 && arr[i] <= arr[i + 1]) {
					upCnt++;
					i++;
				}
				result = upCnt > result ? upCnt : result;
			}
		}

		for (int i = 0; i < arr.length; i++) { // 연속해서 작아질시
			if (i < N - 1 && arr[i] >= arr[i + 1]) {
				int downCnt = 1;
				while (i < N - 1 && arr[i] >= arr[i + 1]) {
					downCnt++;
					i++;
				}
				result = downCnt > result ? downCnt : result;
			}
		}
		System.out.println(result);
	}
}
