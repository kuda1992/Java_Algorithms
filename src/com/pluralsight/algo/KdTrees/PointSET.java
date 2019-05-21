package com.pluralsight.algo.KdTrees;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {

    private final TreeSet<Point2D> points;

    public PointSET() {
        points = new TreeSet<Point2D>();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        if (!contains(p)) {
            points.add(p);
        }

    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return points.contains(p);
    }

    public void draw() {
        for (Point2D p : points) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> pointsInRect = new LinkedList<>();

        for (Point2D p : points) {
            if(rect.contains(p)){
                pointsInRect.add(p);
            }
        }
        return pointsInRect;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        if (isEmpty()) return null;

        Point2D neighbor = null;

        for (Point2D point : points) {
            if (neighbor == null) {
                neighbor = point;
            }

            if (point.distanceSquaredTo(p) < neighbor.distanceSquaredTo(p)) {
                neighbor = point;
            }
        }

        return neighbor;
    }
}
