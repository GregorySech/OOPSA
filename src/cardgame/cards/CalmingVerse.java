package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elena
 */
public class CalmingVerse implements Card {

    private class CalmingVerseEffect extends AbstractCardEffect {

        public CalmingVerseEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        public void resolve() {
            List<Enchantment> enchantments = new ArrayList(CardGame.instance.getRival(owner).getEnchantments());
            for (Enchantment e : enchantments) {
                e.remove();
            }
            enchantments = new ArrayList<>(owner.getEnchantments());
            for (Enchantment e : enchantments) {
                e.remove();
            }
        }
    }

    @Override
    public Effect getEffect(Player owner) {
        return new CalmingVerseEffect(owner, this);
    }

    @Override
    public String name() {
        return "Calming Verse";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Destroy all enchantments";
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    @Override
    public String toString() {
        return name() + " (" + type() + ") [" + ruleText() + "]";
    }

}
