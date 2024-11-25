import java.math.BigInteger;

public class Task3 {
	public static void task3() {
		int number = 100;
		// Count factorial of 100 using method from task 1
		BigInteger factorial = Task1.factorial(number);
		// Count sum of its digits
		int sumOfDigits = sumDigits(factorial);

		System.out.println("The sum of digits in factorial of " + number + " is " + sumOfDigits);
	}

	// Function to count sum of number digits
	public static int sumDigits(BigInteger number) {
		int sum = 0;
		// While number is more than 0
		while (number.compareTo(BigInteger.ZERO) > 0) {
			// Get its last digit using remainder of division by 10 and add it to sum
			sum += number.mod(BigInteger.TEN).intValue();
			// Remove last digit from number
			number = number.divide(BigInteger.TEN);
		}
		return sum;
	}
}
