package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_02605_줄세우기_B2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		LinkedList<Integer> list = new LinkedList<>();
		int numbers[] = new int[n];
		int student = 1;
		for (int i = 0; i < n; i++)
			numbers[i] = Integer.parseInt(st.nextToken());
		// solve
		for (int i = 0; i < n; i++)
			list.add(i - numbers[i], student++);
		// output
		for (int l: list)
			sb.append(l).append(" ");
		System.out.println(sb);
	}
}
