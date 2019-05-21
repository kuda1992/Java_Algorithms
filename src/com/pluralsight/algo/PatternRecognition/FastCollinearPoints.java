package com.pluralsight.algo.PatternRecognition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

public class FastCollinearPoints {

    private final List<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {

        checkNull(points);
        segments = new ArrayList<>();
        final Point[] copyOfPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(copyOfPoints);
        checkDuplicatedEntries(copyOfPoints);
        for (Point point : points) {
            Point[] pointsSortedBySlopeOrder = copyOfPoints.clone();
            Arrays.sort(pointsSortedBySlopeOrder, point.slopeOrder());
            int i = 1;
            while (i < points.length) {
                LinkedList<Point> slopePoints = new LinkedList<>();
                final double SLOPE_REF = point.slopeTo(pointsSortedBySlopeOrder[i]);
                do {
                    slopePoints.add(pointsSortedBySlopeOrder[i++]);
                } while (i < points.length && compare(point.slopeTo(pointsSortedBySlopeOrder[i]), SLOPE_REF) == 0);

                if (slopePoints.size() >= 3 && point.compareTo(slopePoints.peek()) < 0) {
                    Point min = point;
                    Point max = slopePoints.removeLast();
                    segments.add(new LineSegment(min, max));
                }
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }

    private int compare(double f1, double f2) {
        if (f1 < f2)
            return -1;           // Neither val is NaN, thisVal is smaller
        if (f1 > f2)
            return 1;            // Neither val is NaN, thisVal is larger

        long thisBits = Double.doubleToRawLongBits(f1);
        long anotherBits = Double.doubleToRawLongBits(f2);

        return (thisBits == anotherBits ? 0 : // Values are equal
                (thisBits < anotherBits ? -1 : // (-0.0, 0.0) or (!NaN, NaN)
                        1));                          // (0.0, -0.0) or (NaN, !NaN)
    }

    private void checkNull(Point[] points) {
        if (points == null) {
            throw new NullPointerException("The array \"Points\" is null.");
        }
        for (Point p : points) {
            if (p == null) {
                throw new NullPointerException(
                        "The array \"Points\" contains null element.");
            }
        }
    }


    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicate entries found");
            }
        }
    }
}
