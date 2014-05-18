import java.util.Comparator;

public class Point implements Comparable<Point> {

    /*
     * compare points by slope to this point
     */
    public final Comparator<Point> SLOPE_ORDER = new PointSlopeComparator();

    /*
     * construct the point (x, y)
     */
    public Point(int x, int y) {
    }

    /*
     * draw this point
     */
    public void draw() {
    }

    /*
     * draw the line segment from this point to that point
     */
    public void drawTo(Point that) {
    }

    /*
     * string representation(non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "";
    }

    /*
     * is this point lexicographically smaller than that point?(non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Point that) {
        return 0;
    }

    /*
     * the slope between this point and that point
     */
    public double slopeTo(Point that) {
        return 0;
    }

    /*
     * a comparator to compare point through it slope
     */
    private class PointSlopeComparator implements Comparator<Point> {

        @Override
        public int compare(Point arg0, Point arg1) {
            return 0;
        }
    }
}