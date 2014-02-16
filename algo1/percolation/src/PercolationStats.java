public class PercolationStats {
    private int n;
    private int t;
    private double[] results;

    /**
     * perform T independent computational experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T) {
        if (N < 1 || T < 1)
            throw new IllegalArgumentException("cannot be 0");

        n = N;
        t = T;
        results = new double[t];
        run();
    }

    private void run() {
        for (int i = 0; i < t; i++) {

            Percolation percolator = new Percolation(n);
            double attempts = 0;
            while (!percolator.percolates()) {
                int fil = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!percolator.isOpen(fil, col)) {
                    // ignoring already used fils and cols
                    percolator.open(fil, col);
                    attempts++;
                }
            }
            double result = attempts / (n * n);
            results[i] = result;
        }
    }

    /**
     * sample mean of percolation threshold
     */
    public double mean() {
        double threshold = 0;
        for (double a : results) {
            threshold += a;
        }
        return threshold / t;
    }

    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        if (t == 1)
            return Double.NaN;

        double mean = mean();

        double count = 0.0;
        for (double result : results) {
            count += ((result - mean) * (result - mean));
        }

        return Math.sqrt(count / (t - 1));
    }

    /**
     * returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();
        return mean - (1.96 * (stddev / Math.sqrt(t)));
    }

    /**
     * returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        double mean = mean();
        double stddev = stddev();
        return mean + (1.96 * (stddev / Math.sqrt(t)));
    }

    /**
     * test client, described below
     */
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(200, 100);
        System.out.println("java PercolationStats 200 100");
        System.out.println("mean                    =" + stats.mean());
        System.out.println("stddev                  =" + stats.stddev());
        System.out.println("95% confidence interval =" + stats.confidenceLo()
                + "," + stats.confidenceHi());
    }
}