
import java.util.Random;

class Creature {
    private final int attack;
    private final int defense;
    private int health;
    private final int damageMin;
    private final int damageMax;
    private final int maxHealth;
    private final Random random = new Random();

    public Creature(int attack, int defense, int health, int damageMin, int damageMax) {
        validateParameters(attack, defense, health, damageMin, damageMax);
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.maxHealth = health;
    }

    private void validateParameters(int attack, int defense, int health, int damageMin, int damageMax) {
        if (attack < 1 || attack > 30 || defense < 1 || defense > 30)
            throw new IllegalArgumentException("Attack and defense must be in the range from 1 to 30");
        if (health <= 0) throw new IllegalArgumentException("Health must be a positive number");
        if (damageMin < 1 || damageMax < damageMin)
            throw new IllegalArgumentException("Damage range should be natural numbers M-N");
    }

    public void attack(Creature target) {
        int attackModifier = Math.max(attack - target.defense + 1, 1);
        boolean successfulAttack = false;

        for (int i = 0; i < attackModifier; i++) {
            int roll = random.nextInt(6) + 1;
            if (roll == 5 || roll == 6) {
                successfulAttack = true;
                break;
            }
        }

        if (successfulAttack) {
            int damage = random.nextInt((damageMax - damageMin) + 1) + damageMin;
            target.takeDamage(damage);
        }
    }

    void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    void heal() {
        int maxHeal = (int) (maxHealth * 0.3);
        if (maxHeal <= 0) return;
        int healAmount = random.nextInt(maxHeal) + 1;
        health += healAmount;
        if (health > maxHealth) health = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}

class Player extends Creature {
    private int healCount = 0;

    public Player(int attack, int defense, int health, int damageMin, int damageMax) {
        super(attack, defense, health, damageMin, damageMax);
    }

    public void selfHeal() {
        if (getHealth() > 0 && healCount < 4) {
            heal();
            healCount++;
        }
    }
}

class Monster extends Creature {
    public Monster(int attack, int defense, int health, int damageMin, int damageMax) {
        super(attack, defense, health, damageMin, damageMax);
    }
}

public class Main {
    public static void main(String[] args) {
        Player player = new Player(25, 20, 90, 1, 6);
        Monster monster = new Monster(20, 15, 80, 2, 8);

        System.out.println("The player attacks the monster:");
        player.attack(monster);
        System.out.println("Monster Health: " + monster.getHealth());

        System.out.println("Monster attacks player:");
        monster.attack(player);
        System.out.println("Player Health: " + player.getHealth());

        System.out.println("The player is healed:");
        player.selfHeal();
        System.out.println("Player health after healing: " + player.getHealth());
    }
}
