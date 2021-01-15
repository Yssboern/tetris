package com.epam.prejap.tetris.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Holds current number of points and awards new one for each block acquired.
 *
 * @implNote This class evaluates {@link RefereeObserver#levelChanged()} (int)} method
 * when player gets to the next level.
 */

public class Referee implements PlayfieldObserver {

    private static final int LEVEL_POINTS = 10;

    private final List<RefereeObserver> observers = new ArrayList<>();
    private int currentScore = 0;

    @Override
    public void newBlockAppeared() {
        awardPoints();
        if (isNextLevel())
            levelChanged();
    }

    private void awardPoints() {
        currentScore += 1;
    }
    
    private boolean isNextLevel() {
        return currentScore % LEVEL_POINTS == 0;
    }

    private void levelChanged() {
        observers.forEach(RefereeObserver::levelChanged);
    }

    public int currentScore() {
        return currentScore;
    }
    
    public void addObserver(RefereeObserver observer) {
        observers.add(Objects.requireNonNull(observer, "observer must not be null"));
    }

    @Override
    public String toString() {
        return "Score: " + currentScore;
    }
}
