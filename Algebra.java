// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
		// Tests some of the operations
		System.out.println(plus(2, 3)); // 2 + 3
		System.out.println(minus(7, 2)); // 7 - 2
		System.out.println(minus(2, 7)); // 2 - 7
		System.out.println(times(3, 4)); // 3 * 4
		System.out.println(plus(2, times(4, 2))); // 2 + 4 * 2
		System.out.println(pow(5, 3)); // 5^3
		System.out.println(pow(3, 5)); // 3^5
		System.out.println(div(12, 3)); // 12 / 3
		System.out.println(div(5, 5)); // 5 / 5
		System.out.println(div(25, 7)); // 25 / 7
		System.out.println(mod(25, 7)); // 25 % 7
		System.out.println(mod(120, 6)); // 120 % 6
		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
		System.out.println(sqrt(76123));
	}

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		while (x2 != 0) {
			x1++;
			x2--;
		}
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		while (x2 != 0) {
			x1--;
			x2--;
		}
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		// 2 * (4 + 3) can be realized as times(2,plus(4,3)).
		int result = 0;
		while (x2 != 0) {
			result = plus(result, x1);
			x2 = minus(x2, 1);
		}
		return result;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		int result = 1;
		while (n > 0) {
			result = times(result, x); // x=3 n=5
			n = minus(n, 1);
		}
		return result;
	}

	// // Returns the integer part of x1 / x2
	public static int div(int x1, int x2) {
		int x3 = 0;
		while (x1 >= x2) { // 12 >= 3
			x1 = minus(x1, x2); // 12 -3 = 9 -3 = 6 -3= 3
			x3++;
		}
		return x3;
	}

	// // Returns x1 % x2
	public static int mod(int x1, int x2) {
		int x3 = div(x1, x2);
		int result = minus(x1, times(x3, x2));
		return result;
	}

	// // Returns the integer part of sqrt(x)
	public static int sqrt(int x) {
		int result = 0;
		while (times(result, result) <= x) { // result * result <= x
			result++; // 0*0=1 1*1=1 2*2=4 3*3=9 4*4=16 <=x
		}
		return minus(result, 1);
	}
}