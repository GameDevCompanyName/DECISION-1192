package TextGameOld.igorlo.Elements.NPCs;

import TextGameOld.igorlo.Elements.NotPlayableCharacter;

public class Trader extends NotPlayableCharacter {

    public Trader(String name, int strenght, int agility, int intelligence, int health, int maxHealth, String description) {
        super(name, strenght, agility, intelligence, health, maxHealth, description,
                false,
                true,
                false,
                true,
                true,
                false,
                true);
    }

}
