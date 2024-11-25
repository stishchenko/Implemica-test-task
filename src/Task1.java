import java.math.BigInteger;
import java.util.Scanner;

public class Task1 {
	// This task is a standard example of a problem on Catalan numbers.
	// To find a specific number in the sequence, the formula is used:
	// C_n = (2n)! / ((n+1)! * n!)
	public static void task1() {
		Scanner scanner = new Scanner(System.in);
		// Read amount of brackets (element position) from console
		System.out.print("Enter number N: ");
		int N = scanner.nextInt();
		scanner.close();

		// If there are no brackets
		if (N == 0) {
			System.out.println("There is no expression without brackets. Number of correct bracket expressions: 0");
		} else {
			// Count the number of correct expressions
			System.out.println("Number of correct bracket expressions: " + catalanNumber(N));
		}
	}

	// Function to count C_n by formula
	public static BigInteger catalanNumber(int n) {
		BigInteger numerator = factorial(2 * n);
		BigInteger denominator = factorial(n + 1).multiply(factorial(n));
		return numerator.divide(denominator);
	}

	// Function to count the factorial
	// where n! = 1*..*..*n
	public static BigInteger factorial(int num) {
		BigInteger result = BigInteger.ONE;
		for (int i = 2; i <= num; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}
}
