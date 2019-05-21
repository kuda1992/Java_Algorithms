package com.pluralsight.algo.Simulation;

import com.pluralsight.algo.algs4.StdDraw;

public class BouncingBalls {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Ball[] balls = new Ball[N];

        for(int i = 0; i < N; i++)
            balls[i] = new Ball();

        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);
        StdDraw.enableDoubleBuffering();

        while(true) {

            StdDraw.clear();

            for(int i = 0; i < N; i++) {
                balls[i].move();
                balls[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(20);
        }
    }

}
