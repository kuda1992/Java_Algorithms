
package com.pluralsight.algo.Puzzle;

import com.pluralsight.algo.Priority.MinPQ;
import com.pluralsight.algo.algs4.In;
import com.pluralsight.algo.algs4.StdOut;

/*
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;*/

import java.util.Deque;
import java.util.LinkedList;

public class Solver {

    private final boolean isSolvable;
    private final MinPQ<SearchNode> minPQ;
    private SearchNode solutionNode;

    public Solver(Board initial) {
        solutionNode = null;
        minPQ = new MinPQ<>();
        minPQ.insert(new SearchNode(initial, 0, null));

        while (true) {

            SearchNode currentNode = minPQ.delMin();
            Board currentBoard = currentNode.getBoard();

            if (currentBoard.isGoal()) {
                isSolvable = true;
                solutionNode = currentNode;
                break;
            }

            if (currentBoard.hamming() == 2 && currentBoard.twin().isGoal()) {
                isSolvable = false;
                break;
            }

            int moves = currentNode.getMoves();
            Board previousBoard = moves > 0 ? currentNode.getPrev().getBoard() : null;

            for (Board nextBoard : currentBoard.neighbors()) {
                if (previousBoard != null && nextBoard.equals(previousBoard)) continue;
                minPQ.insert(new SearchNode(nextBoard, moves + 1, currentNode));
            }
        }
    }

    private class SearchNode implements Comparable<SearchNode> {

        private final SearchNode prev;
        private final Board board;
        private final int moves;

        SearchNode(Board board, int moves, SearchNode prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
        }

        @Override
        public int compareTo(SearchNode s) {
            return this.priority() - s.priority();
        }

        public int priority() {
            return board.manhattan() + moves;
        }

        public SearchNode getPrev() {
            return prev;
        }

        public Board getBoard() {
            return board;
        }

        public int getMoves() {
            return moves;
        }
    }


    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return isSolvable() ? solutionNode.getMoves() : -1;
    }

    public Iterable<Board> solution() {
        if (!isSolvable) {
            return null;
        }
        Deque<Board> solution = new LinkedList<>();
        SearchNode node = solutionNode;
        while (node != null) {
            solution.addFirst(node.getBoard());
            node = node.getPrev();
        }
        return solution;
    }


    public static void main(String[] args) {

        // create initial board from file
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
