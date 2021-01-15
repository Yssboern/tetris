package com.epam.prejap.tetris.block.blocks;

/**
 * O-shaped block implementation of the {@link TestBlock} abstraction class.

 * @implNote This class implements static 2d array which stores information about OBlock coordinates (1 and 2 columns) and
 * the required value of the variable at these coordinates (3 column).
 *
 * @author Nika Avramchuk
 * @see TestBlock
 */
public class TestOBlock extends TestBlock {
    private static final int ROWS = 2;
    private static final int COLS = 2;

    private static final Object[][] blockCoordinates = {
            {0, 0, 1},
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 1},
    };

    public TestOBlock() {
        super(blockCoordinates, ROWS, COLS);
    }
}
