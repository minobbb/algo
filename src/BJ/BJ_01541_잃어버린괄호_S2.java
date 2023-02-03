package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11540kb, 76ms
public class BJ_01541_잃어버린괄호_S2 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sub = new StringTokenizer(in.readLine(), "-");

        int min = Integer.MAX_VALUE;
        while (sub.hasMoreTokens()) {
            int tmp = 0;

            StringTokenizer add = new StringTokenizer(sub.nextToken(), "+");
            while (add.hasMoreTokens()) {
                tmp += Integer.parseInt(add.nextToken());
            }

            if (min == Integer.MAX_VALUE)
                min = tmp;
            else
                min -= tmp;
        }

        System.out.println(min);
    }
}
