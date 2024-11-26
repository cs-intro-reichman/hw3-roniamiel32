// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {

	static double epsilon = 0.001; // Approximation accuracy
	static int iterationCounter; // Number of iterations

	// Gets the loan data and computes the periodical payment.
	// Expects to get three command-line arguments: loan amount (double),
	// interest rate (double, as a percentage), and number of payments (int).
	public static void main(String[] args) {
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the
	// periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {
		double x = loan;
		for (int i = 0; i <= n; i++) {
			x = (x - payment) * (1 + (rate / 100));
		}
		return x;
	}

	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
	public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		int iterationCounter = 0;
		double g = loan;
		double periodPayment = loan / n;
		while (g >= epsilon) {
			g = endBalance(loan, rate, iterationCounter, periodPayment);
			if (g <= epsilon) {
				break;
			} else {
				periodPayment = periodPayment + epsilon;
				g = loan;
			}
			iterationCounter++;
		}
		return periodPayment;
	}

	// Uses bisection search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
	public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
		double low = 0;
		double high = loan;
		double periodPayment = (low + high) / 2;
		int iterationCounter = 0;
		while ((high - low) > epsilon) {
			if ((endBalance(loan, rate, n, periodPayment) * endBalance(loan, rate, n, low)) > 0) {
				low = periodPayment;
			} else {
				high = periodPayment;
			}
			periodPayment = (low + high) / 2;
			iterationCounter++;
		}
		return periodPayment;
	}
}