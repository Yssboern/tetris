package com.epam.prejap.tetris.game;

/**
 * Observes score changes. When score reaches new level,
 * observable class notifies about level change all observers.
 */

public interface RefereeObserver {
    void levelChanged();
}
