package com.aor.ghostrumble.play.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class HauntedHouseTest {

    private int randomWidth;
    private int randomHeight;
    private HauntedHouse house;

    @Before
    public void init() {
        Random random = new Random();
        this.randomWidth = random.nextInt(100 - 10) + 10;
        this.randomHeight = random.nextInt(40 - 10) + 10;
        this.house = new HauntedHouse(randomWidth, randomHeight);
    }

    @Test
    public void testInitWidth() {
        assertEquals(randomWidth, house.getWidth());
    }

    @Test
    public void testInitHeight() { assertEquals(randomHeight, house.getHeight()); }

    @Test
    public void testInitPlayer() { assertNotEquals(null, house.getPlayer()); }

    @Test
    public void testInitPlayerX() {
        assertEquals(randomWidth / 2, house.getPlayer().getPosition().getX());
    }

    @Test
    public void testInitPlayerY() {
        assertEquals(randomHeight / 2, house.getPlayer().getPosition().getY());
    }

    @Test
    public void testInitWalls() {
        assertEquals(2 * randomWidth + 2 * (randomHeight - 7), house.getWalls().size());
    }

    @Test
    public void testWallsYPos() {
        assertEquals(randomHeight - 1, house.getWalls().get(1).getPosition().getY());
    }

    @Test
    public void testWallsXPos() {
        assertEquals(randomWidth - 1, house.getWalls().get(2 * randomWidth + 1).getPosition().getX());
    }

    @Test
    public void testInitEnemies() {
        assertTrue(house.getEnemies().isEmpty());
    }

    @Test
    public void testAddEnemies() {
        house.addEnemy(new Zombie(10, 10));
        house.addEnemy(new Poltergeist(10, 20));
        house.addEnemy(new Ghost(40, 30));

        assertEquals(3, house.getEnemies().size());
    }

    @Test
    public void testAddEnemiesObs() {
        house.addEnemy(new Zombie(10, 10));
        house.addEnemy(new Poltergeist(10, 20));
        house.addEnemy(new Ghost(40, 30));

        assertEquals(3, house.getPlayer().getObservers().size());
    }

    @Test
    public void testAddBullet() {
        house.addBullet(new HorizontalBullet(0, 0, 1));
        assertEquals(1, house.getBullets().size());
    }

    @Test
    public void testMonsterInPositionTrue() {
        house.addEnemy(new Zombie(10, 10));
        assertTrue(house.hitsEnemies(new Position(10, 10)));
    }

    @Test
    public void testMonsterInPositionFalse() {
        house.addEnemy(new Zombie(20, 10));
        assertFalse(house.hitsEnemies(new Position(10, 10)));
    }

    @Test
    public void testWallInPositionTrue() {
        // always a wall in this position
        assertTrue(house.hitsWall(new Position(house.getWidth() - 1, 6)));
    }

    @Test
    public void testWallInPositionFalse() {
        // never a wall in this position
        assertFalse(house.hitsWall(new Position(house.getWidth(), house.getHeight())));
    }

    @Test
    public void testLastSpawned() {
        long value = 1000;
        house.setLastSpawned(value);
        assertEquals(value, house.getLastSpawned());
    }

    @Test
    public void testLastIncrementedScore() {
        long value = 1000;
        house.setLastIncrementedScore(value);
        assertEquals(value, house.getLastIncrementedScore());
    }

    @Test
    public void testScore() {
        assertEquals(0, house.getScore());
    }

    @Test
    public void testIncreaseScore() {
        house.increaseScore(20);
        house.increaseScore(30);
        assertEquals(50, house.getScore());
    }

}
