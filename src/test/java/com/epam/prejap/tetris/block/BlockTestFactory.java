package com.epam.prejap.tetris.block;

import com.epam.prejap.tetris.block.blocks.*;
import com.epam.prejap.tetris.block.blocks.TestSBlock;
import org.testng.annotations.Factory;

/**
 * In order to avoid duplications, this class provides {@link BlockTest} with concrete subclasses of the {@link Block} class.
 * This means that each method in {@link BlockTest} will check each of the {@link Block} subclasses.
 *
 * In order to add tests for another implementation of {@link Block}, you need to
 * 1) create TestYourBlock that extends {@link TestBlock} in com.epam.prejap.tetris.block/
 * 2) add new instance of TestBlock to {@link #factoryMethod()} as in example below:
 *      {@code new BlockTest(<new_block_subclass>, <new_test_block_subclass>)}
 *
 * @author Nika Avramchuk
 * @see BlockTest
 * @see TestBlock
 */
public class BlockTestFactory {

    @Factory()
    public Object[] factoryMethod() {
        return new Object[]{
                new BlockTest(new OBlock(), new TestOBlock()),
                new BlockTest(new IBlock(), new TestIBlock()),
                new BlockTest(new LBlock(), new TestLBlock()),
                new BlockTest(new ZBlock(), new TestZBlock()),
                new BlockTest(new JBlock(), new TestJBlock()),
                new BlockTest(new SBlock(), new TestSBlock())
        };
    }







}

