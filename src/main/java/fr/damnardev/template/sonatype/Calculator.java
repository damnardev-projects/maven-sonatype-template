package fr.damnardev.template.sonatype;

/**
 * Simple utility class providing basic arithmetic operations.
 * <p>
 * All methods are static and the class cannot be instantiated.
 * This is intended for demonstration and testing purposes in the
 * sonatype template project.
 */
public final class Calculator {

	private Calculator() {
		throw new IllegalStateException("Utility class - no instances allowed");
	}

	/**
	 * Returns the sum of two integers.
	 *
	 * @param a the first addend
	 * @param b the second addend
	 * @return the sum of {@code a} and {@code b}
	 */
	public static int add(int a, int b) {
		return a + b;
	}

	/**
	 * Calculates the difference between two integers.
	 *
	 * @param a the minuend
	 * @param b the subtrahend
	 * @return {@code a} minus {@code b}
	 */
	public static int subtract(int a, int b) {
		return a - b;
	}

	/**
	 * Multiplies two integers.
	 *
	 * @param a the first factor
	 * @param b the second factor
	 * @return the product of {@code a} and {@code b}
	 */
	public static int multiply(int a, int b) {
		return a * b;
	}

	/**
	 * Divides one integer by another using integer division.
	 *
	 * @param a the dividend
	 * @param b the divisor; must not be zero
	 * @return the result of dividing {@code a} by {@code b}
	 * @throws IllegalArgumentException if {@code b} is zero
	 */
	public static int divide(int a, int b) {
		if (b == 0) {
			throw new IllegalArgumentException("Division by zero is not allowed");
		}
		return a / b;
	}

}
