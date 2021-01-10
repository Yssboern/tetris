package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.Block;

import java.io.PrintStream;
import java.time.Duration;

public class Printer {


    private static final String TIME_FORMAT = "%02d:%02d:%02d";
    final PrintStream out;
    private final Timer timer;


    // Width of the side panel for hint block
    private final int PANEL_WIDTH = 6;

    public Printer(PrintStream out, Timer timer) {
        this.out = out;
        this.timer = timer;
    }


    //block to be displayed as hint of next active block
    private Block hintBlock;

    /**
     * @param hintBlock provided by PlayField
     */
    void displayHintblock(Block hintBlock) {
        this.hintBlock = hintBlock;
    }

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
            sidePanel(row);
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
     *
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
     * Prints # row of sidePanel with next block hint
     *
     * @param row - number od row being printed
     */
   private void sidePanel(int row) {
        startRow();
        if (row <= hintBlock.rows()) {
            if (row == 0) out.print("NEXT: ");
            else {
                for (int column = 0; column < PANEL_WIDTH; column++) {
                    if (column < hintBlock.cols()) print(hintBlock.dotAt(row - 1, column));
                    else out.print(" ");
                }
            }
        } else out.print(" ".repeat(PANEL_WIDTH));
        endRow();
    }

}
