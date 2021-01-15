package com.epam.prejap.tetris.block.blocks;

/**
 * I-shaped block implementation of the {@link TestBlock} abstraction class.

 * @implNote This class implements static 2d array which stores information about IBlock coordinates (1 and 2 columns) and
 * the required value of the variable at these coordinates (3 column).
 *
 * @author Nika Avramchuk
 * @see TestBlock
 */
public class TestIBlock extends TestBlock {
    private static final int ROWS = 4;
    private static final int COLS = 1;

    private static final Object[][] blockCoordinates = {
            {0, 0, 1},
            {1, 0, 1},
            {2, 0, 1},
            {3, 0, 1},
    };

    public TestIBlock() {
        super(blockCoordinates, ROWS, COLS);
    }

}
