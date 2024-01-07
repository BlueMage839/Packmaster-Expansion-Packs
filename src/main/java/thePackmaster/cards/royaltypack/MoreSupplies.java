package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.royaltypack.TributeOrAusterityAction;
import thePackmaster.cards.royaltypack.optioncards.MoreSuppliesAusterity;
import thePackmaster.cards.royaltypack.optioncards.MoreSuppliesTribute;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MoreSupplies extends AbstractRoyaltyCard {

    public final static String ID = makeID("MoreSupplies");
    public final static int TRIBUTE_GOLD_AMOUNT = 30;
    public final static int TRIBUTE_GOLD_UPGRADED_AMOUNT = 25;

    public MoreSupplies(){
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void upp() {
        upgradeBaseCost(this.cost - 1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.atb(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));

        AbstractRoyaltyCard moreSuppliesTributeChoiceCard;
        if (!this.upgraded){
            moreSuppliesTributeChoiceCard = new MoreSuppliesTribute(TRIBUTE_GOLD_AMOUNT);
        }
        else {
            moreSuppliesTributeChoiceCard = new MoreSuppliesTribute(TRIBUTE_GOLD_UPGRADED_AMOUNT);
        }
        AbstractRoyaltyCard moreSuppliesAusterityChoiceCard = new MoreSuppliesAusterity();

        Wiz.atb(new TributeOrAusterityAction(moreSuppliesTributeChoiceCard, moreSuppliesAusterityChoiceCard));
    }
}
