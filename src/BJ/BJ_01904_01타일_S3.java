package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 15888kb, 108ms
public class BJ_01904_01타일_S3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N + 1];
		arr[1] = 1;
		if (N > 1)
			arr[2] = 2;
		for (int i = 3; i <= N; i++) {
			arr[i] = (arr[i - 2] + arr[i - 1]) % 15746;
		}
		System.out.println(arr[N]);
	}
}
