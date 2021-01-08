package com.epam.prejap.tetris.game;

import java.io.PrintStream;
import java.time.Duration;

public class Printer {

    /**
     * Width of the sidepanel
     */
    private final int PANEL_WIDTH = 6;
    private static final String TIME_FORMAT = "%02d:%02d:%02d";
    final PrintStream out;
    private final Timer timer;

    public Printer(PrintStream out, Timer timer) {
        this.out = out;
        this.timer = timer;
    }

    byte[][] hintGrid={
            {1, 0},
            {1, 0},
            {1, 1},
    };

    void draw(byte[][] grid) {
        clear();
        header();
        border(grid[0].length);

        int row = 0;
        for (; row < grid.length; row++) {
            startRow();
            for (byte aByte : grid[row]) {
                print(aByte);
            }
            sidePanel(row, hintGrid);
        }
        border(grid[0].length);
    }

    void clear() {
        out.print("\u001b[2J\u001b[H");
    }

    void print(byte dot) {
        out.format(dot == 0 ? " " : "#");
    }

    void startRow() {
        out.print("|");
    }

    void endRow() {
        out.println("|");
    }

    /**
     * Prints top/bottom border of playfield
     * @param width of the playfield
     */
    void border(int width) {
        out.println("+" + "-".repeat(width) + "+" + "-".repeat(PANEL_WIDTH) + "+");
    }

    /**
     * Prints elapsed time in hh:mm:ss format.
     */
    void header() {
        Duration duration = timer.calculateElapsedDuration();
        String elapsedTime = String.format(TIME_FORMAT, duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
        out.println("Time: " + elapsedTime);
    }

    /**
     * prints # row of sidePanel with next block hint
     * @param row - number od row being printed
     * @param hintGrid - image of hinted block
     */
    void sidePanel(int row, byte[][] hintGrid) {
        startRow();
        if (row <= hintGrid.length) {
            if (row == 0) out.print("NEXT: ");
            else {
                for (int column = 0; column < PANEL_WIDTH; column++) {
                    if (column < hintGrid[0].length) print(hintGrid[row - 1][column]);
                    else out.print(" ");
                }
            }
        } else out.print(" ".repeat(PANEL_WIDTH));
        endRow();
    }

}
