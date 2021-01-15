package com.epam.prejap.tetris.game;

import java.util.concurrent.TimeUnit;

/**
 * Responsible for waiting some given time till the next render.
 *
 * @implNote Gets {@link #milliseconds} filed as a constructor argument
 */

public class Waiter implements RefereeObserver {

    private static final int DECREASE_WAITING_TIME = 100;

    private int milliseconds;

    public Waiter(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public void waitForIt() {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException ignore) {
        }
    }

    @Override
    public void levelChanged() {
        decreaseWaitingTime();
    }

    private void decreaseWaitingTime() {
        if (haveEnoughTime()) {
            milliseconds -= DECREASE_WAITING_TIME;
        }
    }

    private boolean haveEnoughTime() {
        return milliseconds - DECREASE_WAITING_TIME > 0;
    }

    public int milliseconds() {
        return milliseconds;
    }
}
