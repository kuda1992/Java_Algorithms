package com.pluralsight.algo.PatternRecognition;

import com.pluralsight.algo.algs4.In;
import com.pluralsight.algo.algs4.StdDraw;
import com.pluralsight.algo.algs4.StdOut;

public class TestClient {

    public static void main(String[] args) {
        // read the N points from a file
        Point[] points = getPointsFromTestFile(args[0]);

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        System.out.println("The number of segments " + collinear.segments().length);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }

    public static Point[] getPointsFromTestFile(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        return points;
    }
}
