package com.epam.prejap.tetris.game;

/**
 * Observes the changes taking place on the Playfield. When a new block appears,
 * all observers are notified of the change. The user of this interface can provide
 * proper steps according to this change.
 */
public interface PlayfieldObserver {

    void newBlockAppeared();

}
