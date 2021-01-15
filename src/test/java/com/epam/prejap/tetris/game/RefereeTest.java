package com.epam.prejap.tetris.game;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

@Test(groups = "Score")
public class RefereeTest {

    @Test
    public void shallIncreaseScoreWhenNewBlockAppears() {
        //given
        var referee = new Referee();
        referee.newBlockAppeared();
        int expectedScore = 1;

        //when
        int actualScore = referee.currentScore();

        //then
        assertEquals(actualScore, expectedScore);
    }

    @Test
    public void shallThrowNullPointerExceptionAddingNullObserver() {
        //given
        var score = new Referee();

        //when

        //then
        assertThrows(NullPointerException.class, () -> score.addObserver(null));
    }

    @Test(groups = "Score")
    public void scoreShouldIncreaseWhenNewBlockAppears() {

    }
}
