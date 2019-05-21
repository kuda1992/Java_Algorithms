package com.pluralsight.algo.PatternRecognition;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] segments;



    public BruteCollinearPoints(Point[] points) {

        checkNull(points);
        checkDuplicatedEntries(points);
        Point[] pointsDuplicate = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsDuplicate);
        ArrayList<LineSegment> foundSegments = new ArrayList<>();

        for (int p = 0; p < pointsDuplicate.length - 3; p++) {
            for (int q = p + 1; q < pointsDuplicate.length - 2; q++) {
                double slopePQ = pointsDuplicate[p].slopeTo(pointsDuplicate[q]);
                for (int r = q + 1; r < pointsDuplicate.length - 1; r++) {
                    double slopePR = pointsDuplicate[p].slopeTo(pointsDuplicate[r]);
                    if (compare(slopePQ, slopePR) == 0) {
                        for (int s = r + 1; s < pointsDuplicate.length; s++) {
                            double slopePS = pointsDuplicate[p].slopeTo(pointsDuplicate[s]);
                            if (compare(slopePR, slopePS) == 0) {
                                foundSegments.add(new LineSegment(pointsDuplicate[p], pointsDuplicate[s]));
                            }
                        }
                    }
                }
            }
        }

        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicate entries found");
            }
        }
    }


    private int compare(double f1, double f2) {
        if (f1 < f2)
            return -1;           // Neither val is NaN, thisVal is smaller
        if (f1 > f2)
            return 1;            // Neither val is NaN, thisVal is larger

        long thisBits = Double.doubleToRawLongBits(f1);
        long anotherBits = Double.doubleToRawLongBits(f2);

        return (thisBits == anotherBits ?  0 : // Values are equal
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


    public int numberOfSegments() {
        return segments.length;
    }     // the number of line segments

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments());
    }


}
