package thePackmaster.cards.royaltypack;

import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.royaltypack.TributeOrAusterityAction;
import thePackmaster.cards.royaltypack.optioncards.BoundlessTalentAusterity;
import thePackmaster.cards.royaltypack.optioncards.BoundlessTalentTribute;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BoundlessTalent extends AbstractRoyaltyCard {

    public final static String ID = makeID("BoundlessTalent");

    public BoundlessTalent() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        baseSecondMagic = secondMagic = 1;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
        this.exhaust = false;
        ExhaustiveVariable.setBaseValue(this, secondMagic);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        int amountOfCardsToDraw = magicNumber - (AbstractDungeon.player.hand.size() - 1);
        for (int i = 0; i < amountOfCardsToDraw; i++){
            Wiz.atb(new DrawCardAction(AbstractDungeon.player, 1));
        }
        AbstractRoyaltyCard btTributeChoiceCard = new BoundlessTalentTribute();
        AbstractRoyaltyCard btAusterityChoiceCard = new BoundlessTalentAusterity();

        Wiz.atb(new TributeOrAusterityAction(btTributeChoiceCard, btAusterityChoiceCard));
    }
}
