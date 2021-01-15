package com.epam.prejap.tetris.game;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(groups = "Waiter")
public class WaiterTest {

    @Test
    public void shallDecreaseWaitingTimeWhenLevelChanged() {
        //given
        var waiter = new Waiter(500);
        waiter.levelChanged();

        //when
        int actualMills = waiter.milliseconds();

        //then
        assertEquals(actualMills, 400);
    }

    @Test
    public void shallNotModifyWaitingTimeWhenLevelChanged() {
        //given
        int initialMills = 100;
        var waiter = new Waiter(initialMills);
        waiter.levelChanged();

        //when
        int actualMills = waiter.milliseconds();

        //then
        assertEquals(actualMills, initialMills);
    }

}
