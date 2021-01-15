package com.epam.prejap.tetris.block;

import com.epam.prejap.tetris.block.blocks.TestBlock;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Each test from this class will be performed for each Block type provided in the {@link BlockTestFactory}.
 *
 * @author Nika Avramchuk
 * @see BlockTestFactory
 */
@Test(groups = "BlockShape")
public class BlockTest {
    private final Block childBlock;
    private final TestBlock testBlock;


    public BlockTest(Block childBlock, TestBlock testBlock) {
        this.childBlock = childBlock;
        this.testBlock = testBlock;
    }


    @Test
    public void shouldCreateBlockWithProperDimensions() {
        //given
        int expectedRows = testBlock.rows();
        int expectedColumns = testBlock.cols();

        //when
        int actualRows = childBlock.rows;
        int actualColumns = childBlock.cols;

        //then
        assertEquals(actualRows, expectedRows, String.format("The expected number of rows for %s should be %d, but was %d%n", childBlock.getClass().getSimpleName(), expectedRows, actualRows));
        assertEquals(actualColumns, expectedColumns, String.format("The expected number of columns for %s should be %d, but was %d", childBlock.getClass().getSimpleName(), expectedColumns, actualColumns));
    }

    @Test(dataProvider = "blockPoints", dependsOnMethods = "testDataProviders")
    public void shouldCreateBlockWithProperDots(int row, int col, int expectedValue) {
        //when
        byte actualValue = childBlock.dotAt(row, col);

        //then
        assertEquals(actualValue, expectedValue, String.format("Should created %s with correct shaped dots, but did not", childBlock.getClass().getSimpleName()));
    }

    @DataProvider()
    public Object[] blockPoints() {
        return testBlock.getCoordinates();
    }

    /**
     * Taking into account that all subsequent tests depend on data providers {@link TestBlock},
     * this method tests the correctness of the created coordinates.
     */
    @Test()
    public void testDataProviders() {
        //given
        int expectedValue = testBlock.cols()* testBlock.rows();

        //when
        int actualValue = testBlock.getCoordinates().length;

        //then
        assertEquals(actualValue, expectedValue, String.format("Should created %s with correct array of points, but did not", testBlock.getClass().getSimpleName()));
    }



}
