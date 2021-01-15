package com.epam.prejap.tetris.block.blocks;

public abstract class TestBlock {
    final Object[][] coordinates;
    final int rows;
    final int cols;

    public TestBlock(Object[][] points, int rows, int cols) {
        this.coordinates = points;
        this.rows = rows;
        this.cols = cols;
    }

    public Object[][] getCoordinates() {
        return coordinates;
}

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }

}

