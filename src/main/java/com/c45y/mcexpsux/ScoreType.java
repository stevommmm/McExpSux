package com.c45y.mcexpsux;

/**
 *
 * @author c45y
 */
public enum ScoreType {

    MOB_KILL(4),
    MOB_KILL_GIANT(120),
    MOB_KILL_GHAST(7),
    PLAYER_KILL(5),
    PLAYER_JOIN(1),
    BLOCK_BREAK(1),
    BLOCK_BREAK_DIAMOND(4),
    BLOCK_BREAK_GOLD(5),
    BLOCK_BREAK_IRON(3),
    BLOCK_BREAK_COAL(1),
    BLOCK_BREAK_EMERALD(4),
    BLOCK_PLACE(3);
    private final int points;

    ScoreType(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }
}