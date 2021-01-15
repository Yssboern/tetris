package com.epam.prejap.tetris.block.blocks;

/**
 * L-shaped block implementation of the {@link TestBlock} abstraction class.

 * @implNote This class implements static 2d array which stores information about LBlock coordinates (1 and 2 columns) and
 * the required value of the variable at these coordinates (3 column).
 *
 * @author Nika Avramchuk
 * @see TestBlock
 */
public class TestLBlock extends TestBlock {
    private static final int ROWS = 3;
    private static final int COLS = 2;

    private static final Object[][] blockCoordinates = {
            {0, 0, 1},
            {0, 1, 0},
            {1, 0, 1},
            {1, 1, 0},
            {2, 0, 1},
            {2, 1, 1}
    };

    public TestLBlock() {
        super(blockCoordinates, ROWS, COLS);
    }


}
