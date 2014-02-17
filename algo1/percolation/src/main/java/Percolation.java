import java.util.Arrays;

public class Percolation {

    private WeightedQuickUnionUF full;
    private WeightedQuickUnionUF percolate;

    private boolean[] opened;
    private int n;

    /**
     * create N-by-N grid, with all sites blocked
     */
    public Percolation(int N) {
        n = N;
        full = new WeightedQuickUnionUF(n * n + 1);
        percolate = new WeightedQuickUnionUF(n * n + 2);
        prepare();
    }

    /**
     * open site (row i, column j) if it is not already
     */
    public void open(int i, int j) {
        if (i < 1 || i > n || j < 1 || j > n)
            throw new IndexOutOfBoundsException("wrong coordinades");

        int pox = toFlatPox(i, j);
        if (!opened[pox]) {
            opened[pox] = true;
            connectWithAdjacents(i, j, pox);
        }
    }

    /**
     * is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        if (i < 1 || i > n || j < 1 || j > n)
            throw new IndexOutOfBoundsException("wrong coordinades");

        return opened[toFlatPox(i, j)];
    }

    /**
     * is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        if (i < 1 || i > n || j < 1 || j > n)
            throw new IndexOutOfBoundsException("wrong coordinades");

        if (!isOpen(i, j))
            return false;

        if (i == 1)
            return true;

        return connectedWithRoof(i, j);
    }

    private boolean connectedWithRoof(int i, int j) {
        return full.connected(toUFPox(i, j), 0);
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {
        if (n == 1) {
            return isOpen(1, 1);
        }
        return percolate.connected(0, n * n + 1);
    }

    private void prepare() {
        opened = new boolean[n * n];
        Arrays.fill(opened, false);

        for (int col = 1; col <= n; col++) {
            // init uf's
            full.union(toUFPox(1, col), 0);
            percolate.union(toUFPox(1, col), 0);
            percolate.union(toUFPox(n, col), n * n + 1);
        }
    }

    private void connectWithAdjacents(int i, int j, int pox) {
        doConnect(i > 1, pox, toFlatPox(i - 1, j)); // connect with up
        doConnect(i < n, pox, toFlatPox(i + 1, j)); // connect with down
        doConnect(j > 1, pox, toFlatPox(i, j - 1)); // connect with left
        doConnect(j < n, pox, toFlatPox(i, j + 1)); // connect with right
    }

    private void doConnect(boolean condition, int fromPox, int toPox) {
        if (condition && opened[toPox]) {
            full.union(toUFPox(toPox), toUFPox(fromPox));
            percolate.union(toUFPox(toPox), toUFPox(fromPox));
        }
    }

    /**
     * utilitaries to transform a grid [n][n] point into a position in a [0,n-1]
     * array
     */
    private int toFlatPox(int i, int j) {
        return ((i - 1) * n) + (j - 1);
    }

    private int toUFPox(int i, int j) {
        return toUFPox(toFlatPox(i, j));
    }

    private int toUFPox(int flatPox) {
        return flatPox + 1;
    }
}
