import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {
    @Test
    void testTakeDamage() {
        Player player = new Player(25, 20, 100, 5, 10);
        player.takeDamage(30);
        assertEquals(70, player.getHealth());
    }

    @Test
    void testHeal() {
        Player player = new Player(25, 20, 100, 5, 10);
        player.takeDamage(40);
        player.selfHeal();
        assertTrue(player.getHealth() > 60 && player.getHealth() <= 100);
    }

    @Test
    void testInvalidCreation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Player(-1, 20, 100, 5, 10);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Player(25, 20, 0, 5, 10);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Player(25, 20, 100, 10, 5);
        });
    }

    @Test
    void testHealLimit() {
        Player player = new Player(25, 20, 100, 5, 10);
        player.takeDamage(40);
        for (int i = 0; i < 5; i++) {
            player.selfHeal();
        }
        assertTrue(player.getHealth() > 60 && player.getHealth() <= 100);
    }

    @Test
    void testGetAttack() {
        Creature creature = new Creature(10, 5, 50, 1, 6);
        int result = creature.getAttack();
        assertEquals(10, result, "The attack value should be 10");
    }

    @Test
    void testGetDefense() {
        Creature creature = new Creature(10, 15, 50, 1, 6);
        int result = creature.getDefense();
        assertEquals(15, result, "The defense value should be 15");
    }
}