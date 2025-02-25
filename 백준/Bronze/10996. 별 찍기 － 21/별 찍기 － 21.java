import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			star(N);
			System.out.println();
		}
	}

	static void star(int n) {
		if (n == 1) {
			System.out.println("*");
		} else {
			for (int i = 0; i < Math.ceil((double) n / 2); i++) {
				System.out.print("* ");
			}
			System.out.println();
			for (int i = 0; i < n / 2; i++) {
				System.out.print(" *");
			}
		}
	}

}
