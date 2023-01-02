package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

//25308kb, 276ms
public class BJ_11279_최대힙_S2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());

		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(in.readLine());
			if (x == 0) {
				if (queue.isEmpty()) {
					sb.append("0\n");
				}
				else {
					sb.append(queue.poll()).append("\n");
				}
			}
			else {
				queue.add(x);
			}
		}
		System.out.println(sb);
	}
}
