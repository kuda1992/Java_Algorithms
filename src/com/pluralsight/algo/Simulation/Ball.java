package com.pluralsight.algo.Simulation;

import com.pluralsight.algo.algs4.StdDraw;
import com.pluralsight.algo.algs4.StdRandom;

public class Ball {

    private double rx, ry;
    private double vx, vy;
    private final double radius;

    public Ball() {
        rx = 0.0;
        ry = 0.0;

        vx = StdRandom.uniform(-0.015, 0.015);
        vy = StdRandom.uniform(-0.015, 0.015);
        radius = StdRandom.uniform(0.025, 0.075);

    }

    private void bounceOfVerticalWall() {
        vx = -vx;
    }

    private void bounceOfHorizontalWall() {
        vy = -vy;
    }

    private boolean ballHasHitHorizontalWall() {
        return Math.abs(rx + vx) + radius > 1.0;
    }

    private boolean ballHasHitVerticalWall() {
        return Math.abs(ry + vy) + radius > 1.0;
    }


    public void move() {

        if (ballHasHitHorizontalWall()) bounceOfVerticalWall();
        if (ballHasHitVerticalWall()) bounceOfHorizontalWall();
        rx = rx + vx;
        ry = ry + vy;
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }


    public static void main(String[] args) {

        // create and initialize two balls
        Ball b1 = new Ball();
        Ball b2 = new Ball();

        // animate them
        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);
        StdDraw.enableDoubleBuffering();

        while (true) {
            StdDraw.clear(StdDraw.GRAY);
            StdDraw.setPenColor(StdDraw.BLACK);
            b1.move();
            b2.move();
            b1.draw();
            b2.draw();
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}
