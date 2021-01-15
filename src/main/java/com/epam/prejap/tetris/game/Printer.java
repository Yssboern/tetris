package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.Block;
import com.epam.prejap.tetris.block.Color;

import java.io.PrintStream;
import java.time.Duration;

public class Printer {

    private static final String TIME_FORMAT = "%02d:%02d:%02d";
    private static final String BLOCK_MARK = "#";
    final PrintStream out;
    private final Timer timer;
    private final Referee referee;
    private Block hintBlock;

    void displayHintblock(Block hintBlock) {
        this.hintBlock = hintBlock;
    }

    public Printer(PrintStream out, Timer timer, Referee referee) {
        this.out = out;
        this.timer = timer;
        this.referee = referee;
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

    /**
     * Print block mark with appropriate color, leave
     * uncoloured empty string in case of zero in game's grid
     *
     * @param colorId id of specific Color enumeration constant
     * @see Color
     * @since 0.8
     */
    void print(byte colorId) {
        String colored = Color.of(colorId).applyFor(BLOCK_MARK);
        out.format(colorId == 0 ? " " : colored);
    }

    void startRow() {
        out.print("|");
    }

    void endRow() {
        out.println("|");
    }

    void border(int width) {
        out.println("+" + "-".repeat(width) + "+" + "-".repeat(PANEL_WIDTH) + "+");
    }

    void printScore() {
        out.println(referee.toString());
    }

    /**
     * Prints elapsed time in hh:mm:ss format
     */
    void header() {
        Duration duration = timer.calculateElapsedDuration();
        String elapsedTime = String.format(TIME_FORMAT, duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
        out.println("Time: " + elapsedTime);
    }

    int PANEL_WIDTH = 6;

    /**
     * Prints # row of sidePanel with next block hint
     *
     * @param row number od row being printed
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
