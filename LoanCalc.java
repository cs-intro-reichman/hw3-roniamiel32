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

	private static double endBalance(double loan, double rate, int n, double payment) {
		double x = loan;
		for (int i = 0; i < n; i++) {
			x = (x - payment) * (1 + (rate / 100));
		}
		return x;
	}

	public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;
		double g = loan / n;
		double periodPayment = endBalance(loan, rate, n, g);
		while (periodPayment > 0) {
			g = g + epsilon;
			periodPayment = endBalance(loan, rate, n, g);
			iterationCounter++;
		}
		return g;
	}

	public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
		double low = loan / n;
		double high = loan;
		double periodPayment = (low + high) / 2;
		iterationCounter = 0;
		while ((high - low) > epsilon) {
			if (endBalance(loan, rate, n, periodPayment) * endBalance(loan, rate, n, low) > 0) {
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