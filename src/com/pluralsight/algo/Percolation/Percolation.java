package com.pluralsight.algo.Percolation;


import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class Percolation {

    private int size;
    private Site[][] grid;
    private WeightedQuickUnionUF weightedQuickUnion;

    public Percolation(int n) {
        int value = 0;
        size = n;
        grid = new Site[n][n];
        weightedQuickUnion = new WeightedQuickUnionUF(n * n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = new Site(i, j, false, value);
                value++;
            }
        }
        System.out.println("The size of the array is " + size);
    }

    private boolean isLeftSiteOpen(Site site) {
        System.out.println("The left position of site is x:" + site.getX() + " y:" + site.getY());
        Site leftSite = new Site(site.getX() - 1, site.getY(), site.isOpen(), site.getValue());
        if (site.getX() - 1 >= 0) {
            Position position = findPosition(leftSite.getX(), leftSite.getY());
            return grid[position.getX()][position.getY()].isOpen();
        }
        return false;
    }

    private boolean isRighSiteOpen(Site site) {
        System.out.println("The right position of site is x:" + site.getX() + " y:" + site.getY());
        Site rightSite = new Site(site.getX() + 1, site.getY(), site.isOpen(), site.getValue());
        if (site.getX() + 1 < this.size) {
            Position position = findPosition(rightSite.getX(), rightSite.getY());
            return grid[position.getX()][position.getY()].isOpen();
        }
        return false;
    }

    private boolean isTopSiteOpen(Site site) {
        System.out.println("The top position of site is x:" + site.getX() + " y:" + site.getY());
        Site topSite = new Site(site.getX(), site.getY() - 1, site.isOpen(), site.getValue());
        if (site.getY() - 1 >= 0) {
            Position position = findPosition(topSite.getX(), topSite.getY());
            return grid[position.getX()][position.getY()].isOpen();
        }
        return false;
    }


    private boolean isBottomSiteOpen(Site site) {
        System.out.println("The bottom position of site is x:" + site.getX() + " y:" + site.getY());
        Site bottomSite = new Site(site.getX(), site.getY() + 1, site.isOpen(), site.getValue());
        if (site.getY() + 1 < this.size) {
            Position position = findPosition(bottomSite.getX(), bottomSite.getY());
            return grid[position.getX()][position.getY()].isOpen();
        }
        return false;
    }

    private Position findPosition(int x, int y) throws NoSuchElementException {

        System.out.println("Finding position x: " + x + ", y: " + y);

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                final Site siteInGrid = grid[i][j];
                if (siteInGrid.getX() == x && siteInGrid.getY() == y) {
                    return new Position(i, j);
                }
            }
        }
        throw new NoSuchElementException();
    }

    private int getSiteValue(int x, int y) {
        Position position = findPosition(x, y);
        final Site site = grid[position.getX()][position.getY()];
        return site.getValue();
    }

    public void open(int row, int col) {

        System.out.println("Opening row: " + row + ", y: " + col);

        final Position position = findPosition(row - 1, col - 1);
        System.out.println("Position x: " + position.getX() + ", y: " + position.getY());
        final Site site = grid[position.getX()][position.getY()];
        site.setOpen(true);
        grid[position.getX()][position.getY()] = site;

        System.out.println("Opening x: " + site.getX() + ", y: " + site.getY());

        if (isLeftSiteOpen(site)) {
            System.out.println("joining the site with the left site");
            weightedQuickUnion.union(site.getValue(), getSiteValue(site.getX() - 1, site.getX()));
        }
        if (isRighSiteOpen(site)) {
            System.out.println("joining the site with the right site");
            weightedQuickUnion.union(site.getValue(), getSiteValue(site.getX() + 1, site.getX()));
        }

        if (isTopSiteOpen(site)) {
            System.out.println("joining the site with the top site");
            weightedQuickUnion.union(site.getValue(), getSiteValue(site.getY(), site.getY() - 1));
        }

        if (isBottomSiteOpen(site)) {
            System.out.println("joining the site with the bottom site");
            weightedQuickUnion.union(site.getValue(), getSiteValue(site.getY(), site.getY() + 1));
        }

    }

    public int generateRandom(int upperLimit) {
        final Random random;
        random = new Random();
        return random.nextInt(upperLimit);
    }

    public boolean isOpen(int row, int col) {
        final Position position = findPosition(row - 1, col - 1);
        final Site site = grid[position.getX()][position.getY()];
        return site.isOpen();
    }

    public boolean isFull(int row, int col) throws IndexOutOfBoundsException {
        //if (row <= 0 || row > this.size || col <= 0 || col > this.size) throw new IndexOutOfBoundsException("Please enter a valid index");
        if (isOpen(row, col)) {
            for (int i = 0; i < size; i++) {
                if (weightedQuickUnion.connected(getSiteValue(row - 1, col - 1), i)) return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                final Site site = grid[i][j];
                if (site.isOpen()) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean percolates() {
        for (int i = 0; i < this.size; i++) {
            for (int j = (this.size * this.size - this.size); j < this.size * this.size; j++) {
                if (weightedQuickUnion.connected(i, j)) return true;
            }
        }
        return false;
    }

    public Position openRandomSite() {
        int positionX = generateRandom(this.size);
        int positionY = generateRandom(this.size);
        Position position = new Position(positionX, positionY);
        open(positionX, positionY);

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                final Site site = grid[i][j];
                if (!site.isOpen() && site.getY() == position.getY() && site.getX() == position.getX()) {
                    site.setOpen(true);
                }
            }
        }
        return position;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(grid);
    }

}
