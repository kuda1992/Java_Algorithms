package com.pluralsight.algo.KdTrees;

import com.pluralsight.algo.algs4.StdDraw;

import java.util.LinkedList;
import java.util.List;

public class KdTree {

    private enum Separator {VERTICAL, HORIZONTAL}

    private Node root;
    private int size;

    private static class Node {

        private final Separator separator;
        private final RectHV rect;
        private final Point2D p;
        private Node leftBottom;
        private Node rightTop;

        Node(Point2D p, Separator separator, RectHV rect) {
            this.p = p;
            this.separator = separator;
            this.rect = rect;
        }

        Separator nextSeparator() {
            return separator == Separator.VERTICAL ? Separator.HORIZONTAL : Separator.VERTICAL;
        }

        public RectHV rectLB() {
            return separator == Separator.VERTICAL
                    ? new RectHV(rect.xmin(), rect.ymin(), p.x(), rect.ymax())
                    : new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), p.y());
        }

        public RectHV rectRT() {
            return separator == Separator.VERTICAL
                    ? new RectHV(p.x(), rect.ymin(), rect.xmax(), rect.ymax())
                    : new RectHV(rect.xmin(), p.y(), rect.xmax(), rect.ymax());
        }

        public boolean isRightOrTopOf(Point2D q) {
            return (separator == Separator.HORIZONTAL && p.y() > q.y())
                    || (separator == Separator.VERTICAL && p.x() > q.x());
        }
    }

    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }


    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) {
            root = new Node(p, Separator.VERTICAL, new RectHV(0, 0, 1, 1));
            size++;
            return;
        }

        // find node position for insertion
        Node prev;
        Node curr = root;
        do {
            if (curr.p.equals(p)) {
                return;
            }
            prev = curr;
            curr = curr.isRightOrTopOf(p) ? curr.leftBottom : curr.rightTop;
        } while (curr != null);

        // prepare new node and insert
        if (prev.isRightOrTopOf(p)) {
            prev.leftBottom = new Node(p, prev.nextSeparator(), prev.rectLB());
        } else {
            prev.rightTop = new Node(p, prev.nextSeparator(), prev.rectRT());
        }
        size++;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        Node node = root;
        while (node != null) {
            if (node.p.equals(p)) {
                return true;
            }
            node = node.isRightOrTopOf(p) ? node.leftBottom : node.rightTop;
        }
        return false;
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> results = new LinkedList<>();
        addAll(root, rect, results);
        return results;
    }

    private void addAll(Node node, RectHV rect, List<Point2D> results) {
        if (node == null) {
            return;
        }
        if (rect.contains(node.p)) {
            results.add(node.p);
            addAll(node.leftBottom, rect, results);
            addAll(node.rightTop, rect, results);
            return;
        }
        if (node.isRightOrTopOf(new Point2D(rect.xmin(), rect.ymin()))) {
            addAll(node.leftBottom, rect, results);
        }
        if (!node.isRightOrTopOf(new Point2D(rect.xmax(), rect.ymax()))) {
            addAll(node.rightTop, rect, results);
        }
    }

    public void draw() {
        draw(root, Separator.VERTICAL);
    }

    private void draw(Node root, Separator separator) {
        if (separator == Separator.VERTICAL) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(root.p.x(), root.rect.ymin(), root.p.x(), root.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(root.rect.xmin(), root.p.y(), root.rect.xmax(), root.p.y());
        }

        if (root.leftBottom != null) {
            draw(root.leftBottom, Separator.HORIZONTAL);
        }

        if (root.rightTop != null) {
            draw(root.rightTop, Separator.VERTICAL);
        }

        // draw point last to be on top of line
        StdDraw.setPenColor(StdDraw.BLACK);
        root.p.draw();
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return isEmpty() ? null : nearest(p, root.p, root);
    }

    private Point2D nearest(Point2D target, Point2D closest, Node node) {
        if (node == null) {
            return closest;
        }

        double closestDist = closest.distanceSquaredTo(target);
        if (node.rect.distanceTo(target) < closestDist) {
            double nodeDist = node.p.distanceSquaredTo(target);
            if (nodeDist < closestDist) {
                closest = node.p;
            }
            if (node.isRightOrTopOf(target)) {
                closest = nearest(target, closest, node.leftBottom);
                closest = nearest(target, closest, node.rightTop);
            } else {
                closest = nearest(target, closest, node.rightTop);
                closest = nearest(target, closest, node.leftBottom);
            }
        }
        return closest;
    }
}
