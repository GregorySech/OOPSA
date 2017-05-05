package cardgame.cards;

import cardgame.AbstractCreatureCardEffect;
import cardgame.AbstractEnchantment;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Permanent;
import cardgame.Player;
import cardgame.TriggerAction;
import static cardgame.Triggers.EFFECT_CASTED;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jona
 */
public class AetherBarrier implements Card {

    private class AetherBarrierEffect extends AbstractEnchantmentCardEffect {

        AetherBarrierEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        protected Enchantment createEnchantment() {
            return new AetherBarrierEnchantment(owner);
        }

    }

    private class AetherBarrierEnchantment extends AbstractEnchantment {

        TriggerAction D; //Contiene la logica dell'effetto "passivo"

        AetherBarrierEnchantment(Player owner) {
            super(owner);
            D = new AetherBarrierTrigger(); //istanzio il command.
        }

        private Permanent choosePermanent(Player p) { //routine per la selezione FORZATA
            int i = 0, j;
            List<Permanent> plP = new ArrayList();
            List<Creature> c = p.getCreatures();
            List<Enchantment> e = p.getEnchantments();

            for (Creature ci : c) {
                plP.add(ci);
            }
            for (Enchantment ei : e) {
                plP.add(ei);
            }

            if (plP.isEmpty()) {
                return null;
            } else {
                System.out.println("Select a permanent to sacrifice.");
                do {
                    i = 0;
                    for (Permanent pe : plP) {
                        System.out.println("[" + (++i) + "]" + c);
                    }
                    j = CardGame.instance.getScanner().nextInt();
                    if (!(j > 0 && j <= plP.size())) {
                        System.out.println("Selection not Suitable");
                    }
                } while (j < 1 || j > plP.size());
            }
            return plP.get(j - 1);

        }

        private class AetherBarrierTrigger implements TriggerAction {

            @Override
            public void execute(Object args) {
                if (args instanceof AbstractCreatureCardEffect) { //controllo che l'effetto sia quello di una carta creatura
                    if (args != null) {
                        Permanent target = choosePermanent(((AbstractCreatureCardEffect) args).getOwner());
                        if (target != null) {
                            target.remove();
                        }
                    }
                }
            }

        }

        @Override
        public void insert() {
            super.insert();
            CardGame.instance.getTriggers().register(EFFECT_CASTED, D);//trigger personalizzato per il lancio di un effetto.
        }

        @Override
        public void remove() {
            super.remove();
            CardGame.instance.getTriggers().deregister(D);
        }

        @Override
        public String name() {
            return "Aether Barrier";
        }
    }

    @Override
    public Effect getEffect(Player owner) {
        return new AetherBarrierEffect(owner, this);
    }

    @Override
    public String name() {
        return "Aether Barrier";
    }

    @Override
    public String type() {
        return "Enchantment";
    }

    @Override
    public String ruleText() {
        return "Whenever a player plays a creature spell that player sacrifices a permanent";
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
