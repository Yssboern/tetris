package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.Block;
import com.epam.prejap.tetris.block.BlockFeed;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.assertTrue;

@Test(groups = "Timer")
public class PrinterTest {

    private BlockFeed feed = new BlockFeed();
    private Block hintblock = feed.nextBlock();

    private ByteArrayOutputStream bos;
    private final byte[][] emptyGrid = new byte[][]{new byte[]{}};
    private final byte[][] grid5x1 = new byte[5][1];

    @BeforeMethod
    public void setUp() {
        bos = new ByteArrayOutputStream();
    }

    @DataProvider
    public Object[][] validTimerDurations() {
        return new Object[][]{{500, 14, "Time: 00:00:07"},
                {1000, 167, "Time: 00:02:47"},
                {1000, 4224, "Time: 01:10:24"},
                {3600000, 25, "Time: 25:00:00"},
                {1000, 25, "Time: 00:00:25"},
                {1000, 84, "Time: 00:01:24"},
                {1000, 5840, "Time: 01:37:20"},
                {3600000, 26, "Time: 26:00:00"}};
    }

    @Test(dataProvider = "validTimerDurations")
    public void drawShouldPrintValidHeader(int tickDurationInMillis, int cycles, String message) {
        // given
        Timer timer = new Timer(tickDurationInMillis);
        Printer printer = Mockito.spy(new Printer(new PrintStream(bos), timer));
        printer.displayHintblock(hintblock);
        for (int i = 0; i < cycles; i++) {
            timer.tick();
        }
        // when
        printer.draw(emptyGrid);
        // then
        Mockito.verify(printer).header();
        assertTrue(bos.toString().contains(message));
    }

    @Test
    public void testSidePanel() {
        //given
        Timer timer = new Timer(1);
        Printer printer = Mockito.spy(new Printer(new PrintStream(bos), timer));
        printer.displayHintblock(hintblock);

        String lBlock = "| |NEXT: |" + System.lineSeparator() +
                "| |#     |" + System.lineSeparator() +
                "| |#     |" + System.lineSeparator() +
                "| |##    |" + System.lineSeparator() +
                "| |      |";


        String oBlock = "| |NEXT: |" + System.lineSeparator() +
                "| |##    |" + System.lineSeparator() +
                "| |##    |" + System.lineSeparator() +
                "| |      |" + System.lineSeparator() +
                "| |      |";

        String iBlock = "| |NEXT: |" + System.lineSeparator() +
                "| |#     |" + System.lineSeparator() +
                "| |#     |" + System.lineSeparator() +
                "| |#     |" + System.lineSeparator() +
                "| |#     |";

        String jBlock = "| |NEXT: |" + System.lineSeparator() +
                "| | #    |" + System.lineSeparator() +
                "| | #    |" + System.lineSeparator() +
                "| |##    |" + System.lineSeparator() +
                "| |      |";
        //when
        printer.draw(grid5x1);
        //then
        assertTrue(bos.toString().contains(lBlock) || bos.toString().contains(oBlock) || bos.toString().contains(iBlock) || bos.toString().contains(jBlock));

    }

}
