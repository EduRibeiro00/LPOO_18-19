package com.aor.ghostrumble;


public class Player extends Movable {
    private int currentHealth;
    private int maxHealth;

    public Player() { this(10, 10); }
    public Player(int x, int y) {
        super(x, y);
        currentHealth = 2;
        maxHealth = 10;
    }

    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }

}
